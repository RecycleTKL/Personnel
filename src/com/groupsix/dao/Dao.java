package com.groupsix.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.groupsix.Item;
import com.groupsix.dao.model.TbManager;

public class Dao {
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL���ݿ������������
	protected static String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// ����MySQL���ݿ��·��
	protected static String dbManager = "studio";// ����MySQL���ݿ���û���
	protected static String dbPwd = "Mystudi0";// ����MySQL���ݿ������
	protected static String second = null;
	public static Connection conn = null;// MySQL���ݿ�����Ӷ���

	static {// ��̬��ʼ��Dao��
	     try {
	         if (conn == null) {
	             Class.forName(dbClassName);// ʵ����MySQL���ݿ������
	             conn = DriverManager.getConnection(dbUrl, dbManager, dbPwd);// ����MySQL���ݿ�
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
	
	/**********************���ݲ���************************/
	// �������
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// ��������
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// ִ��ָ����ѯ(�򵥷�����)
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}

	// ִ��ɾ��
	public static int delete(String sql) {
		return update(sql);
	}
	
	//��ѯָ�����ݼ�������ResultSet���ͣ��÷�����
	//ResultSet rs = findForResultSet("");
	//rs.next();
	//String s = rs.getString(column_index);
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
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//��ѯָ�����ݼ�������List�ṹ,�÷�����
//	List list = findForList("");
//	Iterator iterator = list.iterator();
//	while (iterator.hasNext()) {
//		List info = (List) iterator.next();
//		String columnOne = (String) info.get(0);
//		/*....*/
//	}
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/***************************************************/
	
	/********************ϵͳ�û���Ϣ����*********************/
	// ����û���Ϣ�ķ���
	public static boolean addManager(TbManager manager) {
		if (manager == null)
			return false;
		return insert("insert tb_manager values('" + manager.getId() + "','" 
			+ manager.getPassword() + "','" + manager.getState() + "', " + manager.getPurview() + ")");
	}
	
	// ��ȡ�û�����ķ���

	
	// �޸��û���Ϣ�ķ���
	
	// ��֤��¼
	public static boolean checkLogin(String userStr, String passStr)
			throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from tb_manager where id='"
				+ userStr + "' and password='" + passStr + "'");
		if (rs == null)
			return false;
		return rs.next();
	}
	
	// Ȩ��ʶ��
	public static int getManagerPurview(String userStr) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select purview from tb_manager where id='"
				+ userStr + "'");
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

	// �޸��û����뷽��
	public static int modifyPassword(String id, String oldpw, String pw) {
		return update("update tb_manager set password='" + pw + "' where password='"
				+ oldpw + "' and id = '" + id + "'");
	}
	/***************************************************/
	
}
