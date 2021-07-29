package com.neu.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import com.neu.pojo.Doctor;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author 嚼着麦草的男孩
 *问题列表
 */

public class QuestionList_Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<String> questionComboBox;
	private static Doctor doctor;							//当前登陆的医生
	private static IDoctorService doctorService;			//员工服务
	private static Doctor_Main doctor_Main;					//主界面
	private static TemplateList_Main templateList_Main;		//上一界面
	private static ArrayList<Template> templates;			//模板集合
	private static ArrayList<Question> questions;			//问题集合
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(Doctor doctor1, ArrayList<Template> templates1, ArrayList<Question> questions1, Doctor_Main doctor_Main1,TemplateList_Main templateList_Main1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctor = doctor1;
					templates = templates1;
					questions = questions1;
					doctor_Main = doctor_Main1;
					templateList_Main = templateList_Main1;
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					QuestionList_Main frame = new QuestionList_Main();
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
	public QuestionList_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		questionComboBox = new JComboBox<String>();
		questionComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "A", "B", "C", "D", "E"}));
		questionComboBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		questionComboBox.setBounds(32, 34, 140, 30);
		questionComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				seleteType();
			}
		});
		contentPane.add(questionComboBox);
		
		JLabel doctorTip = new JLabel(doctor.getPosition() +" " + doctor.getName());
		doctorTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		doctorTip.setBounds(550, 34, 164, 30);
		contentPane.add(doctorTip);
		
		JButton deleteButton = new JButton("\u5220 \u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteQuestion();
			}
		});
		deleteButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		deleteButton.setBounds(32, 93, 100, 30);
		contentPane.add(deleteButton);
		
		JButton newQuestionButton = new JButton("\u65B0 \u5EFA \u95EE \u9898");
		newQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuestion();
			}
		});
		newQuestionButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		newQuestionButton.setBounds(584, 93, 150, 30);
		contentPane.add(newQuestionButton);
		
		JButton saveButton = new JButton("\u4FDD \u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		saveButton.setBounds(202, 356, 100, 30);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		backButton.setBounds(461, 356, 100, 30);
		contentPane.add(backButton);
		
		table = new JTable(new QuestionModel(questions));
		//设置表格默认显示器
//		"JTable 里单元格内容的显示器是 TableCellRenderer。",   
//        "默认的显示器（DefaultTableCellRenderer） 继承 JLabel 所以不方便多行显示。",   
//        "要多行显示应该继承 JTextArea",   
//        "调用 JTable.setDefaultRenderer() 登记显示器。"}},    
		table.setDefaultRenderer(Object.class,new TableCellTextAreaRenderer());
		//设置按钮
		table.getColumn("详情").setCellRenderer(new ButtonRenderer());
        table.getColumn("详情").setCellEditor(new ButtonEditor());
		table.setBounds(32, 133, 702, 186);
		contentPane.add(table);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(32, 133, 702, 186);
		contentPane.add(js);
		
		JLabel questionListTip = new JLabel("\u95EE \u9898 \u5217 \u8868");
		questionListTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		questionListTip.setBounds(327, 22, 145, 42);
		contentPane.add(questionListTip);
	}
	
	//前往添加问题界面
	private void addQuestion() {
		AddQuestion.main(questions, this);
		this.setVisible(false);
	}
	
	//选择查看的问题的类型
	private void seleteType() {
		String type = (String)questionComboBox.getSelectedItem();
		ArrayList<Question> questions2 = new ArrayList<>();
		if(type.equals("全部")) {
			table.setModel(new QuestionModel(questions));
			table.getColumn("详情").setCellRenderer(new ButtonRenderer());
	        table.getColumn("详情").setCellEditor(new ButtonEditor());
		}else {
			for(Question question : questions) {
				if(question.getType().equals(type)) {
					questions2.add(question);
				}
			}
			if(questions2.isEmpty()) {
				JOptionPane.showMessageDialog(null,
				          "查找类型" + type + "失败", "失败",
				          JOptionPane.DEFAULT_OPTION);
			}else {
				table.setModel(new QuestionModel(questions2));
				table.getColumn("详情").setCellRenderer(new ButtonRenderer());
		        table.getColumn("详情").setCellEditor(new ButtonEditor());
			}
		}
	}
	
	//删除选中的题目
	private void deleteQuestion() {
		boolean set = false;
		for(int i = 0; i < table.getRowCount(); i++ ) {
			if((boolean)table.getValueAt(i, 0)) {
				doctorService.deleteQuestion((int)table.getValueAt(i, 1), questions, templates);
				set = true;
			}
		}
		if(set) {
			table.setModel(new QuestionModel(questions));
			table.getColumn("详情").setCellEditor(new QuestionList_Main.ButtonEditor());
			table.getColumn("详情").setCellRenderer(new QuestionList_Main.ButtonRenderer());
			JOptionPane.showMessageDialog(null,
					"删除成功","删除成功",
					JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
					"请先勾选要删除的问题","错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//取得表格，用于更新表格数据
	public JTable getTable() {
		return table;
	}
	
	//返回上一界面
	protected void back() {
		// TODO Auto-generated method stub
		templateList_Main.setVisible(true);
		this.dispose();
	}

	
    //更新表格数据后，重建按钮
    public void buildButton() {
    	table.getColumn("详情").setCellEditor(new QuestionList_Main.ButtonEditor());
		table.getColumn("详情").setCellRenderer(new QuestionList_Main.ButtonRenderer());
    }
    
    //前往问题详情界面
    private void gotoDetail(int row) {
    	QuestionDetail.main(this,doctorService.findQuestionByID((int)table.getValueAt(row, 1), questions), questions);
    }
    
    //保存问题修改
    private void save() {
    	questions = templateList_Main.saveForQuestionList_Mian();
    	JOptionPane.showMessageDialog(null,
    			"保存成功","保存成功",
    			JOptionPane.DEFAULT_OPTION);
    }


	//表格数据渲染
	public static class QuestionModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"选择","ID","题目","类型","详情"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Boolean.class, Integer.class, String.class, String.class, JButton.class};
        private Object[][] data;

        public QuestionModel(List<Question> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][5];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = Boolean.FALSE;	//复选框
    				data[i][1] = list.get(i).getId();
    				data[i][2] = list.get(i).getDetail();
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
            	//设置按钮
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
            button.setText("详情");
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
            button.setText("详情");
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                	gotoDetail(row);
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
