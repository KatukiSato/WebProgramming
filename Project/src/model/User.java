package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Userテーブルのデータを格納するためのBeans
 * @author takano
 *
 */
public class User implements Serializable {
	private int id;
	private String loginId;
	private String name;
	private Date birthDate;
	private String password;
	private String createDate;
	private String updateDate;
	private Date date;
	private Date birthDateP;

	// ログインセッションを保存するためのコンストラクタ
	public User(String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}

	// 全てのデータをセットするコンストラクタ
	public User(int id, String loginId, String name, Date birthDate, String password, String createDate,
			String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public User(int iD2, String loginId2, String name2, Date birthDate2, String createDate2, String updateDate2) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.id = iD2;
		this.loginId = loginId2;
		this.name = name2;
		this.birthDate = birthDate2;
		this.createDate = createDate2;
		this.updateDate = updateDate2;
	}

	//データ更新用のコンストラクタ
	public User(String password2, String name2, String birthDate2, int id2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//パスワード未入力の場合、そのまま更新するためのコンストラクタ
	public User(String name2, String birthDate2, int id2) {
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public User(int id2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}





}
