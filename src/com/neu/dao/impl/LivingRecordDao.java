package com.neu.dao.impl;

import java.util.ArrayList;

import com.neu.pojo.LivingRecord;

public class LivingRecordDao {
	ArrayList<LivingRecord> livingRecords;
	private static LivingRecordDao insatnce;
	
	public static LivingRecordDao getInsatnce() {
		if(insatnce == null) {
			insatnce = new LivingRecordDao();
		}
		return insatnce;
	}
	
	private LivingRecordDao() {
		livingRecords = new ArrayList<>();
	}
	
	public void addRecords(LivingRecord livingRecord) {
		livingRecords.add(livingRecord);
	}
	
	public LivingRecord getLivingRecord(int ID) {
		for(LivingRecord livingRecord : livingRecords) {
			if(livingRecord.getRecordID() == ID) {
				return livingRecord;
			}
		}
		return null;
	}
}
