package com.neu.service;

import java.util.ArrayList;

import com.neu.pojo.DialogRecord;
import com.neu.pojo.Patient;
import com.neu.pojo.Question;
import com.neu.pojo.Template;

/*
 * ҽ��Ա������ӿ�
 */

public interface IDoctorService {

	//���һ������
	boolean addQuestion(Question question);

	//��ָ��������ID�б���Ҳ���
	Question findQuestionByID(int id, ArrayList<Question> questions);

	//ɾ�����Ⲣ����ɾ�������������ģ��
	void deleteQuestion(int quesID, ArrayList<Question> questions, ArrayList<Template> templates);

	//���һ��ģ��
	boolean addTemplate(Template template);

	//ɾ����������ɾ��ģ���еĹ���
	void deletTemplate(int temID, ArrayList<Template> templates, ArrayList<Question> questions);

	//��ģ�����Ʋ���ģ��
	Template findTemplateByName(String name, ArrayList<Template> templates);

	//��ָ��ģ�弯������ģ��ID�����ģ��
	Template findTemplateByID(int id, ArrayList<Template> templates);

	//ȡ�ò��˼���
	ArrayList<Patient> getPatients();

	//ȡ�����⼯��
	ArrayList<Question> getQuestions();
	
	//ȡ��ģ�弯��
	ArrayList<Template> geTemplates();
	
	//ȡ����ϼ�¼
	ArrayList<DialogRecord> getRecords();
	
	//���²���
	void setPatients(ArrayList<Patient> patients);
	
	//��������
	void setQuestions(ArrayList<Question> questions);
	
	//����ģ��
	void setTemplates(ArrayList<Template> templates);
	
	//����������¼
	void setRecords(ArrayList<DialogRecord> records);
	
	//��������
	void storeQuestions();
	
	//���没��
	void storePatients();
	
	//����ģ��
	void storeTemplate();
	
	//����������¼
	void storeRecords();
	
	//ʹ�����֤��ѯ�����Ƿ����
	boolean isPatientExistIdentity(String identity);
	
	//����ҽ��
	void storeDoctors();
	
	//ʹ��ID��¼ȡ�����⼯��
	ArrayList<Question> fromIntListToQuestionList(ArrayList<Integer> integers, ArrayList<Question> questions);
	
	//ʹ�����֤������Ҳ���
	Patient findPatientByIdentity(String identity);
	
	//ʹ�����֤��֤�����Ƿ����
	boolean isPatientExistIdentity(String identity, ArrayList<Patient> patients);
	
	//ȡ�����ֺ��ùؼ��ֵĲ��˼���
	ArrayList<Patient> patientSearchByName(String name, ArrayList<Patient> patients1);

	//��ָ��������ʹ�����֤������Ҳ���
	Patient findPatientByIdentity(String identity, ArrayList<Patient> patients1);

}