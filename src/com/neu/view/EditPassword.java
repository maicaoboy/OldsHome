package com.neu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.neu.pojo.Doctor;
import com.neu.service.IDoctorService;
import com.neu.service.IManagerService;
import com.neu.service.impl.DoctorService;
import com.neu.service.impl.ManagerService;
import com.neu.tools.MyTools;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private static EditPersonalInfo editPersonalInfo;
	private static Doctor doctor;
	private static IManagerService managerService;
	private JButton editButton;
	private JButton backButton;
	private JLabel lblNewLabel;
	private JLabel userTip;
	private JLabel passwordOldTip;
	private JPasswordField passwordOld;

	/**
	 * Launch the application.
	 */
	public static void main(EditPersonalInfo editPersonalInfo1, Doctor doctor1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editPersonalInfo = editPersonalInfo1;
					doctor = doctor1;
					managerService = (ManagerService) MyTools.getObject("managerservice");
					EditPassword frame = new EditPassword();
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
	public EditPassword() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel password1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		password1.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		password1.setBounds(169, 195, 144, 30);
		contentPane.add(password1);
		
		JLabel password2 = new JLabel("\u8BF7\u786E\u8BA4\u5BC6\u7801\uFF1A");
		password2.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		password2.setBounds(169, 249, 144, 30);
		contentPane.add(password2);
		
		passwordField1 = new JPasswordField();
		passwordField1.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		passwordField1.setBounds(341, 195, 231, 30);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		passwordField2.setBounds(341, 249, 231, 30);
		contentPane.add(passwordField2);
		
		editButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		editButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		editButton.setBounds(213, 324, 100, 30);
		contentPane.add(editButton);
		
		backButton = new JButton("\u53D6\u6D88");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		backButton.setBounds(452, 324, 100, 30);
		contentPane.add(backButton);
		
		lblNewLabel = new JLabel("\u4FEE \u6539 \u5BC6 \u7801");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(304, 36, 169, 30);
		contentPane.add(lblNewLabel);
		
		userTip = new JLabel(doctor.getPosition() + " " + doctor.getName());
		userTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		userTip.setBounds(270, 86, 231, 30);
		contentPane.add(userTip);
		
		passwordOldTip = new JLabel("\u8BF7\u8F93\u5165\u539F\u5BC6\u7801\uFF1A");
		passwordOldTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		passwordOldTip.setBounds(169, 140, 144, 30);
		contentPane.add(passwordOldTip);
		
		passwordOld = new JPasswordField();
		passwordOld.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 19));
		passwordOld.setBounds(341, 140, 231, 30);
		contentPane.add(passwordOld);
	}
	
	private void back() {
		editPersonalInfo.setVisible(true);
		this.dispose();
	}
	
	private void confirm() {
		String passwordOld1 = passwordOld.getText();
		String password1 = passwordField1.getText();
		String password2 = passwordField2.getText();
		if(passwordOld1.length() != 0 && password1.length() != 0 && password2.length() != 0) {
			if(passwordOld1.equals(doctor.getPassword())) {
				if(password1.equals(password2)) {
					doctor.setPassword(password1);
					managerService.storeDoctorList();
					JOptionPane.showMessageDialog(null,
							"ÐÞ¸Ä³É¹¦£¬ÇëÖØÐÂµÇÂ½","ÐÞ¸Ä³É¹¦",
							JOptionPane.DEFAULT_OPTION);
					editPersonalInfo.logout();
					this.dispose();
				}
			}else {
				JOptionPane.showMessageDialog(null,
						"Ô­ÃÜÂë´íÎó","Ô­ÃÜÂë´íÎó",
						JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
					"ÇëÌîÐ´ÍêÕûÔ­ÃÜÂëºÍÐÂÃÜÂë","´íÎó",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
