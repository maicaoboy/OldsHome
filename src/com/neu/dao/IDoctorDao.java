package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Doctor;

/**
 * 
 * @author ������ݵ��к�
 *ҽ�����ݲ����ӿ�
 */

public interface IDoctorDao {
	//��֤ҽ�������ڵ�¼
	boolean verify(Doctor doctor);

	//���һ��ҽ��
	boolean add(Doctor doctor);

	//���û���ɾ��һ��ҽ��
	void delete(String username);

	//ȡ��ҽ������
	ArrayList<Doctor> getDoctors();
	
	//����ҽ������
	void setDoctors(ArrayList<Doctor> doctors);

	//���û�������ҽ��
	Doctor searchByUsername(String username);

	//���س�����ҽ��
	ArrayList<Doctor> searchBySpeciality(String speciality);

	//�����ֲ���ҽ��
	ArrayList<Doctor> searchByName(String name);

	//����ҽ��
	void store();

}