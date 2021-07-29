package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Question;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * 
 * @author 嚼着麦草的男孩
 *问题详情查看修改界面
 */

public class QuestionDetail extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField detailInput;
	private JTextField answer1Input;
	private JTextField answer2Input;
	private JTextField answer3Input;
	private JTextField typeInput;
	private static Question question;						//当前查看问题日
	private static QuestionList_Main questionList_Main;		//上一界面
	private static ArrayList<Question> questions;			//问题集合

	/**
	 * Launch the application.
	 */
	public static void main(QuestionList_Main questionList_Main1, Question question1, ArrayList<Question> questions1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					questions = questions1;
					question = question1;
					questionList_Main = questionList_Main1;
					QuestionDetail frame = new QuestionDetail();
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
	public QuestionDetail() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel questionDetailTip = new JLabel("\u95EE \u9898 \u8BE6 \u60C5");
		questionDetailTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		questionDetailTip.setBounds(305, 21, 142, 47);
		contentPane.add(questionDetailTip);
		
		JLabel detailTip = new JLabel("\u95EE \u9898 \u8BE6 \u60C5");
		detailTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		detailTip.setBounds(165, 81, 100, 30);
		contentPane.add(detailTip);
		
		JLabel answerTip_1 = new JLabel("5 \u5206 \u7B54 \u6848");
		answerTip_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_1.setBounds(165, 131, 100, 30);
		contentPane.add(answerTip_1);
		
		JLabel answerTip_2 = new JLabel("3 \u5206 \u7B54 \u6848");
		answerTip_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_2.setBounds(165, 179, 100, 30);
		contentPane.add(answerTip_2);
		
		JLabel answerTip_3 = new JLabel("1 \u5206 \u7B54 \u6848");
		answerTip_3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		answerTip_3.setBounds(165, 224, 100, 30);
		contentPane.add(answerTip_3);
		
		JLabel typeTip = new JLabel("\u95EE \u9898 \u7C7B \u578B");
		typeTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		typeTip.setBounds(165, 269, 100, 30);
		contentPane.add(typeTip);
		
		JLabel IDTip = new JLabel("ID " + question.getId());
		IDTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		IDTip.setBounds(36, 27, 116, 30);
		contentPane.add(IDTip);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		backButton.setBounds(446, 346, 135, 30);
		contentPane.add(backButton);
		
		JButton editButton = new JButton("\u4FEE \u6539");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		editButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		editButton.setBounds(197, 346, 135, 30);
		contentPane.add(editButton);
		
		detailInput = new JTextField(question.getDetail());
		detailInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		detailInput.setColumns(10);
		detailInput.setBounds(315, 81, 250, 30);
		contentPane.add(detailInput);
		
		answer1Input = new JTextField(question.getAnswer()[0]);
		answer1Input.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answer1Input.setColumns(10);
		answer1Input.setBounds(315, 131, 250, 30);
		contentPane.add(answer1Input);
		
		answer2Input = new JTextField(question.getAnswer()[1]);
		answer2Input.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answer2Input.setColumns(10);
		answer2Input.setBounds(315, 179, 250, 30);
		contentPane.add(answer2Input);
		
		answer3Input = new JTextField(question.getAnswer()[2]);
		answer3Input.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		answer3Input.setColumns(10);
		answer3Input.setBounds(315, 224, 250, 30);
		contentPane.add(answer3Input);
		
		typeInput = new JTextField(question.getType());
		typeInput.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		typeInput.setColumns(10);
		typeInput.setBounds(315, 269, 250, 30);
		contentPane.add(typeInput);
	}
	
	//返回上一界面
	private void back() {
		questionList_Main.setVisible(true);
		this.dispose();
	}
	
	//保存按钮的操作
	private void save() {
		String detail = detailInput.getText();
		String answer1 = answer1Input.getText();
		String answer2 = answer2Input.getText();
		String answer3 = answer3Input.getText();
		String type = typeInput.getText();
		if(detail.length() != 0 && answer1.length() != 0 && answer2.length() != 0 && answer3.length() != 0 && type.length() != 0) {
			question.setDetail(detail);
			String answer[] = new String[3];
			answer[0] = answer1;
			answer[1] = answer2;
			answer[2] = answer3;
			question.setAnswer(answer);
			question.setType(type);
			questionList_Main.getTable().setModel(new QuestionList_Main.QuestionModel(questions));
			questionList_Main.buildButton();
			JOptionPane.showMessageDialog(null, 
					"修改成功，请返回模板列表保存","修改成功",
					JOptionPane.DEFAULT_OPTION);
		}else {
			JOptionPane.showMessageDialog(null,
					"修改失败","修改失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
