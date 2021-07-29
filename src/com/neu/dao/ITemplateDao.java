package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Template;

/**
 * ģ�����ݲ����ӿ�
 * @author ������ݵ��к�
 *
 */

public interface ITemplateDao {

	//���һ��ģ��
	boolean addTemplate(Template template);

	//ȡ��ģ���б�
	ArrayList<Template> getTemplates();

	//��ģ��ID����ģ��
	Template findByID(int id);

	//���ṩ��ģ�弯���в���ģ��
	Template findByID(int id, ArrayList<Template> templates);

	//����ģ��
	void setTemplates(ArrayList<Template> templates);

	//����ģ��
	void store();

}