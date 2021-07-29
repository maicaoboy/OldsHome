package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.DialogRecord;

/**
 * 
 * @author ������ݵ��к�
 *����������¼���ݲ����ӿ�
 */

public interface IRecordDao {

	//������ϼ�¼����
	void setRecords(ArrayList<DialogRecord> records);

	//���һ����ϼ�¼
	void addRecord(DialogRecord record);

	//ȡ����ϼ�¼
	ArrayList<DialogRecord> getRecords();

	//������ϼ�¼
	void store();

}