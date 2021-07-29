package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Question;

/**
 * 
 * @author ������ݵ��к�
 *�������ݲ����ӿ�
 */

public interface IQuestionDao {

	//���һ������
	boolean addQuestion(Question question);

	//���������Ŀ�ж������Ƿ��Ѿ�����
	boolean isExist(String detail);

	//ȡ�����⼯��
	ArrayList<Question> getQuestions();

	//������IDȡ�����⼯��
	Question findByID(int ID);

	//�ڸ������⼯���в�������
	Question findByID(int ID, ArrayList<Question> questions);

	//�������⼯��
	void setQuestions(ArrayList<Question> questions);

	//ɾ��һ������
	boolean deleteQuestion(Question question);

	//ʹ��IDɾ��һ������
	boolean deleteQuestion(int id);
	
	//��������
	void store();

}