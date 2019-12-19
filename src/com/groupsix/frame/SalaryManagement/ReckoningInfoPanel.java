package com.groupsix.frame.SalaryManagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.groupsix.Item;
import com.groupsix.dao.Dao;
import com.groupsix.dao.model.TbReckoning;
import com.groupsix.dao.model.TbReckoningInfo;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class ReckoningInfoPanel extends JPanel implements MouseListener {
	private JTable leftTable;
	private JTable rightTable;
	private DefaultTableModel dftm;
	public static String str;
	JScrollPane leftScrollPane;
	private int needSaveRow = -1;
	private int lastSelectedRow = -1;
	private final Vector<String> leftTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> leftTableValueV = new Vector<Vector<String>>();// 这个可以不用，后面你整理的时候再删，看我

	private DefaultTableModel leftTableModel;

	private final Vector<String> rightTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> rightTableValueV = new Vector<Vector<String>>();

	private final DefaultTableModel rightTableModel = new DefaultTableModel(rightTableValueV, rightTableColumnV);
	private final Vector<TbReckoning> reckoningV = new Vector<TbReckoning>();
	private String reckoningExplain = "";
	private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private final int width = dimension.width;

	private final int height = dimension.height;

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
		setLayout(null);

		// 新建账套
		JButton addSetButton = new JButton("\u65B0\u5EFA\u8D26\u5957");
		addSetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new newsetManage().setVisible(true);

			}

		});
		addSetButton.setBounds(76, 13, 100, 27);
		add(addSetButton);
		// 修改账套
		JButton updateSetButton = new JButton("\u4FEE\u6539\u8D26\u5957");
		updateSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifysetManage().setVisible(true);
				// 怎么说？//
			}
		});
		updateSetButton.setBounds(177, 13, 100, 27);
		add(updateSetButton);
		// 删除账套
		JButton delSetButton = new JButton("\u5220\u9664\u8D26\u5957");
		delSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		delSetButton.setBounds(279, 13, 100, 27);
		add(delSetButton);
		// 添加项目
		JButton addItemButton = new JButton("\u6DFB\u52A0\u9879\u76EE");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addItemButton.setBounds(405, 13, 100, 27);
		add(addItemButton);
		// 删除项目
		JButton delItemButton = new JButton("\u5220\u9664\u9879\u76EE");
		delItemButton.setBounds(509, 13, 100, 27);
		add(delItemButton);
		// 修改金额
		JButton updateMoneyButton = new JButton("\u4FEE\u6539\u91D1\u989D");
		updateMoneyButton.setBounds(612, 13, 100, 27);
		add(updateMoneyButton);
		// 保存
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.setBounds(752, 13, 70, 27);
		add(saveButton);
		// leftScrollPane = new JScrollPane();
		// leftScrollPane.setBounds(14, 62, 294, 515);
		// add(leftScrollPane);

		// leftTable = new JTable();
		// leftScrollPane.setColumnHeaderView(leftTable);

		JScrollPane rightScrollPane = new JScrollPane();
		rightScrollPane.setBounds(14, 376, 879, 379);
		add(rightScrollPane);

		rightTable = new JTable();
		rightScrollPane.setColumnHeaderView(rightTable);

		initLeftTable();
	}

	// 初始化左边表格内容的方法
	public void initLeftTable() {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// 访问MySQL数据库的路径
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 定义表格
		leftTable = new JTable();
		// leftTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
		leftTable.setModel(new DefaultTableModel() {// 防止表格单击格子进入编辑状态不方便
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置列宽调整模式
		leftTableModel = (DefaultTableModel) leftTable.getModel();
		String[] leftTableHeads = new String[] { "序号", "账套名称", "账套说明" };
		leftTableModel.setColumnIdentifiers(leftTableHeads);
		leftTable.setFont(new Font("宋体", Font.PLAIN, 14));
		leftTable.addMouseListener(this);
		// 将表格组件加入面板
		leftScrollPane = new JScrollPane();
		leftScrollPane.setBounds(14, 53, 879, 310);
		add(leftScrollPane);
		leftScrollPane.setViewportView(leftTable);

		int num = dftm.getRowCount();//初始化要确保表格内清空
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);

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

			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 对添加到数据库成功后执行的表格内容追加操作
	//使用方法，单击了添加账套，成功执行insert数据库的操作弹出了成功提示框后，执行一条下面的代码:
	//refreshTable(tbreckoning, leftTableModel);
	public static void refreshTable(TbReckoning tbrecok, final DefaultTableModel dftm) {
		// int num = dftm.getRowCount();	//这个时候就保留原来的表格内数据别删了
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
	/*
	 * public void refreshItemAllRowValueV(int row) {
	 * rightTableValueV.removeAllElements(); if (reckoningV.size() > 0) {
	 * TbReckoning reckoning = reckoningV.get(row);
	 * textArea.setText(reckoning.getExplain()); Iterator<TbReckoningInfo>
	 * reckoningInfoIt = reckoning.getTbReckoningInfos().iterator(); int
	 * reckoningInfoNum = 1; while (reckoningInfoIt.hasNext()) { TbReckoningInfo
	 * reckoningInfo = reckoningInfoIt.next(); Vector<String> reckoningInfoV = new
	 * Vector<String>(); reckoningInfoV.add(reckoningInfoNum++ + "");
	 * reckoningInfoV.add(reckoningInfo.getTbAccountItem().getName());
	 * reckoningInfoV.add(reckoningInfo.getTbAccountItem().getUnit());
	 * reckoningInfoV.add(reckoningInfo.getTbAccountItem().getType());
	 * reckoningInfoV.add(reckoningInfo.getMoney().toString());
	 * rightTableValueV.add(reckoningInfoV); } } else { textArea.setText(""); }
	 * rightTableModel.setDataVector(rightTableValueV, rightTableColumnV); if
	 * (rightTable.getRowCount() > 0) rightTable.setRowSelectionInterval(0, 0);
	 * 
	 * }
	 */

	@Override
	public void mouseClicked(MouseEvent e) {// 主要是下面那个Init有用，用来刷新表格//你这里的click是指哪种情况下的点击//这个不知道二狗教的//？？？
		// TODO 自动生成的方法存根
		initLeftTable();
		int i = leftTable.getSelectedRow();

		System.out.print(leftTable.getValueAt(i, 1).toString());
		str = setText(leftTable.getValueAt(i, 0).toString());

	}

	private String setText(String string) {
		// TODO 自动生成的方法存根
		return string;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

}
