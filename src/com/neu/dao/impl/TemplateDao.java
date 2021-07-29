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
 * @author ������ݵ��к�
 *ģ�����ݲ�����
 */

public class TemplateDao implements Serializable, ITemplateDao{
	private static final long serialVersionUID = 1L;
	private ArrayList<Template> templates;				//ģ�弯��
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
            	//���ļ��ָ�ģ�弰��ID���
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
	
	//���һ��ģ��
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
	
	//ȡ��ģ�弯��
	@Override
	public ArrayList<Template> getTemplates() {
		return templates;
	}
	
	//��IDȥ�Yģ��
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
	
	///��ָ��ģ�弯������ID����ģ��
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
	
	//����ģ�弯��
	@Override
	public void setTemplates(ArrayList<Template> templates) {
		this.templates = templates;
	}

	//����ģ�弯��
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
