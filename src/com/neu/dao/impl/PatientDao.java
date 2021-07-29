package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.dao.IPatientDao;
import com.neu.pojo.Patient;

/**
 * 
 * @author ������ݵ��к�
 *�������ݲ�����
 */

public class PatientDao implements IPatientDao {
	ArrayList<Patient> patients;					//���˼���
	private static IPatientDao instance;
	
	public static IPatientDao getInstance() {
		if(instance == null) {
			instance = new PatientDao();
		}
		return instance;
	}
	
	private PatientDao() {
		patients = new ArrayList<>();
		try {
			//���ļ��ָ���������
            File file = new File("Patients.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                patients = om.readValue(file,new TypeReference<List<Patient>>(){});
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	//���һ������
	@Override
	public void addPatient(Patient patient) {
		patients.add(patient);
	}
	
	//ɾ��һ������
	@Override
	public void deletePatient(Patient patient) {
		patients.remove(patient);
	}
	
	//ȡ�õ�ǰ���˼���
	@Override
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	//�������жϲ����Ƿ����
	@Override
	public boolean isExist(String name) {
		for (Patient patient : patients) {
			if(patient.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	//���µ�ǰ����
	@Override
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	//���没������
	@Override
	public void store() {
		try {
            File file = new File("Patients.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,patients);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}

	//�����֤��֤�����Ƿ����
	@Override
	public boolean isExistIdentity(String identity) {
		// TODO Auto-generated method stub
		boolean result = false;
		for (Patient patient : patients) {
			if(patient.getIdentity().equals(identity)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
