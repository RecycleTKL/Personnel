package com.groupsix.frame.userManage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.groupsix.Item;
import com.groupsix.dao.Dao;
import com.groupsix.dao.model.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.Dimension;

public class SearchAndDeleteUserPanel extends JPanel {
	private JTable table;
	private JTextField textField_conditionContent;
	private JComboBox comboBox_conditionName;
	private JComboBox comboBox_conditionOperation;
	private DefaultTableModel dftm;
	private JLabel label_queryResult;

	/**
	 * Create the panel.
	 */
	public SearchAndDeleteUserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		label_queryResult = new JLabel("-");
		label_queryResult.setFont(new Font("宋体", Font.BOLD, 14));
		panel_1.add(label_queryResult);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new DeleteAction());
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_1.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] { "员工档案编号", "用户名", "状态", "用户权限" };
		dftm.setColumnIdentifiers(tableHeads);
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("选择查询条件：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(label);
		
		comboBox_conditionName = new JComboBox();
		comboBox_conditionName.setPreferredSize(new Dimension(120, 21));
		comboBox_conditionName.setModel(new DefaultComboBoxModel(new String[] {"员工档案编号", "用户姓名", "用户权限"}));
		comboBox_conditionName.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox_conditionName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO 自动生成的方法存�?
				if(comboBox_conditionName.getSelectedItem().toString().equals("用户权限")) {
					textField_conditionContent.setEnabled(false);
					comboBox_conditionOperation.setModel(new DefaultComboBoxModel(new String[] {"管理员",
							"超级管理员" }));
				} else {
					textField_conditionContent.setEnabled(true);
					comboBox_conditionOperation.setModel(new DefaultComboBoxModel(new String[] {"包含", "等于"}));
				}
			}
		});
		panel.add(comboBox_conditionName);
		
		comboBox_conditionOperation = new JComboBox();
		comboBox_conditionOperation.setPreferredSize(new Dimension(100, 21));
		comboBox_conditionOperation.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox_conditionOperation.setModel(new DefaultComboBoxModel(new String[] {"包含", "等于"}));
		panel.add(comboBox_conditionOperation);
		
		textField_conditionContent = new JTextField();
		textField_conditionContent.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(textField_conditionContent);
		textField_conditionContent.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new QueryAction(dftm));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.add(btnNewButton);
		
		JButton button = new JButton("显示全部数据");
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List list = Dao.findForList("select tb_record.record_number, tb_record.name," 
			+ " tb_manager.state, tb_manager.purview from tb_manager, tb_record where tb_manager.id=tb_record.id");// 点击“显示全部数据�?�按钮后，更新表格内�?
				updateTable(list, dftm);
			}
		});
		panel.add(button);

	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		
		Iterator iterator = list.iterator();
		int rowCounter = 0;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Vector rowData = new Vector();
			rowData.add((String) info.get(0));
			rowData.add((String) info.get(1));
			rowData.add((String) info.get(2));
			rowData.add((String) info.get(3));
			dftm.addRow(rowData);// 向表格对象添加行数据（用户信息）
			rowCounter++;
		}
		label_queryResult.setText("共查询到" + rowCounter + "条记录!");
	}

	// 条件查询
	private final class QueryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private QueryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String conName, conOperation, content;
			conName = comboBox_conditionName.getSelectedItem().toString().trim();
			conOperation = comboBox_conditionOperation.getSelectedItem().toString();
			content = textField_conditionContent.getText().trim();
			List list = null;
			list = searchInfo(conName, conOperation, content, list);
			updateTable(list, dftm);
		}
		// 拼接SQL语句，并获得执行SQL语句后相应的结果�?
		private List searchInfo(String conName, String conOperation,
				String content, List list) {
			String sql = "select tb_record.record_number, tb_record.name, tb_manager.state,"
					+" tb_manager.purview from tb_record, tb_manager where tb_record.id=tb_manager.id and ";
			if(conName.equals("用户权限")) {
				list = Dao.findForList(sql + "tb_manager.purview='" +
							(comboBox_conditionOperation.getSelectedItem().toString())+"'");
			} else {
				if (conOperation.equals("等于")) {
					if (conName.equals("员工档案编号"))
						list = Dao.findForList(sql + "tb_record.record_number='" + content + "'");
					if (conName.equals("用户姓名"))
						list = Dao.findForList(sql + "tb_record.name='" + content + "'");

				} else {
					if (conName.equals("员工档案编号"))
						list = Dao.findForList(sql + "tb_record.record_number like '%" + content + "%'");
					if (conName.equals("用户姓名"))
						list = Dao.findForList(sql + "tb_record.name like '%" + content + "%'");
				}
			}
			
			return list;
		}
	}
	
	private final class DeleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存�?
			String record_number, sql;
			int row = table.getSelectedRow();
			record_number = table.getValueAt(row, 0).toString();
			sql = "delete tb_manager where id=(select id from tb_record where record_number='" + record_number + "'";
			int confirm = JOptionPane.showConfirmDialog(SearchAndDeleteUserPanel.this, "确认删除用户信息吗?");// 弹出“确认删除经办人信息吗？”提示框
			if (confirm == JOptionPane.YES_OPTION) {// 点击“确认�?�键
				int rs = Dao.delete(sql);// 获得删除用户信息的数�?
				if (rs > 0) {// 删除用户信息的数量大�?0
					JOptionPane.showMessageDialog(SearchAndDeleteUserPanel.this, "用户信息删除成功!");// 弹出提示�?
					dftm.removeRow(row);
				}
				else {
					JOptionPane.showMessageDialog(SearchAndDeleteUserPanel.this, "删除失败!请检查是否存在约束问题。");// 弹出提示�?
				}
			}
		}
		
	}
}
