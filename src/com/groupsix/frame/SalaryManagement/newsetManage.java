package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class newsetManage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public static String explain;
	public static String st;
	public void set_Explained(String explain) {
		this.explain=explain;
	}
	
	public static String get_Explained() {
		return explain;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newsetManage frame = new newsetManage();
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
	public newsetManage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 318);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u5957\u540D\u79F0\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 27, 80, 18);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(114, 24, 285, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8D26\u5957\u8BF4\u660E\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 68, 80, 18);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(113, 65, 286, 130);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setBounds(245, 231, 70, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u786E\u5B9A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
				String dbUser = "root";
				String dbPwd = "123456";
				Connection conn = null;
				PreparedStatement stmt = null;
				String name = textField.getText().trim();
				String explained = textField_1.getText().trim();
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	String sql2="insert into tb_reckoning(name,explained) values(?,?)";		 	
				 	stmt = conn.prepareStatement(sql2);
				 	stmt.setString(1, name); 
					stmt.setString(2, explained);
					stmt.executeUpdate();
					st = explained;
				//	stmt.close();
			     //   conn.close();
			        dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(329, 231, 70, 27);
		contentPane.add(button_1);
	}

}
