package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.groupsix.Item;
import com.groupsix.dao.Dao;
import com.groupsix.dao.model.TbReckoning;
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

public class ReckoningInfoPanel extends JPanel implements MouseListener {
	private JTable leftTable;
	private JTable rightTable;
	public static String str;
	JScrollPane leftScrollPane;
	private int needSaveRow = -1;
	private int lastSelectedRow = -1;
	private final Vector<String> leftTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> leftTableValueV = new Vector<Vector<String>>();// 这个可以不用，后面你整理的时候再删，看我

	private DefaultTableModel leftTableModel;

	private final Vector<String> rightTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> rightTableValueV = new Vector<Vector<String>>();

	private DefaultTableModel rightTableModel;
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
		setLayout(new BorderLayout(0, 0));

		/********* 左边面板 **********/
		JPanel panel_left = new JPanel();
		add(panel_left, BorderLayout.WEST);

		panel_left.setLayout(new BorderLayout(0, 0));
		JPanel panel_leftButton = new JPanel();
		panel_left.add(panel_leftButton, BorderLayout.NORTH);

		JButton button = new JButton("\u5220\u9664\u8D26\u5957");
		panel_leftButton.add(button);

		JButton button_1 = new JButton("\u4FEE\u6539\u8D26\u5957");
		panel_leftButton.add(button_1);

		JButton button_2 = new JButton("\u65B0\u5EFA\u8D26\u5957");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel_leftButton.add(button_2);

		// 定义表格
		leftTable = new JTable();
		// leftTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
		leftTable.setModel(new DefaultTableModel() {// 防止表格单击格子进入编辑状态不方便
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置列宽调整模式
		leftTable.setFont(new Font("宋体", Font.PLAIN, 14));
		leftTable.addMouseListener(this);
		leftTableModel = (DefaultTableModel) leftTable.getModel();
		String[] leftTableHeads = new String[] { "序号", "账套名称", "账套说明" };
		leftTableModel.setColumnIdentifiers(leftTableHeads);
		// 将表格组件加入面板
		leftScrollPane = new JScrollPane();
		panel_left.add(leftScrollPane, BorderLayout.CENTER);
		leftScrollPane.setViewportView(leftTable);

		/*********** 右边面板 ***********/
		JPanel panel_right = new JPanel();
		add(panel_right, BorderLayout.EAST);
		panel_right.setLayout(new BorderLayout(0, 0));

		JPanel panel_rightButton = new JPanel();
		panel_right.add(panel_rightButton, BorderLayout.NORTH);

		JButton button_3 = new JButton("\u4FEE\u6539\u91D1\u989D");
		panel_rightButton.add(button_3);

		JButton button_4 = new JButton("\u5220\u9664\u9879\u76EE");
		panel_rightButton.add(button_4);

		JButton button_5 = new JButton("\u6DFB\u52A0\u9879\u76EE");
		panel_rightButton.add(button_5);

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
		rightTable.addMouseListener(this);
		rightTableModel = (DefaultTableModel) rightTable.getModel();
		String[] rightTableHeads = new String[] { "序号", "项目名称", "项目单位", "项目类型", "金额" };
		rightTableModel.setColumnIdentifiers(rightTableHeads);
		// 将表格组件加入面板
		rightScrollPane.setViewportView(rightTable);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JButton button_6 = new JButton("\u4FDD\u5B58");
		panel.add(button_6);

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		initLeftTable();
		int i = leftTable.getSelectedRow();
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
