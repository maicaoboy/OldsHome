package com.neu.pojo;

import java.util.ArrayList;

public class Room {
	private String name;
	private ArrayList<Bed> beds;
	private int now;
	private int max;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Bed> getBeds() {
		return beds;
	}
	public void setBeds(ArrayList<Bed> beds) {
		this.beds = beds;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Room() {}
	
	public Room(String name, int now, int max, String type) {
		super();
		this.name = name;
		this.now = now;
		this.max = max;
		this.type = type;
		beds = new ArrayList<>();
	}

	
}
