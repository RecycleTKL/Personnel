package com.groupsix.frame.userManage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.groupsix.Item;
import com.groupsix.dao.Dao;
import com.groupsix.dao.model.TbManager;
//import com.groupsix.dao.model.TbUser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;

public class AddUserPanel extends JPanel {
	private JTable table;
	private JPasswordField passwordField_pw;
	private JPasswordField passwordField_checkpw;
	private JComboBox comboBox_purview;
	private JComboBox comboBox_recordNumber;

	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(250, 400));
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] { "用户编号", "所有者" };
		dftm.setColumnIdentifiers(tableHeads);
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6DFB\u52A0\u7528\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 14), null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 87, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_ano = new JLabel("员工档案编号：");
		label_ano.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_ano = new GridBagConstraints();
		gbc_label_ano.anchor = GridBagConstraints.EAST;
		gbc_label_ano.insets = new Insets(0, 0, 5, 5);
		gbc_label_ano.gridx = 4;
		gbc_label_ano.gridy = 5;
		panel.add(label_ano, gbc_label_ano);
		
		comboBox_recordNumber = new JComboBox();
		comboBox_recordNumber.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBox_ano = new GridBagConstraints();
		gbc_comboBox_ano.gridwidth = 4;
		gbc_comboBox_ano.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_ano.fill = GridBagConstraints.BOTH;
		gbc_comboBox_ano.gridx = 5;
		gbc_comboBox_ano.gridy = 5;
		panel.add(comboBox_recordNumber, gbc_comboBox_ano);
		
		JLabel label_pw = new JLabel("密码：");
		label_pw.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_pw = new GridBagConstraints();
		gbc_label_pw.anchor = GridBagConstraints.EAST;
		gbc_label_pw.insets = new Insets(0, 0, 5, 5);
		gbc_label_pw.gridx = 4;
		gbc_label_pw.gridy = 7;
		panel.add(label_pw, gbc_label_pw);
		
		passwordField_pw = new JPasswordField();
		passwordField_pw.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_passwordField_pw = new GridBagConstraints();
		gbc_passwordField_pw.gridwidth = 4;
		gbc_passwordField_pw.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_pw.fill = GridBagConstraints.BOTH;
		gbc_passwordField_pw.gridx = 5;
		gbc_passwordField_pw.gridy = 7;
		panel.add(passwordField_pw, gbc_passwordField_pw);
		
		JLabel label_checkpw = new JLabel("确认密码：");
		label_checkpw.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_checkpw = new GridBagConstraints();
		gbc_label_checkpw.anchor = GridBagConstraints.EAST;
		gbc_label_checkpw.insets = new Insets(0, 0, 5, 5);
		gbc_label_checkpw.gridx = 4;
		gbc_label_checkpw.gridy = 9;
		panel.add(label_checkpw, gbc_label_checkpw);
		
		passwordField_checkpw = new JPasswordField();
		passwordField_checkpw.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_passwordField_checkpw = new GridBagConstraints();
		gbc_passwordField_checkpw.gridwidth = 4;
		gbc_passwordField_checkpw.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_checkpw.fill = GridBagConstraints.BOTH;
		gbc_passwordField_checkpw.gridx = 5;
		gbc_passwordField_checkpw.gridy = 9;
		panel.add(passwordField_checkpw, gbc_passwordField_checkpw);
		
		JLabel label_usergroup = new JLabel("用户组：");
		label_usergroup.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_usergroup = new GridBagConstraints();
		gbc_label_usergroup.anchor = GridBagConstraints.EAST;
		gbc_label_usergroup.insets = new Insets(0, 0, 5, 5);
		gbc_label_usergroup.gridx = 4;
		gbc_label_usergroup.gridy = 11;
		panel.add(label_usergroup, gbc_label_usergroup);
		
		comboBox_purview = new JComboBox();
		comboBox_purview.setModel(new DefaultComboBoxModel(new String[] {"管理员", "超级管理员"}));
		comboBox_purview.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBox_purview = new GridBagConstraints();
		gbc_comboBox_purview.gridwidth = 4;
		gbc_comboBox_purview.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_purview.fill = GridBagConstraints.BOTH;
		gbc_comboBox_purview.gridx = 5;
		gbc_comboBox_purview.gridy = 11;
		panel.add(comboBox_purview, gbc_comboBox_purview);
		
		JButton button = new JButton("添加");
		button.addActionListener(new AddManagerActionListener(dftm));
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 11;
		gbc_button.gridy = 17;
		panel.add(button, gbc_button);
		
		List list = Dao.findForList("select tb_record.record_number, tb_record.name "
				+"from tb_record, tb_manager where tb_record.id=tb_manager.id");
		updateTable(list, dftm);
		initRecordNameBox();
	}

	// 更新显示�?有用户列�?
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			
			Vector rowData = new Vector();
			rowData.add((String) info.get(0));// 用户编号
			rowData.add((String) info.get(1));// 所有者姓名
			dftm.addRow(rowData);// 向表格对象添加行数据（用户信息）
			
		}
	}
	
	private final class AddManagerActionListener implements ActionListener {
		private final DefaultTableModel dftm;
		private AddManagerActionListener(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			// 控件为空或初始�?�时，弹出提示框
			if (passwordField_pw.getText().equals("") || passwordField_checkpw.getText().equals("")
					|| comboBox_purview.getSelectedIndex()==-1 || comboBox_recordNumber.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "请填写全部信息");
				return;// �?出应用程�?
			}
			
			String pw = passwordField_pw.getText();
			String checkpw = passwordField_checkpw.getText();
			if(!pw.equals(checkpw)){
				JOptionPane.showMessageDialog(AddUserPanel.this, "请重新输入密码!", "两次密码输入不一致", JOptionPane.WARNING_MESSAGE);// 弹出提示�?
				return;
			}
			
			String[] spilt = comboBox_recordNumber.getSelectedItem().toString().split("\\(");
			String no = spilt[0];
			System.out.println(no);
			TbManager user = new TbManager();// 用户信息
			user.setId(Integer.parseInt(no.trim()));// 设置用户�?
			user.setPassword(passwordField_pw.getText().trim());// 设置用户密码
			user.setState("正常");// 设置用户�?
			user.setPurview(comboBox_purview.getSelectedItem().toString());// 设置经办人编�?

			Dao.addManager(user);// 添加用户信息
			JOptionPane.showMessageDialog(AddUserPanel.this, "已成功添加用户", "用户添加信息", JOptionPane.INFORMATION_MESSAGE);// 弹出提示�?
			// 清空组件内容
			passwordField_pw.setText("");
			passwordField_checkpw.setText("");
			comboBox_purview.setSelectedIndex(-1);
			comboBox_recordNumber.setSelectedIndex(-1);
			List list = Dao.findForList("select * from tb_manager");
			updateTable(list, dftm);

		}
	}
	
	public void initRecordNameBox() {// 初始化经办人编号下拉选择框
		List agencyInfo = Dao.getAllTbRecordInfo();// 获取经办人信息
		List<Item> items = new ArrayList<Item>();// 创建数据公共表的集合
		comboBox_recordNumber.removeAllItems();// 移除下拉列表中现有的经办人信息
		for (Iterator iter = agencyInfo.iterator(); iter.hasNext();) {// 遍历list集合
			List element = (List) iter.next();// 获得集合中下个元素
			Item item = new Item();// 创建数据表公共类对象
			item.setNo(element.get(1).toString().trim());// 设置档案编号
			item.setName(element.get(2).toString().trim());// 设置姓名
			if (items.contains(item))// 集合中包含数据表公共类对�?
				continue;// 跳过本次循环
			items.add(item);// 集合中不包含数据表公共类对象，项集合中添加数据表公共类对象
			comboBox_recordNumber.addItem(item.getNo() + "(" + item.getName() + ")");// 项下拉列表中添加数据表公共类对象
		}
	}
	
}
