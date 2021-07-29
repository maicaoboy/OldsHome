package com.neu.dao;

import java.util.ArrayList;

import com.neu.pojo.Template;

/**
 * 模板数据操作接口
 * @author 嚼着麦草的男孩
 *
 */

public interface ITemplateDao {

	//添加一个模板
	boolean addTemplate(Template template);

	//取得模板列表
	ArrayList<Template> getTemplates();

	//用模板ID查找模板
	Template findByID(int id);

	//在提供的模板集合中查找模板
	Template findByID(int id, ArrayList<Template> templates);

	//更新模板
	void setTemplates(ArrayList<Template> templates);

	//保存模板
	void store();

}