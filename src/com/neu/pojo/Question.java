package com.neu.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 嚼着麦草的男孩
 *
 */
public class Question implements Serializable{
	private static final long serialVersionUID = 1L;
	private String detail;							//问题详情
	private String[] answer;						//问题答案，【0】-五分，【1】-三分，【2】-一分
	private int id;									//问题ID
	private String type;							//问题类型
	private ArrayList<Integer> tempsID;				//有此问题的模板的ID
	public static int recordID = 1000;				
	
	public Question() {}
	public Question(String detail, String[] answer, String type) {
		super();
		this.detail = detail;
		this.answer = answer;
		this.type = type;
		tempsID = new ArrayList<Integer>();
		recordID++;
		this.id = recordID;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Integer> getTempsID() {
		return tempsID;
	}
	public void setTempsID(ArrayList<Integer> tempsID) {
		this.tempsID = tempsID;
	}
	public static int getRecordID() {
		return recordID;
	}
	public static void setRecordID(int recordID) {
		Question.recordID = recordID;
	}
}
