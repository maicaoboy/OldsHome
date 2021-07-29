package com.neu.service;

import java.util.ArrayList;

import com.neu.dao.IDoctorDao;
import com.neu.pojo.Doctor;

/**
 * 
 * @author ������ݵ��к�
 *��������Ա����ӿ�
 */

public interface IManagerService {
	//���һ��Ա��
	boolean addDoctor(String username, String password);

	IDoctorDao getDoctorDao();
	
	//ȡ��Ա������
	ArrayList<Doctor> getDoctors();
	
	//����Ա��
	void storeDoctorList();
	
	//��½��֤����֤�Ƿ����
	boolean verify(Doctor doctor);
	
	//����Ա������
	void setDoctorList(ArrayList<Doctor> doctors);

	//ʹ���û�������ҽ��
	public Doctor doctorFindByUsername(ArrayList<Doctor> list, String username);
}