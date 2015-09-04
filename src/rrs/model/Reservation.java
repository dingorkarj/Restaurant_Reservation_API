package rrs.model;

import java.sql.Date;

public class Reservation {
	private int rid; 
	private int tid;
	private int cid;
	private int confirmationCode;
	private Date date;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		//this.date = sdf.format(date);
		this.date = date;
	}
	public int getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(int confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	
	
}
