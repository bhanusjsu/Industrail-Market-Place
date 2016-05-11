package im.dao;

import im.beans.CustomerTrx;
import im.beans.Products;
import im.beans.SubCategory;
import im.beans.Users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

public class ProductDao {

	public int addProduct(Products prd,FileItem file) {
		try {

			Connection con = ConnectionDao.connect();	
			PreparedStatement pstmt = con.prepareStatement("insert into products values(PRODUCT_SEQ.nextval,?,?,?,?,?,?,?)");
			pstmt.setString(1,prd.getProductName());
			pstmt.setString(2,prd.getDescription());
			pstmt.setInt(3,prd.getUserId());
			pstmt.setInt(4,prd.getPrice());
		  pstmt.setBinaryStream(5, file.getInputStream(), (int) file.getSize());
			pstmt.setInt(6,prd.getSid());
			pstmt.setInt(7,prd.getCid());
			
			
			pstmt.executeUpdate();
			con.commit();
			ConnectionDao.destroy(con);
			return 1;
		} catch (SQLException e) {
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		}
		
	}
	
	public int updateProduct(Products prd,FileItem file) {
		try {
			Connection con = ConnectionDao.connect();
			PreparedStatement pstmt = con.prepareStatement("update products set PRODUCT_NAME=?,DESCRIPTION=?,USERID=?,PRICE=?,PHOTO =?,SID =?,CID=? where product_id=?");
			pstmt.setString(1,prd.getProductName());
			pstmt.setString(2,prd.getDescription());
			pstmt.setInt(3,prd.getUserId());
			pstmt.setInt(4,prd.getPrice());
		  pstmt.setBinaryStream(5, file.getInputStream(), (int) file.getSize());
			pstmt.setInt(6,prd.getSid());
			pstmt.setInt(7,prd.getCid());
			
			pstmt.executeUpdate();
			con.commit();
			ConnectionDao.destroy(con);
			return 1;
		} catch (SQLException e) {
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}	
	
	
	public ArrayList<Products> listAllProducts(Integer catId,Integer subCatId) {
		System.out.println("Selecting products");
		ArrayList<Products> products = new ArrayList<Products>();
		PreparedStatement pstmt=null;
		try {
			Products prd = null;
			Connection con = ConnectionDao.connect();
		    if(subCatId==null&&catId==null)
			{ pstmt = con.prepareStatement("select * from products");
			
			
			}
			else if(subCatId==null)
			{ pstmt = con
					.prepareStatement("select * from products where cid=?");
			System.out.println("Cat Id:"+catId);
			pstmt.setInt(1,catId);
			}
			
			else
			{
				 pstmt = con
						.prepareStatement("select * from products where cid=? and sid=?");
				System.out.println("Cat Id:"+catId);
				pstmt.setInt(1,catId);
				pstmt.setInt(2,subCatId);
				
			}
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("exists");
				prd = new Products();
				prd.setProductId(rs.getInt(1));
				prd.setProductName(rs.getString(2));
				prd.setDescription(rs.getString(3));
				prd.setUserId(rs.getInt(4));
				prd.setPrice(rs.getInt(5));
				prd.setSid(rs.getInt(7));
				prd.setCid(rs.getInt(8));
				products.add(prd);

			}
			ConnectionDao.destroy(con);
			System.out.println(products);
			return products;
		} catch (Exception e) {
			return null;
		}

	}
	
	public int deleteProduct(int productId) {
		try {
			Connection con = ConnectionDao.connect();	
			PreparedStatement pstmt = con.prepareStatement("delete from products where PRODUCT_ID=?");
			pstmt.setInt(1,productId);
			int i = pstmt.executeUpdate();
			ConnectionDao.destroy(con);
			return i;
		} catch (SQLException e) {
			return 0;
		}
	}	
	
	public Products geProductDetails(int productId) {

		try {
			Products prd = null;
			Connection con = ConnectionDao.connect();
			System.out.println("Product Id:" + productId);
			PreparedStatement pstmt = con
					.prepareStatement("select * from products where PRODUCT_ID=?");
			pstmt.setInt(1,productId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				prd = new Products();
				
				prd.setProductId(rs.getInt(1));
				prd.setProductName(rs.getString(2));
				prd.setDescription(rs.getString(3));
				prd.setUserId(rs.getInt(4));
				prd.setPrice(rs.getInt(5));
	            prd.setSid(rs.getInt(7));
	            prd.setCid(rs.getInt(8));
			}

			ConnectionDao.destroy(con);
			return prd;
		} catch (SQLException e) {
			return null;
		}

	}
	
	
	
}
