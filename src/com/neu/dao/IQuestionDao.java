package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Question;

/**
 * 
 * @author 嚼着麦草的男孩
 *问题数据操作接口
 */

public interface IQuestionDao {

	//添加一个问题
	boolean addQuestion(Question question);

	//已问题的题目判断问题是否已经存在
	boolean isExist(String detail);

	//取得问题集合
	ArrayList<Question> getQuestions();

	//以问题ID取得问题集合
	Question findByID(int ID);

	//在给定问题集合中查找问题
	Question findByID(int ID, ArrayList<Question> questions);

	//更新问题集合
	void setQuestions(ArrayList<Question> questions);

	//删除一个问题
	boolean deleteQuestion(Question question);

	//使用ID删除一个问题
	boolean deleteQuestion(int id);
	
	//保存问题
	void store();

}