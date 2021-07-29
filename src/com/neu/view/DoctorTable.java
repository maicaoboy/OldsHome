package com.neu.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;

public class DoctorTable extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"选择","用户名","姓名","出生日期","专长","职称","联系电话"};
	private Object[][] data;
	private IManagerService managerService = (com.neu.service.IManagerService) MyTools.getObject("managerservice");
	
	public DoctorTable() {
			//获得对应数据的集合
			List<Doctor> list = managerService.getDoctors();
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
