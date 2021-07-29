package com.neu.pojo;

import java.util.Calendar;

public class LivingRecord {
	private String identity;
	private Calendar in;
	private Calendar out;
	private String state;
	private int recordID;
	public static int recordAotu = 5467;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Calendar getIn() {
		return in;
	}
	public void setIn(Calendar in) {
		this.in = in;
	}
	public Calendar getOut() {
		return out;
	}
	public void setOut(Calendar out) {
		this.out = out;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public LivingRecord(String identity, Calendar in, Calendar out, String state) {
		super();
		this.identity = identity;
		this.in = in;
		this.out = out;
		this.state = state;
		recordAotu++;
		recordID = recordAotu;
	}
	
	
}
