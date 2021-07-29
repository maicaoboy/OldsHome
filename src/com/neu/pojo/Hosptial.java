package com.neu.pojo;

import java.util.ArrayList;

public class Hosptial {
	private String name;
	private ArrayList<Building> buildings;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}
	public Hosptial(String name) {
		super();
		this.name = name;
		buildings = new ArrayList<>();
	}
	
	
}
