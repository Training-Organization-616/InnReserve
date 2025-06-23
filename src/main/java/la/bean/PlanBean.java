package la.bean;

import java.io.Serializable;

public class PlanBean implements Serializable {
	private int id;
	private int inn_id;
	private String title;
	private int max_people;
	private int price;
	private String detail;
	private boolean delete_flag;

	public PlanBean(int id, int inn_id, String title, int max_people, int price, String detail, boolean delete_flag) {
		this.id = id;
		this.inn_id = inn_id;
		this.title = title;
		this.max_people = max_people;
		this.price = price;
		this.detail = detail;
		this.delete_flag = delete_flag;
	}

	public PlanBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInn_id() {
		return inn_id;
	}

	public void setInn_id(int inn_id) {
		this.inn_id = inn_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMax_people() {
		return max_people;
	}

	public void setMax_people(int max_people) {
		this.max_people = max_people;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

}