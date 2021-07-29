package com.neu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LivingManage_Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField IntimeInput;
	private JTextField outTimeInput;
	private static Doctor_Main doctor_Main;

	/**
	 * Launch the application.
	 */
	public static void main(Doctor_Main doctor_Main1) {
		doctor_Main = doctor_Main1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LivingManage_Main frame = new LivingManage_Main();
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
	public LivingManage_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel buildingTip = new JLabel("\u697C\u53F7");
		buildingTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		buildingTip.setBounds(24, 42, 42, 24);
		contentPane.add(buildingTip);
		
		JComboBox buildingBox = new JComboBox();
		buildingBox.setModel(new DefaultComboBoxModel(new String[] {"1\u53F7\u697C", "2\u53F7\u697C", "3\u53F7\u697C"}));
		buildingBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		buildingBox.setBounds(62, 45, 67, 23);
		contentPane.add(buildingBox);
		
		JLabel floorTip = new JLabel("\u5C42\u6570");
		floorTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		floorTip.setBounds(139, 42, 42, 24);
		contentPane.add(floorTip);
		
		JComboBox floorBox = new JComboBox();
		floorBox.setModel(new DefaultComboBoxModel(new String[] {"1\u5C42", "2\u5C42", "3\u5C42", "4\u5C42", "5\u5C42", "6\u5C42"}));
		floorBox.setBounds(177, 45, 53, 23);
		contentPane.add(floorBox);
		
		JLabel RoomTip = new JLabel("\u623F\u95F4");
		RoomTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		RoomTip.setBounds(240, 42, 42, 24);
		contentPane.add(RoomTip);
		
		JComboBox roomBox = new JComboBox();
		roomBox.setModel(new DefaultComboBoxModel(new String[] {"1\u53F7\u623F", "2\u53F7\u623F", "3\u53F7\u623F", "4\u53F7\u623F", "5\u53F7\u623F", "6\u53F7\u623F", "7\u53F7\u623F", "8\u53F7\u623F"}));
		roomBox.setBounds(276, 45, 63, 23);
		contentPane.add(roomBox);
		
		JLabel buildingTip_1_1_1 = new JLabel("\u5E8A\u53F7");
		buildingTip_1_1_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		buildingTip_1_1_1.setBounds(349, 42, 42, 24);
		contentPane.add(buildingTip_1_1_1);
		
		JComboBox bedTip = new JComboBox();
		bedTip.setModel(new DefaultComboBoxModel(new String[] {"1\u53F7\u5E8A", "2\u53F7\u5E8A", "3\u53F7\u5E8A", "4\u53F7\u5E8A"}));
		bedTip.setBounds(391, 45, 63, 23);
		contentPane.add(bedTip);
		
		JButton searchroom = new JButton("\u67E5 \u8BE2");
		searchroom.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		searchroom.setBounds(473, 42, 93, 23);
		contentPane.add(searchroom);
		
		JLabel lblNewLabel = new JLabel("\u5E8A \u4F4D \u7BA1 \u7406");
		lblNewLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 24));
		lblNewLabel.setBounds(298, 3, 196, 32);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(65, 135, 632, 213);
		contentPane.add(table);

		JScrollPane js = new JScrollPane(table);
		js.setBounds(65, 135, 632, 213);
		contentPane.add(js);
		
		
		JLabel patientTip = new JLabel("\u5165\u4F4F\u4EBA");
		patientTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		patientTip.setBounds(24, 374, 53, 24);
		contentPane.add(patientTip);
		
		JComboBox patientBox = new JComboBox();
		patientBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		patientBox.setBounds(83, 376, 79, 23);
		contentPane.add(patientBox);
		
		JLabel inTimeTip = new JLabel("\u5165\u4F4F\u65F6\u95F4");
		inTimeTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		inTimeTip.setBounds(177, 374, 71, 24);
		contentPane.add(inTimeTip);
		
		JLabel outTimeTip = new JLabel("\u51FA\u9662\u65F6\u95F4");
		outTimeTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		outTimeTip.setBounds(367, 374, 67, 24);
		contentPane.add(outTimeTip);
		
		IntimeInput = new JTextField();
		IntimeInput.setBounds(243, 378, 114, 21);
		contentPane.add(IntimeInput);
		IntimeInput.setColumns(10);
		
		outTimeInput = new JTextField();
		outTimeInput.setColumns(10);
		outTimeInput.setBounds(439, 378, 114, 21);
		contentPane.add(outTimeInput);
		
		JButton liveIn = new JButton("\u5165 \u4F4F");
		liveIn.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		liveIn.setBounds(587, 377, 93, 23);
		contentPane.add(liveIn);
		
		JButton reverseBedButton = new JButton("\u5E8A\u4F4D\u5BF9\u8C03");
		reverseBedButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		reverseBedButton.setBounds(69, 102, 112, 23);
		contentPane.add(reverseBedButton);
		
		JButton outButtton = new JButton("\u9000 \u9662");
		outButtton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		outButtton.setBounds(189, 102, 93, 23);
		contentPane.add(outButtton);
		
		JButton btnNewButton = new JButton("\u8FD4 \u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(690, 377, 93, 23);
		contentPane.add(btnNewButton);
	}
	
	private void back() {
		doctor_Main.setVisible(true);
		this.dispose();
	}
	
	public class DoctorTable extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		private String[] colNames = {"选择","位置","入住开始时间","入住结束时间","状态","姓名","性别","年龄","编号"};
		private Object[][] data;
		private IManagerService managerService = (com.neu.service.IManagerService) MyTools.getObject("managerservice");
		
		public DoctorTable() {
				//获得对应数据的集合
				List<Doctor> list = managerService.getDoctors();
				if(list != null && !list.isEmpty()) {
					data = new Object[list.size()][9];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = Boolean.FALSE;	//复选框
						data[i][1] = list.get(i).getUsername();
						data[i][2] = list.get(i).getName();
						data[i][3] = MyTools.calenderToString(list.get(i).getBirthday());
						data[i][4] = list.get(i).getSpeciality();
						data[i][5] = list.get(i).getPosition();
						data[i][6] = list.get(i).getPhone();
					}
				}else {
					this.data = new Object[0][0];
				}
		}
		
		public DoctorTable(List<Doctor> list) {
			if(list != null && !list.isEmpty()) {
				data = new Object[list.size()][7];
				for (int i = 0; i < list.size(); i++) {
					data[i][0] = Boolean.FALSE;	//复选框
					data[i][1] = list.get(i).getUsername();
					data[i][2] = list.get(i).getName();
					data[i][3] = MyTools.calenderToString(list.get(i).getBirthday());
					data[i][4] = list.get(i).getSpeciality();
					data[i][5] = list.get(i).getPosition();
					data[i][6] = list.get(i).getPhone();
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
				if(columnIndex == 0) {
					return Boolean.class;
				}else {
					return String.class;
				}
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
				if(columnIndex == 1) {
					return false;
				}
				return true;
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
