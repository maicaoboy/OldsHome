package com.neu.pojo;

import java.io.Serializable;

/**
 * ��������Աʵ����
 * @author ������ݵ��к�
 *
 */
public class SuperManager implements Serializable{
	private static final long serialVersionUID = 1L;
	String username;					//�û���
	String password;					//����
	public SuperManager() {}

	public SuperManager(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
