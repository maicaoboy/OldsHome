package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.DialogRecord;
import com.neu.pojo.Doctor;
import com.neu.pojo.Patient;
import com.neu.pojo.Template;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

/**
 * 
 * 
 * @author 嚼着麦草的男孩
 *评估结束界面
 */

public class EndOfDialog extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextPane textPane;
	private static DialogFrame dialogFrame; 
	private static TestPatient_Main testPatient_Main;
	private static String score; 			//得分数据
	private static Doctor doctor;			//评估医生
	private static Patient patient;			//受评估病人
	private static Template template;		//用于评估的模板
	private static Calendar calendar;		//评估日期

	/**
	 * Launch the application.
	 */
	public static void main(DialogFrame dialogFrame1,TestPatient_Main testPatient_Main1, String score1, Doctor doctor1, Patient patient1, Template template1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calendar = Calendar.getInstance();
					dialogFrame = dialogFrame1;
					testPatient_Main = testPatient_Main1;
					score = score1;
					doctor = doctor1;
					patient = patient1;
					template = template1;
					EndOfDialog frame = new EndOfDialog();
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
	public EndOfDialog() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dialogTip = new JLabel("\u8BC4 \u4F30 \u7ED3 \u675F");
		dialogTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		dialogTip.setBounds(317, 10, 200, 30);
		contentPane.add(dialogTip);
		
		JLabel scoreTip = new JLabel("得分详情： " + score);
		scoreTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		scoreTip.setBounds(63, 80, 223, 43);
		contentPane.add(scoreTip);
		
		JLabel templateTip = new JLabel("模板" + template.getName());
		templateTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		templateTip.setBounds(398, 80, 239, 43);
		contentPane.add(templateTip);
		
		JLabel patientTip = new JLabel("患者: " + patient.getName());
		patientTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		patientTip.setBounds(63, 133, 239, 43);
		contentPane.add(patientTip);
		
		JLabel doctorTip = new JLabel("医生: " + doctor.getName());
		doctorTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		doctorTip.setBounds(398, 133, 239, 43);
		contentPane.add(doctorTip);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		textPane.setBounds(63, 205, 652, 111);
		contentPane.add(textPane);
		
		JButton submitButton = new JButton("\u8BC4 \u4F30 \u7ED3 \u675F");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordRestore();
			}
		});
		submitButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		submitButton.setBounds(307, 344, 164, 43);
		contentPane.add(submitButton);
	}

	//保存评估记录按钮
	protected void recordRestore() {
		// TODO Auto-generated method stub
		String advice = textPane.getText();
		if(advice.length() != 0) {
			DialogRecord record = new DialogRecord(patient.getName(), patient.getSex(), template.getName(), template.getType(), calendar, doctor.getName(), score, advice);
			testPatient_Main.repaintTable(record);
			testPatient_Main.setVisible(true);
			dialogFrame.dispose();
			this.dispose();
			
		}else {
			JOptionPane.showMessageDialog(null,
					"请填写评语","失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
