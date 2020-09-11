package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Admin page");
		System.out.println("doGet Admin");
		if (request.getSession(false) == null) {
			response.sendRedirect("http://localhost:8080/rocp-project/login");
		} else if (request.getSession(false).getAttribute("id") == null) {
			request.getSession(false).invalidate();
			response.sendRedirect("http://localhost:8080/rocp-project/login");
		} else {
			request.getRequestDispatcher("/Admin.html").forward(request, response);
		}
	}

}
