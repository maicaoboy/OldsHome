package com.neu.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

/**
 * 
 * @author 嚼着麦草的男孩
 *系统管理员主界面
 */

public class ManageDoctor_Main extends JFrame {
	private JPanel contentPane;
	private JTextField nameSearchTextField;
	private JTable table;
	private Login login;							//上一界面
	private IManagerService managerService;			//管理员服务
	private ArrayList<Doctor> doctors;				//医生集合
	private String[] positionList= {"全部","医生","护士","护工","药师","楼层管理员"};

	/**
	 * Launch the application.
	 */
	public static void main(Login login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageDoctor_Main frame = new ManageDoctor_Main();
					frame.managerService = (IManagerService) MyTools.getObject("managerservice");
					try {
						frame.doctors = (ArrayList<Doctor>) MyTools.deepCopy(frame.managerService.getDoctors());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.login = login;
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
	public ManageDoctor_Main() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameSearchTextField = new JTextField();
		nameSearchTextField.setBounds(480, 20, 150, 30);
		contentPane.add(nameSearchTextField);
		nameSearchTextField.setColumns(10);
		
		JButton nameSearchButton = new JButton("\u59D3\u540D\u67E5\u8BE2");
		nameSearchButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		nameSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByName(nameSearchTextField.getText());
			}
		});
		nameSearchButton.setBounds(658, 20, 90, 30);
		contentPane.add(nameSearchButton);
		
		JComboBox<String> positionSearchJComBox = new JComboBox(positionList);
		positionSearchJComBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		positionSearchJComBox.setBounds(40, 90, 150, 30);
		positionSearchJComBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String item = (String)cb.getSelectedItem();
		        combobox(item);
			}
		});
		contentPane.add(positionSearchJComBox);
		
		table = new JTable(new DoctorTable());
		table.setBounds(40, 125, 710, 220);
		contentPane.add(table);
		
		JButton addDoctorbutton = new JButton("\u6DFB\u52A0\u5DE5\u4F5C\u4EBA\u5458");
		addDoctorbutton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		addDoctorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDoctor();
			}
		});
		addDoctorbutton.setBounds(415, 90, 150, 30);
		contentPane.add(addDoctorbutton);
		
		JButton deleteDoctorButton = new JButton("\u5220 \u9664");
		deleteDoctorButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		deleteDoctorButton.setBackground(Color.RED);
		deleteDoctorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		deleteDoctorButton.setBounds(660, 90, 90, 30);
		contentPane.add(deleteDoctorButton);
		
		JButton saveButton = new JButton("\u4FDD \u5B58");
		saveButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setBounds(181, 371, 100, 28);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("\u8FD4 \u56DE");
		backButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		backButton.setBounds(491, 371, 100, 28);
		contentPane.add(backButton);
		
		JLabel managerTip = new JLabel("\u7CFB\u7EDF\u7BA1\u7406\u5458");
		managerTip.setFont(new Font("微软雅黑 Light", Font.PLAIN, 25));
		managerTip.setBounds(40, 20, 150, 30);
		contentPane.add(managerTip);
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(40, 125, 710, 220);
		contentPane.add(jp);
	}
	
	private void addDoctor() {
		ManageDoctor_Add.main(this,doctors);
		this.setVisible(false);
	}
	
	//JComboBox选择显示的职称的医生
	private void combobox(String item) {
		ArrayList<Doctor> doctors3 = null;
		try {
			doctors3 = (ArrayList<Doctor>) MyTools.deepCopy(doctors);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(item) {
		case "全部" -> {
			table.setModel(new DoctorTable(doctors3));
		}
		case "医生" -> {
			changeTable(doctors3, "医生");
		}
		case "护士" -> {
			changeTable(doctors3, "护士");
		}
		case "药师" -> {
			changeTable(doctors3, "药师");
		}
		case "楼层管理员" -> {
			changeTable(doctors3, "楼层管理员");
		}
		}
	}
	
	//删除按钮的操作
	private void delete() {
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Doctor doctor = managerService.doctorFindByUsername(doctors, (String)table.getValueAt(i, 1));
			if((boolean)table.getValueAt(i, 0) && doctor != null) {
				doctors.remove(doctor);
			}
		}
		table.setModel(new DoctorTable(doctors));
		JOptionPane.showMessageDialog(null,
				"成功添加工作人员，请不要忘记保存","添加成功",
				JOptionPane.DEFAULT_OPTION);
	}
	
	//返回操作
	private void back() {
		this.setVisible(false);
		login.setVisible(true);
	}
	
	//取得table用于更新表格
	public JTable getTable() {
		return table;
	}
	

	//使用名字查询按钮
	private void searchByName(String name) {
		ArrayList<Doctor> doctors2 = null;
		try {
			doctors2 = (ArrayList<Doctor>) MyTools.deepCopy(doctors);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Iterator<Doctor> iterator = doctors.iterator(); iterator.hasNext();) {
			Doctor doctor = (Doctor) iterator.next();
			if(!doctor.getName().contains(name)) {
				doctors2.remove(doctor);
			}
		}
		if(doctors2.isEmpty() || doctors2 == null) {
			JOptionPane.showMessageDialog(null,
			          "未找到此用户", "查询失败",
			          JOptionPane.ERROR_MESSAGE);
		}else {
			table.setModel(new DoctorTable(doctors2));
		}
	}
	
	//根据JCombobox选择的特长筛选
	private void changeTable(ArrayList<Doctor> doctors3,String speciality) {
		for (Iterator<Doctor> iterator = doctors3.iterator(); iterator.hasNext();) {
			Doctor doctor = (Doctor) iterator.next();
			if(!doctor.getPosition().equals(speciality)) {
				iterator.remove();
			}
		}
		if(doctors3.isEmpty() || doctors3 == null) {
			JOptionPane.showMessageDialog(null,
			          "未找到此职称的员工", "查找失败",
			          JOptionPane.ERROR_MESSAGE);
		}else {
			table.setModel(new DoctorTable(doctors3));
		}
	}
	
	//保存修改
	private void save() {
		for(int i = 0; i < table.getRowCount(); i++ ) {
			Doctor doctor = managerService.doctorFindByUsername(doctors, (String)table.getValueAt(i, 1));
			if(doctor != null) {
				doctor.setName((String)table.getValueAt(i, 2));
				doctor.setBirthday(MyTools.StringtoCalendar((String)table.getValueAt(i, 3)));
				doctor.setSpeciality((String)table.getValueAt(i, 4));
				doctor.setPosition((String)table.getValueAt(i, 5));
				doctor.setPhone((String)table.getValueAt(i, 6));
			}
		}
		managerService.setDoctorList(doctors);
		managerService.storeDoctorList();
		try {
			doctors = (ArrayList<Doctor>) MyTools.deepCopy(managerService.getDoctors());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
		          "已保存", "保存成功",
		          JOptionPane.DEFAULT_OPTION);
	}
	
	
}
