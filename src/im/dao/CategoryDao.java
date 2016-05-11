package im.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import im.beans.Category;

public class CategoryDao {

	public Category searchCategory(int catId) {

		try {
			Category cat = null;
			Connection con = ConnectionDao.connect();
			System.out.println("Category Id:" + catId);
			PreparedStatement pstmt = con
					.prepareStatement("select * from category where cid=?");
			pstmt.setInt(1, catId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				cat = new Category();
				cat.setCategoryId(rs.getInt(1));
				cat.setCategoryName(rs.getString(2));
			}

			ConnectionDao.destroy(con);
			return cat;
		} catch (SQLException e) {
			return null;
		}

	}

	public ArrayList<Category> selectAllCategories() {
		System.out.println("Selecting all categories");
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			Category cat = null;
			Connection con = ConnectionDao.connect();
			PreparedStatement pstmt = con
					.prepareStatement("select * from category");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				cat = new Category();
				cat.setCategoryId(rs.getInt(1));
				cat.setCategoryName(rs.getString(2));
				categories.add(cat);

			}
			ConnectionDao.destroy(con);
			System.out.println(categories);
			return categories;
		} catch (Exception e) {
			return null;
		}

	}

}
