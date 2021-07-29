package com.neu.service;

import java.util.ArrayList;

import com.neu.dao.IDoctorDao;
import com.neu.pojo.Doctor;

/**
 * 
 * @author 嚼着麦草的男孩
 *超级管理员服务接口
 */

public interface IManagerService {
	//添加一个员工
	boolean addDoctor(String username, String password);

	IDoctorDao getDoctorDao();
	
	//取得员工集合
	ArrayList<Doctor> getDoctors();
	
	//保存员工
	void storeDoctorList();
	
	//登陆验证，验证是否存在
	boolean verify(Doctor doctor);
	
	//更新员工集合
	void setDoctorList(ArrayList<Doctor> doctors);

	//使用用户名搜索医生
	public Doctor doctorFindByUsername(ArrayList<Doctor> list, String username);
}