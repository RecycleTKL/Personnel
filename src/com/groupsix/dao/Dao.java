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
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
	protected static String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
	protected static String dbManager = "studio";// 访问MySQL数据库的用户名
	protected static String dbPwd = "Mystudi0";// 访问MySQL数据库的密码
	protected static String second = null;
	public static Connection conn = null;// MySQL数据库的连接对象

	static {// 静态初始化Dao类
	     try {
	         if (conn == null) {
	             Class.forName(dbClassName);// 实例化MySQL数据库的驱动
	             conn = DriverManager.getConnection(dbUrl, dbManager, dbPwd);// 连接MySQL数据库
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
	
	/**********************数据操作************************/
	// 添加数据
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
	
	// 更新数据
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
	
	// 执行指定查询(简单方法名)
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}

	// 执行删除
	public static int delete(String sql) {
		return update(sql);
	}
	
	//查询指定数据集，返回ResultSet类型，用法如下
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
	
	//查询指定数据集，返回List结构,用法如下
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
	
	/********************系统用户信息操作*********************/
	// 添加用户信息的方法
	public static boolean addManager(TbManager manager) {
		if (manager == null)
			return false;
		return insert("insert tb_manager values('" + manager.getId() + "','" 
			+ manager.getPassword() + "','" + manager.getState() + "', " + manager.getPurview() + ")");
	}
	
	// 获取用户对象的方法

	
	// 修改用户信息的方法
	
	// 验证登录
	public static boolean checkLogin(String userStr, String passStr)
			throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from tb_manager where id='"
				+ userStr + "' and password='" + passStr + "'");
		if (rs == null)
			return false;
		return rs.next();
	}
	
	// 权限识别
	public static int getManagerPurview(String userStr) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select purview from tb_manager where id='"
				+ userStr + "'");
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

	// 修改用户密码方法
	public static int modifyPassword(String id, String oldpw, String pw) {
		return update("update tb_manager set password='" + pw + "' where password='"
				+ oldpw + "' and id = '" + id + "'");
	}
	/***************************************************/
	
}
