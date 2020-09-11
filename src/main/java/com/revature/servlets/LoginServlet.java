package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.HelperClass;
import com.revature.users.User;

public class LoginServlet {
	
	public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Login");
		if (request.getSession(false) == null) {
			request.getRequestDispatcher("/Login.html").forward(request, response);
		} else if (request.getSession(false).getAttribute("id") == null) {
			request.getSession(false).invalidate();
			request.getRequestDispatcher("/Login.html").forward(request, response);
		} else {
			System.out.println(request.getSession(false).getAttribute("id"));
//			BankingDAO dao = new BankingDAOImpl();
			if (HelperClass.dao.selectUserById((int) request.getSession(false).getAttribute("id")).getRole().getRole().equals("Standard")) {
				response.sendRedirect("http://localhost:8080/rocp-project/standard");
			} else if (HelperClass.dao.selectUserById((int) request.getSession(false).getAttribute("id")).getRole().getRole().equals("Employee")) {
				response.sendRedirect("http://localhost:8080/rocp-project/employee");
			} else if (HelperClass.dao.selectUserById((int) request.getSession(false).getAttribute("id")).getRole().getRole().equals("Admin")) {
				response.sendRedirect("http://localhost:8080/rocp-project/admin");
			}
		}
	}

	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean found = false;
//		BankingDAO dao = new BankingDAOImpl();
		List<User> users = HelperClass.dao.selectAllUsers();
		for (User u : users) {
			if (username.equals(u.getUsername()) && password.equals(u.getPassword()) && u.getRole().getRole().equals("Standard")) {
				found = true;
				request.getSession().setAttribute("id", u.getUserId());
				request.getSession().setAttribute("role", u.getRole().getRole());
				response.sendRedirect("http://localhost:8080/rocp-project/standard");
				break;
			} else if (username.equals(u.getUsername()) && password.equals(u.getPassword()) && u.getRole().getRole().equals("Employee")) {
				found = true;
				request.getSession().setAttribute("id", u.getUserId());
				request.getSession().setAttribute("role", u.getRole().getRole());
				response.sendRedirect("http://localhost:8080/rocp-project/employee");
				break;
			} else if (username.equals(u.getUsername()) && password.equals(u.getPassword()) && u.getRole().getRole().equals("Admin")) {
				found = true;
				request.getSession().setAttribute("id", u.getUserId());
				request.getSession().setAttribute("role", u.getRole().getRole());
				response.sendRedirect("http://localhost:8080/rocp-project/admin");
				break;
			}
		}
		if (!found) {
			System.out.println("Failed login");
			if (request.getSession(false) != null) {
				System.out.println("Delete session");
				request.getSession(false).invalidate();
			}
			request.getRequestDispatcher("/FailedLogin.html").forward(request, response);
		}
	}

}
