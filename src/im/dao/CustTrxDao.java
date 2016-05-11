package im.dao;

import im.beans.Category;
import im.beans.CustomerTrx;
import im.beans.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustTrxDao {
	public int addTrxn(CustomerTrx trxn) {
		try {
		
			Connection con = ConnectionDao.connect();	
			PreparedStatement pstmt = con.prepareStatement("insert into cust_trx values(trx_seq.nextval,?,?,?,?,?,?,?)");
			pstmt.setString(1,trxn.getName());
			pstmt.setString(2,trxn.getEmail());
			pstmt.setString(3,trxn.getPhone());
			pstmt.setString(4,trxn.getAddress());
			pstmt.setString(5,trxn.getStatus());
			pstmt.setInt(6,trxn.getAmount());
			pstmt.setInt(7,trxn.getUserId());
			pstmt.executeUpdate();
			ConnectionDao.destroy(con);
			return 1;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public CustomerTrx geTrxDetails(int transId) {

		try {
			CustomerTrx trxn = null;
			Connection con = ConnectionDao.connect();
			System.out.println("Transaction Id:" + transId);
			PreparedStatement pstmt = con
					.prepareStatement("select * from cust_trx where cust_trx_id=?");
			pstmt.setInt(1, transId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("exists");
				trxn = new CustomerTrx();
				
				trxn.setCustTrxId(rs.getInt(1));
				trxn.setName(rs.getString(2));
				trxn.setEmail(rs.getString(3));
				trxn.setPhone(rs.getString(4));
				trxn.setAddress(rs.getString(5));
	            trxn.setStatus(rs.getString(6));
	            trxn.setAmount(rs.getInt(7));
	            trxn.setUserId(rs.getInt(8));
			}

			ConnectionDao.destroy(con);
			return trxn;
		} catch (SQLException e) {
			return null;
		}

	}

	
	
	}
