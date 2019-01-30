package com.mitrais.rms.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.rms.controller.AbstractController;
import com.mitrais.rms.dao.CustomerDao;
import com.mitrais.rms.dao.impl.CustomerDaoImpl;
import com.mitrais.rms.model.Customer;

@WebServlet("/customers/delete")
public class CustomerDeleteServlet extends AbstractController{

	public CustomerDeleteServlet() {
        // Do Nothing
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String custId = request.getParameter("custId");
		 
        if (custId == "" || custId == null)
            request.getRequestDispatcher("/customers/list").forward(request, response);
        else {
            Long id = Long.parseLong(custId);
            CustomerDao customerDao = CustomerDaoImpl.getInstance();
 
            customerDao.delete(new Customer(id, null, null, null, null));
 
            response.sendRedirect(request.getContextPath() + "/customers/list");
        }
	}
}
