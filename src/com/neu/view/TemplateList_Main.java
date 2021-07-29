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
 * ģ���б����
 * @author ������ݵ��к�
 *
 */

public class TemplateList_Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JLabel doctorTip;
	JComboBox<String> templateComboBox;
	private static Doctor doctor;					///��ǰ��½��ҽ��
	private static IDoctorService doctorService;	//Ա������
	private static Doctor_Main doctor_Main;			//��һ����
	private static ArrayList<Template> templates;	//ģ���б�
	private static ArrayList<Question> questions;	//�����б�

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
		templateComboBox.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
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
		doctorTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		doctorTip.setBounds(578, 29, 164, 30);
		contentPane.add(doctorTip);
		
		JButton deleteButton = new JButton("\u5220 \u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		deleteButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		deleteButton.setBounds(38, 85, 100, 30);
		contentPane.add(deleteButton);
		
		JButton newTemplateButton = new JButton("\u65B0 \u5EFA \u6A21 \u677F");
		newTemplateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTemplate();
			}
		});
		newTemplateButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		newTemplateButton.setBounds(600, 85, 150, 30);
		contentPane.add(newTemplateButton);
		
		table = new JTable(new TamplateModel(templates));
		table.getColumn("Ԥ��").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ԥ��").setCellEditor(new ButtonEditor());
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
		saveButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		saveButton.setBounds(200, 350, 100, 30);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		backButton.setBounds(500, 350, 100, 30);
		contentPane.add(backButton);
		
		JButton questionListButton = new JButton("\u95EE \u9898 \u5217 \u8868");
		questionListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterQuestionList_Main();
			}
		});
		questionListButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		questionListButton.setBounds(432, 85, 150, 30);
		contentPane.add(questionListButton);
		
		JLabel templateListTip = new JLabel("\u6A21 \u677F \u5217 \u8868");
		templateListTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 25));
		templateListTip.setBounds(334, 20, 145, 42);
		contentPane.add(templateListTip);
		
	}
	
	/**
	 * �������⿪ʼ
	 */

	//ǰ�������б�
	private void enterQuestionList_Main() {
		QuestionList_Main.main(doctor,templates,questions,doctor_Main,this);
		this.setVisible(false);
	}
	
	//ѡ����ʾ��Щ���͵�ģ��
	private void seleteType() {
		ArrayList<Template> templates2 = new ArrayList<>();
		String type = (String) templateComboBox.getSelectedItem();
		if(type.equals("ȫ��")) {
			table.setModel(new TamplateModel(templates));
			table.getColumn("Ԥ��").setCellRenderer(new ButtonRenderer());
	        table.getColumn("Ԥ��").setCellEditor(new ButtonEditor());
		}else {
			for(Template template : templates) {
				if(template.getType().equals(type)) {
					templates2.add(template);
				}
			}
			if(!templates2.isEmpty()) {
				table.setModel(new TamplateModel(templates2));
				table.getColumn("Ԥ��").setCellRenderer(new ButtonRenderer());
		        table.getColumn("Ԥ��").setCellEditor(new ButtonEditor());
			}else {
				JOptionPane.showMessageDialog(null,
				          "δ�ҵ������͵�ģ��", "ʧ��",
				          JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//�޸����ݺ�������ݵĸ���
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
	
	//��������
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
			          "�ѱ���", "����ɹ�",
			          JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
			          "����ʧ��", "����ʧ��",
			          JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//ɾ��ģ��
	private void delete() {
		for(int i = 0; i < table.getRowCount(); i++) {
			if((boolean)table.getValueAt(i, 0)) {
				doctorService.deletTemplate((int)table.getValueAt(i, 1), templates, questions);
			}
		}
		table.setModel(new TamplateModel(templates));
		buildButton();
		
		JOptionPane.showMessageDialog(null,
	            "ɾ���ɹ�,δ����", "ɾ���ɹ�",
	    JOptionPane.DEFAULT_OPTION);
	}
	
	//ǰ�����ģ�����
	private void addTemplate() {
		AddTemplate.main(this, questions, templates);
		this.setVisible(false);
	}
	
	//�ؽ�����
	public void buildButton() {
		table.getColumn("Ԥ��").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ԥ��").setCellEditor(new ButtonEditor());
	}
	
	//���±������
	public void updateTable() {
		// TODO Auto-generated method stub
		table.setModel(new TamplateModel(templates));
		buildButton();
	}
	
	//������һ����
	private void back() {
		doctor_Main.setVisible(false);
		doctor_Main.setVisible(true);
		this.dispose();
	}
	
	//ǰ��ģ��Ԥ������
	private void gotoPreview(int row) {
		TemplatePreview.main(this,doctorService.findTemplateByID((int)table.getValueAt(row, 1), templates),templates,questions);
	}
	
	//�����������
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
	
	//���������Ⱦ
	public static class TamplateModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"ѡ��","ID","ģ��","����","Ԥ��"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Boolean.class, Integer.class, String.class, String.class, JButton.class};
        private Object[][] data;

        public TamplateModel(List<Template> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][5];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = Boolean.FALSE;	//��ѡ��
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
        
      //�޸ı���е�����
  		@Override
  		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  			if(columnIndex != 4) {
  				data[rowIndex][columnIndex] = aValue;
      			/*�޸ı�����ݺ���ʾ�ĺ���*/
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
            button.setText("Ԥ��");
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
            button.setText("Ԥ��");
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
