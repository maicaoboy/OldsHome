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
 * @author 嚼着麦草的男孩
 *医生数据操作类
 */

public class DoctorDao implements IDoctorDao {
	ArrayList<Doctor> doctors;								//医生集合
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
			//若数据文件存在则加载
            File file = new File("Doctors.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                doctors = om.readValue(file,new TypeReference<List<Doctor>>(){});
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	//验证医生，用于登录
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
	
	//添加一个医生
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
	
	
	//以用户名删除一个医生
	@Override
	public void delete(String username) {
		for(Doctor doctor : doctors) {
			if(doctor.getName().equals(username)) {
				doctors.remove(doctor);
				break;
			}
		}
	}
	
	//取得医生集合
	@Override
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	
	//更新医生集合
	@Override
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	//以用户名搜索医生
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
	
	//以特长搜索医生，返回此特长的医生集合
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
	
	//以名称搜索医生，若该医生名称包含此关键字，返回这些医生的集合
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
	
	//保存医生到json文件中
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
