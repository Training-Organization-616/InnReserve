package la.bean;

import java.io.Serializable;

public class InnBean implements Serializable {
	private int id;
	private String name;
	private String address;
	private String tel;
	private int price;
	private boolean delete_flag;

	public InnBean(int id, String name, String address, String tel, int price) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.price = price;
		this.delete_flag = false;
	}

	public InnBean() {

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

}