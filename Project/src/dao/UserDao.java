package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.Common;
import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

	/**
	 * ログインIDとパスワードに紐づくユーザ情報を返す
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			try {
				Common.Ango(password);
				pStmt.setString(2, password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			// 必要なデータのみインスタンスのフィールドに追加
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	/**
	 * 全てのユーザ情報を取得する
	 * @return
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user where id >1";

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	/**
	 * 絞り込み検索
	 * @return
	 */
	public List<User> findSearch(String loginIdP, String nameP, String birth_dateP, String birth_datePP) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user where id >1";

			if(!loginIdP.equals("")) {
				sql += " AND login_id = '" + loginIdP + "'";
			}

			if(!nameP.equals("")) {
				sql += " AND name like '%" + nameP + "%'";
			}

			if(!birth_dateP.equals("")) {
				sql += " AND birth_date >= '" + birth_dateP + "'";
			}

			if(!birth_datePP.equals("")) {
				sql += " AND birth_date <= '" + birth_datePP + "'";
			}

			if(!birth_dateP.equals("") && !birth_datePP.equals("")) {
				sql += "and birth_date between '" + birth_dateP + "' and '" + birth_datePP +"'";
			}


			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	//新規登録画面用
	public void entry(String loginId, String name, String birthDate, String password, String checkPassword)
			throws SQLException {

		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "insert into user(login_id, name, birth_date, password, create_date, update_date) values (?, ?, ?, ?, now(), now())";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, name);
			pStmt.setString(3, birthDate);

			try {
				Common.Ango(password);
				pStmt.setString(4, password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			int rs = pStmt.executeUpdate();
			System.out.println(rs);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	//DBからログインID、名前、生年月日などの情報を呼び出すためのDao

	public User detailAll(String id) {

		Connection conn = null;
		List<User> detailList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();

			String sql = "select id, login_id, name, birth_date, create_date, update_date from user where id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int ID = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");

			User user = new User(ID, loginId, name, birthDate, createDate, updateDate);
			detailList.add(user);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}

	//ユーザ情報更新用

	public User UpdateAll(String password, String Name, String birthDate, int id) {

		Connection conn = null;
		List<User> updateList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();

			String sql = "update user set password = ?, name = ?, birth_date = ?, update_date = now() where id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				Common.Ango(password);
				pStmt.setString(1, password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			pStmt.setString(2, Name);
			pStmt.setString(3, birthDate);
			pStmt.setInt(4, id);

			int rs = pStmt.executeUpdate();

			User user = new User(password, Name, birthDate, id);
			updateList.add(user);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	//パスワード未入力の場合、そのまま更新するためのDao
	public User UpdateAllX(String Name, String birthDate, int id) {

		Connection conn = null;
		List<User> updateList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "update user set name = ?, birth_date = ?, update_date = now() where id = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Name);
			pStmt.setString(2, birthDate);
			pStmt.setInt(3, id);

			int rs = pStmt.executeUpdate();

			User use = new User(Name, birthDate, id);
			updateList.add(use);

			return use;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	//ユーザ情報を消去するためのDao
	public User Delete(int id) {
		Connection conn = null;
		List<User> updateList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "delete from user where id =?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			int rs = pStmt.executeUpdate();

			User user = new User(id);
			updateList.add(user);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

//	//IDの部分検索のためのDao
//	public List<User> SearchId(String login_id) {
//		Connection conn = null;
//		List<User> SearchIdList = new ArrayList<User>();
//
//		try {
//			conn = DBManager.getConnection();
//			String sql = "select * from user where login_id like '%?%';";
//
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, login_id);
//
//			ResultSet rs = pStmt.executeQuery(sql);
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String loginId = rs.getString("login_id");
//				String name = rs.getString("name");
//				Date birthDate = rs.getDate("birth_date");
//				String password = rs.getString("password");
//				String createDate = rs.getString("create_date");
//				String updateDate = rs.getString("update_date");
//				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);
//
//				SearchIdList.add(user);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			// データベース切断
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					return null;
//				}
//			}
//		}
//		return SearchIdList;
//	}

}