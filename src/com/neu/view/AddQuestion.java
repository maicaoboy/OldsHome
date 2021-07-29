package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Question;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * 添加一个问题界面
 * @author 嚼着麦草的男孩
 *
 */

public class AddQuestion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField detailInput;
	private JTextField answerInput1;
	private JTextField answerInput2;
	private JTextField answerInput3;
	private JTextField answerTypeInput;
	private static ArrayList<Question> questions;			//问题集合
	private static QuestionList_Main questionList_Main;		//上一界面
	private static IDoctorService doctorService;			//员工服务

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<Question> questions1, QuestionList_Main questionList_Main1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					questions = questions1;
					questionList_Main = questionList_Main1;
					AddQuestion frame = new AddQuestion();
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
	public AddQuestion() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel addQuestionTip = new JLabel("\u6DFB \u52A0 \u95EE \u9898");
		addQuestionTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		addQuestionTip.setBounds(315, 23, 145, 42);
		contentPane.add(addQuestionTip);
		
		JLabel detailTip = new JLabel("\u95EE \u9898 \u8BE6 \u60C5");
		detailTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		detailTip.setBounds(161, 88, 100, 30);
		contentPane.add(detailTip);
		
		JLabel answerTip_1 = new JLabel("5 \u5206 \u7B54 \u6848");
		answerTip_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_1.setBounds(161, 138, 100, 30);
		contentPane.add(answerTip_1);
		
		JLabel answerTip_2 = new JLabel("3 \u5206 \u7B54 \u6848");
		answerTip_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_2.setBounds(161, 188, 100, 30);
		contentPane.add(answerTip_2);
		
		JLabel answerTip_3 = new JLabel("1 \u5206 \u7B54 \u6848");
		answerTip_3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_3.setBounds(161, 235, 100, 30);
		contentPane.add(answerTip_3);
		
		JLabel typeTip = new JLabel("\u95EE \u9898 \u7C7B \u578B");
		typeTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		typeTip.setBounds(161, 285, 100, 30);
		contentPane.add(typeTip);
		
		detailInput = new JTextField();
		detailInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		detailInput.setBounds(315, 89, 250, 30);
		contentPane.add(detailInput);
		detailInput.setColumns(10);
		
		answerInput1 = new JTextField();
		answerInput1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answerInput1.setColumns(10);
		answerInput1.setBounds(315, 139, 250, 30);
		contentPane.add(answerInput1);
		
		answerInput2 = new JTextField();
		answerInput2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answerInput2.setColumns(10);
		answerInput2.setBounds(315, 189, 250, 30);
		contentPane.add(answerInput2);
		
		answerInput3 = new JTextField();
		answerInput3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answerInput3.setColumns(10);
		answerInput3.setBounds(315, 236, 250, 30);
		contentPane.add(answerInput3);
		
		answerTypeInput = new JTextField();
		answerTypeInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answerTypeInput.setColumns(10);
		answerTypeInput.setBounds(315, 286, 250, 30);
		contentPane.add(answerTypeInput);
		
		JButton btnNewButton = new JButton("\u6DFB \u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuestion();
			}
		});
		btnNewButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		btnNewButton.setBounds(190, 351, 135, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4 \u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(455, 351, 135, 30);
		contentPane.add(btnNewButton_1);
	}
	
	//添加一个问题
	private void addQuestion() {
		boolean result = true;
		Question question = null;
		String detail = detailInput.getText();
		String answer1 = answerInput1.getText();
		String answer2 = answerInput2.getText();
		String answer3 = answerInput3.getText();
		String type = answerTypeInput.getText();
		if(detail.length() != 0 && answer1.length() != 0 && answer2.length() != 0 && answer3.length() != 0 && type.length() != 0) {
			String[] answer = {answer1,answer2,answer3};
			question = new Question(detail,answer,type);
		}else {
			result = false;
		}
		if(result) {
			if(doctorService.addQuestion(question)) {
				doctorService.getQuestions().remove(question);
				questions.add(question);
				JOptionPane.showMessageDialog(null,
				          "添加成功，请返回问题列表保存", "添加成功",
				          JOptionPane.DEFAULT_OPTION);
				questionList_Main.getTable().setModel(new QuestionList_Main.QuestionModel(questions));
				questionList_Main.buildButton();
				doctorService.storeQuestions();
			}else {
				JOptionPane.showMessageDialog(null,
				          "问题已存在", "添加失败",
				          JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
			          "请填写完整题目后重试", "添加失败",
			          JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//返回上一界面
	private void back() {
		questionList_Main.setVisible(true);
		this.dispose();
	}
}
