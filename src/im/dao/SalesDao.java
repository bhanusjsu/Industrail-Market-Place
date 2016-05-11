package im.dao;

import im.beans.SubCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SalesDao {
	

public HashMap<String,Integer> viewCurrSalByCategory(int catId)
{
	System.out.println("Inside viewCurSalCat");
	HashMap<String,Integer> listByCat = new HashMap<String,Integer>();
	try {
		Connection con = ConnectionDao.connect();
		PreparedStatement pstmt = con
				.prepareStatement("select s.sname,sum(amount) from category c,subcategory s,sales ss where c.cid=s.cid and ss.cid=c.cid and ss.sid=s.sid and ss.year=extract(year from sysdate) and c.cid=? group by s.sname");
		System.out.println("Cat Id:"+catId);
		pstmt.setInt(1,catId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("exists");
			
	listByCat.put(rs.getString(1),rs.getInt(2));

		}
		ConnectionDao.destroy(con);
		System.out.println(listByCat);
		return listByCat;
	} catch (Exception e) {
		return null;
	}
	
	
}

public HashMap<String,Integer> viewCurrSalByArea(int catId,int subCatId)
{
	System.out.println("Inside viewCurSalByArea");
	HashMap<String,Integer> listByCat = new HashMap<String,Integer>();
	try {
		Connection con = ConnectionDao.connect();
		PreparedStatement pstmt = con
				.prepareStatement("SELECT ss.area,SUM(amount) FROM category c, subcategory s,sales ss WHERE c.cid=s.cid AND ss.cid =c.cid AND ss.sid =s.sid AND ss.year=extract(YEAR FROM sysdate) AND c.cid  =? and s.sid=? GROUP BY ss.area");
		System.out.println("Cat Id:"+catId);
		pstmt.setInt(1,catId);
		pstmt.setInt(2,subCatId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("exists");
			
	listByCat.put(rs.getString(1),rs.getInt(2));

		}
		ConnectionDao.destroy(con);
		System.out.println(listByCat);
		return listByCat;
	} catch (Exception e) {
		return null;
	}
	
}

public HashMap<Integer,Integer> viewSalesByYear(int catId,int subCatId)
{
	System.out.println("Inside viewSalesByYear");
	HashMap<Integer,Integer> listByCat = new HashMap<Integer,Integer>();
	try {
		Connection con = ConnectionDao.connect();
		PreparedStatement pstmt = con
				.prepareStatement("SELECT ss.year,SUM(amount) FROM category c,subcategory s,sales ss WHERE c.cid=s.cid AND ss.cid =c.cid AND ss.sid =s.sid AND c.cid  =? AND s.sid  =? GROUP BY ss.year");
		System.out.println("Cat Id:"+catId);
		pstmt.setInt(1,catId);
		pstmt.setInt(2,subCatId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("exists");
			
	listByCat.put(rs.getInt(1),rs.getInt(2));

		}
		ConnectionDao.destroy(con);
		System.out.println(listByCat);
		return listByCat;
	} catch (Exception e) {
		return null;
	}


}

}
