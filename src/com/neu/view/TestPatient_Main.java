package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import com.neu.pojo.DialogRecord;
import com.neu.pojo.Doctor;
import com.neu.pojo.Patient;
import com.neu.pojo.Question;
import com.neu.pojo.Template;
import com.neu.service.IDoctorService;
import com.neu.tools.MyTools;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * 
 * @author 嚼着麦草的男孩
 *评估记录查看和评估模板选择界面
 */

public class TestPatient_Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JComboBox<String> comboBox;
	private static Doctor doctor;							//当前登录医生
	private static Patient patient;							//接受评估的病人
	private static ArrayList<Template> templates;			//模板集合
	private static ArrayList<Question> questions;			//问题集合
	private static Doctor_Main doctor_Main;					//上一界面
	private static ArrayList<DialogRecord> records;			//评估记录集合
	private static IDoctorService doctorService;			//员工服务

	/**
	 * Launch the application.
	 */
	public static void main(Doctor_Main doctor_Main1,Doctor doctor1, Patient patient1, ArrayList<Template> templates1, ArrayList<Question> questions1, ArrayList<DialogRecord> records1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorService = (IDoctorService) MyTools.getObject("doctorservice");
					doctor_Main = doctor_Main1;
					doctor = doctor1;
					patient = patient1;
					templates = templates1;
					questions = questions1;
					records = records1;
					TestPatient_Main frame = new TestPatient_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//返回上一界面
	private void backToDoctorMain() {
		doctor_Main.setVisible(true);
		this.dispose();
	}
	

	/**
	 * Create the frame.
	 */
	public TestPatient_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TestPatienrTip = new JLabel("\u8BC4 \u4F30");
		TestPatienrTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		TestPatienrTip.setBounds(341, 7, 178, 55);
		contentPane.add(TestPatienrTip);
		
		JLabel PatientTip = new JLabel("\u75C5\u4EBA " + patient.getName());
		PatientTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		PatientTip.setBounds(41, 22, 100, 30);
		contentPane.add(PatientTip);
		
		JLabel doctorTip = new JLabel("\u533B\u751F " + doctor.getName());
		doctorTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		doctorTip.setBounds(636, 22, 100, 30);
		contentPane.add(doctorTip);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToDoctorMain();
			}
		});
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		backButton.setBounds(545, 353, 100, 40);
		contentPane.add(backButton);
		
		JButton beginTestButton = new JButton("\u5F00 \u59CB \u8BC4 \u4F30");
		beginTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beginTestPatient();
			}
		});
		beginTestButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		beginTestButton.setBounds(168, 353, 120, 40);
		contentPane.add(beginTestButton);
		
		comboBox = new JComboBox<String>();
		for(Template template : templates) {
			comboBox.addItem(template.getName());
		}
		comboBox.setBounds(522, 86, 200, 23);
		contentPane.add(comboBox);
		
		table = new JTable(new RecordTable());
		table.setBounds(62, 122, 660, 210);
		contentPane.add(table);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(62, 122, 660, 210);
		contentPane.add(js);
	}
	
	//开始评测按钮操作
	private void beginTestPatient() {
		String templateItem = (String)comboBox.getSelectedItem();
		DialogFrame.main(this,doctor, patient, doctorService.findTemplateByName(templateItem, templates), questions);
		this.setVisible(false);
	}
	
	//更新评测记录i奥格和数据
	public void repaintTable(DialogRecord record) {
		records.add(record);
		table.setModel(new RecordTable());
		doctorService.setRecords(records);
		doctorService.storeRecords();
		try {
			records = (ArrayList<DialogRecord>)MyTools.deepCopy(records);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"保存评估结果成功","提交成功",
				JOptionPane.DEFAULT_OPTION);
	}
	
	
	
	
	private class RecordTable extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private String[] colNames = {"姓名","性别","模板名称","模板类型","评估时间","评估人","得分","建议"};
		private Object[][] data;
		
		public RecordTable() {
				//获得对应数据的集合
				List<DialogRecord> list = records;
				if(list != null && !list.isEmpty()) {
					data = new Object[list.size()][8];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).getName();
						data[i][1] = list.get(i).getGender();
						data[i][2] = list.get(i).getTemplateName();
						data[i][3] = list.get(i).getTemplateType();
						data[i][4] = MyTools.calenderToString(list.get(i).getDialogTime());
						data[i][5] = list.get(i).getDoctorName();
						data[i][6] = list.get(i).getScore();
						data[i][7] = list.get(i).getResult();
					}
				}else {
					this.data = new Object[0][0];
				}
		}
		
		
		
		//返回结果行数
			@Override
			public int getRowCount() {
				return this.data.length;
			}

			//返回结果列数
			@Override
			public int getColumnCount() {
				return this.colNames.length;
			}

			//获得对应列名
			@Override
			public String getColumnName(int column) {
				return this.colNames[column];
			}

			//每列对应的类型
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
				//return data[0][columnIndex].getClass();
			}
			
			//返回哪行哪列对应的数据
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return data[rowIndex][columnIndex];
			}
			
			/**
			 * 哪列不能编辑
			 * 返回false 不可编辑
			 */
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			
			//修改表格中的数据
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				data[rowIndex][columnIndex] = aValue;
				/*修改表格数据后，显示改后结果*/
				fireTableCellUpdated(rowIndex, columnIndex);
			}
			
	}

}
