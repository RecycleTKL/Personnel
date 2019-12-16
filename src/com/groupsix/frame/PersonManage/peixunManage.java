package com.groupsix.frame.PersonManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class peixunManage extends JInternalFrame{
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					peixunManage frame = new peixunManage();
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
	public peixunManage() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 907, 755);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u65B0\u5EFA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new peixun_add().setVisible(true);
			}
		});
		button.setBounds(326, 13, 80, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
				String dbUser = "root";
				String dbPwd = "123456";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				Vector rowData = new Vector();
				Vector<String> columnName = new Vector<String>();
				
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	columnName.add("序号");
				 	columnName.add("培训名称");
				 	columnName.add("培训内容");
				 	columnName.add("培训对象");
				 	columnName.add("培训开始时间");
				 	columnName.add("培训结束时间");
				 	columnName.add("培训单位");
				 	columnName.add("培训讲师");
				 	columnName.add("培训地点");			 	
				 	String sql="select * from tb_bring_up_content";
				 	stmt = conn.prepareStatement(sql);
				 	conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				 	rs = stmt.executeQuery();
				 	
				 	while(rs.next()) {	 	
				 		Vector<Object> V = new Vector<>();
				 		V.add(rs.getString(1));
				 		V.add(rs.getString(2));
				 		V.add(rs.getString(3));
				 		V.add(rs.getString(4));
				 		V.add(rs.getString(5));
				 		V.add(rs.getString(6));
				 		V.add(rs.getString(7));
				 		V.add(rs.getString(8));
				 		V.add(rs.getString(9));
				 		rowData.add(V);
				 	}
				 	DefaultTableCellRenderer de=new DefaultTableCellRenderer();
		            table.setDefaultRenderer(Object.class, de);
		            table=new JTable(rowData, columnName);
		            table.updateUI();
		            scrollPane.setViewportView(table);
		            this.setVisible(true);
		            stmt.close();
		            conn.close();
				}catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			} 

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}

		});
		button_1.setBounds(430, 13, 80, 27);
		getContentPane().add(button_1);
		
		
		scrollPane.setBounds(14, 68, 863, 557);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);

	}

	public void setMaximizedBounds(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
