package com.mitrais.rms.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.rms.controller.AbstractController;
import com.mitrais.rms.dao.CustomerDao;
import com.mitrais.rms.dao.impl.CustomerDaoImpl;
import com.mitrais.rms.model.Customer;

@WebServlet("/customers/*")
public class CustomerServlet extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
		
		if ("/list".equalsIgnoreCase(req.getPathInfo())){
			CustomerDao customerDao = CustomerDaoImpl.getInstance();
			List<Customer> customers = customerDao.findAll();
			req.setAttribute("customers", customers);
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		
        requestDispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
