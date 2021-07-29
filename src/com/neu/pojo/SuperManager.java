package com.neu.pojo;

import java.io.Serializable;

/**
 * 超级管理员实体类
 * @author 嚼着麦草的男孩
 *
 */
public class SuperManager implements Serializable{
	private static final long serialVersionUID = 1L;
	String username;					//用户名
	String password;					//密码
	public SuperManager() {}

	public SuperManager(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
