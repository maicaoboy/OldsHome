package com.neu.pojo;

import java.util.ArrayList;

public class Building {
	private ArrayList<Floor> floors;
	private String name;
	public ArrayList<Floor> getFloors() {
		return floors;
	}
	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Building(String name) {
		super();
		this.name = name;
		floors = new ArrayList<>();
	}
	
	
}
