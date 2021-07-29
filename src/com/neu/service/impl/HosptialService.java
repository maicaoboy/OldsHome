package com.neu.service.impl;

import com.neu.dao.impl.HosptialDao;
import com.neu.pojo.Bed;
import com.neu.pojo.Building;
import com.neu.pojo.Floor;
import com.neu.pojo.Room;
import com.neu.tools.MyTools;

public class HosptialService {
	private HosptialDao hosptialDao;
	
	private HosptialService() {
		hosptialDao = (HosptialDao)MyTools.getObject("hosptialdao");
	}
	
	public Bed findByDetail(String buildingName, String floorName, String roomName,String bedName) {
		for(Building building : hosptialDao.getHosptial().getBuildings()) {
			if(building.getName().equals(buildingName)) {
				for(Floor floor : building.getFloors()) {
					if(floor.getName().equals(floorName)) {
						for(Room room : floor.getRooms()) {
							if(room.getName().equals(roomName)) {
								for(Bed bed : room.getBeds()) {
									if(bed.getName().equals(bedName)) {
										return bed;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
}
