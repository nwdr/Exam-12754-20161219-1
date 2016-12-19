package com.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.db.JdbcUtil;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, 
			          HttpServletResponse response) 
			        		  throws ServletException, IOException{
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		try {
			out.println(login(username,request));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}
	
	private String login(String username,HttpServletRequest request) 
										throws SQLException{
		ResultSet rs = JdbcUtil.getCustomerByName(username);
		String info = "µÇÂ¼Ê§°Ü£¡";
		if(rs.next()){
			info = "µÇÂ¼³É¹¦£¡";
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
		}
		rs.close();
		return info;
	}
	
}
