package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.pojo.Bed;
import com.neu.pojo.Building;
import com.neu.pojo.Floor;
import com.neu.pojo.Hosptial;
import com.neu.pojo.Room;

public class HosptialDao {
	private Hosptial hosptial;
	private static HosptialDao instance;
	
	public static HosptialDao getInstance() {
		if(instance == null) {
			instance = new HosptialDao();
		}
		return instance;
	}
	
	private HosptialDao() {
		try {
			hosptial = new Hosptial("医院");
			//若数据文件存在则加载
            File file = new File("Hosptial.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                hosptial = om.readValue(file,new TypeReference<Hosptial>(){});
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	public Hosptial getHosptial() {
		return hosptial;
	}
	
	public static void main(String[] args) {
		HosptialDao dao = HosptialDao.getInstance();
	}
	
	public void addBuilding(Building building) {
		hosptial.getBuildings().add(building);
	}
	
	public void addFloor(String building, Floor floor) {
		for(Building building2 : hosptial.getBuildings()) {
			if(building2.getName().equals(building)) {
				building2.getFloors().add(floor);
			}
		}
	}
	
	public void addRoom(String buildingName, String floorName, Room room) {
		for(Building building : hosptial.getBuildings()) {
			if(building.getName().equals(buildingName)) {
				for(Floor floor : building.getFloors()) {
					if(floor.getName().equals(floorName)) {
						floor.getRooms().add(room);
					}
				}
			}
		}
	}
	
	public boolean addBed(String buildingName, String floorName, String roomName, Bed bed) {
		for(Building building : hosptial.getBuildings()) {
			if(building.getName().equals(buildingName)) {
				for(Floor floor : building.getFloors()) {
					if(floor.getName().equals(floorName)) {
						for(Room room : floor.getRooms()) {
							if(room.getName().equals(roomName) && room.getBeds().size() < room.getMax()) {
								room.getBeds().add(bed);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	public Building findBuildingByName(String buildingName,ArrayList<Building> buildings) {
		Building find = null;
		for (Building building : buildings) {
			if(building.getName().equals(buildingName)) {
				find = building;
				break;
			}
		}
		return find;
	}
	
	public void store() {
        try {
            File file = new File("Hosptial.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,hosptial);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
}
