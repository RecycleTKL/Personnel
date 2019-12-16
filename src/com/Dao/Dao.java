package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Dao {
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
	protected static String dbUrl = "jdbc:mysql://localhost:3306/java?useSSL=false&serverTimezone=UTC";// 访问MySQL数据库的路径
	protected static String dbUser = "root";// 访问MySQL数据库的用户名
	protected static String dbPwd = "123456";// 访问MySQL数据库的密码
	public static Connection conn = null;// MySQL数据库的连接对象
	public static Object stmt;

	static {// 静态初始化Dao类
	     try {
	         if (conn == null) {
	             Class.forName(dbClassName);// 实例化MySQL数据库的驱动
	             conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// 连接MySQL数据库
	          }
	    } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         JOptionPane.showMessageDialog(null, "请将MySQL的JDBC驱动包复制到lib文件夹中。");// 捕获异常后，弹出提示框
	         System.exit(-1);// 系统停止运行
	    } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

	//对外提供一个方法来获取数据库连接
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
