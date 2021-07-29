package com.neu.pojo;

import java.util.ArrayList;

public class Floor {
	private String name;
	private ArrayList<Room> rooms;
	int maxRoom;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	public int getMaxRoom() {
		return maxRoom;
	}
	public void setMaxRoom(int maxRoom) {
		this.maxRoom = maxRoom;
	}
	
	public Floor() {}
	
	public Floor(String name, int maxRoom) {
		super();
		this.name = name;
		this.maxRoom = maxRoom;
		rooms = new ArrayList<>();
	}
	
	
}
