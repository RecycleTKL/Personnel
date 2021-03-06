package com.groupsix.frame.SalaryManagement;
import com.groupsix.dao.model.TbReckoning;
import com.groupsix.frame.SalaryManagement.ReckoningInfoPanel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class newsetManage extends JFrame {

	private JPanel contentPane;
	public static JTextField nameTextField;
	private static JTextArea explainTextArea;
	private DefaultTableModel leftTableModel;
	private boolean submit = true;
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
		super();
		setTitle("新建账套");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				submit = false;
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//账套名称：
		JLabel label = new JLabel("\u8D26\u5957\u540D\u79F0\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 27, 80, 18);
		contentPane.add(label);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(114, 24, 285, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		//账套说明：
		JLabel label_1 = new JLabel("\u8D26\u5957\u8BF4\u660E\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 68, 80, 18);
		contentPane.add(label_1);
		
		explainTextArea = new JTextArea();
		explainTextArea.setBounds(113, 65, 286, 130);
		contentPane.add(explainTextArea);
		explainTextArea.setColumns(10);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit = false;
				dispose();
			}
		});
		button.setBounds(245, 231, 70, 27);
		contentPane.add(button);
		
		JButton submitButton = new JButton("\u786E\u5B9A");
		submitButton.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
				String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
				String dbUser = "studio";
				String dbPwd = "Mystudi0";
				Connection conn = null;
				PreparedStatement stmt = null;
				TbReckoning tbrecok=null;
				String name = nameTextField.getText().trim();
				//tbrecok.setId(10);
				//tbrecok.setName(name);
				String explained = explainTextArea.getText().trim();
				//tbrecok.setExplain(explained);
				if (nameTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写账套名称！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (explainTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写账套说明！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			//	ReckoningInfoPanel.refreshTable(tbrecok, leftTableModel);
				dispose();
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	String sql2="insert into tb_reckoning(name,explained) values(?,?)";		 	
				 	stmt = conn.prepareStatement(sql2);
				 	stmt.setString(1, name); 
					stmt.setString(2, explained);
					stmt.executeUpdate();
					stmt.close();
					conn.close();
					st = explained;
					ReckoningInfoPanel.initLeftTable();
					
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
		submitButton.setBounds(329, 231, 70, 27);
		contentPane.add(submitButton);
	}

	public boolean isSubmit() {
		return submit;
	}
	
	public static String getNameTextField() {
		return nameTextField.getText();
	}
	
	public static String getExplainTextArea() {
		return explainTextArea.getText();
	}
}
