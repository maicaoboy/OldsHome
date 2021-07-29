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
			hosptialDao.addBuilding(new Building(i + "��¥"));
			for(int j = 1; j <= 6; j++) {
				hosptialDao.addFloor(i + "��¥",new Floor(j + "��",8));
				for(int k = 1; k <= 8; k++) {
					hosptialDao.addRoom(i + "��¥", j + "��", new Room(k + "��",0,4,"��ͨ"));
					for(int l = 1; l <= 4; l++) {
						hosptialDao.addBed(i + "��¥", j + "��", k + "��", new Bed(l + "�Ŵ�"));
					}
				}
					
			}
		}
		hosptialDao.store();
	}

}
