package com.neu.pojo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * ����ʵ����
 * @author ������ݵ��к�
 *
 */
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;								//����
	private Calendar birthday;							//��������
	private String sex;									//�Ա�
	private String identity;							//���֤
	private String phone;								//�ֻ���
	private String emergename;							//������ϵ������
	private String emergephone;							//������ϵ�˵绰
	
	public Patient() {
		Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, 1000);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,1);
		name = " ";
		birthday = c;
		sex = " ";
		identity = " ";
		phone = " ";
		emergename = " ";
		emergephone = " ";
	}

	

	public Patient(String name, Calendar birthday, String sex, String identity, String phone, String emergename,
			String emergephone) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.identity = identity;
		this.phone = phone;
		this.emergename = emergename;
		this.emergephone = emergephone;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmergename() {
		return emergename;
	}

	public void setEmergename(String emergename) {
		this.emergename = emergename;
	}

	public String getEmergephone() {
		return emergephone;
	}

	public void setEmergephone(String emergephone) {
		this.emergephone = emergephone;
	}
	
	
}
