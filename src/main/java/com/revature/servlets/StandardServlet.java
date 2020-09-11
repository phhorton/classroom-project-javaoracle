package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StandardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Standard page");
//		PrintWriter pw = response.getWriter();
//		pw.write("Standard page");
		System.out.println("doGet Standard");
		if (request.getSession(false) == null) {
			response.sendRedirect("http://localhost:8080/rocp-project/login");
		} else if (request.getSession(false).getAttribute("id") == null) {
			request.getSession(false).invalidate();
			response.sendRedirect("http://localhost:8080/rocp-project/login");
		} else {
			request.getRequestDispatcher("/Standard.html").forward(request, response);
		}
		
	}

}
