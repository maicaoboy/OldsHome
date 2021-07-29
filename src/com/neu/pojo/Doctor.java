package com.neu.pojo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author ������ݵ��к�
 *ҽ��ʵ����
 */
public class Doctor implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;							//�û���
	private String name;								//����
	private String password;							//����
	private Calendar birthday;							//��������
	private String speciality;							//�س�
	private String position;							//ְ��
	private String phone;								//�ֻ���
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Calendar getBirthday() {
		return birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Doctor() {
		super();
		Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, 1000);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,1);
		this.username = " ";
		this.password = " ";
		this.name = " ";
		this.birthday = c;
		this.speciality = " ";
		this.position = " ";
		this.phone = " ";
	}
	
	public Doctor(String username, String passsword) {
		super();
		Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, 1111);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,1);
		this.username = username;
		this.password = passsword;
		this.name = " ";
		this.birthday = c;
		this.speciality = " ";
		this.position = " ";
		this.phone = " ";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Doctor doctor = (Doctor)obj;
		return doctor.getUsername().equals(this.username) && doctor.getPassword().equals(this.password);
	}
	
}
