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

		/********* ������ **********/
		JPanel panel_left = new JPanel();
		add(panel_left, BorderLayout.WEST);

		panel_left.setLayout(new BorderLayout(0, 0));
		JPanel panel_leftButton = new JPanel();
		panel_left.add(panel_leftButton, BorderLayout.NORTH);
		
		// ������
		leftTable = new JTable();
		leftTable.setModel(new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		leftTable.getSelectionModel().addListSelectionListener(new SelectLeftTableListener());
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// �����п����ģʽ
		leftTable.setFont(new Font("����", Font.PLAIN, 14));
		leftTableModel = (DefaultTableModel) leftTable.getModel();
		String[] leftTableHeads = new String[] { "���", "��������", "����˵��" };
		leftTableModel.setColumnIdentifiers(leftTableHeads);
		// ���������������
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
					"��ѡ��Ҫ�޸ĵ�����!", "δѡ���޸Ķ���", JOptionPane.WARNING_MESSAGE);
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

		/*********** �ұ���� ***********/
		JPanel panel_right = new JPanel();
		add(panel_right, BorderLayout.EAST);
		panel_right.setLayout(new BorderLayout(0, 0));

		JPanel panel_rightButton = new JPanel();
		panel_right.add(panel_rightButton, BorderLayout.NORTH);
		
				JButton button_5 = new JButton("\u6DFB\u52A0\u9879\u76EE");
				button_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int leftSelectedRow = leftTable.getSelectedRow();// ���ѡ�е�����
						if (leftSelectedRow == -1) {// δѡ���κ�����
							JOptionPane.showMessageDialog(null, "���Ƚ������ף�", "������ʾ",
									JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
						} else {// ��ѡ������
							addItem(leftSelectedRow);// ���÷����������
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
		rightTable.setModel(new DefaultTableModel() {// ��ֹ��񵥻����ӽ���༭״̬������
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		rightTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// �����п����ģʽ
		rightTable.setFont(new Font("����", Font.PLAIN, 14));
		rightTable.getSelectionModel().addListSelectionListener(new SelectRightTableListener());
		rightTableModel = (DefaultTableModel) rightTable.getModel();
		String[] rightTableHeads = new String[] { "���", "��Ŀ����", "��Ŀ��λ", "��Ŀ����", "���" };
		rightTableModel.setColumnIdentifiers(rightTableHeads);
		// ���������������
		rightScrollPane.setViewportView(rightTable);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JButton button_6 = new JButton("\u4FDD\u5B58");
		panel.add(button_6);
		
	}

	/**
	 * ɾ�����״����¼�
	 * @throws ClassNotFoundException 
	 */
	protected void deletesetManageActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";// ����MySQL���ݿ��·��
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
			JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "���������",
					JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		initLeftTable();
	}

	// ��ʼ����߱�����ݵķ���
	public static void initLeftTable() {
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
				int row = leftTable.getSelectedRow();// ѡ����
				String id = leftTable.getValueAt(row, 0).toString();
				Dao.getReckoningItem(id);
			}
		}
	}
	private class SelectRightTableListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO �Զ����ɵķ������
			if (e.getValueIsAdjusting()) {
				//�Լ���֯
			}
		}
	}

	public void addItem(int leftSelectedRow) {
		AddAccountItemDialog addAccountItemDialog = new AddAccountItemDialog();
		addAccountItemDialog.setBounds((WIDTH - 500) / 2, (HEIGHT - 375) / 2,
				500, 375);
		addAccountItemDialog.setVisible(true);// ���������Ŀ�Ի���
		//
		JTable itemTable = addAccountItemDialog.getTable();// ��öԻ����еı�����
		int[] selectedRows = itemTable.getSelectedRows();// ���ѡ���е�����
		if (selectedRows.length > 0) {// ������ӵ���Ŀ
			needSaveRow = leftSelectedRow;// ���õ�ǰ����Ϊ��Ҫ���������
			int defaultSelectedRow = rightTable.getRowCount();// ��ѡ��������Ϊ�������Ŀ�ĵ�һ��
			TbReckoning reckoning = reckoningV.get(leftSelectedRow);// ���ѡ�����׵Ķ���
			for (int i = 0; i < selectedRows.length; i++) {// ͨ��ѭ���������������Ŀ
				String name = itemTable.getValueAt(selectedRows[i], 1)
						.toString();// �����Ŀ����
				String unit = itemTable.getValueAt(selectedRows[i], 2)
						.toString();// �����Ŀ��λ
				Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
						.getTbReckoningInfos().iterator();// ���������е�������Ŀ
				boolean had = false;// Ĭ����������Ŀ�в���������ӵ���Ŀ
				while (reckoningInfoIt.hasNext()) {// ͨ��ѭ�������Ƿ����
					TbAccountItem accountItem = reckoningInfoIt.next()
							.getTbAccountItem();// ������е���Ŀ����
					if (accountItem.getName().equals(name)
							&& accountItem.getUnit().equals(unit)) {
						had = true;// ����
						break;// ����ѭ��
					}
				}
				if (!had) {// ���û�������
					TbReckoningInfo reckoningInfo = new TbReckoningInfo();// ����������Ϣ����
					TbAccountItem accountItem = (TbAccountItem) dao
							.queryAccountItemByNameUnit(name, unit);// ���������Ŀ����
					accountItem.getTbReckoningInfos().add(reckoningInfo);// ������������Ŀ����������Ϣ����Ĺ���
					reckoningInfo.setTbAccountItem(accountItem);// ������������Ϣ����������Ŀ����Ĺ���
					reckoningInfo.setMoney(0);// ������Ŀ���Ϊ0
					reckoningInfo.setTbReckoning(reckoning);// ������������Ϣ�������׶���Ĺ���
					reckoning.getTbReckoningInfos().add(reckoningInfo);// ���������׶���������Ϣ����Ĺ���
				}
			}
			refreshItemAllRowValueV(leftSelectedRow);// ͬ��ˢ���Ҳ��������Ŀ���
			rightTable.setRowSelectionInterval(defaultSelectedRow,
					defaultSelectedRow);// �����������Ŀ�ĵ�һ��Ϊѡ����
			addAccountItemDialog.dispose();// ���������Ŀ�Ի���
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

}
