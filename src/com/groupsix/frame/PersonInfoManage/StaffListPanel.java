package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StaffListPanel extends JPanel {
	public static String str;
	private JPanel panel_1;
	private JButton button_new;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel dftm;

	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public StaffListPanel() throws ClassNotFoundException {
		// new JPanel();

		setBounds(0, 0, 907, 755);
		setLayout(new BorderLayout(0, 0));
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		JButton button_modify = new JButton("\u4FEE\u6539\u5458\u5DE5\u6863\u6848");
		button_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(StaffListPanel.this, "请选择要修改的员工!", "未选择修改对象",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				str = table.getValueAt(table.getSelectedRow(), 0).toString();
				try {
					initList();
					RecordInfoPanel.initForModifyMode(str);
					initList();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				PersonnelInfoManage.setTabPane(1);
				setVisible(true);
			}
		});

		button_new = new JButton("\u65B0\u5EFA\u5458\u5DE5\u6863\u6848");
		button_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordInfoPanel.initForNewMode();
				PersonnelInfoManage.setTabPane(1);
				setVisible(true);
			}
		});

		panel.add(button_new);
		panel.add(button_modify);

		
		// 定义表格
		table = new JTable();
		// leftTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
		table.setModel(new DefaultTableModel() {// 防止表格单击格子进入编辑状态不方便
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置列宽调整模式
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.getSelectionModel().addListSelectionListener(new SelectRowListener());
		dftm = (DefaultTableModel) table.getModel();
		String[] leftTableHeads = new String[] { "序号", "档案编号", "姓名", "性别", "部门", "职务" };
		dftm.setColumnIdentifiers(leftTableHeads);
		// 将表格组件加入面板
		//scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		initList();
	}

	public void initList() throws ClassNotFoundException {
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
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

			String sql2 = "select tb_record.id,tb_record.record_number,tb_record.name,tb_record.sex,tb_duty_info.dept_id,tb_duty_info.duty_id"
					+ " from tb_record,tb_duty_info where tb_record.id=tb_duty_info.id";
			stmt = conn.prepareStatement(sql2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Vector<Object> V = new Vector<>();
				V.add(rs.getString(1));
				V.add(rs.getString(2));
				V.add(rs.getString(3));
				V.add(rs.getString(4));
				V.add(rs.getString(5));
				V.add(rs.getString(6));
				dftm.addRow(V);
			}
			
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	class SelectRowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getValueIsAdjusting()) {

			}
		}
	}

}
