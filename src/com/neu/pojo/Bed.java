package com.neu.pojo;

import java.util.Calendar;

public class Bed {
	private String name;
	private int patientID;
	private Calendar end;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public Calendar getEnd() {
		return end;
	}
	
	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	public Bed() {}
	
	public Bed(String name) {
		super();
		this.name = name;
		end = Calendar.getInstance();
		patientID = 0;
	}
	
	
}
