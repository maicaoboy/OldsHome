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
 * ҽ��Ա��������
 */

public class DoctorService implements IDoctorService {
	private IDoctorDao doctorDao;						//ҽ��Ա�����ݲ���ʵ��
	private IPatientDao patientDao;						//�������ݲ���ʵ��
	private IQuestionDao questionDao;					//�������ݲ���
	private ITemplateDao templateDao;					//ģ�����ݲ���
	private IRecordDao recordDao;						//��ϼ�¼���ݲ���
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
	
	//���һ������
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
	
	//��ָ��������ID�б���Ҳ���
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
	
	//ɾ�����Ⲣ����ɾ�������������ģ��Ը�����ID�ļ�¼
	@Override
	public void deleteQuestion(int quesID, ArrayList<Question> questions, ArrayList<Template> templates) {
		Question delete = questionDao.findByID(quesID, questions);
		ArrayList<Integer> temID = delete.getTempsID();
		for (Integer integer : temID) {
			templateDao.findByID(integer, templates).getQuesID().remove(Integer.valueOf(delete.getId()));
		}
		questions.remove(delete);
	}


	//���һ��ģ��
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
	
	//ɾ����������ɾ��ģ���еĹ���
	@Override
	public void deletTemplate(int temID, ArrayList<Template> templates, ArrayList<Question> questions) {
		Template delete = null;
		//�ҳ�ģ��
		for(Template template : templates) {
			if(template.getId() == temID) {
				delete = template;
				break;
			}
		}
		//ȡ��ģ�������ID��¼��ɾ������
		ArrayList<Integer> quesID = delete.getQuesID();
		for (Integer integer : quesID) {
			questionDao.findByID(integer, questions).getTempsID().remove(Integer.valueOf(delete.getId()));
		}
		templates.remove(delete);
	}
	
	//��ģ�����Ʋ���ģ��
	@Override
	public Template findTemplateByName(String name, ArrayList<Template> templates) {
		for(Template template : templates) {
			if(template.getName().equals(name)) {
				return template;
			}
		}
		return null;
	}
	
	//��ָ��ģ�弯������ģ��ID�����ģ��
	@Override
	public Template findTemplateByID(int id, ArrayList<Template> templates) {
		for(Template template : templates) {
			if(template.getId()== id) {
				return template;
			}
		}
		return null;
	}
	
	//ȡ�ò��˼���
	@Override
	public ArrayList<Patient> getPatients() {
		return patientDao.getPatients();
	}
	
	//ȡ�����⼯��
	@Override
	public ArrayList<Question> getQuestions() {
		return questionDao.getQuestions();
	}
	
	//ȡ��ģ�弯��
	@Override
	public ArrayList<Template> geTemplates() {
		return templateDao.getTemplates();
	}
	
	//ȡ����ϼ�¼
	@Override
	public ArrayList<DialogRecord> getRecords() {
		return recordDao.getRecords();
	}
	
	//���²���
	@Override
	public void setPatients(ArrayList<Patient> patients) {
		patientDao.setPatients(patients);
	}
	
	//��������
	@Override
	public void setQuestions(ArrayList<Question> questions) {
		questionDao.setQuestions(questions);
	}
	
	//����ģ��
	@Override
	public void setTemplates(ArrayList<Template> templates) {
		templateDao.setTemplates(templates);
	}
	
	//����������¼
	@Override
	public void setRecords(ArrayList<DialogRecord> records) {
		recordDao.setRecords(records);
	}
	
	//��������
	@Override
	public void storeQuestions() {
		questionDao.store();
	}
	
	//���没��
	@Override
	public void storePatients() {
		patientDao.store();
	}
	
	//����ģ��
	@Override
	public void storeTemplate() {
		templateDao.store();
	}
	
	//����������¼
	@Override
	public void storeRecords() {
		recordDao.store();
	}
	
	//ʹ�����֤��ѯ�����Ƿ����
	@Override
	public boolean isPatientExistIdentity(String identity) {
		return patientDao.isExistIdentity(identity);
	}
	
	//����ҽ��
	@Override
	public void storeDoctors() {
		// TODO Auto-generated method stub
		doctorDao.store();
	}
	
	//ʹ��ID��¼ȡ�����⼯��
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
	
	//ʹ�����֤������Ҳ���
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
	
	//��ָ��������ʹ�����֤������Ҳ���
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

	//ʹ�����֤��֤�����Ƿ����
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

	//ȡ�����ֺ��ùؼ��ֵĲ��˼���
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












