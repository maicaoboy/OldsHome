package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * 
 * @author 嚼着麦草的男孩
 *超级用户添加员工界面
 */

public class ManageDoctor_Add extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameInputTextField;
	private JButton addDoctorButton;
	private JButton backButton;
	private ManageDoctor_Main managerDoctor_Main;			//上一界面
	private ArrayList<Doctor> doctors;						//医生集合
	private IManagerService manageService;					//超级管理员服务
	private JLabel comfirmJLabel;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(ManageDoctor_Main manageDoctor_Main, ArrayList<Doctor> doctors) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageDoctor_Add frame = new ManageDoctor_Add();
					frame.managerDoctor_Main = manageDoctor_Main;
					frame.doctors = doctors;
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
	public ManageDoctor_Add() {
		setType(Type.UTILITY);
		setTitle("\u65B0\u589E\u5DE5\u4F5C\u4EBA\u5458");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameTip = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF1A");
		usernameTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		usernameTip.setBounds(158, 102, 118, 39);
		contentPane.add(usernameTip);
		
		JLabel passwordTip = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		passwordTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		passwordTip.setBounds(158, 164, 118, 39);
		contentPane.add(passwordTip);
		
		usernameInputTextField = new JTextField();
		usernameInputTextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		usernameInputTextField.setBounds(353, 105, 229, 32);
		contentPane.add(usernameInputTextField);
		usernameInputTextField.setColumns(10);
		
		addDoctorButton = new JButton("\u6DFB\u52A0");
		addDoctorButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		addDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addADoctor();
			}
		});
		addDoctorButton.setBounds(223, 306, 93, 39);
		contentPane.add(addDoctorButton);
		
		backButton = new JButton("\u53D6\u6D88");
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
				
			}
		});
		backButton.setBounds(480, 306, 93, 39);
		contentPane.add(backButton);
		
		comfirmJLabel = new JLabel("\u8BF7\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		comfirmJLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		comfirmJLabel.setBounds(158, 225, 118, 39);
		contentPane.add(comfirmJLabel);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		passwordField_2.setBounds(353, 231, 229, 32);
		contentPane.add(passwordField_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		passwordField_1.setBounds(353, 170, 229, 32);
		contentPane.add(passwordField_1);
		
		lblNewLabel = new JLabel("\u65B0\u589E\u5DE5\u4F5C\u4EBA\u5458");
		lblNewLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(298, 26, 182, 53);
		contentPane.add(lblNewLabel);
	}
	
	//添加按钮操作
	private void addADoctor() {
		String username = usernameInputTextField.getText();
		String password1 = passwordField_1.getText();
		String password2 = passwordField_2.getText();
		if(username.length() != 0 && password1.length() != 0) {
			if(!password1.equals(password2)) {
				JOptionPane.showMessageDialog(null,
				          "两次输入密码不相同，请重试", "注册失败",
				          JOptionPane.ERROR_MESSAGE);
			}else if(username.equals("root") && password1.equals("root")) {
				JOptionPane.showMessageDialog(null,
				          " root 是超级用户，不可注册！", "注册失败",
				          JOptionPane.ERROR_MESSAGE);
			}else if(manageService.verify(new Doctor(username,password1))) {
				JOptionPane.showMessageDialog(null,
				          "用户已存在，请勿重复注册", "注册失败",
				          JOptionPane.ERROR_MESSAGE);
			}else {
				doctors.add(new Doctor(username,password1));
				JOptionPane.showMessageDialog(null,
				          "用户 " + username + " 注册成功", "注册成功",
				          JOptionPane.DEFAULT_OPTION);
				managerDoctor_Main.getTable().setModel(new DoctorTable(doctors));
				this.setVisible(false);
				managerDoctor_Main.setVisible(true);
				JOptionPane.showMessageDialog(null,
				          "添加成功，未保存", "添加用户成功",
				          JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
			          "请输入用户名或密码", "注册失败",
			          JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//返回按钮操作
	void cancel() {
		this.setVisible(false);
		managerDoctor_Main.setVisible(true);
	}
	
	{
		try {
			manageService = (IManagerService) MyTools.getObject("managerservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
