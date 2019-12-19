package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.groupsix.Item;
import com.groupsix.dao.Dao;
import com.groupsix.dao.model.TbReckoning;
import com.groupsix.frame.PersonInfoManage.RecordInfoPanel;
import com.groupsix.frame.PersonInfoManage.StaffListPanel;
import com.mwq.frame.treatement.AddAccountItemDialog;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.hibernate.mapping.TbReckoningInfo;

import java.util.Iterator;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReckoningInfoPanel extends JPanel {
	private static JTable leftTable;
	private JTable rightTable;
	public static String str;
	static JScrollPane leftScrollPane;
	private static DefaultTableModel leftTableModel;

	private DefaultTableModel rightTableModel;
	private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReckoningInfoPanel frame = new ReckoningInfoPanel();
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
	public ReckoningInfoPanel() {
		setBounds(100, 100, 907, 755);
		setLayout(new BorderLayout(0, 0));

		/********* 左边面板 **********/
		JPanel panel_left = new JPanel();
		add(panel_left, BorderLayout.WEST);

		panel_left.setLayout(new BorderLayout(0, 0));
		JPanel panel_leftButton = new JPanel();
		panel_left.add(panel_leftButton, BorderLayout.NORTH);
		
		// 定义表格
		leftTable = new JTable();
		leftTable.setModel(new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		leftTable.getSelectionModel().addListSelectionListener(new SelectLeftTableListener());
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置列宽调整模式
		leftTable.setFont(new Font("宋体", Font.PLAIN, 14));
		leftTableModel = (DefaultTableModel) leftTable.getModel();
		String[] leftTableHeads = new String[] { "序号", "账套名称", "账套说明" };
		leftTableModel.setColumnIdentifiers(leftTableHeads);
		// 将表格组件加入面板
		leftScrollPane = new JScrollPane();
		panel_left.add(leftScrollPane, BorderLayout.CENTER);
		leftScrollPane.setViewportView(leftTable);
		
		initLeftTable();
		
		JButton button_2 = new JButton("\u65B0\u5EFA\u8D26\u5957");
		button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new newsetManage().setVisible(true);
			}
		});
		panel_leftButton.add(button_2);
		
		JButton button_1 = new JButton("\u4FEE\u6539\u8D26\u5957");
		button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(leftTable.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(ReckoningInfoPanel.this,
					"请选择要修改的账套!", "未选择修改对象", JOptionPane.WARNING_MESSAGE);
				return;
			}
			str=leftTable.getValueAt(leftTable.getSelectedRow(), 0).toString();
			try {
				initLeftTable();
				ModifysetManage modifyd = new ModifysetManage();
				modifyd.initModifyMode(str);
				modifyd.setVisible(true);
				initLeftTable();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_leftButton.add(button_1);

		JButton button = new JButton("\u5220\u9664\u8D26\u5957");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletesetManageActionPerformed();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_leftButton.add(button);

		/*********** 右边面板 ***********/
		JPanel panel_right = new JPanel();
		add(panel_right, BorderLayout.EAST);
		panel_right.setLayout(new BorderLayout(0, 0));

		JPanel panel_rightButton = new JPanel();
		panel_right.add(panel_rightButton, BorderLayout.NORTH);
		
				JButton button_5 = new JButton("\u6DFB\u52A0\u9879\u76EE");
				button_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int leftSelectedRow = leftTable.getSelectedRow();// 获得选中的账套
						if (leftSelectedRow == -1) {// 未选中任何账套
							JOptionPane.showMessageDialog(null, "请先建立账套！", "友情提示",
									JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
						} else {// 已选中账套
							addItem(leftSelectedRow);// 调用方法添加账套
						}
						
					}
				});
				panel_rightButton.add(button_5);
		
				JButton button_4 = new JButton("\u5220\u9664\u9879\u76EE");
				panel_rightButton.add(button_4);

		JButton button_3 = new JButton("\u4FEE\u6539\u91D1\u989D");
		panel_rightButton.add(button_3);

		JScrollPane rightScrollPane = new JScrollPane();
		panel_right.add(rightScrollPane);
		rightTable = new JTable();
		rightScrollPane.setColumnHeaderView(rightTable);
		rightTable.setModel(new DefaultTableModel() {// 防止表格单击格子进入编辑状态不方便
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		rightTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置列宽调整模式
		rightTable.setFont(new Font("宋体", Font.PLAIN, 14));
		rightTable.getSelectionModel().addListSelectionListener(new SelectRightTableListener());
		rightTableModel = (DefaultTableModel) rightTable.getModel();
		String[] rightTableHeads = new String[] { "序号", "项目名称", "项目单位", "项目类型", "金额" };
		rightTableModel.setColumnIdentifiers(rightTableHeads);
		// 将表格组件加入面板
		rightScrollPane.setViewportView(rightTable);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JButton button_6 = new JButton("\u4FDD\u5B58");
		panel.add(button_6);
		
	}

	/**
	 * 删除账套处理事件
	 * @throws ClassNotFoundException 
	 */
	protected void deletesetManageActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(dbClassName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			String SQL = "delete from tb_reckoning where id=?";
			stmt = conn.prepareStatement(SQL);	
			stmt.setString(1,leftTable.getValueAt(leftTable.getSelectedRow(), 0).toString());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "删除成功！", "请继续操作",
					JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		initLeftTable();
	}

	// 初始化左边表格内容的方法
	public static void initLeftTable() {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int num = leftTableModel.getRowCount();// 初始化要确保表格内清空
		for (int i = 0; i < num; i++)
			leftTableModel.removeRow(0);

		try {
			Class.forName(dbClassName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			String sql = "select * from tb_reckoning";
			stmt = conn.prepareStatement(sql);
			conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Vector<Object> rowData = new Vector<>();
				rowData.add(rs.getString(1));
				rowData.add(rs.getString(2));
				rowData.add(rs.getString(3));
				leftTableModel.addRow(rowData);
			}
			leftTable.updateUI();
			leftScrollPane.setViewportView(leftTable);
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	class SelectLeftTableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getValueIsAdjusting()) {
				int row = leftTable.getSelectedRow();// 选中行
				String id = leftTable.getValueAt(row, 0).toString();
				Dao.getReckoningItem(id);
			}
		}
	}
	private class SelectRightTableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO 自动生成的方法存根
			if (e.getValueIsAdjusting()) {
				//自己组织
			}
		}
	}

	public void addItem(int leftSelectedRow) {
		AddAccountItemDialog addAccountItemDialog = new AddAccountItemDialog();
		addAccountItemDialog.setBounds((WIDTH - 500) / 2, (HEIGHT - 375) / 2,
				500, 375);
		addAccountItemDialog.setVisible(true);// 弹出添加项目对话框
		//
		JTable itemTable = addAccountItemDialog.getTable();// 获得对话框中的表格对象
		int[] selectedRows = itemTable.getSelectedRows();// 获得选中行的索引
		if (selectedRows.length > 0) {// 有新添加的项目
			needSaveRow = leftSelectedRow;// 设置当前账套为需要保存的账套
			int defaultSelectedRow = rightTable.getRowCount();// 将选中行设置为新添加项目的第一行
			TbReckoning reckoning = reckoningV.get(leftSelectedRow);// 获得选中账套的对象
			for (int i = 0; i < selectedRows.length; i++) {// 通过循环向账套中添加项目
				String name = itemTable.getValueAt(selectedRows[i], 1)
						.toString();// 获得项目名称
				String unit = itemTable.getValueAt(selectedRows[i], 2)
						.toString();// 获得项目单位
				Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
						.getTbReckoningInfos().iterator();// 遍历账套中的现有项目
				boolean had = false;// 默认在现有项目中不包含新添加的项目
				while (reckoningInfoIt.hasNext()) {// 通过循环查找是否存在
					TbAccountItem accountItem = reckoningInfoIt.next()
							.getTbAccountItem();// 获得已有的项目对象
					if (accountItem.getName().equals(name)
							&& accountItem.getUnit().equals(unit)) {
						had = true;// 存在
						break;// 跳出循环
					}
				}
				if (!had) {// 如果没有则添加
					TbReckoningInfo reckoningInfo = new TbReckoningInfo();// 创建账套信息对象
					TbAccountItem accountItem = (TbAccountItem) dao
							.queryAccountItemByNameUnit(name, unit);// 获得账套项目对象
					accountItem.getTbReckoningInfos().add(reckoningInfo);// 建立从账套项目对象到账套信息对象的关联
					reckoningInfo.setTbAccountItem(accountItem);// 建立从账套信息对象到账套项目对象的关联
					reckoningInfo.setMoney(0);// 设置项目金额为0
					reckoningInfo.setTbReckoning(reckoning);// 建立从账套信息对象到账套对象的关联
					reckoning.getTbReckoningInfos().add(reckoningInfo);// 建立从账套对象到账套信息对象的关联
				}
			}
			refreshItemAllRowValueV(leftSelectedRow);// 同步刷新右侧的账套项目表格
			rightTable.setRowSelectionInterval(defaultSelectedRow,
					defaultSelectedRow);// 设置新添加项目的第一行为选中行
			addAccountItemDialog.dispose();// 销毁添加项目对话框
		}
	}
	
	// 对添加到数据库成功后执行的表格内容追加操作
	// 使用方法，单击了添加账套，成功执行insert数据库的操作弹出了成功提示框后，执行一条下面的代码:
	// refreshTable(tbreckoning, leftTableModel);
	public static void refreshTable(TbReckoning tbrecok, final DefaultTableModel dftm) {
		// int num = dftm.getRowCount(); //这个时候就保留原来的表格内数据别删了
		// for (int i = 0; i < num; i++)
		// dftm.removeRow(0);
		TbReckoning tbreckoning;// 信息对象
		Item item = new Item();
		item.setName(tbrecok.getName());
		tbreckoning = Dao.getReckoning(item);
		Vector rowData = new Vector();
		rowData.add(tbreckoning.getId());// 编号
		rowData.add(tbreckoning.getName());// tbreckoning.getName() 名称
		rowData.add(tbreckoning.getExplain());
		System.out.println(tbreckoning.getId() + tbreckoning.getName());

		dftm.addRow(rowData);// 向表格对象中添加行数据（信息）
	}

}
