package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitrais.rms.controller.request.UserRoleRequestWrapper;
import com.mitrais.rms.model.User;
import com.mitrais.rms.utilities.AppUtils;
import com.mitrais.rms.utilities.SecurityUtils;
import com.mitrais.rms.utilities.UrlPatternUtils;

@WebFilter(urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String servletPath = req.getServletPath();
		logger.info("servletPath : " + servletPath);

		// User information stored in the Session.
		// (After successful login).
		User loginedUser = AppUtils.getLoginedUser(req.getSession());

		HttpServletRequest wrapRequest = req;

		if ("/login".equals(servletPath)) {
			logger.info("redirect login page");
			chain.doFilter(req, res);
			return;
		} else if (servletPath.indexOf("css") != -1
				|| servletPath.indexOf("js") != -1) {
			logger.info("static folder");
			chain.doFilter(req, res);
		} else {
			logger.info("AuthenticationFilter do filter");

			if (loginedUser != null) {
				// User Name
				String userName = loginedUser.getUserName();

				// Roles
				List<String> roles = loginedUser.getRoles();

				// Wrap old request by a new Request with userName and Roles
				// information.
				wrapRequest = new UserRoleRequestWrapper(userName, roles, req);
			}

			boolean isSecuredPage = SecurityUtils.isSecurityPage(req);

			// Pages must be signed in.
			if (isSecuredPage) {

				// If the user is not logged in,
				// Redirect to the login page.
				if (loginedUser == null) {

					String requestUri = req.getRequestURI();

					// Store the current page to redirect to after successful
					// login.
					int redirectId = AppUtils.storeRedirectAfterLoginUrl(
							req.getSession(), requestUri);

					res.sendRedirect(wrapRequest.getContextPath()
							+ "/login?redirectId=" + redirectId);
					return;
				}

				// Check if the user has a valid role?
				boolean hasPermission = SecurityUtils
						.hasPermission(wrapRequest);
				if (!hasPermission) {

					RequestDispatcher dispatcher //
					= request.getServletContext().getRequestDispatcher(
							"/WEB-INF/jsp/access_denied.jsp");

					dispatcher.forward(request, response);
					return;
				}
			}
			
			if ("/".equals(servletPath)) {
				logger.info("accesing index page ...");
				req.getRequestDispatcher("/home").forward(req, res);
				return;
			}

			chain.doFilter(wrapRequest, res);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
