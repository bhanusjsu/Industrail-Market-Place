package im.controller;

import im.beans.Users;
import im.dao.UsersDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String uname=(request.getParameter("uname")==null)?null:request.getParameter("uname").toString();
	String pwd=(request.getParameter("pwd")==null)?null:request.getParameter("pwd").toString();
	String cpwd=(request.getParameter("cpwd")==null)?null:request.getParameter("cpwd").toString();
	String trinst=(request.getParameter("trinst")==null)?null:request.getParameter("trinst").toString();
	String email=(request.getParameter("email")==null)?null:request.getParameter("email").toString();
	String phone=(request.getParameter("phone")==null)?null:request.getParameter("phone").toString();
	String address=(request.getParameter("address")==null)?null:request.getParameter("address").toString();
	PrintWriter out=	response.getWriter();
	
	
	if(!cpwd.equals(pwd))
		out.println("Passwords are not matching");
	
	else
	{
		
		Users usr=new Users();
		usr.setUserName(uname);
		usr.setPassword(pwd);
		usr.setInstName(trinst);
		usr.setEmail(email);
		usr.setPhone(phone);
		usr.setAddress(address);
		UsersDao ud=new UsersDao();
	  int flag=ud.addUser(usr);
	
	  if(flag==1)
		  out.print("Success");
	  else if(flag==-1)
		  out.println("User Exists already");
	  else
		  out.println("Error occured in Registration");
	
	}
		
	
		
		
	}

}
