package im.controller;

import im.dao.ConnectionDao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayPhotoServlet
 */
public class DisplayPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        
    	        try {
    	            
    	            Connection con = ConnectionDao.connect();
    	            PreparedStatement ps = con.prepareStatement("select photo from products where product_id = ?");
    	            String id = request.getParameter("pid");
    	           System.out.println(id);
    	            ps.setString(1,id);
    	            ResultSet rs = ps.executeQuery();
    	            rs.next();
    	            Blob  b = rs.getBlob("photo");
    	            response.setContentType("image/jpeg");
    	            response.setContentLength( (int) b.length());
    	            InputStream is = b.getBinaryStream();
    	            OutputStream os = response.getOutputStream();
    	            byte buf[] = new byte[(int) b.length()];
    	            is.read(buf);
    	            os.write(buf);
    	            os.close();
    	        }
    	        catch(Exception ex) {
    	             System.out.println(ex.getMessage());
    	        }
    	    } 

    	    @Override
    	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        processRequest(request, response);
    	    } 
    	    @Override
    	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        processRequest(request, response);
    	    }
}
