package com.neu.view;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 嚼着麦草的男孩
 *添加模板界面
 */

public class AddTemplate extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameInput;
	private JTextField typeInput;
	private JTable table_Added;
	private JTable table_Unadd;
	private static TemplateList_Main templateList_Main;			//上一界面
	private static ArrayList<Question> questions;				//所有问题集合
	private static ArrayList<Template> templates;				//模板集合
	private static ArrayList<Question> questionsAdded;			//已添加问题集合
	private static ArrayList<Question> questionsUnadd;			//未添加问题集合
	private static ArrayList<Integer> questionsAddedInt;		//已添加问题ID集合
	private static ArrayList<Integer> questionsUnaddInt;		//未添加问题ID集合
	private static IDoctorService doctorService;				//员工服务

	/**
	 * Launch the application.
	 */
	public static void main(TemplateList_Main templateList_Main1, ArrayList<Question> questions1, ArrayList<Template> templates1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					questionsAddedInt = new ArrayList<>();
					questionsUnaddInt = new ArrayList<>();
					questions = questions1;
					templates = templates1;
					questionsUnadd = (ArrayList<Question>)MyTools.deepCopy(questions);
					questionsAdded = new ArrayList<Question>();
					for(Question question : questions1) {
						questionsUnaddInt.add(question.getId());
					}
					doctorService = (IDoctorService)MyTools.getObject("doctorservice");
					templateList_Main = templateList_Main1;
					questionsAdded = (ArrayList<Question>)MyTools.deepCopy(questionsAdded);
					AddTemplate frame = new AddTemplate();
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
	public AddTemplate() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel addTemplateTip = new JLabel("\u6DFB \u52A0 \u6A21 \u677F");
		addTemplateTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		addTemplateTip.setBounds(314, 10, 145, 42);
		contentPane.add(addTemplateTip);
		
		JLabel nameInputTip = new JLabel("\u6A21 \u677F \u540D \u79F0");
		nameInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		nameInputTip.setBounds(233, 69, 100, 30);
		contentPane.add(nameInputTip);
		
		JLabel IDTip = new JLabel("\u6A21 \u677F ID");
		IDTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		IDTip.setBounds(26, 69, 117, 30);
		contentPane.add(IDTip);
		
		JLabel typeInputTip = new JLabel("\u6A21 \u677F \u7C7B \u578B");
		typeInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		typeInputTip.setBounds(503, 69, 100, 30);
		contentPane.add(typeInputTip);
		
		nameInput = new JTextField();
		nameInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		nameInput.setColumns(10);
		nameInput.setBounds(343, 69, 150, 30);
		contentPane.add(nameInput);
		
		typeInput = new JTextField();
		typeInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		typeInput.setColumns(10);
		typeInput.setBounds(613, 69, 150, 30);
		contentPane.add(typeInput);
		
		JLabel showIDLable = new JLabel("ID " + (Template.getRecordID() + 1));
		showIDLable.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		showIDLable.setBounds(108, 69, 94, 30);
		contentPane.add(showIDLable);
		
		JButton addtemplate = new JButton("\u6DFB \u52A0 \u6A21 \u677F");
		addtemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addATemplate();
			}
		});
		addtemplate.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		addtemplate.setBounds(203, 361, 135, 30);
		contentPane.add(addtemplate);
		
		JButton back = new JButton("\u8FD4 \u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToTemplateList_Main();
			}
		});
		back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		back.setBounds(429, 361, 135, 30);
		contentPane.add(back);
		
		table_Added = new JTable(new QuestionModel_Added(questionsAdded));
		table_Added.setDefaultRenderer(Object.class,new TableCellTextAreaRenderer());
		table_Added.getColumn("移除").setCellRenderer(new ButtonRenderer_Added());
        table_Added.getColumn("移除").setCellEditor(new ButtonEditor_Added());
		table_Added.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		table_Added.setBounds(10, 149, 375, 188);
		contentPane.add(table_Added);
		
		JScrollPane js1 = new JScrollPane(table_Added);
		js1.setBounds(10, 149, 375, 188);
		contentPane.add(js1);
		
		table_Unadd = new JTable(new QuestionModel_Unadd(questionsUnadd));
		table_Unadd.setDefaultRenderer(Object.class,new TableCellTextAreaRenderer());
		table_Unadd.getColumn("添加").setCellRenderer(new ButtonRenderer_Unadd());
        table_Unadd.getColumn("添加").setCellEditor(new ButtonEditor_Unadd());
		table_Unadd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		table_Unadd.setBounds(399, 149, 375, 188);
		contentPane.add(table_Unadd);
		
		JScrollPane js2 = new JScrollPane(table_Unadd);
		js2.setBounds(399, 149, 375, 188);
		contentPane.add(js2);
		
		JLabel addedTip = new JLabel("\u5DF2 \u6DFB \u52A0 \u95EE \u9898");
		addedTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		addedTip.setBounds(118, 109, 140, 30);
		contentPane.add(addedTip);
		
		JLabel unaddTip = new JLabel("\u672A \u6DFB \u52A0 \u95EE \u9898");
		unaddTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		unaddTip.setBounds(513, 109, 140, 30);
		contentPane.add(unaddTip);
	}
	
	//回到上一界面
	private void backToTemplateList_Main() {
		templateList_Main.setVisible(true);
		this.dispose();
	}
	
	//添加按钮操作，确认数据添加新模板
	private void addATemplate() {
		String name = nameInput.getText();
		String type = nameInput.getText();
		if(name.length() != 0 && type.length() != 0) {
			if(questionsAdded.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"请添加问题后重试","添加失败",
						JOptionPane.ERROR_MESSAGE);
			}else {
				for(Question question : questionsAdded) {
					questionsAddedInt.add(Integer.valueOf(question.getId()));
				}
				Template template = new Template(name, type,questionsAddedInt);
				templates.add(template);
				templateList_Main.updateTable();
				JOptionPane.showMessageDialog(null,
						"添加成功，请返回模板列表保存","添加成功",
						JOptionPane.DEFAULT_OPTION);
			}
		}else {
			JOptionPane.showMessageDialog(null,
					"填写完整名称和类型","添加失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//表格按钮
	public static class QuestionModel_Added extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"ID","题目","类型","移除"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class, String.class, JButton.class};
        private Object[][] data;

        public QuestionModel_Added(List<Question> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][4];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = list.get(i).getId();
    				data[i][1] = list.get(i).getDetail();
    				data[i][2] = list.get(i).getType();
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
            if(columnIndex != 3) {
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
            if(columnIndex == 3) {
            	return true;
            }else {
            	return false;
            }
        }
      		
      	public Object[][] getData() {
      		return this.data;
      	}
      	
    }
    
	//
    private class ButtonRenderer_Added implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("移除");
            return button;
        }
    }

    private class ButtonEditor_Added extends DefaultCellEditor {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonEditor_Added() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("移除");
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                	Question question = doctorService.findQuestionByID((int)table_Added.getValueAt(row, 0), questionsAdded);
                	questionsAdded.remove(question);
                	questionsUnadd.add(question);
                	buildButton();
                }
            });
            return button;
        }
    }
    
    //重新加载按钮
    public void buildButton() {
    	table_Unadd.setModel(new QuestionModel_Unadd(questionsUnadd));
    	table_Added.setModel(new QuestionModel_Added(questionsAdded));
    	table_Unadd.getColumn("添加").setCellEditor(new AddTemplate.ButtonEditor_Unadd());
		table_Unadd.getColumn("添加").setCellRenderer(new AddTemplate.ButtonRenderer_Unadd());
		table_Added.getColumn("移除").setCellEditor(new AddTemplate.ButtonEditor_Added());
		table_Added.getColumn("移除").setCellRenderer(new AddTemplate.ButtonRenderer_Added());
    }
    
    
    
    
    public static class QuestionModel_Unadd extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"ID","题目","类型","添加"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class, JButton.class};
        private Object[][] data;

        public QuestionModel_Unadd(List<Question> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][4];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = list.get(i).getId();
    				data[i][1] = list.get(i).getDetail();
    				data[i][2] = list.get(i).getType();
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
            if(columnIndex != 3) {
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
            if(columnIndex == 3) {
            	return true;
            }else {
            	return false;
            }
        }
      		
      	public Object[][] getData() {
      		return this.data;
      	}
      	
    }
    
    private class ButtonRenderer_Unadd implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("添加");
            return button;
        }
    }

    private class ButtonEditor_Unadd extends DefaultCellEditor {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonEditor_Unadd() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("添加");
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                	Question question = doctorService.findQuestionByID((int)table_Unadd.getValueAt(row, 0), questionsUnadd);
                	questionsAdded.add(question);
                	questionsUnadd.remove(question);
                	buildButton();
                }
            });
            return button;
        }
    }
    
    private class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {   
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TableCellTextAreaRenderer() {   
            setLineWrap(true);     
            setWrapStyleWord(true);   
        }   
      
        public Component getTableCellRendererComponent(JTable table, Object value,   
                boolean isSelected, boolean hasFocus, int row, int column) {   
            // 计算当下行的最佳高度   
            int maxPreferredHeight = 0;   
            for (int i = 0; i < table.getColumnCount() - 1; i++) {   
                setText("" + table.getValueAt(row, i));   
                setSize(table.getColumnModel().getColumn(column).getWidth(), 0);   
                maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);   
            }   
      
            if (table.getRowHeight(row) != maxPreferredHeight)  // 少了这行则处理器瞎忙   
                table.setRowHeight(row, maxPreferredHeight);   
      
            setText(value == null ? "" : value.toString());   
            return this;   
        }   
    }   
    
   
}
