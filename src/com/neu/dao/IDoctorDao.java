package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Doctor;

/**
 * 
 * @author 嚼着麦草的男孩
 *医生数据操作接口
 */

public interface IDoctorDao {
	//验证医生，用于登录
	boolean verify(Doctor doctor);

	//添加一个医生
	boolean add(Doctor doctor);

	//以用户名删除一个医生
	void delete(String username);

	//取得医生集合
	ArrayList<Doctor> getDoctors();
	
	//更新医生集合
	void setDoctors(ArrayList<Doctor> doctors);

	//以用户名搜索医生
	Doctor searchByUsername(String username);

	//以特长搜索医生
	ArrayList<Doctor> searchBySpeciality(String speciality);

	//以名字查找医生
	ArrayList<Doctor> searchByName(String name);

	//保存医生
	void store();

}