package com.neu.view;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.w3c.dom.ls.LSOutput;

import com.neu.pojo.Doctor;
import com.neu.pojo.Patient;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * 
 * @author ������ݵ��к�
 *ҽ����½�ɹ����������
 */

public class Doctor_Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameInputTextField;
	JLabel doctorTip;
	private JTable table;
	private static IDoctorService doctorService;			//Ա������
	private static Doctor doctor;							//��½��ҽ��
	private static ArrayList<Patient> patients;				//���˼���
	private static Login login;								//��½����

	/**
	 * Launch the application.
	 */
	
	public static void main(Doctor doctor1, Login login1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						doctorService = (IDoctorService) MyTools.getObject("doctorservice");
						patients = (ArrayList<Patient>) MyTools.deepCopy(doctorService.getPatients());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					doctor = doctor1;
					login = login1;
					Doctor_Main frame = new Doctor_Main();
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
	public Doctor_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		doctorTip = new JLabel(doctor.getPosition() +" " + doctor.getName());
		doctorTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		doctorTip.setBounds(489, 25, 125, 26);
		contentPane.add(doctorTip);
		
		JButton modifyInfomation = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		modifyInfomation.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		modifyInfomation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyInfo();
			}
		});
		modifyInfomation.setBounds(620, 25, 130, 25);
		contentPane.add(modifyInfomation);
		
		nameInputTextField = new JTextField();
		nameInputTextField.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		nameInputTextField.setBounds(50, 25, 132, 25);
		contentPane.add(nameInputTextField);
		nameInputTextField.setColumns(10);
		
		JButton nameSearchButton = new JButton("\u59D3\u540D\u67E5\u8BE2");
		nameSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameSearch();
			}
		});
		nameSearchButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		nameSearchButton.setBounds(200, 25, 102, 25);
		contentPane.add(nameSearchButton);
		
		JButton deleteButton = new JButton("\u5220 \u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelectedPatient();
			}
		});
		deleteButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		deleteButton.setBounds(50, 85, 100, 25);
		contentPane.add(deleteButton);
		
		JButton templateList = new JButton("\u6A21\u677F\u5217\u8868");
		templateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoTemplateList_Main();
			}
		});
		templateList.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		templateList.setBounds(527, 85, 100, 25);
		contentPane.add(templateList);
		
		JButton addUserButton = new JButton("\u6DFB\u52A0\u7528\u6237");
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPatient();
			}
		});
		addUserButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		addUserButton.setBounds(650, 85, 100, 25);
		contentPane.add(addUserButton);
		
		table = new JTable(new PatientModel(patients));
		table.setDefaultRenderer(Object.class,new TableCellTextAreaRenderer());
        table.getColumn("����").setCellRenderer(new ButtonRenderer());
        table.getColumn("����").setCellEditor(new ButtonEditor());
		table.setFont(new Font("΢���ź� Light", Font.PLAIN, 12));
		table.setBounds(50, 129, 700, 201);
		contentPane.add(table);
        
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 129, 700, 201);
		contentPane.add(js);
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setBounds(200, 359, 100, 30);
		contentPane.add(saveButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnNewButton_1.setBounds(500, 359, 100, 30);
		contentPane.add(btnNewButton_1);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		resetButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		resetButton.setBounds(202, 85, 100, 25);
		contentPane.add(resetButton);
		
		JButton livingManage = new JButton("\u4F4F\u623F\u7BA1\u7406");
		livingManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoLivingManage();
			}
		});
		livingManage.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		livingManage.setBounds(362, 85, 100, 25);
		contentPane.add(livingManage);
	}
	
	//ǰ��ס���������
	public void gotoLivingManage() {
		LivingManage_Main.main(this);
	}
	
	//�޸ĸ�����Ϣ��ť
	private void modifyInfo() {
		this.setVisible(false);
		EditPersonalInfo.main(doctor,this);
	}
	
	//���ñ����Ϣ
	protected void reset() {
		// TODO Auto-generated method stub
		table.setModel(new PatientModel(doctorService.getPatients()));
		table.getColumn("����").setCellRenderer(new ButtonRenderer());
        table.getColumn("����").setCellEditor(new ButtonEditor());
	}


	//������������
	private void nameSearch() {
		// TODO Auto-generated method stub
		String name = nameInputTextField.getText();
		if(name.length() != 0) {
			ArrayList<Patient> patients1 = doctorService.patientSearchByName(name,patients);
			if(patients1 != null && !patients1.isEmpty()) {
				table.setModel(new PatientModel(patients1));
				table.getColumn("����").setCellRenderer(new ButtonRenderer());
		        table.getColumn("����").setCellEditor(new ButtonEditor());
			}else {
				JOptionPane.showMessageDialog(null,
				          "δ��ѯ���˹ؼ���", "��ѯʧ��",
				          JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
			          "������������", "��ѯʧ��",
			          JOptionPane.ERROR_MESSAGE);
		}
	}


	//���湦��
	private void save() {
		// TODO Auto-generated method stub
		boolean result = true;
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Patient patient = doctorService.findPatientByIdentity((String)table.getValueAt(i, 4),patients);
			String time = (String)table.getValueAt(i, 2);
			String[] time1 = time.split("-");
			int[] time2 = {Integer.valueOf(time1[0]),Integer.valueOf(time1[1]),Integer.valueOf(time1[2])};
			String name = ((String)table.getValueAt(i, 1));
			String sex = (String)table.getValueAt(i, 3);
			String identity = (String)table.getValueAt(i, 4);
			String phone = (String)table.getValueAt(i, 5);
			String emergename = (String)table.getValueAt(i, 6);
			String emergephone = (String)table.getValueAt(i, 7);
			if(patient != null) {
				if(time1.length == 3 && time2[0] > 0 && time2[1] > 0 && time2[2] > 0 && name.length() != 0 && sex.length() != 0 && 
						identity.length() != 0 && phone.length() != 0 &&emergename.length() != 0 && emergephone.length() != 0) {
					Calendar c = MyTools.StringtoCalendar(time);
					patient.setBirthday(c);
					patient.setName(name);
					patient.setSex(sex);
					patient.setIdentity(identity);
					patient.setPhone(phone);
					patient.setEmergename(emergename);
					patient.setEmergephone(emergephone);
				}else {
					result = false;
					break;
				}
			}
		}
		if(result) {
			doctorService.setPatients(patients);
			doctorService.storePatients();
			JOptionPane.showMessageDialog(null,
			          "�ѱ���", "����ɹ�",
			          JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
			          "����ʧ��", "����ʧ��",
			          JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//��תģ���б����
	private void gotoTemplateList_Main() {
		TemplateList_Main.main(doctor, this);
		this.setVisible(false);
	}
	
	//���ص�½����
	public void back() {
		// TODO Auto-generated method stub
		login.setVisible(true);
		this.dispose();
	}
	
	
	
	//ɾ����ѡ�Ĳ���
	private void deleteSelectedPatient() {
		// TODO Auto-generated method stub
		boolean delete = false;
		for(int i = 0; i < table.getRowCount(); i++ ) {
			if((Boolean)table.getValueAt(i, 0)) {
				
				patients.remove(doctorService.findPatientByIdentity((String)table.getValueAt(i, 4),patients));
				delete = true;
			}
		}
		if(delete) {
			table.setModel(new PatientModel(patients));
			table.getColumn("����").setCellRenderer(new ButtonRenderer());
	        table.getColumn("����").setCellEditor(new ButtonEditor());
	        JOptionPane.showMessageDialog(null,
					"ɾ���ɹ�","ɾ���ɹ�",
					JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
					"ɾ��ʧ�ܣ�δѡ���κβ���","ɾ��ʧ��",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//��ת����Ӳ��˽���
	private void addPatient() {
		AddPatient.main(this,patients);
		this.setVisible(false);
	}

	
	//�޸ı�����ݺ������ʾ
	public JLabel getDoctorTip() {
		return doctorTip;
	}
	
	//�������˰�ť
	private void gotoTestPatient(int row) {
		// TODO Auto-generated method stub
		 TestPatient_Main.main(this, doctor, doctorService.findPatientByIdentity((String)table.getValueAt(row, 4)), doctorService.geTemplates(), doctorService.getQuestions(), doctorService.getRecords());
		 this.setVisible(false);
	}
	
	
	
	//���±������
	public void updateTable() {
		table.setModel(new PatientModel(patients));
		table.getColumn("����").setCellRenderer(new ButtonRenderer());
		table.getColumn("����").setCellEditor(new ButtonEditor());
	}
	

	//���������
    public static class PatientModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"ѡ��","����","��������","�Ա�","���֤","��ϵ�绰","������ϵ��","������ϵ�˵绰","����"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, JButton.class};
        private Object[][] data;

        public PatientModel(List<Patient> list) {
        	if(list != null && !list.isEmpty()) {
    			data = new Object[list.size()][8];
    			for (int i = 0; i < list.size(); i++) {
    				data[i][0] = Boolean.FALSE;	//��ѡ��
    				data[i][1] = list.get(i).getName();
    				data[i][2] = MyTools.calenderToString(list.get(i).getBirthday());
    				data[i][3] = list.get(i).getSex();
    				data[i][4] = list.get(i).getIdentity();
    				data[i][5] = list.get(i).getPhone();
    				data[i][6] = list.get(i).getEmergename();
    				data[i][7] = list.get(i).getEmergephone();
    			}
    		}else {
    			this.data = new Object[0][0];
    		}
        }
        
        @Override public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override public int getRowCount() {
            return data.length;
        }

        @Override public String getColumnName(int columnIndex) {
            return COLUMN_NAMES[columnIndex];
        }

        @Override public Class<?> getColumnClass(int columnIndex) {
            return COLUMN_TYPES[columnIndex];
        }

        @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
            if(columnIndex != 8) {
            	return data[rowIndex][columnIndex];
            }else {
            	final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
//                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button),
//                                "Button clicked for row "+rowIndex);
                    }
                });
                return button;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
        
      //�޸ı���е�����
      		@Override
      		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      			if(columnIndex != 8) {
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
            button.setText("����");
            return button;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private static final long serialVersionUID = -6546334664166791132L;

        public ButtonEditor() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("����");
            button.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                   gotoTestPatient(row);
                }
            });
            return button;
        }
    }
    
    //�滻����JTextFieldΪJTextArea����ʾȫ����Ϣ
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
            for (int i = 0; i < table.getColumnCount() - 1; i++) {   
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
}
