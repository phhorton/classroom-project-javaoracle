package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.BankingDAO;
import com.revature.dao.BankingDAOImpl;
import com.revature.users.Role;
import com.revature.users.User;

public class RegisterController {

	public static void prepareUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Register.html").forward(request, response);
	}
	
	public static void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BankingDAO dao = new BankingDAOImpl();
		List<User> users = HelperClass.dao.selectAllUsers();
		List<Role> roles = HelperClass.dao.selectAllRoles();
		Role role = null;
		System.out.println(request.getParameter("newUsername"));
		System.out.println(request.getParameter("newRole"));
		int max = 0;
		for (User u : users) {
			if (request.getParameter("newUsername").equals(u.getUsername()) || request.getParameter("newEmail").equals(u.getEmail())) {
				response.sendError(400, "Invalid fields");
				return;
			}
			if (u.getUserId() > max) {
				max = u.getUserId();
			}
		}
		max++;
		int roleId = Integer.parseInt(request.getParameter("newRole"));
		for (Role r : roles) {
			if (roleId == r.getRoleId()) {
				role = r;
				break;
			}
		}
		HelperClass.dao.insertUser(new User(max, request.getParameter("newUsername"), request.getParameter("newPassword"), request.getParameter("newFirstName"), request.getParameter("newLastName"), request.getParameter("newEmail"), role));
		response.sendRedirect("http://localhost:8080/rocp-project/admin");
	}
}
