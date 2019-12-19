package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.groupsix.frame.PersonInfoManage.StaffListPanel;

public class ModifysetManage extends JFrame {
	
	private JPanel contentPane1;
	public static JTextField nameTextField;
	public static JTextArea explainTextArea;
	private String name;
	public static String st;
	public void set_Explain(String name) {
		this.name = name;
	}

	public String get_Explain() {
		return name;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifysetManage frame = new ModifysetManage();
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
	public ModifysetManage() {
		super();
		setTitle("账套维护");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		setLocationRelativeTo(null);
		System.out.print(ReckoningInfoPanel.str);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		//账套名称：
		JLabel label = new JLabel("\u8D26\u5957\u540D\u79F0\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 27, 80, 18);
		contentPane1.add(label);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(114, 24, 285, 24);
		contentPane1.add(nameTextField);
		nameTextField.setColumns(10);
		//账套说明：
		JLabel label_1 = new JLabel("\u8D26\u5957\u8BF4\u660E\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 68, 80, 18);
		contentPane1.add(label_1);
		
		explainTextArea = new JTextArea();
		explainTextArea.setBounds(113, 65, 286, 130);
		contentPane1.add(explainTextArea);
		explainTextArea.setColumns(10);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();//算了就这样把
			}
		});
		button.setBounds(245, 231, 70, 27);
		contentPane1.add(button);
		
		JButton submitButton = new JButton("\u786E\u5B9A");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ModifysetManageActionPerformed();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		submitButton.setBounds(329, 231, 70, 27);
		contentPane1.add(submitButton);
	}
	/**
	 * 修改账套管理处理事件
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void ModifysetManageActionPerformed() throws ClassNotFoundException, SQLException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		String name = nameTextField.getText().trim();
		String explained = explainTextArea.getText().trim();
		
		
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	String sql2="update tb_reckoning set id = ?,name=?,explained=? where id=?";		 	
		 	stmt = conn.prepareStatement(sql2);
		 	stmt.setString(1, ReckoningInfoPanel.str); 
			stmt.setString(2, name);
			stmt.setString(3, explained);
			stmt.setString(4, ReckoningInfoPanel.str);
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "修改成功！", "请继续操作",
					JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		} 
		dispose();
		
	}

	public static void initModifyMode(String id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//System.out.print(StaffListPanel.str);
		String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			String sql1 = "select * from tb_reckoning where id = ?";
			conn.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				System.out.println("ok");
				nameTextField.setText(rs.getString(2));
				explainTextArea.setText(rs.getString(3));
			}
			
			
			
			stmt.close();
			conn.close();
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		} 

	}

}
