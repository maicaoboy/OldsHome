package com.neu.service.impl;

import java.util.ArrayList;

import com.neu.dao.IDoctorDao;
import com.neu.pojo.Doctor;
import com.neu.tools.MyTools;
import com.neu.service.IManagerService;

/**
 * 
 * @author ������ݵ��к�
 *��������Ա������
 */

public class ManagerService implements IManagerService {
	private IDoctorDao doctorDao;							//Ա������
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
	
	//���һ��Ա��
	@Override
	public boolean addDoctor(String username, String password) {
		return doctorDao.add(new Doctor(username, password));
	}
	
	@Override
	public IDoctorDao getDoctorDao() {
		return doctorDao;
	}
	
	//ȡ��Ա������
	@Override
	public ArrayList<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}

	//����Ա��
	@Override
	public void storeDoctorList() {
		doctorDao.store();
	}

	//��½��֤����֤�Ƿ����
	@Override
	public boolean verify(Doctor doctor) {
		return doctorDao.verify(doctor);
	}

	//����Ա������
	@Override
	public void setDoctorList(ArrayList<Doctor> doctors) {
		doctorDao.setDoctors(doctors);
	}

	//ʹ���û�������
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
