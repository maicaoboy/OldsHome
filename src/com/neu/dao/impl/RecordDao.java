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
 * @author ������ݵ��к�
 *������¼���ݲ�����
 */

public class RecordDao implements IRecordDao {
	private ArrayList<DialogRecord> records;			//��Ͻ������
	private static IRecordDao instance;
	
	
	//������ϼ�¼
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
	
	//���һ����ϼ�¼
	@Override
	public void addRecord(DialogRecord record) {
		records.add(record);
	}
	
	//ȡ����ϼ�¼����
	@Override
	public ArrayList<DialogRecord> getRecords() {
		return records;
	}
	
	//������ϼ�¼
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
