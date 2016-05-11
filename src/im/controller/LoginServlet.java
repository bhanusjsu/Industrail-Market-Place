package im.controller;

import im.beans.Category;
import im.beans.Users;
import im.dao.CategoryDao;
import im.dao.UsersDao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		if(option.equals("logout"))
		{
			HttpSession session = request.getSession(true);
			session.removeAttribute("userid");
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else if(option.equals("guest"))
		{
			HttpSession session = request.getSession(true);
			CategoryDao cd=new CategoryDao();
			ArrayList<Category> al1= cd.selectAllCategories();
			session.setAttribute("categories", al1);
			response.sendRedirect(request.getContextPath()+"/lumino/index.jsp?guest=yes");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String uname=(request.getParameter("username")==null)?null:request.getParameter("username").toString();
		String pwd=(request.getParameter("password")==null)?null:request.getParameter("password").toString();
		Users usr=new Users();
		usr.setUserName(uname);usr.setPassword(pwd);
		UsersDao ud=new UsersDao();
		int uid=ud.verifyDetails(usr);
		System.out.println(uid);
		if(uid!=0)
		{
			HttpSession session = request.getSession(true);
			session.removeAttribute("guest");
			session.setAttribute("userid",uid);
			response.setHeader("Pragma","no-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Cache-Control","no-store");
			response.setDateHeader("Expires",0);
			
			CategoryDao cd=new CategoryDao();
			ArrayList<Category> al1= cd.selectAllCategories();
			session.setAttribute("categories", al1);
			
			response.sendRedirect(request.getContextPath()+"/lumino/index.jsp");
		
			
		}
		
		else
		{
		
			RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/lumino/userlogin.jsp");
			request.setAttribute("message","Invalid Login Details");
			rd.forward(request, response);
		}
	
	}

}
