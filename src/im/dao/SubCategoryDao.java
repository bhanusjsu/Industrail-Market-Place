package im.dao;

import im.beans.Category;
import im.beans.SubCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubCategoryDao {

	public SubCategory searchSubCategory(int subCatId,int catId) {

		try {
			SubCategory subCat = null;
			Connection con = ConnectionDao.connect();
			System.out.println("SubCategory Id:" + subCatId+"Category Id:"+catId);
			PreparedStatement pstmt = con
					.prepareStatement("select * from subcategory where sid=? and cid=?");
			pstmt.setInt(1, subCatId);
			pstmt.setInt(2,catId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				subCat = new SubCategory();
				subCat.setSubcategoryId(rs.getInt(1));
				subCat.setSubcategoryName(rs.getString(2));
				subCat.setCategoryId(rs.getInt(3));
			}

			ConnectionDao.destroy(con);
			return subCat;
		} catch (SQLException e) {
			return null;
		}

	}

	public ArrayList<SubCategory> selectAllSubCategories(int catId) {
		System.out.println("Selecting all subcategories");
		ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();
		try {
			SubCategory subCat = null;
			Connection con = ConnectionDao.connect();
			PreparedStatement pstmt = con
					.prepareStatement("select * from subcategory where cid=?");
			System.out.println("Cat Id:"+catId);
			pstmt.setInt(1,catId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				subCat = new SubCategory();
				subCat.setSubcategoryId(rs.getInt(1));
				subCat.setSubcategoryName(rs.getString(2));
				subCat.setCategoryId(rs.getInt(3));
				subCategories.add(subCat);

			}
			ConnectionDao.destroy(con);
			System.out.println(subCategories);
			return subCategories;
		} catch (Exception e) {
			return null;
		}

	}
	
}
