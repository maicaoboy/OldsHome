package com.neu.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.neu.pojo.Question;

/**
 * 
 * @author ������ݵ��к�
 *������
 */

public class MyTools {
private static Properties prop = new Properties();

	//��key��ö���
	public static Object getObject(String key) {
		String value = prop.getProperty(key);
		try {
			Class clazz = Class.forName(value);
			Method m = clazz.getMethod("getInstance");
			Object result = m.invoke(null);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//ת�������ʽ
	public static String calenderToString(Calendar calendar) {
		if(calendar == null) {
			return "";
		}else {
			return calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE);
		}
	}
	
	//ת�������ʽ����0000-00-00��ʽ������ת����Calendar
	public static Calendar StringtoCalendar(String time) {
		String[] times = time.split("-");
		if(times.length != 3) {
			return null;
		}else {
			Calendar c = Calendar.getInstance();
	        c.clear();
	        c.set(Calendar.YEAR, Integer.parseInt(times[0]));
	        c.set(Calendar.MONTH,Integer.parseInt(times[1]));
	        c.set(Calendar.DATE,Integer.parseInt(times[2]));
	        return c;
		}
	}
	
	//��ȸ������飬���ڱ�������޸ĵı��ݺͲ���
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {  
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
	    out.writeObject(src);  

	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
	    ObjectInputStream in = new ObjectInputStream(byteIn);  
	    @SuppressWarnings("unchecked")  
	    List<T> dest = (List<T>) in.readObject();  
	    return dest;  
	}  

	
	static {
		//���������ļ�
		try {
			prop.load(MyTools.class.getClassLoader().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//���������0��1��2�����ظ�
	public static int[] randomSelect() {
		int[] result = new int[3];
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		Random random = new Random();
		result[0] = random.nextInt(3);
		result[1] = random.nextInt(3);
		result[2] = random.nextInt(3);
		while(result[0] == result[1]) {
			result[1] = random.nextInt(3);
		}
		while(result[0] == result[2] || result[1] == result[2]) {
			result[2] = random.nextInt(3);
		}
		return result;
	}
	
	//�������
	public static int count(String answer, Question question) {
		int score = 1;
		if(answer.equals(question.getAnswer()[0])) {
			score = 5;
		}else if(answer.equals(question.getAnswer()[0])) {
			score = 3;
		}
		return score;
	}
	
}
