package com.myfiles.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	private static final long serialVersionUID = 1L;
    
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uemail=request.getParameter("username");
		String upass=request.getParameter("password");
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		PrintWriter ob=response.getWriter();
		System.out.println(uemail + " "+ upass);
		int flag=0;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","12345678");
			System.out.println("Connection created");
			ps=con.prepareStatement("select * from reg where uemail=? and upass=?;");
			ps.setString(1, uemail);
			ps.setString(2, upass);
			
			rs=ps.executeQuery();
			System.out.println("Query execute");
			while(rs.next()) 
			{	
				ob.print("hello");
				session.setAttribute("name", rs.getString("uname"));
				dispatcher=request.getRequestDispatcher("P_Dashboard.html");
				flag=1;
				dispatcher.forward(request, response);
				break;
			}
			if(flag==1)
			{
				request.setAttribute("status", "failed");
				dispatcher=request.getRequestDispatcher("P_Registration.html");
				dispatcher.forward(request, response);
			}
			
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
		
			e.printStackTrace();
		}
	}

}
