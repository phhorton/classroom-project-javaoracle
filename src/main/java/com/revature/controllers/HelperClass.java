package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.BankingDAO;
import com.revature.dao.BankingDAOImpl;
import com.revature.servlets.LoginServlet;

public class HelperClass {
	
	public static BankingDAO dao;
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("process");
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getMethod());
//		System.out.println(request.getSession().getAttribute("id"));
//		System.out.println(request.getSession().getAttribute("role"));
		System.out.println(request.getParameter("newUsername"));
		System.out.println(request.getParameter("newRole"));
		
		dao = new BankingDAOImpl();
		
		String s = request.getRequestURI();
		String r = "";
		if (request.getSession(false) != null) {
			r = (String) request.getSession(false).getAttribute("role");
		}
		String m = (String) request.getMethod();
		
		switch(s) {
			case "/rocp-project/login":
				if (request.getMethod().equals("POST")) {
					LoginServlet.doPost(request, response);
				} else {
					LoginServlet.doGet(request, response);
				}
				break;
			case "/rocp-project/logout":
				request.getSession(false).invalidate();
				request.getRequestDispatcher("/Logout.html").forward(request, response);
				break;
			case "/rocp-project/register":
				if (r.equals("Standard")) {
					response.sendError(401, "The requested action is not permitted");
					response.sendRedirect("http://localhost:8080/rocp-project/standard");
				} else if (r.equals("Employee")) {
					response.setStatus(400);
					response.sendRedirect("http://localhost:8080/rocp-project/employee");
				} else if (r.equals("Admin") && request.getMethod().equals("GET")) {
					RegisterController.prepareUser(request, response);
				} else if (r.equals("Admin") && request.getMethod().equals("POST")) {
					RegisterController.registerUser(request, response);
				} else {
					LoginServlet.doGet(request, response);
				}
				break;
			case "/rocp-project/accounts/withdraw":
				if (request.getMethod().equals("POST")) {
					AccountsController.withdraw(request, response);
				} else {
					AccountsController.withdrawOrder(request, response);
				}
				break;
			default:
				LoginServlet.doGet(request, response);
				break;
		}
	}
}
