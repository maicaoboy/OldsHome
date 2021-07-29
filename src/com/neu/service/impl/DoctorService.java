package com.neu.service.impl;

import java.util.ArrayList;

import com.neu.dao.IDoctorDao;
import com.neu.dao.IPatientDao;
import com.neu.dao.IQuestionDao;
import com.neu.dao.IRecordDao;
import com.neu.dao.ITemplateDao;
import com.neu.pojo.DialogRecord;
import com.neu.pojo.Patient;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;

/*
 * 医生员工服务类
 */

public class DoctorService implements IDoctorService {
	private IDoctorDao doctorDao;						//医生员工数据操作实例
	private IPatientDao patientDao;						//病人数据操作实例
	private IQuestionDao questionDao;					//问题数据操作
	private ITemplateDao templateDao;					//模板数据操作
	private IRecordDao recordDao;						//诊断记录数据操作
	private static IDoctorService instance;
	
	
	public static IDoctorService getInstance() {
		if(instance == null) {
			instance = new DoctorService();
		}
		return instance;
	}
	
	private DoctorService() {
		doctorDao = (IDoctorDao) MyTools.getObject("doctordao");
		patientDao = (IPatientDao) MyTools.getObject("patientdao");
		questionDao = (IQuestionDao) MyTools.getObject("questiondao");
		templateDao = (ITemplateDao) MyTools.getObject("templatedao");
		recordDao = (IRecordDao) MyTools.getObject("recorddao");
	}
	
	//添加一个问题
	@Override
	public boolean addQuestion(Question question) {
		for (Question question2 : questionDao.getQuestions()) {
			if(question2.getDetail().equals(question.getDetail())) {
				return false;
			}
		}
		questionDao.addQuestion(question);
		return true;
	}
	
	//在指定问题以ID列表查找病人
	@Override
	public Question findQuestionByID(int id,ArrayList<Question> questions) {
		Question find = null;
		for(Question question : questions) {
			if(question.getId() == id) {
				find = question;
				break;
			}
		}
		return find;
	}
	
	//删除问题并联动删除包含该问题的模板对该问题ID的记录
	@Override
	public void deleteQuestion(int quesID, ArrayList<Question> questions, ArrayList<Template> templates) {
		Question delete = questionDao.findByID(quesID, questions);
		ArrayList<Integer> temID = delete.getTempsID();
		for (Integer integer : temID) {
			templateDao.findByID(integer, templates).getQuesID().remove(Integer.valueOf(delete.getId()));
		}
		questions.remove(delete);
	}


	//添加一个模板
	@Override
	public boolean addTemplate(Template template) {
		for(Template template2 : templateDao.getTemplates()) {
			if(template2.getName().equals(template.getName())) {
				return false;
			}
		}
		templateDao.getTemplates().add(template);
		return true;
	}
	
	//删除问题联动删除模板中的关联
	@Override
	public void deletTemplate(int temID, ArrayList<Template> templates, ArrayList<Question> questions) {
		Template delete = null;
		//找出模板
		for(Template template : templates) {
			if(template.getId() == temID) {
				delete = template;
				break;
			}
		}
		//取得模板的问题ID记录并删除关联
		ArrayList<Integer> quesID = delete.getQuesID();
		for (Integer integer : quesID) {
			questionDao.findByID(integer, questions).getTempsID().remove(Integer.valueOf(delete.getId()));
		}
		templates.remove(delete);
	}
	
	//以模板名称查找模板
	@Override
	public Template findTemplateByName(String name, ArrayList<Template> templates) {
		for(Template template : templates) {
			if(template.getName().equals(name)) {
				return template;
			}
		}
		return null;
	}
	
	//在指定模板集合中以模板ID查查找模板
	@Override
	public Template findTemplateByID(int id, ArrayList<Template> templates) {
		for(Template template : templates) {
			if(template.getId()== id) {
				return template;
			}
		}
		return null;
	}
	
	//取得病人集合
	@Override
	public ArrayList<Patient> getPatients() {
		return patientDao.getPatients();
	}
	
	//取得问题集合
	@Override
	public ArrayList<Question> getQuestions() {
		return questionDao.getQuestions();
	}
	
	//取得模板集合
	@Override
	public ArrayList<Template> geTemplates() {
		return templateDao.getTemplates();
	}
	
	//取得诊断记录
	@Override
	public ArrayList<DialogRecord> getRecords() {
		return recordDao.getRecords();
	}
	
	//更新病人
	@Override
	public void setPatients(ArrayList<Patient> patients) {
		patientDao.setPatients(patients);
	}
	
	//更新问题
	@Override
	public void setQuestions(ArrayList<Question> questions) {
		questionDao.setQuestions(questions);
	}
	
	//更新模板
	@Override
	public void setTemplates(ArrayList<Template> templates) {
		templateDao.setTemplates(templates);
	}
	
	//更新评估记录
	@Override
	public void setRecords(ArrayList<DialogRecord> records) {
		recordDao.setRecords(records);
	}
	
	//保存问题
	@Override
	public void storeQuestions() {
		questionDao.store();
	}
	
	//保存病人
	@Override
	public void storePatients() {
		patientDao.store();
	}
	
	//保存模板
	@Override
	public void storeTemplate() {
		templateDao.store();
	}
	
	//保存评估记录
	@Override
	public void storeRecords() {
		recordDao.store();
	}
	
	//使用身份证查询病人是否存在
	@Override
	public boolean isPatientExistIdentity(String identity) {
		return patientDao.isExistIdentity(identity);
	}
	
	//保存医生
	@Override
	public void storeDoctors() {
		// TODO Auto-generated method stub
		doctorDao.store();
	}
	
	//使用ID记录取回问题集合
	@Override
	public ArrayList<Question> fromIntListToQuestionList(ArrayList<Integer> integers, ArrayList<Question> questions) {
		ArrayList<Question> result = new ArrayList<>();
		for(Integer integer : integers) {
			for(Question question : questions) {
				if(question.getId() == integer) {
					result.add(question);
					break;
				}
			}
		}
		return result;
	}
	
	//使用身份证号码查找病人
	@Override
	public Patient findPatientByIdentity(String identity) {
		Patient patient = null;
		for(Patient patient3 : patientDao.getPatients()) {
			if(patient3.getIdentity().equals(identity)) {
				patient = patient3;
				break;
			}
		}
		return patient;
	}
	
	//在指定集合中使用身份证号码查找病人
	@Override
	public Patient findPatientByIdentity(String identity,ArrayList<Patient> patients1) {
		Patient patient = null;
		for(Patient patient3 : patients1) {
			if(patient3.getIdentity().equals(identity)) {
				patient = patient3;
				break;
			}
		}
		return patient;
	}

	//使用身份证验证病人是否存在
	@Override
	public boolean isPatientExistIdentity(String identity, ArrayList<Patient> patients) {
		// TODO Auto-generated method stub
		boolean exist = false;
		for(Patient patient : patients) {
			if(patient.getIdentity().equals(identity)) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	//取得名字含该关键字的病人集合
	@Override
	public ArrayList<Patient> patientSearchByName(String name, ArrayList<Patient> patients1) {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		for(Patient patient : patients1) {
			if(patient.getName().contains(name)) {
				patients.add(patient);
			}
		}
		return patients;
	}
	
	
}












