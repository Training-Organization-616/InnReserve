package la.bean;

import java.io.Serializable;
import java.sql.Date;

public class ReserveBean implements Serializable {
	private int id;
	private int customer_id;
	private int inn_id;
	private int plan_id;
	private int people;
	private int stay_days;
	private Date first_day;
	private int total_price;
	private boolean cancel_flag;

	public ReserveBean(int id, int customer_id, int inn_id, int plan_id, int people,
			int stay_days, Date first_day, int total_price) {
		this.id = id;
		this.customer_id = customer_id;
		this.inn_id = inn_id;
		this.plan_id = plan_id;
		this.people = people;
		this.stay_days = stay_days;
		this.first_day = first_day;
		this.total_price = total_price;
		this.cancel_flag = false;
	}

	public ReserveBean(int id, int customer_id, int inn_id, int plan_id, int people,
			int stay_days, Date first_day, int total_price, boolean cancel_flag) {
		this.id = id;
		this.customer_id = customer_id;
		this.inn_id = inn_id;
		this.plan_id = plan_id;
		this.people = people;
		this.stay_days = stay_days;
		this.first_day = first_day;
		this.total_price = total_price;
		this.cancel_flag = cancel_flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getInn_id() {
		return inn_id;
	}

	public void setInn_id(int inn_id) {
		this.inn_id = inn_id;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getStay_days() {
		return stay_days;
	}

	public void setStay_days(int stay_days) {
		this.stay_days = stay_days;
	}

	public Date getFirst_day() {
		return first_day;
	}

	public void setFirst_day(Date first_day) {
		this.first_day = first_day;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public boolean isCancel_flag() {
		return cancel_flag;
	}

	public void setCancel_flag(boolean cancel_flag) {
		this.cancel_flag = cancel_flag;
	}

}