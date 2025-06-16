package la.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {
	private int id;
	private String name;
	private String tel;
	private String email;
	private String password;
	private boolean delete_flag;

	public CustomerBean(int id, String name, String tel, String email, String password) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.delete_flag = false;
	}

	public CustomerBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}
}