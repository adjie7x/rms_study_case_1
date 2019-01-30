package com.mitrais.rms.controller.customer;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.rms.controller.AbstractController;
import com.mitrais.rms.dao.CustomerDao;
import com.mitrais.rms.dao.impl.CustomerDaoImpl;
import com.mitrais.rms.model.Customer;

@WebServlet("/customers/update")
public class CustomerUpdateServlet extends AbstractController {

	public CustomerUpdateServlet() {
		// Do Nothing
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("custId");
		
		String path = "/customers/list";

		if (custId == "" || custId == null)
			request.getRequestDispatcher(path).forward(request,
					response);
		else {
			Long id = Long.parseLong(custId);
			CustomerDao customerDao = CustomerDaoImpl.getInstance();
			Optional<Customer> optional = customerDao.find(id);
			Customer customer = new Customer();
			if (optional.isPresent()) {
				customer = optional.get();
			}

			request.setAttribute("customer", customer);

			request.getRequestDispatcher(path).forward(request,
					response);
		}

	}

}
