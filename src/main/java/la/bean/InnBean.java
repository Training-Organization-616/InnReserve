package la.bean;

import java.io.Serializable;

public class InnBean implements Serializable {
	private int id;
	private String name;
	private String address;
	private String tel;
	private int min_price;
	private String picture;
	private boolean delete_flag;

	public InnBean(int id, String name, String address, String tel, int min_price, String picture) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.min_price = min_price;
		this.picture = picture;
		this.delete_flag = false;
	}

	public InnBean(int id, String name, String address, String tel, String picture, boolean delete_flag) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.picture = picture;
		this.delete_flag = delete_flag;
	}

	public InnBean(int id, String name, String address, String tel, int min_price, String picture,
			boolean delete_flag) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.min_price = min_price;
		this.picture = picture;
		this.delete_flag = delete_flag;
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

	public int getMin_price() {
		return min_price;
	}

	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

}