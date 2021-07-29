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
 * @author 嚼着麦草的男孩
 *问题数据操作类
 */

public class QuestionDao implements IQuestionDao{
	private ArrayList<Question> questions;					//问题集合
	private static IQuestionDao instance;
	
	private QuestionDao() {
		questions = new ArrayList<Question>();
		try {
			//从文件取得问题，并恢复编号
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
	
	//添加一个问题
	@Override
	public boolean addQuestion(Question question) {
		return questions.add(question);
	}
	
	//以题目验证题目是否存在
	@Override
	public boolean isExist(String detail) {
		for (Question question : questions) {
			if(question.getDetail().equals(detail)) {
				return true;
			}
		}
		return false;
	}
	
	//取得问题集合
	@Override
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}
	
	//以ID取得问题
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
	
	//在指定问题集合中找到问题
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
	
	//更新问题集合
	@Override
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	//删除一个问题
	@Override
	public boolean deleteQuestion(Question question) {
		return questions.remove(question);
	}
	
	//以ID删除一个问题
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

	//保存问题到文件中
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











