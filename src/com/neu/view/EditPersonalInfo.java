package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

/**
 * 医生修改个人信息
 * @author 嚼着麦草的男孩
 *
 */

public class EditPersonalInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameInput;
	private JTextField phoneInput;
	private JTextField specialityInput;
	private JTextField positionInput;
	JComboBox<String> yearComboBox;
	JComboBox<String> monthComboBox;
	JComboBox<String> dayComboBox;	
	private static Doctor doctor;							//当前医生
	private static IDoctorService doctorService;			//员	工服务
	private static Doctor_Main doctor_Main;					//
	private static String[] yearList;
	private static String[] monthList;
	private static String[] dayList;

	/**
	 * Launch the application.
	 */
	public static void main(Doctor doctor1,Doctor_Main doctor_Main1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctor_Main = doctor_Main1;
					doctor = doctor1;
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					EditPersonalInfo frame = new EditPersonalInfo();
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
	public EditPersonalInfo() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainTip = new JLabel("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		mainTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		mainTip.setBounds(320, 22, 167, 33);
		contentPane.add(mainTip);
		
		JLabel nameTip = new JLabel("\u59D3\u540D");
		nameTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		nameTip.setBounds(129, 93, 70, 30);
		contentPane.add(nameTip);
		
		JButton passwordEdit = new JButton("\u4FEE\u6539\u5BC6\u7801");
		passwordEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editPassword();
			}
		});
		passwordEdit.setBackground(Color.PINK);
		passwordEdit.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		passwordEdit.setBounds(42, 33, 100, 30);
		contentPane.add(passwordEdit);
		
		JButton editButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmEdit();
			}
		});
		editButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		editButton.setBounds(221, 322, 100, 30);
		contentPane.add(editButton);
		
		JButton backButton = new JButton("\u53D6 \u6D88");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		backButton.setBounds(462, 322, 100, 30);
		contentPane.add(backButton);
		
		nameInput = new JTextField(doctor.getName());
		nameInput.setBounds(194, 95, 120, 30);
		contentPane.add(nameInput);
		nameInput.setColumns(10);
		
		JLabel phoneInputTip = new JLabel("\u624B\u673A\u53F7");
		phoneInputTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		phoneInputTip.setBounds(417, 93, 70, 30);
		contentPane.add(phoneInputTip);
		
		phoneInput = new JTextField(doctor.getPhone());
		phoneInput.setColumns(10);
		phoneInput.setBounds(497, 97, 130, 30);
		contentPane.add(phoneInput);
		
		JLabel specialityTip = new JLabel("\u7279\u957F");
		specialityTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		specialityTip.setBounds(129, 157, 70, 30);
		contentPane.add(specialityTip);
		
		specialityInput = new JTextField(doctor.getSpeciality());
		specialityInput.setColumns(10);
		specialityInput.setBounds(194, 160, 120, 30);
		contentPane.add(specialityInput);
		
		JLabel pisitionTip = new JLabel("\u804C\u79F0");
		pisitionTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		pisitionTip.setBounds(417, 157, 70, 30);
		contentPane.add(pisitionTip);
		
		positionInput = new JTextField(doctor.getPosition());
		positionInput.setColumns(10);
		positionInput.setBounds(497, 161, 130, 30);
		contentPane.add(positionInput);
		
		JLabel birthTip = new JLabel("\u51FA\u751F\u65E5\u671F");
		birthTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		birthTip.setBounds(129, 250, 73, 25);
		contentPane.add(birthTip);
		
		JLabel yearTip = new JLabel("\u5E74");
		yearTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		yearTip.setBounds(260, 247, 28, 30);
		contentPane.add(yearTip);
		
		yearComboBox = new JComboBox(yearList);
		yearComboBox.setSelectedItem(String.valueOf(doctor.getBirthday().get(Calendar.YEAR)));
		yearComboBox.setBounds(298, 253, 55, 25);
		contentPane.add(yearComboBox);
		
		JLabel monthTip = new JLabel("\u6708");
		monthTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		monthTip.setBounds(400, 247, 28, 30);
		contentPane.add(monthTip);
		
		monthComboBox = new JComboBox(monthList);
		monthComboBox.setSelectedItem((String.valueOf(doctor.getBirthday().get(Calendar.MONTH))));
		monthComboBox.setBounds(438, 253, 55, 25);
		contentPane.add(monthComboBox);
		
		JLabel dayTip = new JLabel("\u65E5");
		dayTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		dayTip.setBounds(532, 250, 28, 30);
		contentPane.add(dayTip);
		
		dayComboBox = new JComboBox(dayList);
		dayComboBox.setSelectedItem(String.valueOf(doctor.getBirthday().get(Calendar.DATE)));
		dayComboBox.setBounds(570, 253, 55, 25);
		contentPane.add(dayComboBox);
	}
	
	//修改密码
	private void editPassword() {
		EditPassword.main(this,doctor);
	}
	
	//修改按钮，确认修改个人信息
	protected void confirmEdit() {
		// TODO Auto-generated method stub
		String name = nameInput.getText();
		String phone = phoneInput.getText();
		String speciality = specialityInput.getText();
		String position = positionInput.getText();
		int year = Integer.parseInt((String)yearComboBox.getSelectedItem());
		int month = Integer.parseInt((String)monthComboBox.getSelectedItem());
		int date = Integer.parseInt((String)dayComboBox.getSelectedItem());
		if(name.length() != 0 && phone.length() != 0 && speciality.length() != 9 && position.length() != 0) {
			Calendar c = Calendar.getInstance();
			c.clear();
			c.set(Calendar.YEAR,year);
			c.set(Calendar.MONTH,month);
			c.set(Calendar.DATE,date);
			doctor.setBirthday(c);
			doctor.setName(name);
			doctor.setPhone(phone);
			doctor.setSpeciality(speciality);
			doctor.setPosition(position);
			doctorService.storeDoctors();
			JOptionPane.showMessageDialog(null,
			          "修改个人信息成功", "修改成功",
			          JOptionPane.DEFAULT_OPTION);
			doctor_Main.getDoctorTip().setText(doctor.getPosition() +" " + doctor.getName());
			doctor_Main.setVisible(true);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null,
			          "请填写完整个人信息后提交", "修改失败",
			          JOptionPane.ERROR_MESSAGE);
		}
	}

	//返回上一界面
	protected void back() {
		// TODO Auto-generated method stub
		doctor_Main.setVisible(true);
		this.dispose();
	}
	
	//登出
	public void logout() {
		// TODO Auto-generated method stub
		doctor_Main.back();
		this.dispose();
	}
	
	static {
		int j = 1;
		Calendar c = Calendar.getInstance();
		yearList = new String[200];
		monthList = new String[12];
		dayList = new String[31];
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
