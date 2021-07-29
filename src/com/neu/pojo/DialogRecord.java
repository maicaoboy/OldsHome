package com.neu.pojo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author ������ݵ��к�
 *������¼��
 */
public class DialogRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;							//�������˵�����
	private String gender;							//���˵��Ա�
	private String templateName;					//ģ������
	private String templateType;					//ģ������
	private Calendar dialogTime;					//����ʱ��
	private String doctorName;						//����ҽ��������
	private String score;							//�÷�/�ܷ�
	private String result;							//����
	
	
	public DialogRecord() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,1000);
		c.set(Calendar.MONTH,1);
		c.set(Calendar.DATE,1);
		name = " ";
		gender = " ";
		templateName = " ";
		templateType = " ";
		dialogTime = c;
		doctorName = " ";
		score = "0/0";
		result = " ";
	}


	public DialogRecord(String name, String gender, String templateName, String templateType, Calendar dialogTime,
			String doctorName, String score, String result) {
		super();
		this.name = name;
		this.gender = gender;
		this.templateName = templateName;
		this.templateType = templateType;
		this.dialogTime = dialogTime;
		this.doctorName = doctorName;
		this.score = score;
		this.result = result;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getTemplateType() {
		return templateType;
	}


	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}


	public Calendar getDialogTime() {
		return dialogTime;
	}


	public void setDialogTime(Calendar dialogTime) {
		this.dialogTime = dialogTime;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
	public String toString() {
		return "{\"DialogRecord\":{"
				+ "\"name\":\""
				+ name + '\"'
				+ ",\"gender\":\""
				+ gender + '\"'
				+ ",\"templateName\":\""
				+ templateName + '\"'
				+ ",\"templateType\":\""
				+ templateType + '\"'
				+ ",\"dialogTime\":"
				+ dialogTime
				+ ",\"doctorName\":\""
				+ doctorName + '\"'
				+ ",\"score\":\""
				+ score + '\"'
				+ ",\"result\":\""
				+ result + '\"'
				+ "}}";

	}

	
	
}
