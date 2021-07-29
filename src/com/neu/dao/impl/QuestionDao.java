package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.dao.IQuestionDao;
import com.neu.pojo.Question;

/**
 * 
 * @author ������ݵ��к�
 *�������ݲ�����
 */

public class QuestionDao implements IQuestionDao{
	private ArrayList<Question> questions;					//���⼯��
	private static IQuestionDao instance;
	
	private QuestionDao() {
		questions = new ArrayList<Question>();
		try {
			//���ļ�ȡ�����⣬���ָ����
            File file = new File("Questions.json");
            if(file.exists()) {
                ObjectMapper om = new ObjectMapper();
                questions = om.readValue(file,new TypeReference<List<Question>>(){});
                int maxid = 0;
                for (Question question : questions) {
					if(question.getId() > maxid) {
						maxid = question.getId();
					}
				}
                Question.recordID = maxid;
            }
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	public static IQuestionDao getInstance() {
		if(instance == null) {
			instance = new QuestionDao();
		}
		return instance;
	}
	
	//���һ������
	@Override
	public boolean addQuestion(Question question) {
		return questions.add(question);
	}
	
	//����Ŀ��֤��Ŀ�Ƿ����
	@Override
	public boolean isExist(String detail) {
		for (Question question : questions) {
			if(question.getDetail().equals(detail)) {
				return true;
			}
		}
		return false;
	}
	
	//ȡ�����⼯��
	@Override
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}
	
	//��IDȡ������
	@Override
	public Question findByID(int ID) {
		Question find = null;
		for(Question question : questions) {
			if(ID == question.getId()) {
				find = question;
				break;
			}
		}
		return find;
	}
	
	//��ָ�����⼯�����ҵ�����
	@Override
	public Question findByID(int ID, ArrayList<Question> questions) {
		Question find = null;
		for(Question question : questions) {
			if(ID == question.getId()) {
				find = question;
				break;
			}
		}
		return find;
	}
	
	//�������⼯��
	@Override
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	//ɾ��һ������
	@Override
	public boolean deleteQuestion(Question question) {
		return questions.remove(question);
	}
	
	//��IDɾ��һ������
	@Override
	public boolean deleteQuestion(int id) {
		Boolean delete = false;
		for(Question question : questions) {
			if(question.getId() == id) {
				questions.remove(question);
				delete = true;
				break;
			}
		}
		return delete;
	}

	//�������⵽�ļ���
	@Override
	public void store() {
        try {
            File file = new File("Questions.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,questions);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}











