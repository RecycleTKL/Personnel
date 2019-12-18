package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StaffListPanel extends JPanel {
	private JScrollPane scrollPane;
	public static String str;
	private JPanel panel2;
	private JPanel panel_1;
	private JTable table;
	private JButton button_new;
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public StaffListPanel() throws ClassNotFoundException {
		panel2=new JPanel();
		setBounds(0, 0, 907, 755);
		setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			panel_1.add(panel, BorderLayout.NORTH);
				JButton button_1 = new JButton("\u4FEE\u6539\u5458\u5DE5\u6863\u6848");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(table.getSelectedRow()==-1) {
							JOptionPane.showMessageDialog(StaffListPanel.this,
									"请选择要修改的员工!", "未选择修改对象", JOptionPane.WARNING_MESSAGE);
							return;
						}
						String str=table.getValueAt(table.getSelectedRow(), 0).toString();
						System.out.println(str);
						RecordInfoPanel.toModifyMode();
						PersonnelInfoManage.setTabPane(1);
						setVisible(true);
					}
				});
				
				button_new = new JButton("\u65B0\u5EFA\u5458\u5DE5\u6863\u6848");
				button_new.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RecordInfoPanel.toNewMode();
					}
				});
				panel.add(button_new);
				
				panel.add(button_1);
			
		try {
		Init();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private void Init() throws ClassNotFoundException {
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
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 598, 239);
		scrollPane.setViewportView(table);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		}
        catch(SQLException e1) {
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
	
}
