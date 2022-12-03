package com.myfiles.registration;

import java.io.IOException;
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

/**
 * Servlet implementation class AdminRegistrationServlet
 */
@WebServlet("/AdminRegistrationServlet")
public class AdminRegistrationServlet extends HttpServlet 
{
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    
	private static final long serialVersionUID = 1L;
       
   
    public AdminRegistrationServlet() {
        super();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	    String name=request.getParameter("uname");
        String email=request.getParameter("uemail");
        String mobile=request.getParameter("umobile");
        String password=request.getParameter("upass");
        RequestDispatcher dispatcher=null;
        
        int checkstatus=0;
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admindata?useSSL=false","root","12345678");
            rs=null;
            ps=con.prepareStatement("insert into reg(uname,uemail,umobile,upass) values(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, password);
            
            checkstatus=ps.executeUpdate();
            
            dispatcher=request.getRequestDispatcher("P_Admin.html");
            
            if(checkstatus>0)
            {
                request.setAttribute("status", "success");
            }
            else
            {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
            con.close();
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            
            e.printStackTrace();
        }

	}

}
