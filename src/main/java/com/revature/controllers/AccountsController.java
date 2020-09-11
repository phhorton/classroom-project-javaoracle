package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.accounts.Account;
import com.revature.dao.BankingDAO;
import com.revature.dao.BankingDAOImpl;

public class AccountsController {

	public static void withdrawOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/Withdraw.html").forward(request, response);
		PrintWriter pw = response.getWriter();
		pw.write("<h1>Accounts:</h1>");
		
		List<Account> accounts = null;
		if (request.getSession(false).getAttribute("role").equals("Admin")) {
			accounts = HelperClass.dao.selectAllAccounts();
		} else {
			accounts = HelperClass.dao.selectAccountByUser(Integer.parseInt((String)request.getSession(false).getAttribute("id")));
		}
		for (Account a : accounts) {
			pw.write(a.getAccountId() + " - User: " + HelperClass.dao.selectUserById(a.getUserId()).getFirstName() + " Amount: $" + a.getBalance() + "<br>");
		}
		pw.write("<form action = \"/rocp-project/accounts/withdraw\" method=\"post\"><br>"
				+ "	<button type=\"submit\">Withdraw</button><br>"
				+ "	Account ID: <input name=\"accountId\" type=\"number\"><br>"
				+ "	Balance: <input name=\"balance\" type=\"text\">"
				+ "</form>");
	}
	
	public static void withdraw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		if (Double.parseDouble(request.getParameter("balance")) > HelperClass.dao.selectAccountById(Integer.parseInt(request.getParameter("accountId"))).getBalance()) {
			pw.write("<p>Not enough to withdraw</p>");
		} else {
			Account a = HelperClass.dao.selectAccountById(Integer.parseInt(request.getParameter("accountId")));
			a.setBalance(a.getBalance() - Double.parseDouble(request.getParameter("balance")));
			HelperClass.dao.updateAccount(a);
			pw.write("<p>$" + Double.parseDouble(request.getParameter("balance")) + " has been removed from the account</p>");
		}
		if (request.getSession(false).getAttribute("role").equals("Standard")) {
			
		} else if (request.getSession(false).getAttribute("role").equals("Employee")) {
			
		} else if (request.getSession(false).getAttribute("role").equals("Admin")) {
			pw.write("<p><form action = \"/rocp-project/admin\" method=\"get\"><br>" 
					+ "	<button type=\"submit\">Return</button>" 
					+ "</form></p>");
		}
		
	}
}
