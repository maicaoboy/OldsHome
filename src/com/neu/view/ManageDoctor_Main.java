package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

/**
 * 
 * @author ������ݵ��к�
 *ϵͳ����Ա������
 */

public class ManageDoctor_Main extends JFrame {
	private JPanel contentPane;
	private JTextField nameSearchTextField;
	private JTable table;
	private Login login;							//��һ����
	private IManagerService managerService;			//����Ա����
	private ArrayList<Doctor> doctors;				//ҽ������
	private String[] positionList= {"ȫ��","ҽ��","��ʿ","����","ҩʦ","¥�����Ա"};

	/**
	 * Launch the application.
	 */
	public static void main(Login login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageDoctor_Main frame = new ManageDoctor_Main();
					frame.managerService = (IManagerService) MyTools.getObject("managerservice");
					try {
						frame.doctors = (ArrayList<Doctor>) MyTools.deepCopy(frame.managerService.getDoctors());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.login = login;
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
	public ManageDoctor_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameSearchTextField = new JTextField();
		nameSearchTextField.setBounds(480, 20, 150, 30);
		contentPane.add(nameSearchTextField);
		nameSearchTextField.setColumns(10);
		
		JButton nameSearchButton = new JButton("\u59D3\u540D\u67E5\u8BE2");
		nameSearchButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 13));
		nameSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByName(nameSearchTextField.getText());
			}
		});
		nameSearchButton.setBounds(658, 20, 90, 30);
		contentPane.add(nameSearchButton);
		
		JComboBox<String> positionSearchJComBox = new JComboBox(positionList);
		positionSearchJComBox.setFont(new Font("΢���ź� Light", Font.PLAIN, 13));
		positionSearchJComBox.setBounds(40, 90, 150, 30);
		positionSearchJComBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String item = (String)cb.getSelectedItem();
		        combobox(item);
			}
		});
		contentPane.add(positionSearchJComBox);
		
		table = new JTable(new DoctorTable());
		table.setBounds(40, 125, 710, 220);
		contentPane.add(table);
		
		JButton addDoctorbutton = new JButton("\u6DFB\u52A0\u5DE5\u4F5C\u4EBA\u5458");
		addDoctorbutton.setFont(new Font("΢���ź� Light", Font.PLAIN, 13));
		addDoctorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDoctor();
			}
		});
		addDoctorbutton.setBounds(415, 90, 150, 30);
		contentPane.add(addDoctorbutton);
		
		JButton deleteDoctorButton = new JButton("\u5220 \u9664");
		deleteDoctorButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 13));
		deleteDoctorButton.setBackground(Color.RED);
		deleteDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		deleteDoctorButton.setBounds(660, 90, 90, 30);
		contentPane.add(deleteDoctorButton);
		
		JButton saveButton = new JButton("\u4FDD \u5B58");
		saveButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setBounds(181, 371, 100, 28);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setBounds(491, 371, 100, 28);
		contentPane.add(backButton);
		
		JLabel managerTip = new JLabel("\u7CFB\u7EDF\u7BA1\u7406\u5458");
		managerTip.setFont(new Font("΢���ź� Light", Font.PLAIN, 25));
		managerTip.setBounds(40, 20, 150, 30);
		contentPane.add(managerTip);
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(40, 125, 710, 220);
		contentPane.add(jp);
	}
	
	private void addDoctor() {
		ManageDoctor_Add.main(this,doctors);
		this.setVisible(false);
	}
	
	//JComboBoxѡ����ʾ��ְ�Ƶ�ҽ��
	private void combobox(String item) {
		ArrayList<Doctor> doctors3 = null;
		try {
			doctors3 = (ArrayList<Doctor>) MyTools.deepCopy(doctors);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(item) {
		case "ȫ��" -> {
			table.setModel(new DoctorTable(doctors3));
		}
		case "ҽ��" -> {
			changeTable(doctors3, "ҽ��");
		}
		case "��ʿ" -> {
			changeTable(doctors3, "��ʿ");
		}
		case "ҩʦ" -> {
			changeTable(doctors3, "ҩʦ");
		}
		case "¥�����Ա" -> {
			changeTable(doctors3, "¥�����Ա");
		}
		}
	}
	
	//ɾ����ť�Ĳ���
	private void delete() {
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Doctor doctor = managerService.doctorFindByUsername(doctors, (String)table.getValueAt(i, 1));
			if((boolean)table.getValueAt(i, 0) && doctor != null) {
				doctors.remove(doctor);
			}
		}
		table.setModel(new DoctorTable(doctors));
		JOptionPane.showMessageDialog(null,
				"�ɹ���ӹ�����Ա���벻Ҫ���Ǳ���","��ӳɹ�",
				JOptionPane.DEFAULT_OPTION);
	}
	
	//���ز���
	private void back() {
		this.setVisible(false);
		login.setVisible(true);
	}
	
	//ȡ��table���ڸ��±��
	public JTable getTable() {
		return table;
	}
	

	//ʹ�����ֲ�ѯ��ť
	private void searchByName(String name) {
		ArrayList<Doctor> doctors2 = null;
		try {
			doctors2 = (ArrayList<Doctor>) MyTools.deepCopy(doctors);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Iterator<Doctor> iterator = doctors.iterator(); iterator.hasNext();) {
			Doctor doctor = (Doctor) iterator.next();
			if(!doctor.getName().contains(name)) {
				doctors2.remove(doctor);
			}
		}
		if(doctors2.isEmpty() || doctors2 == null) {
			JOptionPane.showMessageDialog(null,
			          "δ�ҵ����û�", "��ѯʧ��",
			          JOptionPane.ERROR_MESSAGE);
		}else {
			table.setModel(new DoctorTable(doctors2));
		}
	}
	
	//����JComboboxѡ����س�ɸѡ
	private void changeTable(ArrayList<Doctor> doctors3,String speciality) {
		for (Iterator<Doctor> iterator = doctors3.iterator(); iterator.hasNext();) {
			Doctor doctor = (Doctor) iterator.next();
			if(!doctor.getPosition().equals(speciality)) {
				iterator.remove();
			}
		}
		if(doctors3.isEmpty() || doctors3 == null) {
			JOptionPane.showMessageDialog(null,
			          "δ�ҵ���ְ�Ƶ�Ա��", "����ʧ��",
			          JOptionPane.ERROR_MESSAGE);
		}else {
			table.setModel(new DoctorTable(doctors3));
		}
	}
	
	//�����޸�
	private void save() {
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Doctor doctor = managerService.doctorFindByUsername(doctors, (String)table.getValueAt(i, 1));
			if(doctor != null) {
				doctor.setName((String)table.getValueAt(i, 2));
				doctor.setBirthday(MyTools.StringtoCalendar((String)table.getValueAt(i, 3)));
				doctor.setSpeciality((String)table.getValueAt(i, 4));
				doctor.setPosition((String)table.getValueAt(i, 5));
				doctor.setPhone((String)table.getValueAt(i, 6));
			}
		}
		managerService.setDoctorList(doctors);
		managerService.storeDoctorList();
		try {
			doctors = (ArrayList<Doctor>) MyTools.deepCopy(managerService.getDoctors());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
		          "�ѱ���", "����ɹ�",
		          JOptionPane.DEFAULT_OPTION);
	}
	
	
}
