package com.groupsix.frame.PersonnelManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.groupsix.frame.PersonInfoManage.RecordInfoPanel;

public class peixun_add extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JTextField textField_7;
	private JPanel contentPane;
	JScrollPane scrollPane;
	public String str1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					peixun_add frame = new peixun_add();
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
	public peixun_add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 907, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBounds(0, 40, 891, 675);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u57F9\u8BAD\u540D\u79F0\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(44, 45, 80, 20);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(123, 45, 300, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u57F9\u8BAD\u5BF9\u8C61\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(445, 45, 80, 20);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(526, 45, 300, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u57F9\u8BAD\u5185\u5BB9\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(44, 90, 80, 20);
		panel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(123, 90, 300, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u57F9\u8BAD\u5355\u4F4D\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(445, 90, 80, 20);
		panel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(526, 90, 300, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_3 = new JLabel("\u57F9\u8BAD\u65F6\u95F4\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(44, 134, 80, 20);
		panel.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(123, 134, 128, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_4 = new JLabel("\u57F9\u8BAD\u8BB2\u5E08\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(445, 134, 80, 20);
		panel.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(526, 134, 300, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(303, 134, 120, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_5 = new JLabel("\u53C2\u8BAD\u4EBA\u5458\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(44, 218, 80, 20);
		panel.add(label_5);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 220, 703, 419);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JLabel label_6 = new JLabel("\u57F9\u8BAD\u5730\u70B9\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(44, 178, 80, 20);
		panel.add(label_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(123, 178, 703, 21);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label_7 = new JLabel("\u2014\u2014");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(242, 135, 72, 18);
		panel.add(label_7);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_1.add(btnNewButton);
		btnNewButton.setBounds(181, 7, 93, 23);
		
		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u53C2\u8BAD\u4EBA\u5458");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddPersonActionPerformed();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		btnNewButton_1.setBounds(286, 7, 140, 23);
		
		JButton button = new JButton("\u53D6\u6D88\u53C2\u8BAD\u8D44\u683C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CancelPersonActionPerformed();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					AddPersonActionPerformed();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(button);
		button.setBounds(440, 7, 140, 23);
		
		JButton savebutton = new JButton("\u4FDD\u5B58");
		panel_1.add(savebutton);
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					baocunPXActionPerformed();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		savebutton.setBounds(594, 7, 93, 23);

	}
	/**
	 * 取消参训人员处理事件
	 * @throws ClassNotFoundException 
	 */
	protected void CancelPersonActionPerformed() throws ClassNotFoundException {
		str1 = table.getValueAt(table.getSelectedRow(), 0).toString();
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	
		 	String sql = "delete from tb_record where id = ?";
		 	stmt = conn.prepareStatement(sql);	
			stmt.setString(1,str1);
			stmt.executeUpdate();
		 	
			String Sql = "delete from tb_duty_info where id = ?";
		 	stmt = conn.prepareStatement(Sql);	
			stmt.setString(1,str1);
			stmt.executeUpdate();
			
		 	stmt.close();
            conn.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 添加培训人员处理事件
	 */
	public void AddPersonActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector rowData = new Vector();
		Vector<String> columnName = new Vector<String>();
		
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	columnName.add("序号");
		 	columnName.add("档案编号");
		 	columnName.add("姓名");
		 	columnName.add("性别");
		 	columnName.add("部门");
		 	columnName.add("职务");
		 
		 	String sql2="select tb_record.id,tb_record.record_number,tb_record.name,tb_record.sex,tb_duty_info.dept_id,tb_duty_info.duty_id"
		 			+ " from tb_record,tb_duty_info where tb_record.id=tb_duty_info.id";
		 	stmt = conn.prepareStatement(sql2);
		 	rs = stmt.executeQuery();
		 	
		 	while(rs.next()) {
		 		Vector<Object> V = new Vector<>();
		 		V.add(rs.getString(1));
		 		V.add(rs.getString(2));
		 		V.add(rs.getString(3));
		 		V.add(rs.getString(4));
		 		V.add(rs.getString(5));
		 		V.add(rs.getString(6));
		 		rowData.add(V);
		 	}
            table=new JTable(rowData, columnName);
            table.getSelectionModel().addListSelectionListener(new SelectRowListener());
            table.updateUI();
            scrollPane.setViewportView(table);
            this.setVisible(true);
            
            JOptionPane.showMessageDialog(peixun_add.this,
					"添加成功!", "请继续操作", JOptionPane.WARNING_MESSAGE);
            
            stmt.close();
            conn.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}
	class SelectRowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getValueIsAdjusting()) {
				
			}
		}
	}
	/**
	 * 保存培训内容处理事件
	 * @throws ClassNotFoundException 
	 */
	private void baocunPXActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String name = textField.getText();
		String object = textField_1.getText();
		String content = textField_2.getText();
		String unit = textField_3.getText();
		String start_date = textField_4.getText();
		String end_date = textField_6.getText();
		String lecturer = textField_5.getText(); 
		String place = textField_7.getText();
		
		if("".equals(name.trim())||"".equals(object.trim())||"".equals(content.trim())||"".equals(content.trim())
				||"".equals(unit.trim())||"".equals(start_date.trim())||"".equals(end_date.trim())||
				"".equals(lecturer.trim())||"".equals(place.trim())) {
			JOptionPane.showMessageDialog(peixun_add.this,
					"保存信息成功!", "请继续操作", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	String sql = "insert into tb_bring_up_content(name,content,object,start_date,end_date,unit,lecturer,place) values(?,?,?,?,?,?,?,?)";
		 	stmt = conn.prepareStatement(sql);
		 	stmt.setString(1, name);
            stmt.setString(2, content); 
            stmt.setString(3, object); 
            stmt.setString(4, start_date);
            stmt.setString(5, end_date);
            stmt.setString(6, unit);
            stmt.setString(7, lecturer);
            stmt.setString(8, place);
           
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(peixun_add.this,
					"保存成功!", "请继续操作", JOptionPane.WARNING_MESSAGE);
            setNull();
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setNull() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
	}
}
