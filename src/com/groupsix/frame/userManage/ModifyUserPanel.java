package com.groupsix.frame.userManage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.studio.Item;
import com.studio.dao.Dao;
import com.studio.dao.model.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;

public class ModifyUserPanel extends JPanel {
	private JTextField textField_name;
	private JTextField textField_userid;
	private JTable table;
	private JTextField textField_search;
	private JComboBox comboBox_usergroup;
	private JComboBox comboBox_ano;

	/**
	 * Create the panel.
	 */
	public ModifyUserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_info = new JPanel();
		panel_info.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5BA2\u6237\u8D44\u6599", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "\u7ECF\u529E\u4EBA\u8D44\u6599", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_info, BorderLayout.NORTH);
		panel_info.setLayout(new GridLayout(0, 6, 5, 5));
		
		JLabel label_no = new JLabel("用户编号：");
		label_no.setFont(new Font("宋体", Font.PLAIN, 14));
		label_no.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_info.add(label_no);
		
		textField_userid = new JTextField();
		textField_userid.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_userid.setEditable(false);
		panel_info.add(textField_userid);
		textField_userid.setColumns(10);
		
		JLabel label_name = new JLabel("用户名：");
		label_name.setHorizontalAlignment(SwingConstants.RIGHT);
		label_name.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(label_name);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_name.setColumns(10);
		panel_info.add(textField_name);
		
		JLabel label_sex = new JLabel("用户组：");
		label_sex.setHorizontalAlignment(SwingConstants.RIGHT);
		label_sex.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(label_sex);
		
		comboBox_usergroup = new JComboBox();
		comboBox_usergroup.setModel(new DefaultComboBoxModel(new String[] {"管理�?", "库存经办�?", "经办�?"}));
		comboBox_usergroup.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(comboBox_usergroup);
		
		JLabel label_phone = new JLabel("经办人编号：");
		label_phone.setHorizontalAlignment(SwingConstants.RIGHT);
		label_phone.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(label_phone);
		
