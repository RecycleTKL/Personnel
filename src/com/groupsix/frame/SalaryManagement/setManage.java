package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mwq.hibernate.mapping.TbReckoning;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class setManage extends JInternalFrame {
	private JTextField textArea;
	private JTable leftTable;
	private JTable rightTable;
	private int needSaveRow = -1;
	private int lastSelectedRow = -1;
	private final Vector<String> leftTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> leftTableValueV = new Vector<Vector<String>>();

	private final DefaultTableModel leftTableModel = new DefaultTableModel(
			leftTableValueV, leftTableColumnV);

	private final Vector<String> rightTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> rightTableValueV = new Vector<Vector<String>>();

	private final DefaultTableModel rightTableModel = new DefaultTableModel(
			rightTableValueV, rightTableColumnV);
	private final Vector<TbReckoning> reckoningV = new Vector<TbReckoning>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setManage frame = new setManage();
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
	public setManage() {
		setBounds(100, 100, 907, 755);
		getContentPane().setLayout(null);
		
		JButton button =  
				new JButton("\u65B0\u5EFA\u8D26\u5957");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (needSaveRow == -1) {// 没有需要保存的账套
					newsetManage createCriterionSet = new newsetManage();
					createCriterionSet.setVisible(true);// 弹出新建账套对话框
					if (createCriterionSet.isSubmit()) {// 单击“确定”按钮
						String name = createCriterionSet.getNameTextField()
								.getText();// 获得账套名称
						String explain = createCriterionSet
								.getExplainTextArea().getText();// 获得账套说明

						needSaveRow = leftTableValueV.size();// 将新建账套设置为需要保存的账套

						Vector<String> newCriterionSetV = new Vector<String>();// 创建代表账套表格行的向量对象
						newCriterionSetV.add(needSaveRow + 1 + "");// 添加账套序号
						newCriterionSetV.add(name);// 添加账套名称
						leftTableModel.addRow(newCriterionSetV);// 将向量对象添加到左侧的账套表格中
						leftTable.setRowSelectionInterval(needSaveRow,
								needSaveRow);// 设置新建账套为选中行
						textArea.setText(explain);// 设置账套说明

						TbReckoning reckoning = new TbReckoning();// 创建账套对象
						reckoning.setName(name);// 设置账套名称
						reckoning.setExplain(explain);// 设置账套说明
						reckoningV.add(reckoning);// 将账套对象添加到向量中

						refreshItemAllRowValueV(needSaveRow);// 同步刷新右侧的账套项目表格
					}
				} else {// 有需要保存的账套，弹出提示保存对话框
					JOptionPane.showMessageDialog(null, "请先保存账套： "
							+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
				new newsetManage().setVisible(true);
				//textField.setText(newsetManage.get_Explained().toString());
			}
		});
		button.setBounds(76, 13, 100, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539\u8D26\u5957");
		button_1.setBounds(177, 13, 100, 27);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664\u8D26\u5957");
		button_2.setBounds(279, 13, 100, 27);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u6DFB\u52A0\u9879\u76EE");
		button_3.setBounds(405, 13, 100, 27);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u5220\u9664\u9879\u76EE");
		button_4.setBounds(509, 13, 100, 27);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\u4FEE\u6539\u91D1\u989D");
		button_5.setBounds(612, 13, 100, 27);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("\u4FDD\u5B58");
		button_6.setBounds(752, 13, 70, 27);
		getContentPane().add(button_6);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 590, 863, 116);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u5957\u8BF4\u660E\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(14, 13, 80, 20);
		panel.add(label);
		
		textArea = new JTextField();
		textArea.setBounds(99, 11, 750, 92);
		panel.add(textArea);
		textArea.setColumns(10);
		
		JScrollPane leftScrollPane = new JScrollPane();
		leftScrollPane.setBounds(14, 62, 294, 515);
		getContentPane().add(leftScrollPane);
		
		leftTable = new JTable();
		leftTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = leftTable.getSelectedRow();
				if (selectedRow != lastSelectedRow) {
					lastSelectedRow = selectedRow;
					refreshItemAllRowValueV(selectedRow);
				}
			}
		});
		leftScrollPane.setColumnHeaderView(leftTable);
		
		JScrollPane rightScrollPane = new JScrollPane();
		rightScrollPane.setBounds(322, 62, 555, 515);
		getContentPane().add(rightScrollPane);
		
		rightTable = new JTable();
		rightScrollPane.setColumnHeaderView(rightTable);

	}

	public void refreshItemAllRowValueV(int selectedRow) {
		rightTableValueV.removeAllElements();
		if (reckoningV.size() > 0) {
			TbReckoning reckoning = reckoningV.get(row);
			textArea.setText(reckoning.getExplain());
			Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
					.getTbReckoningInfos().iterator();
			int reckoningInfoNum = 1;
			while (reckoningInfoIt.hasNext()) {
				TbReckoningInfo reckoningInfo = reckoningInfoIt.next();
				Vector<String> reckoningInfoV = new Vector<String>();
				reckoningInfoV.add(reckoningInfoNum++ + "");
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getName());
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getUnit());
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getType());
				reckoningInfoV.add(reckoningInfo.getMoney().toString());
				rightTableValueV.add(reckoningInfoV);
			}
		} else {
			textArea.setText("");
		}
		rightTableModel.setDataVector(rightTableValueV, rightTableColumnV);
		if (rightTable.getRowCount() > 0)
			rightTable.setRowSelectionInterval(0, 0);
		
	}
}
