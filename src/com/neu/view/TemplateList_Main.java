package com.neu.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.neu.pojo.Doctor;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

/**
 * 模板列表界面
 * @author 嚼着麦草的男孩
 *
 */

public class TemplateList_Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JLabel doctorTip;
	JComboBox<String> templateComboBox;
	private static Doctor doctor;					///当前登陆的医生
	private static IDoctorService doctorService;	//员工服务
	private static Doctor_Main doctor_Main;			//上一界面
	private static ArrayList<Template> templates;	//模板列表
	private static ArrayList<Question> questions;	//问题列表

	/**
	 * Launch the application.
	 */
	public static void main(Doctor doctor1, Doctor_Main doctor_Main1 ) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctor = doctor1;
					doctor_Main = doctor_Main1;
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					templates = (ArrayList<Template>) MyTools.deepCopy(doctorService.geTemplates());
					questions = (ArrayList<Question>) MyTools.deepCopy(doctorService.getQuestions());
					TemplateList_Main frame = new TemplateList_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TemplateList_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		templateComboBox = new JComboBox<String>();
		templateComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "A", "B", "C", "D", "E"}));
		templateComboBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		templateComboBox.setBounds(38, 29, 140, 30);
		templateComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				seleteType();
			}
		});
		contentPane.add(templateComboBox);
		
		doctorTip = new JLabel(doctor.getPosition() +" " + doctor.getName());
		doctorTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		doctorTip.setBounds(578, 29, 164, 30);
		contentPane.add(doctorTip);
		
		JButton deleteButton = new JButton("\u5220 \u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		deleteButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		deleteButton.setBounds(38, 85, 100, 30);
		contentPane.add(deleteButton);
		
		JButton newTemplateButton = new JButton("\u65B0 \u5EFA \u6A21 \u677F");
		newTemplateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTemplate();
			}
		});
		newTemplateButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		newTemplateButton.setBounds(600, 85, 150, 30);
		contentPane.add(newTemplateButton);
		
		table = new JTable(new TamplateModel(templates));
		table.getColumn("预览").setCellRenderer(new ButtonRenderer());
        table.getColumn("预览").setCellEditor(new ButtonEditor());
		table.setBounds(38, 122, 710, 196);
		contentPane.add(table);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(38, 122, 710, 212);
		contentPane.add(js);
		
		JButton saveButton = new JButton("\u4FDD \u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		saveButton.setBounds(200, 350, 100, 30);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		backButton.setBounds(500, 350, 100, 30);
		contentPane.add(backButton);
		
		JButton questionListButton = new JButton("\u95EE \u9898 \u5217 \u8868");
		questionListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterQuestionList_Main();
			}
		});
		questionListButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		questionListButton.setBounds(432, 85, 150, 30);
		contentPane.add(questionListButton);
		
		JLabel templateListTip = new JLabel("\u6A21 \u677F \u5217 \u8868");
		templateListTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		templateListTip.setBounds(334, 20, 145, 42);
		contentPane.add(templateListTip);
		
	}
	
	/**
	 * 方法从这开始
	 */

	//前往问题列表
	private void enterQuestionList_Main() {
		QuestionList_Main.main(doctor,templates,questions,doctor_Main,this);
		this.setVisible(false);
	}
	
	//选择显示哪些类型的模板
	private void seleteType() {
		ArrayList<Template> templates2 = new ArrayList<>();
		String type = (String) templateComboBox.getSelectedItem();
		if(type.equals("全部")) {
			table.setModel(new TamplateModel(templates));
			table.getColumn("预览").setCellRenderer(new ButtonRenderer());
	        table.getColumn("预览").setCellEditor(new ButtonEditor());
		}else {
			for(Template template : templates) {
				if(template.getType().equals(type)) {
					templates2.add(template);
				}
			}
			if(!templates2.isEmpty()) {
				table.setModel(new TamplateModel(templates2));
				table.getColumn("预览").setCellRenderer(new ButtonRenderer());
		        table.getColumn("预览").setCellEditor(new ButtonEditor());
			}else {
				JOptionPane.showMessageDialog(null,
				          "未找到此类型的模板", "失败",
				          JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//修改数据后表格的数据的更新
	public void reinit () {
		try {
			templates = (ArrayList<Template>) MyTools.deepCopy(doctorService.geTemplates());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			questions = (ArrayList<Question>) MyTools.deepCopy(doctorService.getQuestions());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//保存数据
	private void save() {
		boolean result = true;
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Template template = doctorService.findTemplateByID((Integer)table.getValueAt(i, 1), templates);
			String name = (String)table.getValueAt(i, 2);
			String type = (String)table.getValueAt(i, 3);
			if(template != null && name.length() != 0 && type.length() != 0) {
				template.setName(name);
				template.setType(type);
			}else {
				result = false;
				break;
			}
		}
		if(result) {
			doctorService.setTemplates(templates);
			doctorService.setQuestions(questions);
			doctorService.storeQuestions();
			doctorService.storeTemplate();
			try {
				templates = (ArrayList<Template>) MyTools.deepCopy(templates);
				questions = (ArrayList<Question>) MyTools.deepCopy(questions);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
			          "已保存", "保存成功",
			          JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
			          "保存失败", "保存失败",
			          JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//删除模板
	private void delete() {
		for(int i = 0; i < table.getRowCount(); i++) {
			if((boolean)table.getValueAt(i, 0)) {
				doctorService.deletTemplate((int)table.getValueAt(i, 1), templates, questions);
			}
		}
		table.setModel(new TamplateModel(templates));
		buildButton();
		
		JOptionPane.showMessageDialog(null,
	            "删除成功,未保存", "删除成功",
	    JOptionPane.DEFAULT_OPTION);
	}
	
	//前往添加模板界面
	private void addTemplate() {
		AddTemplate.main(this, questions, templates);
		this.setVisible(false);
	}
	
	//重建按键
	public void buildButton() {
		table.getColumn("预览").setCellRenderer(new ButtonRenderer());
        table.getColumn("预览").setCellEditor(new ButtonEditor());
	}
	
	//更新表格数据
	public void updateTable() {
		// TODO Auto-generated method stub
		table.setModel(new TamplateModel(templates));
		buildButton();
	}
	
	//返回上一界面
	private void back() {
		doctor_Main.setVisible(false);
		doctor_Main.setVisible(true);
		this.dispose();
	}
	
	//前往模板预览界面
	private void gotoPreview(int row) {
		TemplatePreview.main(this,doctorService.findTemplateByID((int)table.getValueAt(row, 1), templates),templates,questions);
	}
	
	//保存问题更改
	public ArrayList<Question> saveForQuestionList_Mian() {
		doctorService.setQuestions(questions);
		doctorService.storeQuestions();
		try {
			questions = (ArrayList<Question>)MyTools.deepCopy(questions);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}
	
	//表格数据渲染
	public static class TamplateModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"选择","ID","模板","类型","预览"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Boolean.class, Integer.class, String.class, String.class, JButton.class};
        private Object[][] data;

        public TamplateModel(List<Template> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][5];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = Boolean.FALSE;	//复选框
    				data[i][1] = list.get(i).getId();
    				data[i][2] = list.get(i).getName();
    				data[i][3] = list.get(i).getType();
    			}
    		}else {
    			this.data = new Object[0][0];
    		}
        }
        
        @Override 
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override 
        public int getRowCount() {
            return data.length;
        }

        @Override 
        public String getColumnName(int columnIndex) {
            return COLUMN_NAMES[columnIndex];
        }

        @Override 
        public Class<?> getColumnClass(int columnIndex) {
            return COLUMN_TYPES[columnIndex];
        }

        @Override 
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
            if(columnIndex != 4) {
            	return data[rowIndex][columnIndex];
            }else {
            	final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        
                    }
                });
                return button;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 1) {
            	return false;
            }else {
            	return true;
            }
        }
        
      //修改表格中的数据
  		@Override
  		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  			if(columnIndex != 4) {
  				data[rowIndex][columnIndex] = aValue;
      			/*修改表格数据后，显示改后结果*/
      			fireTableCellUpdated(rowIndex, columnIndex);
  			}
  		}
      		
      	public Object[][] getData() {
      		return this.data;
      	}
      	
    }
    
    private class ButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("预览");
            return button;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonEditor() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("预览");
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                	gotoPreview(row);
                }
            });
            return button;
        }
    }

	
}
