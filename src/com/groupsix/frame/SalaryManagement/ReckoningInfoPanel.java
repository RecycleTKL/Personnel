package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
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
	public static String str;
	JScrollPane leftScrollPane;
	private int needSaveRow = -1;
	private int lastSelectedRow = -1;
	private final Vector<String> leftTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> leftTableValueV = new Vector<Vector<String>>();// ������Բ��ã������������ʱ����ɾ������

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

		/*********������**********/
		JPanel panel_left = new JPanel();
		add(panel_left, BorderLayout.WEST);
		// ������
		leftTable = new JTable();
		// leftTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
		leftTable.setModel(new DefaultTableModel() {// ��ֹ��񵥻����ӽ���༭״̬������
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// �����п����ģʽ
		leftTable.setFont(new Font("����", Font.PLAIN, 14));
		leftTable.addMouseListener(this);
		leftTableModel = (DefaultTableModel) leftTable.getModel();
		String[] leftTableHeads = new String[] { "���", "��������", "����˵��" };
		leftTableModel.setColumnIdentifiers(leftTableHeads);
		
		panel_left.setLayout(new BorderLayout(0, 0));

		JPanel panel_leftButton = new JPanel();
		panel_left.add(panel_leftButton, BorderLayout.NORTH);

		JButton button = new JButton("\u5220\u9664\u8D26\u5957");
		panel_leftButton.add(button);

		JButton button_1 = new JButton("\u4FEE\u6539\u8D26\u5957");
		panel_leftButton.add(button_1);

		JButton button_2 = new JButton("\u65B0\u5EFA\u8D26\u5957");
		panel_leftButton.add(button_2);
		// ���������������
		leftScrollPane = new JScrollPane();
		panel_left.add(leftScrollPane);
		leftScrollPane.setViewportView(leftTable);

		/***********�ұ����***********/
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
		rightTable.setModel(new DefaultTableModel() {// ��ֹ��񵥻����ӽ���༭״̬������
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		rightTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// �����п����ģʽ
		rightTable.setFont(new Font("����", Font.PLAIN, 14));
		rightTable.addMouseListener(this);
		rightTableModel = (DefaultTableModel) leftTable.getModel();
		String[] rightTableHeads = new String[] { "���", "��Ŀ����", "��Ŀ��λ", "��Ŀ����", "���" };
		rightTableModel.setColumnIdentifiers(rightTableHeads);
		// ���������������
		rightScrollPane.setViewportView(rightTable);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JButton button_6 = new JButton("\u4FDD\u5B58");
		panel.add(button_6);

		initLeftTable();
	}

	// ��ʼ����߱�����ݵķ���
	public void initLeftTable() {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// ����MySQL���ݿ��·��
		String dbUser = "studio";
		String dbPwd = "Mystudi0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int num = leftTableModel.getRowCount();// ��ʼ��Ҫȷ����������
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

	// ����ӵ����ݿ�ɹ���ִ�еı������׷�Ӳ���
	// ʹ�÷�����������������ף��ɹ�ִ��insert���ݿ�Ĳ��������˳ɹ���ʾ���ִ��һ������Ĵ���:
	// refreshTable(tbreckoning, leftTableModel);
	public static void refreshTable(TbReckoning tbrecok, final DefaultTableModel dftm) {
		// int num = dftm.getRowCount(); //���ʱ��ͱ���ԭ���ı�������ݱ�ɾ��
		// for (int i = 0; i < num; i++)
		// dftm.removeRow(0);
		TbReckoning tbreckoning;// ��Ϣ����
		Item item = new Item();
		item.setName(tbrecok.getName());
		tbreckoning = Dao.getReckoning(item);
		Vector rowData = new Vector();
		rowData.add(tbreckoning.getId());// ���
		rowData.add(tbreckoning.getName());// tbreckoning.getName() ����
		rowData.add(tbreckoning.getExplain());
		System.out.println(tbreckoning.getId() + tbreckoning.getName());

		dftm.addRow(rowData);// �����������������ݣ���Ϣ��
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
	public void mouseClicked(MouseEvent e) {// ��Ҫ�������Ǹ�Init���ã�����ˢ�±��//�������click��ָ��������µĵ��//�����֪�������̵�//������
		// TODO �Զ����ɵķ������
		initLeftTable();
		int i = leftTable.getSelectedRow();

		System.out.print(leftTable.getValueAt(i, 1).toString());
		str = setText(leftTable.getValueAt(i, 0).toString());

	}

	private String setText(String string) {
		// TODO �Զ����ɵķ������
		return string;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

}
