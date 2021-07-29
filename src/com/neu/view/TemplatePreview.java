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
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author ������ݵ��к�
 *ģ��Ԥ������
 */

public class TemplatePreview extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static IDoctorService doctorService;					//Ա������
	private static TemplateList_Main templateList_Main;				//��һ����
	private static Template template;								//Ԥ��ģ��
	private static ArrayList<Template> templates;					//ģ�弯��
	private static ArrayList<Question> questions;					//���⼯��
	

	/**
	 * Launch the application.
	 */
	public static void main(TemplateList_Main templateList_Main1, Template template1, ArrayList<Template> templates1, ArrayList<Question> questions1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					templateList_Main = templateList_Main1;
					template = template1;
					templates = templates1;
					questions = questions1;
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					TemplatePreview frame = new TemplatePreview();
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
	public TemplatePreview() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel templateTip = new JLabel(template.getName());
		templateTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		templateTip.setBounds(35, 46, 116, 30);
		contentPane.add(templateTip);
		
		JLabel previewButtonTip = new JLabel("\u9884 \u89C8 \u6A21 \u677F");
		previewButtonTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 25));
		previewButtonTip.setBounds(321, 29, 142, 47);
		contentPane.add(previewButtonTip);
		
		table = new JTable(new TemplateTable(doctorService.fromIntListToQuestionList(template.getQuesID(), questions)));
		table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());   
		table.setBounds(100, 127, 600, 210);
		contentPane.add(table);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(100, 127, 600, 210);
		contentPane.add(js);
		
		JButton addQuestion = new JButton("\u6DFB \u52A0 \u95EE \u9898");
		addQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuestion();
			}
		});
		addQuestion.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		addQuestion.setBounds(584, 85, 116, 30);
		contentPane.add(addQuestion);
		
		JButton deleteTemplateButton = new JButton("\u5220 \u9664");
		deleteTemplateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		deleteTemplateButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		deleteTemplateButton.setBounds(186, 360, 100, 30);
		contentPane.add(deleteTemplateButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		backButton.setBounds(362, 360, 100, 30);
		contentPane.add(backButton);
		
		JButton saveButton = new JButton("\u4FDD \u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		saveButton.setBounds(542, 360, 100, 30);
		contentPane.add(saveButton);
	}
	
	//����ģ���޸�
	private void save() {
		doctorService.setQuestions(questions);
		doctorService.setTemplates(templates);
		doctorService.storeQuestions();
		doctorService.storeTemplate();
		JOptionPane.showMessageDialog(null,
				"����ɹ�","����ɹ�",
				JOptionPane.DEFAULT_OPTION);
		templateList_Main.reinit();
		back();
		}
	
	//������һ����
	private void back() {
		templateList_Main.setVisible(true);
		this.dispose();
	}
	
	//ɾ��ģ��
	private void delete() {
		for(int i = 0; i < table.getRowCount(); i++ ) {
			if((boolean)table.getValueAt(i, 0)) {
				Question question =  doctorService.findQuestionByID((int)table.getValueAt(i, 1), questions);
				question.getTempsID().remove(Integer.valueOf(template.getId()));
				template.getQuesID().remove(Integer.valueOf(question.getId()));
			}
		}
		table.setModel(new TemplateTable(doctorService.fromIntListToQuestionList(template.getQuesID(), questions)));
		JOptionPane.showMessageDialog(null,
				"ɾ���ɹ�,δ����","ɾ���ɹ�",
				JOptionPane.DEFAULT_OPTION);
	}
	
	//��ģ���������
	private void addQuestion() {
		AddQuestionForTemplatePreview.main(this,template,questions);
		this.setVisible(false);
	}
	
	
	
	//ģ����������Ⱦ
	public static class TemplateTable extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private String[] colNames = {"ѡ��","ID","��Ŀ"};
		private Object[][] data;
		private IManagerService managerService = (IManagerService) MyTools.getObject("managerservice");
		
		
		public TemplateTable(List<Question> list) {
			if(list != null && !list.isEmpty()) {
				data = new Object[list.size()][3];
				for (int i = 0; i < list.size(); i++) {
					data[i][0] = Boolean.FALSE;	//��ѡ��
					data[i][1] = list.get(i).getId();
					data[i][2] = list.get(i).getDetail();
				}
			}else {
				this.data = new Object[0][0];
			}
		}
		
		
		
		//���ؽ������
			@Override
			public int getRowCount() {
				return this.data.length;
			}

			//���ؽ������
			@Override
			public int getColumnCount() {
				return this.colNames.length;
			}

			//��ö�Ӧ����
			@Override
			public String getColumnName(int column) {
				return this.colNames[column];
			}

			//ÿ�ж�Ӧ������
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 0) {
					return Boolean.class;
				}else {
					return String.class;
				}
				//return data[0][columnIndex].getClass();
			}
			
			//�����������ж�Ӧ������
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return data[rowIndex][columnIndex];
			}
			
			/**
			 * ���в��ܱ༭
			 * ����false ���ɱ༭
			 */
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if(columnIndex == 1) {
					return false;
				}
				return true;
			}
			
			//�޸ı���е�����
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				data[rowIndex][columnIndex] = aValue;
				/*�޸ı�����ݺ���ʾ�ĺ���*/
				fireTableCellUpdated(rowIndex, columnIndex);
			}
			
			public Object[][] getData() {
				return data;
			}
			
			public void setData(Object[][] data) {
				this.data = data;
			}
			
	}
	//�����滻JTable��ÿ�����е�JTextArea
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
	            // ���㵱���е���Ѹ߶�   
	            int maxPreferredHeight = 0;   
	            for (int i = 0; i < table.getColumnCount(); i++) {   
	                setText("" + table.getValueAt(row, i));   
	                setSize(table.getColumnModel().getColumn(column).getWidth(), 0);   
	                maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);   
	            }   
	      
	            if (table.getRowHeight(row) != maxPreferredHeight)  // ��������������Ϲæ   
	                table.setRowHeight(row, maxPreferredHeight);   
	      
	            setText(value == null ? "" : value.toString());   
	            return this;   
	        }   
	    }
	 //���±��
	public void reload() {
		// TODO Auto-generated method stub
		table.setModel(new TemplateTable(doctorService.fromIntListToQuestionList(template.getQuesID(), questions)));
	}   
}
