package im.dao;

import im.beans.Category;
import im.beans.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDao {

	public Users searchUser(String userName) {

		try {
			Users usr = null;
			Connection con = ConnectionDao.connect();
			System.out.println("User Name:" + userName);
			PreparedStatement pstmt = con
					.prepareStatement("select * from users where username=?");
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				usr = new Users();
			    usr.setUserId(rs.getInt(1));
			    usr.setUserName(rs.getString(2));
			    usr.setPassword(rs.getString(3));
			    usr.setInstName(rs.getString(4));
			    usr.setAddress(rs.getString(5));
			    usr.setEmail(rs.getString(6));
			    usr.setPhone(rs.getString(7));
			
			}

			ConnectionDao.destroy(con);
			return usr;
		} catch (SQLException e) {
			return null;
		}

	}
	
	
	public int verifyDetails(Users usrDetails) {
		String passwd = usrDetails.getPassword();
		String userName = usrDetails.getUserName();
		try {
			Connection con = ConnectionDao.connect();
			System.out.println(usrDetails.getUserName()+"&"+usrDetails.getPassword());
			PreparedStatement ptmt = con.prepareStatement("select * from users where username=? and password=?");
			System.out.println("After query");
			ptmt.setString(1,userName);
			ptmt.setString(2,passwd);
			ResultSet rs = ptmt.executeQuery();
			System.out.println("After result set");
			if(rs.next()) 
					return rs.getInt(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;
	}
	
	
	public int addUser(Users usr) {
		try {
			Users cust = searchUser(usr.getUserName());
			if(cust!=null)
				return -1;
			Connection con = ConnectionDao.connect();	
			PreparedStatement pstmt = con.prepareStatement("insert into users values(user_seq.nextval,?,?,?,?,?,?)");
			pstmt.setString(1,usr.getUserName());
			pstmt.setString(2,usr.getPassword());
			pstmt.setString(3,usr.getInstName());
			pstmt.setString(4,usr.getAddress());
			pstmt.setString(5,usr.getEmail());
			pstmt.setString(6,usr.getPhone());
			pstmt.executeUpdate();
			ConnectionDao.destroy(con);
			return 1;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int updateUser(Users usr) {
		try {
			Connection con = ConnectionDao.connect();
			PreparedStatement pstmt = con.prepareStatement("update users set password=?,instname=?,address=?,email=?,phone=? where userid=?");
			pstmt.setString(1,usr.getPassword());
			pstmt.setString(2,usr.getInstName());
			pstmt.setString(3,usr.getAddress());
			pstmt.setString(4,usr.getEmail());
			pstmt.setString(5,usr.getPhone());
			pstmt.setInt(6,usr.getUserId());
			
			pstmt.executeUpdate();
			ConnectionDao.destroy(con);
			return 1;
		} catch (SQLException e) {
			return 0;
		}
	}	
	
	public String getUserName(Integer userId) {

		try {
			String usr = null;
			Connection con = ConnectionDao.connect();
			System.out.println("User Id:" + userId);
			PreparedStatement pstmt = con
					.prepareStatement("select username from users where userid=?");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				usr=rs.getString(1);
			
			}

			ConnectionDao.destroy(con);
			return usr;
		} catch (SQLException e) {
			return null;
		}

	}
	
	
	
}
