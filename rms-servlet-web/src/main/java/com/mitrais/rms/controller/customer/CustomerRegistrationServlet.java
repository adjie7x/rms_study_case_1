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

@WebServlet("/customers/register")
public class CustomerRegistrationServlet extends AbstractController{
	
	private CustomerDao customerDao = CustomerDaoImpl.getInstance();
	 
    public CustomerRegistrationServlet() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/customers/list").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String custId = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
 
        Customer customer = new Customer(null,firstName, lastName, email, mobile);
 
        if (custId == null || custId == "")
            customerDao.save(customer);
        else {
            Long id = Long.parseLong(custId);
            customer.setId(id);
            customerDao.update(customer);
        }
 
        response.sendRedirect(request.getContextPath() + "/customers/list");
    }

}
