package dao;
//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class EntryDao {
//
//	public void entry(String loginId, String name, String birthDate, String password, String checkPassword) throws SQLException {
//
//		Connection conn = null;
//
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "insert into user(login_id, name, birth_date, password, create_date, update_date) values (?, ?, ?, ?, now(), now())";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, loginId);
//			pStmt.setString(2, name);
//			pStmt.setString(3, birthDate);
//			pStmt.setString(4, password);
//
//			int rs = pStmt.executeUpdate();
//			System.out.println(rs);
//
//
//		}catch (SQLException e) {
//		e.printStackTrace();
//		throw e;
//		}
//
//
//	}
//
//
//}
