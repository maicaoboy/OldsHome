package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Patient;

/**
 * 
 * @author ������ݵ��к�
 *�������ݲ����ӿ�
 */

public interface IPatientDao {

	//���һ������
	void addPatient(Patient patient);

	//ɾ��һ������
	void deletePatient(Patient patient);

	//ȡ�ò��˼���
	ArrayList<Patient> getPatients();

	//ʹ�ò��������жϲ����Ƿ����
	boolean isExist(String name);

	//���²��˼���
	void setPatients(ArrayList<Patient> patients);

	//���没�˼���
	void store();

	//ʹ�ò��˵����֤�жϲ����Ƿ����
	boolean isExistIdentity(String identity);

}