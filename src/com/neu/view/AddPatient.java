package com.neu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Patient;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author 嚼着麦草的男孩
 *添加一个病人界面
 */

public class AddPatient extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameInput;
	private JTextField identityInput;
	private JLabel phoneInputTip;
	private JLabel emerNameInputTip;
	private JLabel emerPhoneInputTip;
	private JTextField phoneInput;
	private JTextField emerNameInput;
	private JTextField emerPhoneInput;
	private JLabel genderTip;
	private JLabel birthTip;
	private JComboBox<String> genderComboBox;
	private JComboBox<String> DayComboBox;
	private JComboBox<String> YearComboBox;
	private JComboBox<String> MonthComboBox;
	private Doctor_Main doctor_Main;				//上一界面
	private IDoctorService doctorService;			//员工服务
	private ArrayList<Patient> patients;			//病人集合
	private String[] genderList = {"男","女"};
	private static String[] yearList = new String[200];
	private static String[] monthList = new String[12];
	private static String[] dayList = new String[31];

	/**
	 * Launch the application.
	 */
	public static void main(Doctor_Main doctor_Main, ArrayList<Patient> patients) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					init();
					AddPatient frame = new AddPatient();
					frame.doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					frame.doctor_Main = doctor_Main;
					frame.patients = patients;
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
	public AddPatient() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel addTip = new JLabel("\u6DFB  \u52A0  \u7528  \u6237");
		addTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		addTip.setBounds(314, 10, 158, 47);
		contentPane.add(addTip);
		
		nameInput = new JTextField();
		nameInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		nameInput.setBounds(105, 75, 172, 30);
		contentPane.add(nameInput);
		nameInput.setColumns(10);
		
		JLabel nameInoutTip = new JLabel("\u59D3\u540D");
		nameInoutTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		nameInoutTip.setBounds(30, 75, 54, 25);
		contentPane.add(nameInoutTip);
		
		JLabel identityInputTip = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		identityInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		identityInputTip.setBounds(30, 130, 73, 25);
		contentPane.add(identityInputTip);
		
		identityInput = new JTextField();
		identityInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		identityInput.setBounds(105, 127, 172, 30);
		contentPane.add(identityInput);
		identityInput.setColumns(10);
		
		phoneInputTip = new JLabel("\u624B\u673A\u53F7");
		phoneInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		phoneInputTip.setBounds(30, 185, 73, 25);
		contentPane.add(phoneInputTip);
		
		emerNameInputTip = new JLabel("\u7D27\u6025\u8054\u7CFB\u4EBA");
		emerNameInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		emerNameInputTip.setBounds(354, 80, 118, 25);
		contentPane.add(emerNameInputTip);
		
		emerPhoneInputTip = new JLabel("\u7D27\u6025\u8054\u7CFB\u4EBA\u7535\u8BDD");
		emerPhoneInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		emerPhoneInputTip.setBounds(354, 133, 118, 25);
		contentPane.add(emerPhoneInputTip);
		
		phoneInput = new JTextField();
		phoneInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		phoneInput.setColumns(10);
		phoneInput.setBounds(105, 185, 172, 30);
		contentPane.add(phoneInput);
		
		emerNameInput = new JTextField();
		emerNameInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		emerNameInput.setColumns(10);
		emerNameInput.setBounds(487, 80, 172, 30);
		contentPane.add(emerNameInput);
		
		emerPhoneInput = new JTextField();
		emerPhoneInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		emerPhoneInput.setColumns(10);
		emerPhoneInput.setBounds(487, 127, 172, 30);
		contentPane.add(emerPhoneInput);
		
		genderTip = new JLabel("\u6027\u522B");
		genderTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		genderTip.setBounds(70, 265, 54, 25);
		contentPane.add(genderTip);
		
		birthTip = new JLabel("\u51FA\u751F\u65E5\u671F");
		birthTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		birthTip.setBounds(280, 265, 73, 25);
		contentPane.add(birthTip);
		
		genderComboBox = new JComboBox(genderList);
		genderComboBox.setBounds(130, 265, 55, 25);
		contentPane.add(genderComboBox);
		
		YearComboBox = new JComboBox(yearList);
		YearComboBox.setBounds(405, 265, 55, 25);
		contentPane.add(YearComboBox);
		
		MonthComboBox = new JComboBox(monthList);
		MonthComboBox.setBounds(514, 265, 55, 25);
		contentPane.add(MonthComboBox);
		
		JLabel yearTip = new JLabel("\u5E74");
		yearTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		yearTip.setBounds(377, 262, 28, 30);
		contentPane.add(yearTip);
		
		JLabel monthTip = new JLabel("\u6708");
		monthTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		monthTip.setBounds(484, 262, 28, 30);
		contentPane.add(monthTip);
		
		JLabel dayTip = new JLabel("\u65E5");
		dayTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		dayTip.setBounds(595, 262, 28, 30);
		contentPane.add(dayTip);
		
		DayComboBox = new JComboBox(dayList);
		DayComboBox.setBounds(633, 265, 55, 25);
		contentPane.add(DayComboBox);
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPatient();
			}
		});
		saveButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		saveButton.setBounds(184, 334, 93, 30);
		contentPane.add(saveButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(471, 334, 93, 30);
		contentPane.add(btnNewButton_1);
	}
	
	//返回Doctor_Mian界面，销毁此界面
	private void back() {
		doctor_Main.setVisible(true);
		doctor_Main.updateTable();
		this.dispose();
	}
	
	//添加按钮操作，添加一个病人
	private void addPatient() {
		String name = nameInput.getText();
		String identity = identityInput.getText();
		String phone = phoneInput.getText();
		String emerName = emerNameInput.getText();
		String emerPhone = emerPhoneInput.getText();
		String gender = (String)genderComboBox.getSelectedItem();
		String year1 = (String)YearComboBox.getSelectedItem();
		String month1 = (String)MonthComboBox.getSelectedItem();
		String day1 = (String)DayComboBox.getSelectedItem();
		//保证数据完整
		if(name.length() != 0 && identity.length() != 0 && phone.length() != 0 && emerName.length() != 0 && emerPhone.length() != 0 &&
				gender.length() != 0 && year1.length() != 0 && month1.length() != 0 && day1.length() != 0) {
			if(doctorService.isPatientExistIdentity(identity,patients)) {
				JOptionPane.showMessageDialog(null,
				          "用户已存在，请勿重复添加", "添加失败",
				          JOptionPane.ERROR_MESSAGE);
			}else {
				int year = Integer.valueOf(year1);
				int month = Integer.valueOf(month1);
				int day = Integer.valueOf(day1);
				Calendar c = Calendar.getInstance();
				c.clear();
				c.set(Calendar.YEAR,year);
				c.set(Calendar.MONTH,month);
				c.set(Calendar.DATE,day);
				Patient patient = new Patient(name,c,gender,identity,phone,emerName,emerPhone);
				patients.add(patient);
				JOptionPane.showMessageDialog(null,
				          "添加成功", "添加成功",
				          JOptionPane.DEFAULT_OPTION);
				back();
			}
		}else {
			JOptionPane.showMessageDialog(null,
			          "请完善用户信息再添加", "添加失败",
			          JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//初始化JComboBox
	private static void init() {
		int j = 1;
		Calendar c = Calendar.getInstance();
		for(int i = c.get(Calendar.YEAR); i > c.get(Calendar.YEAR) - 199; i-- ) {
			yearList[j++] = String.valueOf(i); 
		}
		for(int i = 1; i < 13; i++) {
			monthList[i - 1] = String.valueOf(i);
		}
		for(int i = 1; i < 32; i++) {
			dayList[i - 1] = String.valueOf(i);
		}
	}
	
}













