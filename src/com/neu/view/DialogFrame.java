package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.pojo.Patient;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author 嚼着麦草的男孩
 *评估答题界面
 */

public class DialogFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_Main;
	private static Doctor doctor;						//当前登录医生
	private static Patient patient;						//收评估病人
	private static ArrayList<Question> questions;		//问题列表
	private static Template template;					//评估模板
	private static IDoctorService doctorService;		//员工服务
	private static TestPatient_Main testPatient_Main;	//上一界面

	/**
	 * Launch the application.
	 */
	public static void main(TestPatient_Main testPatient_Main1, Doctor doctor1, Patient patient1, Template template1, ArrayList<Question> questions1 ) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				testPatient_Main = testPatient_Main1;
				doctor = doctor1;
				template = template1;
				patient = patient1;
				doctorService = (IDoctorService) MyTools.getObject("doctorservice");
				questions = doctorService.fromIntListToQuestionList(template1.getQuesID(), questions1);
				DialogFrame frame = new DialogFrame();
				frame.setVisible(true);
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DialogFrame() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_Main = new JPanel();
		panel_Main.setBounds(50, 31, 678, 342);
		contentPane.add(panel_Main);
		panel_Main.setLayout(new BoxLayout(panel_Main, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < questions.size(); i++ ) {
			JPanel panel_Question = new JPanel();
			panel_Question.setLayout(new BoxLayout(panel_Question, BoxLayout.Y_AXIS));
			JLabel label = new JLabel(questions.get(i).getDetail());
		    ButtonGroup g = new ButtonGroup();
		    int[] answer = MyTools.randomSelect();
		    JRadioButton
		            rb1 = new JRadioButton(questions.get(i).getAnswer()[answer[0]], false),
		            rb2 = new JRadioButton(questions.get(i).getAnswer()[answer[1]], false),
		            rb3 = new JRadioButton(questions.get(i).getAnswer()[answer[2]], false);
		    g.add(rb1); g.add(rb2); g.add(rb3);
		    panel_Question.add(label);
		    panel_Question.add(rb1);
		    panel_Question.add(rb2);
		    panel_Question.add(rb3);
			panel_Main.add(panel_Question);
		}
		contentPane.add(panel_Main);
		JScrollPane js = new JScrollPane(panel_Main);
		js.setBounds(50, 31, 678, 342);
		contentPane.add(js);
		
		JLabel dialogTip = new JLabel("\u8BC4 \u4F30 \u4EBA\uFF1A" + patient.getName());
		dialogTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		dialogTip.setBounds(580, 1, 200, 30);
		contentPane.add(dialogTip);
		
		JButton submitButton = new JButton("\u63D0 \u4EA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		submitButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		submitButton.setBounds(148, 381, 120, 30);
		contentPane.add(submitButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		backButton.setBounds(530, 383, 120, 30);
		contentPane.add(backButton);
	}

	protected void back() {
		testPatient_Main.setVisible(true);
		this.dispose();
	}

	protected void submit() {
		boolean isSelect = true;
		int score = 0;
		for(int i = 0; i < panel_Main.getComponentCount(); i++ ) {
			JPanel panel = (JPanel)panel_Main.getComponent(i);
			String select = null;
			if(((JRadioButton) panel.getComponent(1)).isSelected()) {
				select = ((JRadioButton) panel.getComponent(1)).getText();
			}else if(((JRadioButton) panel.getComponent(2)).isSelected()) {
				select = ((JRadioButton) panel.getComponent(2)).getText();
			}else if(((JRadioButton) panel.getComponent(3)).isSelected()){
				select = ((JRadioButton) panel.getComponent(3)).getText();
			}else {
				isSelect = false;
				break;
			}
			score += MyTools.count(select, questions.get(i));
		}
		if(isSelect) {
			String scoreTotal = score + "/" + questions.size() * 5;
			EndOfDialog.main(this,testPatient_Main, scoreTotal, doctor, patient, template);
			this.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null,
					"请填写完整再提交","提交失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
