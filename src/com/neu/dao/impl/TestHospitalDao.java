package com.neu.dao.impl;

import com.neu.pojo.Bed;
import com.neu.pojo.Building;
import com.neu.pojo.Floor;
import com.neu.pojo.Room;
import com.neu.tools.MyTools;

public class TestHospitalDao {
	public static void main(String[] args) {
		HosptialDao hosptialDao = (HosptialDao) MyTools.getObject("hosptialdao");
		for(int i = 1; i <= 3; i ++) {
			hosptialDao.addBuilding(new Building(i + "ºÅÂ¥"));
			for(int j = 1; j <= 6; j++) {
				hosptialDao.addFloor(i + "ºÅÂ¥",new Floor(j + "²ã",8));
				for(int k = 1; k <= 8; k++) {
					hosptialDao.addRoom(i + "ºÅÂ¥", j + "²ã", new Room(k + "·¿",0,4,"ÆÕÍ¨"));
					for(int l = 1; l <= 4; l++) {
						hosptialDao.addBed(i + "ºÅÂ¥", j + "²ã", k + "·¿", new Bed(l + "ºÅ´²"));
					}
				}
					
			}
		}
		hosptialDao.store();
	}

}
