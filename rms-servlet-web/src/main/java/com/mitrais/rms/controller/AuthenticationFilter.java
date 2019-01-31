package com.mitrais.rms.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitrais.rms.utilities.SecurityUtils;
import com.mitrais.rms.utilities.UrlPatternUtils;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter{
	
	private ServletContext context;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

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
        
        HttpServletRequest wrapRequest = req;
        
        String servletPath = req.getServletPath();
        
        logger.info("AuthenticationFilter do filter");
        
        if("/login".equals(servletPath) || "/css".equals(servletPath)){
        	logger.info("redirect login page");
        	chain.doFilter(req, res);
        	return;
        }else{
        	chain.doFilter(wrapRequest, res);
        }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
