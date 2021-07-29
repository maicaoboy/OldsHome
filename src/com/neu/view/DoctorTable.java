package com.neu.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.neu.pojo.Doctor;
import com.neu.service.IManagerService;
import com.neu.tools.MyTools;

public class DoctorTable extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] colNames = {"ѡ��","�û���","����","��������","ר��","ְ��","��ϵ�绰"};
	private Object[][] data;
	private IManagerService managerService = (com.neu.service.IManagerService) MyTools.getObject("managerservice");
	
	public DoctorTable() {
			//��ö�Ӧ���ݵļ���
			List<Doctor> list = managerService.getDoctors();
			if(list != null && !list.isEmpty()) {
				data = new Object[list.size()][7];
				for (int i = 0; i < list.size(); i++) {
					data[i][0] = Boolean.FALSE;	//��ѡ��
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
				data[i][0] = Boolean.FALSE;	//��ѡ��
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
	
	
	
	//���ؽ������
		@Override
		public int getRowCount() {
			return this.data.length;
		}

		//���ؽ������
		@Override
		public int getColumnCount() {
			return this.colNames.length;
		}

		//��ö�Ӧ����
		@Override
		public String getColumnName(int column) {
			return this.colNames[column];
		}

		//ÿ�ж�Ӧ������
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if(columnIndex == 0) {
				return Boolean.class;
			}else {
				return String.class;
			}
			//return data[0][columnIndex].getClass();
		}
		
		//�����������ж�Ӧ������
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
		
		/**
		 * ���в��ܱ༭
		 * ����false ���ɱ༭
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex == 1) {
				return false;
			}
			return true;
		}
		
		//�޸ı���е�����
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = aValue;
			/*�޸ı�����ݺ���ʾ�ĺ���*/
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		
}
