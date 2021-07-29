package com.neu.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 嚼着麦草的男孩
 *模板实体类
 */

public class Template implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;							//模板名称
	private String type;							//模板类型
	private int id;									//模板ID
	public static int recordID = 1;					
	private ArrayList<Integer> quesID;				//模板所包含的问题的ID
	
	public Template() {}

	public Template(String name, String type, ArrayList<Integer> quesID) {
		super();
		this.name = name;
		this.type = type;
		this.quesID = quesID;
		recordID ++;
		this.id = recordID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getRecordID() {
		return recordID;
	}

	public static void setRecordID(int recordID) {
		Template.recordID = recordID;
	}

	public ArrayList<Integer> getQuesID() {
		return quesID;
	}

	public void setQuesID(ArrayList<Integer> quesID) {
		this.quesID = quesID;
	}
	
	
	
}
