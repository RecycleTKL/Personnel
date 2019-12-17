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
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;

public class jiangcheng extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
	JComboBox comboBox_3 = new JComboBox();
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	private final ButtonGroup buttonGroup = new ButtonGroup(); 
	private JRadioButton radioButton,radioButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jiangcheng frame = new jiangcheng();
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
	public jiangcheng() {
		setBounds(100, 100, 907, 755);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton button = new JButton("\u65B0\u5EFA");
		panel.add(button);
		
		JButton button_1 = new JButton("\u4FDD\u5B58");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					baocunJCActionPerformed();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JLabel label = new JLabel("\u6240\u5728\u90E8\u95E8\uFF1A");
		label.setBounds(182, 47, 75, 18);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
				String dbUser = "root";
				String dbPwd = "123456";
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
		
		
		comboBox.setBounds(263, 44, 85, 24);
		
		radioButton = new JRadioButton("\u5956\u52B1");
		buttonGroup.add(radioButton);
		radioButton.setBounds(567, 43, 59, 27);
		
		JLabel label_1 = new JLabel("\u5956\u60E9\u5458\u5DE5\uFF1A");
		label_1.setBounds(182, 89, 75, 18);
		
		
		comboBox_1.setBounds(263, 86, 85, 24);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9"}));
		
		JLabel label_2 = new JLabel("\u539F    \u56E0\uFF1A");
		label_2.setBounds(182, 134, 77, 18);
		
		
		textArea.setBounds(263, 132, 395, 143);
		
		radioButton_1 = new JRadioButton("\u60E9\u7F5A");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(567, 82, 63, 27);
		
		JLabel label_3 = new JLabel("\u5185   \u5BB9\uFF1A");
		label_3.setBounds(185, 318, 72, 18);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(263, 316, 401, 143);
		
		JLabel label_4 = new JLabel("\u91D1   \u989D\uFF1A");
		label_4.setBounds(185, 482, 72, 18);
		
		textField = new JTextField();
		textField.setBounds(263, 479, 114, 24);
		textField.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5F00\u59CB\u65E5\u671F\uFF1A");
		label_5.setBounds(182, 531, 75, 18);
		
		textField_1 = new JTextField();
		textField_1.setBounds(262, 528, 115, 24);
		textField_1.setColumns(10);
		
		JLabel label_6 = new JLabel("\u7ED3\u675F\u65E5\u671F\uFF1A");
		label_6.setBounds(494, 531, 75, 18);
		
		textField_2 = new JTextField();
		textField_2.setBounds(571, 528, 115, 24);
		textField_2.setColumns(10);
		
		JLabel label_7 = new JLabel("\u6279\u51C6\u90E8\u95E8\uFF1A");
		label_7.setBounds(182, 579, 75, 18);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
				String dbUser = "root";
				String dbPwd = "123456";
				String dept_id = (String) comboBox_2.getSelectedItem();
				
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
		
		
		comboBox_2.setBounds(263, 576, 104, 24);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"", "\u4EBA\u4E8B\u7BA1\u7406\u90E8"}));
		
		JLabel label_8 = new JLabel("\u6279\u51C6\u4EBA\uFF1A");
		label_8.setBounds(494, 579, 72, 18);
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		comboBox_3.setBounds(571, 576, 87, 24);
		
		JLabel label_9 = new JLabel("\u6279\u51C6\u65E5\u671F\uFF1A");
		label_9.setBounds(182, 621, 75, 18);
		
		textField_3 = new JTextField();
		textField_3.setBounds(263, 618, 114, 24);
		textField_3.setColumns(10);
		panel_1.setLayout(null);
		panel_1.add(label);
		panel_1.add(comboBox);
		panel_1.add(radioButton);
		panel_1.add(label_1);
		panel_1.add(comboBox_1);
		panel_1.add(label_2);
		panel_1.add(textArea);
		panel_1.add(radioButton_1);
		panel_1.add(label_3);
		panel_1.add(textArea_1);
		panel_1.add(label_4);
		panel_1.add(textField);
		panel_1.add(label_5);
		panel_1.add(textField_1);
		panel_1.add(label_6);
		panel_1.add(textField_2);
		panel_1.add(label_7);
		panel_1.add(comboBox_2);
		panel_1.add(label_8);
		panel_1.add(comboBox_3);
		panel_1.add(label_9);
		panel_1.add(textField_3);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}
	/**
	 * 保存奖惩管理信息处理事件
	 * @throws ClassNotFoundException 
	 */
	private void baocunJCActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
		String dbUser = "root";// 访问MySQL数据库的用户名
		String dbPwd = "123456";// 访问MySQL数据库的密码
		Connection conn = null;
		PreparedStatement stmt = null;
		String record_id =(String) comboBox_1.getSelectedItem();
		String type = radioButton.isSelected()?radioButton.getText():radioButton_1.getText();
		String reason = textArea.getText();
		System.out.print(reason);
		String content = textArea_1.getText();
		System.out.print(content);
		String money = textField.getText();
		String start_date = textField_1.getText();
		String end_date = textField_2.getText();
		String ratifier_dept_id = (String) comboBox_2.getSelectedItem();
		String ratifier_record_id = (String) comboBox_2.getSelectedItem();
		String ratifier_date = textField_3.getText();
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	//插入奖惩信息表
			String sql = "insert into tb_rewards_and_punishment(record_id,type,reason,content,money,start_date,end_date,ratifier_dept_id,"
					+ "ratifier_record_id,ratifier_date) values(?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
		 	stmt.setObject(1, record_id);
            stmt.setString(2, type);  
            stmt.setString(3, reason);
            stmt.setString(4, content);
            stmt.setString(5, money);
            stmt.setString(6, start_date);
            stmt.setString(7, end_date);
            stmt.setString(8, ratifier_dept_id);
            stmt.setString(9, ratifier_record_id);
            stmt.setString(10, ratifier_date);
            stmt.executeUpdate();
            setNull();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 清空所有信息处理事件
	 */
	private void setNull() {
		textField.setText("");
		textField_1.setText("");
		comboBox.setSelectedItem("");
		comboBox_1.setSelectedItem("");
		comboBox_2.setSelectedItem("");
		comboBox_3.setSelectedItem("");
		textField_2.setText("");
		textField_3.setText("");	
		textArea.setText("");
		textArea_1.setText("");
	}
}
