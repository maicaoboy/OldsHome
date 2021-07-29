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
 * @author 嚼着麦草的男孩
 *病人数据操作类
 */

public class PatientDao implements IPatientDao {
	ArrayList<Patient> patients;					//病人集合
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
			//从文件恢复病人数据
            File file = new File("Patients.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                patients = om.readValue(file,new TypeReference<List<Patient>>(){});
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	//添加一个病人
	@Override
	public void addPatient(Patient patient) {
		patients.add(patient);
	}
	
	//删除一个病人
	@Override
	public void deletePatient(Patient patient) {
		patients.remove(patient);
	}
	
	//取得当前病人集合
	@Override
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	//以姓名判断病人是否存在
	@Override
	public boolean isExist(String name) {
		for (Patient patient : patients) {
			if(patient.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	//更新当前病人
	@Override
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	//保存病人数据
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

	//以身份证验证病人是否存在
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
