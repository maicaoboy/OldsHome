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
 * @author ½À×ÅÂó²ÝµÄÄÐº¢
 *ÆÀ¹À½áÊø½çÃæ
 */

public class EndOfDialog extends JFrame {
	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextPane textPane;
	private static DialogFrame dialogFrame; 
	private static TestPatient_Main testPatient_Main;
	private static String score; 			//µÃ·ÖÊý¾Ý
	private static Doctor doctor;			//ÆÀ¹ÀÒ½Éú
	private static Patient patient;			//ÊÜÆÀ¹À²¡ÈË
	private static Template template;		//ÓÃÓÚÆÀ¹ÀµÄÄ£°å
	private static Calendar calendar;		//ÆÀ¹ÀÈÕÆÚ

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
		dialogTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 25));
		dialogTip.setBounds(317, 10, 200, 30);
		contentPane.add(dialogTip);
		
		JLabel scoreTip = new JLabel("µÃ·ÖÏêÇé£º " + score);
		scoreTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		scoreTip.setBounds(63, 80, 223, 43);
		contentPane.add(scoreTip);
		
		JLabel templateTip = new JLabel("Ä£°å" + template.getName());
		templateTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		templateTip.setBounds(398, 80, 239, 43);
		contentPane.add(templateTip);
		
		JLabel patientTip = new JLabel("»¼Õß: " + patient.getName());
		patientTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		patientTip.setBounds(63, 133, 239, 43);
		contentPane.add(patientTip);
		
		JLabel doctorTip = new JLabel("Ò½Éú: " + doctor.getName());
		doctorTip.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		doctorTip.setBounds(398, 133, 239, 43);
		contentPane.add(doctorTip);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 16));
		textPane.setBounds(63, 205, 652, 111);
		contentPane.add(textPane);
		
		JButton submitButton = new JButton("\u8BC4 \u4F30 \u7ED3 \u675F");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordRestore();
			}
		});
		submitButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 18));
		submitButton.setBounds(307, 344, 164, 43);
		contentPane.add(submitButton);
	}

	//±£´æÆÀ¹À¼ÇÂ¼°´Å¥
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
					"ÇëÌîÐ´ÆÀÓï","Ê§°Ü",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
