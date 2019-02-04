package com.mitrais.rms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home"})
public class IndexServlet extends AbstractController{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(getTemplatePath("/home"));
//		if("/".equals(servletPath)){
//			requestDispatcher = req.getRequestDispatcher(getTemplatePath("/"));
//		}else {
//			requestDispatcher = req.getRequestDispatcher(servletPath);
//		}
		
        requestDispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
