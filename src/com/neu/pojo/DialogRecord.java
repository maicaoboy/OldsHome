package com.neu.pojo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author 嚼着麦草的男孩
 *评估记录类
 */
public class DialogRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;							//评估病人的名称
	private String gender;							//病人的性别
	private String templateName;					//模板名称
	private String templateType;					//模板类型
	private Calendar dialogTime;					//评估时间
	private String doctorName;						//评估医生的姓名
	private String score;							//得分/总分
	private String result;							//评价
	
	
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