		comboBox_ano = new JComboBox();
		comboBox_ano.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(comboBox_ano);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_1);
		
		JButton button_modify = new JButton("修改");
		button_modify.addActionListener(new ModifyButtonActionListener());
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_2);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_3);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_4);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_5);
		
		Component horizontalGlue_6 = Box.createHorizontalGlue();
		panel_info.add(horizontalGlue_6);
		
		JButton button_initpw = new JButton("初始化密�?");
		button_initpw.addActionListener(new InitButtonActionListener());
		button_initpw.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(button_initpw);
		button_modify.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(button_modify);
		
		JButton button_reset = new JButton("重置");
		button_reset.addActionListener(new ResetButtonActionListener());
		button_reset.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_info.add(button_reset);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] { "用户编号", "用户�?", "用户�?", "经办人编�?", "�?有�?�姓�?" };
		dftm.setColumnIdentifiers(tableHeads);
		
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.getSelectionModel().addListSelectionListener(new SelectRowListener());
		scrollPane.setViewportView(table);
		
		JPanel panel_search = new JPanel();
		panel_search.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6A21\u7CCA\u68C0\u7D22", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_search, BorderLayout.SOUTH);
		
		JLabel label_keywordtip = new JLabel("包含关键字：");
		label_keywordtip.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_search.add(label_keywordtip);
		
		textField_search = new JTextField();
		textField_search.setName("");
		textField_search.setToolTipText("");
		textField_search.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_search.add(textField_search);
		textField_search.setColumns(30);
		
		JButton button_search = new JButton("�?�?");
		button_search.addActionListener(new SearchButtonActionListener(dftm));
		button_search.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_search.add(button_search);
		textField_search.addKeyListener(new KeyAdapter() {// 为文本框添加键盘时间的监�?
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')// 按下的按键是回车�?
					button_search.doClick();
			}
		});
		
		initAnoBox();
	}

	private class SelectRowListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO 自动生成的方法存�?
			if (e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();// 选中�?
				textField_userid.setText(table.getValueAt(row, 0).toString());
				textField_name.setText(table.getValueAt(row, 1).toString());
				comboBox_usergroup.setSelectedItem(table.getValueAt(row, 2).toString());
				comboBox_ano.getModel().setSelectedItem(table.getValueAt(row, 3));
			}
		}
	}
	
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbUser userInfo;// 用户信息
		
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setNo((String) info.get(0));
			item.setName((String) info.get(1));
			userInfo = Dao.getManagerInfo(item);

			try {
				ResultSet rs = Dao.findForResultSet("select aname from tb_agency where ano=" + userInfo.getAno());
				rs.next();
				Vector rowData = new Vector();
				rowData.add(userInfo.getUserid().trim());// 用户编号
				rowData.add(userInfo.getName().trim());// 用户�?
				String usergroup = null;
				switch(Integer.parseInt(userInfo.getUsergroup().toString().trim())) {
				case 1:
					usergroup = "管理�?";
					break;
				case 2:
					usergroup = "库存经办�?";
					break;
				case 3:
					usergroup = "经办�?";
					break;
				}
				rowData.add(usergroup);// 用户�?
				rowData.add(userInfo.getAno().trim());// 用户�?
				rowData.add(rs.getString(1));// �?有�?�姓�?
				dftm.addRow(rowData);// 向表格对象添加行数据（用户信息）
			} catch (SQLException e) {
				// TODO 自动生成�? catch �?
				e.printStackTrace();
			}
		}
	}
	
	public class SearchButtonActionListener implements ActionListener {
		private final DefaultTableModel dftm;
		private SearchButtonActionListener(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存�?
			List list = null;
			
			if(textField_search.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(ModifyUserPanel.this, "请输入要�?索的关键�?!",
						"提示：检索关键字为空", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				table.clearSelection();
				
				String key = textField_search.getText().trim();
				String sql = "select * from tb_userlist where userid like '%" + key + "%' or name like '%"
						+ key + "%' or ano like '%" + key + "%'";
				list = Dao.findForList(sql);
				updateTable(list, dftm);
			}
		}	
	}
	
	private class ModifyButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存�?
			if (textField_userid.getText().equals("") || textField_name.getText().equals("") 
					|| comboBox_usergroup.getSelectedIndex()==-1 || comboBox_ano.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "请填写全部信�?");
				return;// �?出应用程�?
			}
			
			int groupnum = comboBox_usergroup.getSelectedIndex()+1;
			String[] spilt = comboBox_ano.getSelectedItem().toString().split("\\(");
			String no = spilt[0];
			System.out.println(no);
			
			TbUser userInfo = new TbUser();// 用户信息
			userInfo.setUserid(textField_userid.getText().trim());// 设置用户编号
			userInfo.setName(textField_name.getText().trim());// 设置用户姓名
			userInfo.setUsergroup(""+groupnum);// 设置用户�?
			userInfo.setAno(no);// 设置经办人编�?
			
			if (Dao.updateUser(userInfo) == 1)// 更改供应商信息的数量等于1
				JOptionPane.showMessageDialog(ModifyUserPanel.this, "修改完成");// 弹出提示�?
			else// 更改供应商信息的数量不等�?1
				JOptionPane.showMessageDialog(ModifyUserPanel.this, "修改失败");// 弹出提示�?
		}
	}
	
	private final class InitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存�?
			String userid, sql;
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(ModifyUserPanel.this, "请�?�择对应用户!");// 弹出提示�?
				return;
			}
			userid = table.getValueAt(row, 0).toString();
			sql = "update tb_userlist set password='000000' where userid=" + userid;
			int confirm = JOptionPane.showConfirmDialog(ModifyUserPanel.this, "确认初始化用户密码吗?(初始值为000000)");
			if (confirm == JOptionPane.YES_OPTION) {// 点击“确认�?�键
				int rs = Dao.update(sql);// 获得修改信息的数�?
				if (rs > 0) {// 修改数大�?0
					JOptionPane.showMessageDialog(ModifyUserPanel.this, "用户密码重置成功!");// 弹出提示�?
				}
				else {
					JOptionPane.showMessageDialog(ModifyUserPanel.this, "修改失败!");// 弹出提示�?
				}
			}
		}
		
	}
	
	private class ResetButtonActionListener implements ActionListener {// “重置�?�按钮动作事件的监听
		public void actionPerformed(final ActionEvent e) {
			table.clearSelection();
			
			// 设置信息控件的内容为�?
			textField_userid.setText("");
			textField_name.setText("");
			comboBox_usergroup.setSelectedIndex(-1);
			comboBox_ano.setSelectedIndex(-1);
		}
	}
	
	public void initAnoBox() {// 初始化经办人编号下拉选择�?
		List agencyInfo = Dao.getAllAgencyInfo();// 获取经办人信�?
		List<Item> items = new ArrayList<Item>();// 创建数据公共表的集合
		comboBox_ano.removeAllItems();// 移除下拉列表中现有的经办人信�?
		for (Iterator iter = agencyInfo.iterator(); iter.hasNext();) {// 遍历list集合
			List element = (List) iter.next();// 获得集合中下�?个元�?
			Item item = new Item();// 创建数据表公共类对象
			item.setNo(element.get(0).toString().trim());// 设置编号属�??
			item.setName(element.get(1).toString().trim());// 设置名称信息
			if (items.contains(item))// 集合中包含数据表公共类对�?
				continue;// 跳过本次循环
			items.add(item);// 集合中不包含数据表公共类对象，项集合中添加数据表公共类对�?
			comboBox_ano.addItem(item.getNo() + "(" + item.getName() + ")");// 项下拉列表中添加数据表公共类对象
		}
	}
	
}
