package com.neu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.dao.ITemplateDao;
import com.neu.pojo.Template;

/**
 * 
 * @author 嚼着麦草的男孩
 *模板数据操作类
 */

public class TemplateDao implements Serializable, ITemplateDao{
	private static final long serialVersionUID = 1L;
	private ArrayList<Template> templates;				//模板集合
	private static ITemplateDao instance;
	
	public static ITemplateDao getInstance() {
		if(instance == null) {
			instance = new TemplateDao();
		}
		return instance;
	}

	private TemplateDao() {
		templates = new ArrayList<Template>();
		try {
            File file = new File("Templates.json");
            if(file.exists()) {
            	//从文件恢复模板及其ID编号
                ObjectMapper om = new ObjectMapper();
                templates = om.readValue(file,new TypeReference<List<Template>>(){});
            }
            int maxID = 0;
            for (Template template : templates) {
				if(template.getId() > maxID) {
					maxID = template.getId();
				}
			}
            Template.recordID = maxID;
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	//添加一个模板
	@Override
	public boolean addTemplate(Template template) {
		for (Template template1 : templates) {
			if(template1.getName().equals(template.getName())) {
				return false;
			}
		}
		templates.add(template);
		return true;
	}
	
	//取得模板集合
	@Override
	public ArrayList<Template> getTemplates() {
		return templates;
	}
	
	//以ID去扽模板
	@Override
	public Template findByID(int id) {
		Template find = null;
		for (Template template : templates) {
			if(template.getId() == id) {
				find = template;
			}
		}
		return find;
	}
	
	///在指定模板集合中以ID查找模板
	@Override
	public Template findByID(int id, ArrayList<Template> templates) {
		Template find = null;
		for (Template template : templates) {
			if(template.getId() == id) {
				find = template;
			}
		}
		return find;
	}
	
	//更新模板集合
	@Override
	public void setTemplates(ArrayList<Template> templates) {
		this.templates = templates;
	}

	//保存模板集合
	@Override
	public void store() {
        try {
            File file = new File("Templates.json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(file,templates);

        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
