package com.neu.service;

import java.util.ArrayList;

import com.neu.pojo.DialogRecord;
import com.neu.pojo.Patient;
import com.neu.pojo.Question;
import com.neu.pojo.Template;

/*
 * 医生员工服务接口
 */

public interface IDoctorService {

	//添加一个问题
	boolean addQuestion(Question question);

	//在指定问题以ID列表查找病人
	Question findQuestionByID(int id, ArrayList<Question> questions);

	//删除问题并联动删除包含该问题的模板
	void deleteQuestion(int quesID, ArrayList<Question> questions, ArrayList<Template> templates);

	//添加一个模板
	boolean addTemplate(Template template);

	//删除问题联动删除模板中的关联
	void deletTemplate(int temID, ArrayList<Template> templates, ArrayList<Question> questions);

	//以模板名称查找模板
	Template findTemplateByName(String name, ArrayList<Template> templates);

	//在指定模板集合中以模板ID查查找模板
	Template findTemplateByID(int id, ArrayList<Template> templates);

	//取得病人集合
	ArrayList<Patient> getPatients();

	//取得问题集合
	ArrayList<Question> getQuestions();
	
	//取得模板集合
	ArrayList<Template> geTemplates();
	
	//取得诊断记录
	ArrayList<DialogRecord> getRecords();
	
	//更新病人
	void setPatients(ArrayList<Patient> patients);
	
	//更新问题
	void setQuestions(ArrayList<Question> questions);
	
	//更新模板
	void setTemplates(ArrayList<Template> templates);
	
	//更新评估记录
	void setRecords(ArrayList<DialogRecord> records);
	
	//保存问题
	void storeQuestions();
	
	//保存病人
	void storePatients();
	
	//保存模板
	void storeTemplate();
	
	//保存评估记录
	void storeRecords();
	
	//使用身份证查询病人是否存在
	boolean isPatientExistIdentity(String identity);
	
	//保存医生
	void storeDoctors();
	
	//使用ID记录取回问题集合
	ArrayList<Question> fromIntListToQuestionList(ArrayList<Integer> integers, ArrayList<Question> questions);
	
	//使用身份证号码查找病人
	Patient findPatientByIdentity(String identity);
	
	//使用身份证验证病人是否存在
	boolean isPatientExistIdentity(String identity, ArrayList<Patient> patients);
	
	//取得名字含该关键字的病人集合
	ArrayList<Patient> patientSearchByName(String name, ArrayList<Patient> patients1);

	//在指定集合中使用身份证号码查找病人
	Patient findPatientByIdentity(String identity, ArrayList<Patient> patients1);

}