package im.controller;

import im.beans.Category;
import im.beans.SubCategory;
import im.dao.CategoryDao;
import im.dao.SalesDao;
import im.dao.SubCategoryDao;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import im.awt.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	PrintWriter out=response.getWriter();
		String option = (request.getParameter("option")==null)?null:request.getParameter("option").toString();
	if(option.equals("analyze"))
	{
		String catId = (request.getParameter("category")==null)?null:request.getParameter("category").toString();
		String subCatId = (request.getParameter("subcategory")==null)?null:request.getParameter("subcategory").toString();
        System.out.println("Inside OPtion");
							if(catId!=null&&catId!=""&&(subCatId==""||subCatId==null))
							{
								System.out.println("Inside if cat");
								SalesDao sd=new SalesDao();
							HashMap<String,Integer> ahm=sd.viewCurrSalByCategory(Integer.parseInt(catId));
						    //request.setAttribute("categorydata", ahm);
							//out.print(ahm);
							if(ahm.size()==0)
								{
								out.println("No Data available for selected criteria");
								return;
								}
								DefaultCategoryDataset bardataset = new DefaultCategoryDataset();  
							
								for(Map.Entry<String, Integer> entry : ahm.entrySet()){
								    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
								    bardataset.setValue(entry.getValue(),"Sales" ,entry.getKey() );
								}
								JFreeChart barchart = ChartFactory.createBarChart(  
								        "Current market Trend in SubCategories ",      //Title  
								        "Categories",             // X-axis Label  
								        "Sales",               // Y-axis Label  
								        bardataset,             // Dataset  
								        PlotOrientation.VERTICAL,      //Plot orientation  
								        false,                // Show legend  
								        true,                // Use tooltips  
								        false                // Generate URLs  
								     );  
								    barchart.getTitle().setPaint(Color.BLUE);    // Set the colour of the title  
								    barchart.setBackgroundPaint(Color.WHITE);    // Set the background colour of the chart  
								    CategoryPlot cp = barchart.getCategoryPlot();  // Get the Plot object for a bar graph  
								    cp.setBackgroundPaint(Color.lightGray);       // Set the plot background colour  
								    CategoryItemRenderer renderer = new CustomRenderer(); 
								    
								   
								    cp.setRenderer(renderer);
								    cp.setRangeGridlinePaint(Color.BLUE);
								   
						
								    
								  //  String fileame =request.getRealPath("barchart.jpg");
								  //  System.out.println(absoluteDiskPath);
								   File f=new File(getServletConfig().getServletContext().getRealPath("lumino/images"),"barchart.jpg");
								
								    ChartUtilities.saveChartAsJPEG(f, barchart, 500, 300);  
								    
									System.out.println(f.getPath());
								String path=f.getPath();
							
								out.print("<img src=\""+request.getContextPath()+"/lumino/images/barchart.jpg\" height=200 width=400 style='float:left;text-align: center;float: left;margin: 10px;' ></img>");
														
											
							}
							
							if(subCatId!=null&& subCatId!=""&&(catId!=null||catId!=""))
							{
							System.out.println("Inside");
								SalesDao sd2=new SalesDao();
								HashMap<String,Integer> ahm2=sd2.viewCurrSalByArea(Integer.parseInt(catId),Integer.parseInt(subCatId));
							    
								//request.setAttribute("categorydata", ahm);
								//out.print(ahm);
								if(ahm2.size()==0)
								{
								out.println("<br />No Data available for selected criteria");
								return;
								}
								
								DefaultCategoryDataset bardataset2 = new DefaultCategoryDataset();  
								
									for(Map.Entry<String, Integer> entry : ahm2.entrySet()){
									    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
									    bardataset2.setValue(entry.getValue(),"Sales" ,entry.getKey() );
									}
									JFreeChart barchart2 = ChartFactory.createBarChart(  
									        "Current market Trend  Areawise",      //Title  
									        "Areas",             // X-axis Label  
									        "Sales",               // Y-axis Label  
									        bardataset2,             // Dataset  
									        PlotOrientation.VERTICAL,      //Plot orientation  
									        false,                // Show legend  
									        true,                // Use tooltips  
									        false                // Generate URLs  
									     );  
									    barchart2.getTitle().setPaint(Color.BLUE);    // Set the colour of the title  
									    barchart2.setBackgroundPaint(Color.WHITE);    // Set the background colour of the chart  
									    CategoryPlot cp2 = barchart2.getCategoryPlot();  // Get the Plot object for a bar graph  
									    cp2.setBackgroundPaint(Color.lightGray);       // Set the plot background colour  
									    CategoryItemRenderer renderer2 = new CustomRenderer(); 
									    cp2.setRenderer(renderer2);
									    cp2.setRangeGridlinePaint(Color.BLUE);
									    
									  //  String fileame =request.getRealPath("barchart.jpg");
									  //  System.out.println(absoluteDiskPath);
									   File f2=new File(getServletConfig().getServletContext().getRealPath("lumino/images"),"barchart2.jpg");
									
									    ChartUtilities.saveChartAsJPEG(f2, barchart2, 500, 300);  
									    
										System.out.println(f2.getPath());
									String path2=f2.getPath();
							
							
							
							out.print("<img src=\""+request.getContextPath()+"/lumino/images/barchart2.jpg\" height=200 width=400 style='float:left;text-align: center;float: left;margin: 10px;'  ></img>");
						
							//YearWise
							
							SalesDao sd3=new SalesDao();
							HashMap<Integer,Integer> ahm3=sd3.viewSalesByYear(Integer.parseInt(catId),Integer.parseInt(subCatId));
						    
							//request.setAttribute("categorydata", ahm);
							//out.print(ahm);
							if(ahm3.size()==0)
							{
							out.println("<br />No Data available for selected criteria");
							return;
							}
							DefaultCategoryDataset bardataset3 = new DefaultCategoryDataset();  
							
								for(Map.Entry<Integer, Integer> entry : ahm3.entrySet()){
								    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
								    bardataset3.setValue(entry.getValue(),"Sales" ,entry.getKey() );
								}
								JFreeChart barchart3 = ChartFactory.createBarChart(  
								        "Current market Trend  Yearwise",      //Title  
								        "Years",             // X-axis Label  
								        "Sales",               // Y-axis Label  
								        bardataset3,             // Dataset  
								        PlotOrientation.VERTICAL,      //Plot orientation  
								        false,                // Show legend  
								        true,                // Use tooltips  
								        false                // Generate URLs  
								     );  
								    barchart3.getTitle().setPaint(Color.BLUE);    // Set the colour of the title  
								    barchart3.setBackgroundPaint(Color.white);    // Set the background colour of the chart  
								    CategoryPlot cp3 = barchart3.getCategoryPlot();  // Get the Plot object for a bar graph  

								    cp3.setBackgroundPaint(Color.lightGray);      // Set the plot background colour  
								    cp3.setRangeGridlinePaint(Color.BLUE);
								    CategoryItemRenderer renderer3 = new CustomRenderer(); 
								    cp3.setRenderer(renderer3);
								  //  String fileame =request.getRealPath("barchart.jpg");
								  //  System.out.println(absoluteDiskPath);
								   File f3=new File(getServletConfig().getServletContext().getRealPath("lumino/images"),"barchart3.jpg");
								
								    ChartUtilities.saveChartAsJPEG(f3, barchart3, 500, 300);  
								    
									System.out.println(f3.getPath());
								String path3=f3.getPath();
						
						
						
						out.print("<img src=\""+request.getContextPath()+"/lumino/images/barchart3.jpg\" height=200 width=400 style='float:left;text-align: center;float: left;margin: 10px;'  ></img>");
					
							
							
							
							}
									
		                if(subCatId=="" && catId=="")
		                	out.println("<br/>No data avaialable for selected criteria");
		
	}

	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String output="";
	String catId=request.getParameter("category")==null?null:request.getParameter("category").toString();
	response.setContentType("text/html");
	System.out.println("Outside Writer"+catId);	
	PrintWriter out=response.getWriter();
				if(catId!=null&& catId!="")
				{
				  	SubCategoryDao scd=new SubCategoryDao();
				    ArrayList<SubCategory> al=scd.selectAllSubCategories(Integer.parseInt(catId));
				
				ListIterator<SubCategory> lit=al.listIterator();
				System.out.println("Outside"+catId);	
				output=output+"<option value=''>Select</option>";
							while(lit.hasNext())
							{
								System.out.println(catId);
								 SubCategory sc=lit.next();
								 output=output+"<option value="+sc.getSubcategoryId()+">"+sc.getSubcategoryName()+"</option>";
								 
							}
						
							
				out.println(output);
				}	else
					{
					output+="<option value=''>Select a Category first</option>";
					out.println(output);
					}
	
	}

}
