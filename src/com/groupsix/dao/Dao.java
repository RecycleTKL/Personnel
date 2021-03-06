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
import java.util.Vector;

import javax.swing.JOptionPane;

import com.groupsix.Item;
import com.groupsix.dao.model.TbAccountItem;
import com.groupsix.dao.model.TbManager;
import com.groupsix.dao.model.TbReckoning;

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
	
	/**********************标准数据库操作************************/
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
	
	public static TbAccountItem queryAccountItemByNameUnit(String name, String unit) throws Exception {
		TbAccountItem tb_accountItem = new TbAccountItem();
		ResultSet rs = query("select * from TbAccountItem where name='" + name
				+ "' and unit='" + unit + "'");
		if(rs.next()==false) {
			return null;
		}
		tb_accountItem.setId(Integer.parseInt(rs.getString(1)));
		tb_accountItem.setName(rs.getString(2));
		tb_accountItem.setType(rs.getString(3));
		tb_accountItem.setUnit(rs.getString(4));
		tb_accountItem.setIsTimecard(rs.getString(5));
		return tb_accountItem;
	}
	
	/********************tb_record档案表操作*********************/
	//获取档案表所有
	public static List getAllTbRecordInfo() {
		List list = findForList("select id, record_number, name from tb_record");
		return list;
	}
	
	public static String getMaxRecordNumber() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select max(record_number) from tb_record");
		rs.next();
		return rs.getString(1);
	}
	
	/********************系统用户信息操作*********************/
	// 添加用户信息的方法
	public static boolean addManager(TbManager manager) {
		if (manager == null)
			return false;
		return insert("insert tb_manager values(" + manager.getId() + ",'" 
			+ manager.getPassword() + "','" + manager.getState() + "', '" + manager.getPurview() + "')");
	}
	
	// 获取用户对象的方法
	public static TbManager getManagerInfo(Item item) {
		String where = "id='" + item.getNo() + "'";
		ResultSet rs = findForResultSet("select * from tb_manager where "
				+ where);
		TbManager manager = new TbManager();
		try {
			if (rs.next()) {
				manager.setId(Integer.parseInt(rs.getString("id").trim()));
				manager.setPassword(rs.getString("password").trim());
				manager.setState(rs.getString("state").trim());
				manager.setPurview(rs.getString("purview").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	
	// 验证登录
	public static boolean checkLogin(String userStr, String passStr)
			throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from tb_manager, tb_record where"
				+ " tb_manager.id=tb_record.id and tb_record.record_number='"
				+ userStr + "' and tb_manager.password='" + passStr + "'");
		if (rs == null)
			return false;
		return rs.next();
	}
	
	// 权限识别
	public static String getManagerPurview(String userStr) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select purview from tb_manager, tb_record where tb_record.record_number='"
				+ userStr + "' and tb_manager.id=tb_record.id");
		rs.next();
		return rs.getString(1);
	}

	// 修改用户密码方法
	public static int modifyPassword(String id, String oldpw, String pw) {
		return update("update tb_manager set password='" + pw + "' where password='"
				+ oldpw + "' and id = '" + id + "'");
	}
	/***************************************************/

	public static ResultSet getReckoningItem(String id) {
		// TODO Auto-generated method stub
		return query("select * from tb_reckoning_item where id="+id);
	}

	public static TbReckoning getReckoning(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
