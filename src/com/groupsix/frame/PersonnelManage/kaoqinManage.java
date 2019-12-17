package com.groupsix.frame.PersonnelManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class kaoqinManage extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	JTextArea textArea = new JTextArea();
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
	JComboBox comboBox_3 = new JComboBox();
	JComboBox comboBox_4 = new JComboBox();
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kaoqinManage frame = new kaoqinManage();
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
	public kaoqinManage() {
		//setIconifiable(true);
		//setClosable(true);
		setBounds(100, 100, 907, 755);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(185, 13, 489, 56);
		add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("\u65B0\u5EFA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(108, 13, 80, 30);
		panel.add(button);
		
		JButton button_1 = new JButton("\u4FDD\u5B58");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					baocunKQMActionPerformed();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(269, 13, 80, 30);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(14, 82, 863, 564);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u6240\u5728\u90E8\u95E8\uFF1A");
		label.setBounds(96, 121, 80, 24);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(label);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
				String dbUser = "studio";
				String dbPwd = "Mystudi0";
				String dept_id = (String) comboBox.getSelectedItem();
				
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	String sql="select tb_duty_info.dept_id,tb_record.name from tb_duty_info,tb_record where tb_record.id=tb_duty_info.id and tb_duty_info.dept_id=?";
				 	stmt = conn.prepareStatement(sql);
				 	stmt.setString(1, dept_id);
				 	rs = stmt.executeQuery();
				 	 
				 	while(rs.next()){ 
				 		comboBox_1.addItem(rs.getString("tb_record.name")); 
				 	} 
				 	stmt.close();
				 	conn.close();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		comboBox.setBounds(180, 121, 100, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2"}));
		panel_1.add(comboBox);
		
		JLabel label_1 = new JLabel("\u8003\u52E4\u5458\u5DE5\uFF1A");
		label_1.setBounds(398, 124, 80, 24);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(label_1);
		
		
		comboBox_1.setBounds(475, 121, 154, 24);
		panel_1.add(comboBox_1);
		
		JLabel label_2 = new JLabel("\u8003\u52E4\u7C7B\u578B\uFF1A");
		label_2.setBounds(96, 171, 80, 24);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(label_2);
		
		
		comboBox_2.setBounds(180, 171, 70, 24);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "\u52A0\u73ED", "\u8FDF\u5230", "\u65E9\u9000", "\u4E8B\u5047", "\u75C5\u5047"}));
		panel_1.add(comboBox_2);
		
		JLabel lblNewLabel = new JLabel("\u8003\u52E4\u8BF4\u660E\uFF1A");
		lblNewLabel.setBounds(96, 213, 80, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 213, 601, 156);
		panel_1.add(scrollPane);
		
		
		scrollPane.setViewportView(textArea);
		
		JLabel label_3 = new JLabel("\u5F00\u59CB\u65E5\u671F\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(96, 389, 80, 24);
		panel_1.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(180, 389, 100, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel label_4 = new JLabel("\u7ED3\u675F\u65E5\u671F\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(398, 389, 80, 24);
		panel_1.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(475, 389, 100, 24);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_5 = new JLabel("\u6279\u51C6\u90E8\u95E8\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(96, 433, 80, 24);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("\u6279\u51C6\u4EBA\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(398, 433, 80, 24);
		panel_1.add(label_6);
		
		
		comboBox_3.setBounds(475, 433, 100, 24);
		panel_1.add(comboBox_3);
		
		JLabel label_7 = new JLabel("\u6279\u51C6\u65E5\u671F\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(96, 470, 80, 24);
		panel_1.add(label_7);
		
		
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
				String dbUser = "studio";
				String dbPwd = "Mystudi0";
				String dept_id = (String) comboBox_4.getSelectedItem();
				
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	String sql1="select tb_duty_info.dept_id,tb_record.name from tb_duty_info,tb_record where tb_record.id=tb_duty_info.id and tb_duty_info.dept_id=?";
				 	stmt = conn.prepareStatement(sql1);
				 	stmt.setString(1, dept_id);
				 	rs = stmt.executeQuery();
				 	 
				 	while(rs.next()){ 
				 		comboBox_3.addItem(rs.getString("tb_record.name")); 
				 	} 
				 	stmt.close();
				 	conn.close();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"", "\u4EBA\u4E8B\u7BA1\u7406\u90E8"}));
		comboBox_4.setBounds(180, 433, 100, 24);
		panel_1.add(comboBox_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 470, 104, 24);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

	}
	/**
	 * 保存考勤信息表处理事件
	 * @throws ClassNotFoundException 
	 */
	private void baocunKQMActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		String record_id = (String) comboBox_1.getSelectedItem();
		String account_item_id = (String) comboBox_2.getSelectedItem();
		String explained = textArea.getText().trim();
		String start_data = textField.getText().trim();
		String end_data = textField_1.getText().trim();
		String ratifier_dept_id = (String) comboBox_4.getSelectedItem();
		String ratifier_record_id = (String) comboBox_4.getSelectedItem();
		String ratifier_data = textField_2.getText().trim();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	String sql2="insert into tb_timecard(record_id,account_item_id,explained,start_date,end_date,ratifier_dept_id,"
		 			+ "ratifier_record_id,ratifier_date) values(?,?,?,?,?,?,?,?)";		 	
		 	stmt = conn.prepareStatement(sql2);
		 	stmt.setString(1, record_id); 
			stmt.setString(2, account_item_id);
			stmt.setString(3, explained);
			stmt.setString(4, start_data);
			stmt.setString(5, end_data);
			stmt.setString(6, ratifier_dept_id);
			stmt.setString(7, ratifier_record_id);
			stmt.setString(8, ratifier_data);
			stmt.executeUpdate();
		 	 
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
}
