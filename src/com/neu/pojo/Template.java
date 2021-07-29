package com.neu.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author ������ݵ��к�
 *ģ��ʵ����
 */

public class Template implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;							//ģ������
	private String type;							//ģ������
	private int id;									//ģ��ID
	public static int recordID = 1;					
	private ArrayList<Integer> quesID;				//ģ���������������ID
	
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
