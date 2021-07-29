package com.neu.service.impl;

import java.util.ArrayList;

import com.neu.dao.IDoctorDao;
import com.neu.pojo.Doctor;
import com.neu.tools.MyTools;
import com.neu.service.IManagerService;

/**
 * 
 * @author 嚼着麦草的男孩
 *超级管理员服务类
 */

public class ManagerService implements IManagerService {
	private IDoctorDao doctorDao;							//员工集合
	private static IManagerService instance;
	
	public static IManagerService getInstance() {
		if(instance == null) {
			instance = new ManagerService();
		}
		return instance;
	}
	
	private ManagerService() {
		doctorDao = (IDoctorDao) MyTools.getObject("doctordao");
	}
	
	//添加一个员工
	@Override
	public boolean addDoctor(String username, String password) {
		return doctorDao.add(new Doctor(username, password));
	}
	
	@Override
	public IDoctorDao getDoctorDao() {
		return doctorDao;
	}
	
	//取得员工集合
	@Override
	public ArrayList<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}

	//保存员工
	@Override
	public void storeDoctorList() {
		doctorDao.store();
	}

	//登陆验证，验证是否存在
	@Override
	public boolean verify(Doctor doctor) {
		return doctorDao.verify(doctor);
	}

	//更新员工集合
	@Override
	public void setDoctorList(ArrayList<Doctor> doctors) {
		doctorDao.setDoctors(doctors);
	}

	//使用用户名搜索
	@Override
	public Doctor doctorFindByUsername(ArrayList<Doctor> list, String username) {
		Doctor doctor = null;
		for(Doctor doctor2 : list) {
			if(doctor2.getUsername().equals(username)) {
				doctor = doctor2;
				break;
			}
		}
		return doctor;
	}

	
	
}
