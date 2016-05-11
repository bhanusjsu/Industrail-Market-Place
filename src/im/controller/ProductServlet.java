package im.controller;

import im.beans.Products;
import im.dao.ProductDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String catId = (request.getParameter("category")==null)?null:request.getParameter("category").toString();
		String subCatId = (request.getParameter("subcategory")==null)?null:request.getParameter("subcategory").toString();
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
	System.out.println("outside");
		if(catId!=null&&catId!=""&&(subCatId==""||subCatId==null))
		{
			System.out.println("Inside 1st if");
			ProductDao pd =new  ProductDao();
		   ArrayList<Products> al=	pd.listAllProducts(Integer.parseInt(catId), null);
		   //System.out.println("After al"+al);
		   if(al.size()==0)
		   {
			   out.println("<br /> No data found for the selected criteria");
			   return;
		   }
		   ListIterator<Products> lit=al.listIterator();
		   
		   out.println("");
		  
		   while(lit.hasNext()){
			   out.print("<div class='card-layout'>");
			   Products p=lit.next();
		   out.print("<img  src="+request.getContextPath()+"/DisplayPhotoServlet?pid=" +p.getProductId() + "></img>");
		 out.print(p.getProductName()+"<br />");
		 out.print("Price: $"+p.getPrice()+"<br />");
		 out.print("<input type='button' value='Add to Cart' onclick=\"alert('Added to Cart');\"></button>");  
		 out.print("</div>");
		}
	
		}
		else if(subCatId!=null&& subCatId!=""&&(catId!=null||catId!=""))
		{
			ProductDao pd =new  ProductDao();
			ArrayList<Products> al=pd.listAllProducts(Integer.parseInt(catId), Integer.parseInt(subCatId));
		//out.print(al);
			  if(al.size()==0)
			   {
				   out.println("<br /> No data found for the selected criteria");
				   return;
			   }
		 ListIterator<Products> lit=al.listIterator();
		   
		   out.println("");
		  
				   while(lit.hasNext()){
					   out.print("<div class='card-layout'>");
					   Products p=lit.next();
				   out.print("<img  src="+request.getContextPath()+"/DisplayPhotoServlet?pid=" +p.getProductId() + "></img>");
				 out.print(p.getProductName()+"<br />");
				 out.print("Price: $"+p.getPrice()+"<br />");
				 out.print("<input type='button' value='Add to Cart' onclick=\"alert('Added to Cart');\"></button>");  
				 out.print("</div>");
				}
		}
		else
		{
			ProductDao pd =new  ProductDao();
			ArrayList<Products> al=pd.listAllProducts(null,null);
		//out.print(al);
			  if(al.size()==0)
			   {
				   out.println("<br /> No data available");
				   return;
			   }
		 ListIterator<Products> lit=al.listIterator();
		   
		   out.println("");
				  
		   while(lit.hasNext()){
			   out.print("<div class='card-layout'>");
			   Products p=lit.next();
		   out.print("<img  src="+request.getContextPath()+"/DisplayPhotoServlet?pid=" +p.getProductId() + "></img>");
		 out.print(p.getProductName()+"<br />");
		 out.print("Price: $"+p.getPrice()+"<br />");
		 out.print("<input type='button' value='Add to Cart' onclick=\"alert('Added to Cart');\"></button>");  
		 out.print("</div>");
		}
			
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            try {
				// Apache Commons-Fileupload library classes
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload sfu  = new ServletFileUpload(factory);

				if (! ServletFileUpload.isMultipartContent(request)) {
				    System.out.println("sorry. No file uploaded");
				    return;
				}

				// parse request
				List items = sfu.parseRequest(request);
				FileItem  category = (FileItem) items.get(0);
				String cat =  category.getString();
				
				FileItem subCategory = (FileItem) items.get(1);
				String   subCat =  subCategory.getString();

				FileItem prodname = (FileItem) items.get(2);
				String   prodName =  prodname.getString();

				FileItem proddesc = (FileItem) items.get(3);
				String   prodDesc =  proddesc.getString();

				FileItem pricef = (FileItem) items.get(4);
				String   price =  pricef.getString();
				
				FileItem file = (FileItem) items.get(5);
				
				FileItem uid = (FileItem) items.get(6);
				String   userId =  uid.getString();
				
				
				Products prd=new Products();
				prd.setCid(Integer.parseInt(cat));
				prd.setSid(Integer.parseInt(subCat));
				prd.setProductName(prodName);
				prd.setDescription(prodDesc);
				prd.setUserId(Integer.parseInt(userId));
				prd.setPrice(Integer.parseInt(price));
				ProductDao pd=new ProductDao();
				
				pd.addProduct(prd, file);

				response.sendRedirect(request.getContextPath()+"/lumino/PostProduct.jsp?result=success");
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
