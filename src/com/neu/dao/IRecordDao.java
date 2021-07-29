package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.DialogRecord;

/**
 * 
 * @author 嚼着麦草的男孩
 *病人评估记录数据操作接口
 */

public interface IRecordDao {

	//更新诊断记录集合
	void setRecords(ArrayList<DialogRecord> records);

	//添加一个诊断记录
	void addRecord(DialogRecord record);

	//取得诊断记录
	ArrayList<DialogRecord> getRecords();

	//保存诊断记录
	void store();

}