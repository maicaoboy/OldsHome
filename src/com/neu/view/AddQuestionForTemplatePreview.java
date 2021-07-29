package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

/**
 * 
 * @author ������ݵ��к�
 *ģ��Ԥ��ʱ�������
 */

public class AddQuestionForTemplatePreview extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static TemplatePreview templatePreview;				//��һ����
	private static ArrayList<Question> questionUnadd;			//δ�������
	private static ArrayList<Question> questionAdded;			//���������
	private static ArrayList<Integer> questionAddedInt;			//���������ID
	private static ArrayList<Question> questions;				//���⼯��
	private static IDoctorService doctorService;				//Ա������
	private static Template template;							//��ǰԤ��ģ��

	/**
	 * Launch the application.
	 */
	public static void main(TemplatePreview templatePreview1, Template template1, ArrayList<Question> questions1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					templatePreview = templatePreview1;
					template = template1;
					questionAddedInt = new ArrayList<>();
					questions = (ArrayList<Question>) MyTools.deepCopy(questions1);
					questionAdded = doctorService.fromIntListToQuestionList(template.getQuesID(), questions);
					for(Question question2 : questionAdded) {
						questions.remove(question2);
					}
					questionUnadd = questions;
					AddQuestionForTemplatePreview frame = new AddQuestionForTemplatePreview();
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
	public AddQuestionForTemplatePreview() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u6DFB \u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuestion();
			}
		});
		btnNewButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		btnNewButton.setBounds(167, 367, 93, 30);
		contentPane.add(btnNewButton);
		
		table = new JTable(new QuestionModel(questionUnadd));
		table.setBounds(66, 101, 646, 220);
		contentPane.add(table);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(66, 101, 646, 220);
		contentPane.add(js);
		
		JLabel lblNewLabel = new JLabel("\u6DFB \u52A0 \u95EE \u9898");
		lblNewLabel.setFont(new Font("΢���ź� Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(317, 38, 164, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("\u8FD4 \u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToQuestionPreview();
			}
		});
		btnNewButton_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(510, 367, 93, 30);
		contentPane.add(btnNewButton_1);
	}
	
	
	
	
	//�ص���һ����
	private void backToQuestionPreview() {
		templatePreview.setVisible(true);
		this.dispose();	
	}
	
	//��Ӱ�ť���������ѡ�е�δ�������
	private void addQuestion() {
		for(int i = 0; i < table.getColumnCount(); i++) {
			if((boolean)table.getValueAt(i, 0)) {
				questionAdded.add(doctorService.findQuestionByID((int)table.getValueAt(i, 1), questionUnadd));
				doctorService.findQuestionByID((int)table.getValueAt(i, 1), questionUnadd).getTempsID().add(template.getId());
				questionUnadd.remove(doctorService.findQuestionByID((int)table.getValueAt(i, 1), questionUnadd));
				template.getQuesID().add(template.getId());
			}
		}
		for(Question question : questionAdded) {
			questionAddedInt.add(question.getId());
		}
		table.setModel(new QuestionModel(questionUnadd));
		template.setQuesID(questionAddedInt);
		JOptionPane.showMessageDialog(null,
				"��ӳɹ�������ģ���б���","��ӳɹ�",
				JOptionPane.DEFAULT_OPTION);
		templatePreview.reload();
		backToQuestionPreview();
	}

	
	
	
	
	
	
	
	
	
	public static class QuestionModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"ѡ��","ID","��Ŀ","����"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Boolean.class, Integer.class, String.class, String.class};
        private Object[][] data;

        public QuestionModel(List<Question> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][4];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = Boolean.FALSE;	//��ѡ��
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
        	return data[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 0) {
            	return true;
            }else {
            	return false;
            }
        }
        
      //�޸ı���е�����
  		@Override
  		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  			if(columnIndex == 0) {
  				data[rowIndex][columnIndex] = aValue;
      			/*�޸ı�����ݺ���ʾ�ĺ���*/
      			fireTableCellUpdated(rowIndex, columnIndex);
  			}
  		}
      		
      	public Object[][] getData() {
      		return this.data;
      	}
      	
    }
}
