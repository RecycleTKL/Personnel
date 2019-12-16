package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Dao {
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL���ݿ������������
	protected static String dbUrl = "jdbc:mysql://localhost:3306/java?useSSL=false&serverTimezone=UTC";// ����MySQL���ݿ��·��
	protected static String dbUser = "root";// ����MySQL���ݿ���û���
	protected static String dbPwd = "123456";// ����MySQL���ݿ������
	public static Connection conn = null;// MySQL���ݿ�����Ӷ���
	public static Object stmt;

	static {// ��̬��ʼ��Dao��
	     try {
	         if (conn == null) {
	             Class.forName(dbClassName);// ʵ����MySQL���ݿ������
	             conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// ����MySQL���ݿ�
	          }
	    } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         JOptionPane.showMessageDialog(null, "�뽫MySQL��JDBC���������Ƶ�lib�ļ����С�");// �����쳣�󣬵�����ʾ��
	         System.exit(-1);// ϵͳֹͣ����
	    } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

	//�����ṩһ����������ȡ���ݿ�����
	public Dao(){
		
	}
	
	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
