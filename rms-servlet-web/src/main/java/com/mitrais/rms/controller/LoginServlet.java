package com.mitrais.rms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.utilities.AppUtils;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	String username = req.getParameter("username");
        String userpass = req.getParameter("userpass");
        
        UserDao userDao = UserDaoImpl.getInstance();
        Optional<User> optional = userDao.findUserByUserNameAndPwd(username, userpass);
        if (!optional.isPresent()) {
        	String errorMessage = "Invalid userName or Password";
            
            req.setAttribute("errorMessage", errorMessage);
            this.doGet(req, resp);
            
            return;
		}
        
        AppUtils.storeLoginedUser(req.getSession(), optional.get());
        
        int redirectId = -1;
        try {
			redirectId = Integer.parseInt(req.getParameter("redirectId"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        String requestUri = AppUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
        if (requestUri != null) {
            resp.sendRedirect(requestUri);
        } else {
            // Default after successful login
            // redirect to /customers/list page
        	resp.sendRedirect(req.getContextPath() + "/customers/list");
        }
         
       
    }
}
