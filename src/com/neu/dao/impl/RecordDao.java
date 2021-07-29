package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.dao.IRecordDao;
import com.neu.pojo.DialogRecord;

/**
 * 
 * @author 嚼着麦草的男孩
 *评估记录数据操作类
 */

public class RecordDao implements IRecordDao {
	private ArrayList<DialogRecord> records;			//诊断结果集合
	private static IRecordDao instance;
	
	
	//更新诊断记录
	@Override
	public void setRecords(ArrayList<DialogRecord> records) {
		this.records = records;
	}

	public static IRecordDao getInstance() {
		if(instance == null) {
			instance = new RecordDao();
		}
		return instance;
	}
	
	private RecordDao() {
		records = new ArrayList<>();
		 File file = new File("Records.json");
         if(file.exists()) {
             ObjectMapper om = new ObjectMapper();
             try {
				records = om.readValue(file,new TypeReference<List<DialogRecord>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }

	}
	
	//添加一个诊断记录
	@Override
	public void addRecord(DialogRecord record) {
		records.add(record);
	}
	
	//取得诊断记录集合
	@Override
	public ArrayList<DialogRecord> getRecords() {
		return records;
	}
	
	//保存诊断记录
	@Override
	public void store() {
        try {
            File file = new File("Records.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,records);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
