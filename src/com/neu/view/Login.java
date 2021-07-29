package com.neu.view;

import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author ½À×ÅÂó²ÝµÄÄÐº¢
 *µÇÂ½½çÃæ
 */

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameInputTextField;
	private JPasswordField passwordField;
	private IManagerService manageService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		manageService = (IManagerService) MyTools.getObject("managerservice");
		
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameTip = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D\uFF1A");
		usernameTip.setFont(new Font("Î¢ï¿½ï¿½ï¿½Åºï¿½ Light", Font.PLAIN, 14));
		usernameTip.setBounds(200, 110, 120, 40);
		contentPane.add(usernameTip);
		
		JLabel passwordTip = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		passwordTip.setFont(new Font("Î¢ï¿½ï¿½ï¿½Åºï¿½ Light", Font.PLAIN, 14));
		passwordTip.setBounds(200, 190, 120, 40);
		contentPane.add(passwordTip);
		
		usernameInputTextField = new JTextField();
		usernameInputTextField.setColumns(10);
		usernameInputTextField.setBounds(385, 115, 235, 30);
		contentPane.add(usernameInputTextField);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.setFont(new Font("Î¢ï¿½ï¿½ï¿½Åºï¿½ Light", Font.PLAIN, 15));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_main();
			}
		});
		loginButton.setBounds(200, 300, 100, 40);
		contentPane.add(loginButton);
		
		JButton quitButton = new JButton("\u9000\u51FA");
		quitButton.setFont(new Font("Î¢ï¿½ï¿½ï¿½Åºï¿½ Light", Font.PLAIN, 15));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setBounds(500, 300, 100, 40);
		contentPane.add(quitButton);
		
		JLabel lblNewLabel = new JLabel("\u5EB7 \u517B \u793E \u533A");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(45, 10, 150, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6B22  \u8FCE  \u767B  \u5F55");
		lblNewLabel_1.setFont(new Font("Î¢ï¿½ï¿½ï¿½Åºï¿½ Light", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(320, 38, 150, 50);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(386, 195, 235, 30);
		contentPane.add(passwordField);
	}
	
	private void manager_main() {
		String username = usernameInputTextField.getText();
		String password = passwordField.getText();
		passwordField.setText("");
		if(username.length() != 0 && password.length() != 0) {
			if(username.equals("root") && password.equals("root")) {
				JOptionPane.showMessageDialog(null,
				          "³¬¼¶ÓÃ»§ " + username + " µÇÂ½³É¹¦", "µÇÂ½³É¹¦",
				          JOptionPane.DEFAULT_OPTION);
				this.setVisible(false);
				ManageDoctor_Main.main(this);
			}else if(manageService.verify(new Doctor(username,password))) {
				JOptionPane.showMessageDialog(null,
						manageService.getDoctorDao().searchByUsername(username).getPosition() + " " +
						manageService.getDoctorDao().searchByUsername(username).getName(),"µÇÂ½³É¹¦",
						JOptionPane.DEFAULT_OPTION);
				Doctor_Main.main(manageService.getDoctorDao().searchByUsername(username), this);
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null,
				          "ÃÜÂë»òÓÃ»§Ãû³ö´í", "µÇÂ½Ê§°Ü",
				          JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
			          "ÇëÊäÈëÓÃ»§ÃûºÍÃÜÂë", "µÇÂ½Ê§°Ü",
			          JOptionPane.ERROR_MESSAGE);
		}
	}
}
