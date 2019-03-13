package dao;
//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import model.User;
//
////DBからログインID、名前、生年月日などの情報を呼び出すためのDao
//
//public class DetailDao {
//
//	public User detailAll(String id) {
//
//		Connection conn = null;
//		List<User> detailList = new ArrayList<User>();
//
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "select id, login_id, name, birth_date, create_date, update_date from user where id = ?;";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, id);
//
//			ResultSet rs = pStmt.executeQuery();
//
//			if (!rs.next()) {
//				return null;
//			}
//				int ID = rs.getInt("id");
//				String loginId = rs.getString("login_id");
//				String name = rs.getString("name");
//				Date birthDate = rs.getDate("birth_date");
//				String createDate = rs.getString("create_date");
//				String updateDate = rs.getString("update_date");
//
//				User user = new User(ID, loginId, name, birthDate, createDate, updateDate);
//				detailList.add(user);
//
//				return user;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//
//		}
//
//	}
//
//}
