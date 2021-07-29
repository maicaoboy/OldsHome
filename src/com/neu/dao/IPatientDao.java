package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Patient;

/**
 * 
 * @author 嚼着麦草的男孩
 *病人数据操作接口
 */

public interface IPatientDao {

	//添加一个病人
	void addPatient(Patient patient);

	//删除一个病人
	void deletePatient(Patient patient);

	//取得病人集合
	ArrayList<Patient> getPatients();

	//使用病人姓名判断病人是否存在
	boolean isExist(String name);

	//更新病人集合
	void setPatients(ArrayList<Patient> patients);

	//保存病人集合
	void store();

	//使用病人的身份证判断病人是否存在
	boolean isExistIdentity(String identity);

}