package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.dao.IDoctorDao;
import com.neu.pojo.Doctor;

/**
 * 
 * @author ������ݵ��к�
 *ҽ�����ݲ�����
 */

public class DoctorDao implements IDoctorDao {
	ArrayList<Doctor> doctors;								//ҽ������
	private static IDoctorDao instance;
	
	public static IDoctorDao getInstance() {
		if(instance == null) {
			instance = new DoctorDao();
		}
		return instance;
	}
	
	private DoctorDao() {
		doctors = new ArrayList<>();
		try {
			//�������ļ����������
            File file = new File("Doctors.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                doctors = om.readValue(file,new TypeReference<List<Doctor>>(){});
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	//��֤ҽ�������ڵ�¼
	@Override
	public boolean verify(Doctor doctor) {
		boolean result = false;
		for(Doctor doc : doctors) {
			if(doc.equals(doctor)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	//���һ��ҽ��
	@Override
	public boolean add(Doctor doctor) {
		boolean result = true;
		if(doctor.getUsername().equals("root") && doctor.getPassword().equals("root")) {
			result = false;
		}
		for(Doctor doctor2 : doctors) {
			if(doctor2.getUsername().equals(doctor)) {
				result = false;
				break;
			}
		}
		if(result) {
			doctors.add(doctor);
		}
		return result;
	}
	
	
	//���û���ɾ��һ��ҽ��
	@Override
	public void delete(String username) {
		for(Doctor doctor : doctors) {
			if(doctor.getName().equals(username)) {
				doctors.remove(doctor);
				break;
			}
		}
	}
	
	//ȡ��ҽ������
	@Override
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	
	//����ҽ������
	@Override
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	//���û�������ҽ��
	@Override
	public Doctor searchByUsername(String username) {
		Doctor result = null;
		for(Doctor doctor : doctors) {
			if(doctor.getUsername().equals(username)) {
				result = doctor;
				break;
			}
		}
		return result;
	}
	
	//���س�����ҽ�������ش��س���ҽ������
	@Override
	public ArrayList<Doctor> searchBySpeciality(String speciality) {
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		for(Doctor doctor : doctors) {
			if(doctor.getSpeciality().equals(speciality)) {
				list.add(doctor);
			}
		}
		return list;
	}
	
	//����������ҽ��������ҽ�����ư����˹ؼ��֣�������Щҽ���ļ���
	@Override
	public ArrayList<Doctor> searchByName(String name) {
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		for(Doctor doctor : doctors) {
			if(doctor.getName().contains(name)) {
				list.add(doctor);
			}
		}
		return list;
	}
	
	//����ҽ����json�ļ���
	@Override
	public void store() {
        try {
            File file = new File("Doctors.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,doctors);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
}
