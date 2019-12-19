package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.groupsix.dao.Dao;

public class AddAccountItemDialog extends JDialog {

	private JTable table;
	private DefaultTableModel dftm;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			AddAccountItemDialog dialog = new AddAccountItemDialog();
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog
	 */
	public AddAccountItemDialog() {
		super();
		setTitle("添加项目");
		setModal(true);
		setBounds(100, 100, 601, 419);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] { "序号", "名称", "单位", "类型" };
		dftm.setColumnIdentifiers(tableHeads);
		scrollPane.setViewportView(table);

		String sql = "select * from tb_account_item";
		Iterator it = Dao.findForList(sql).iterator();
		while (it.hasNext()) {
			List info = (List) it.next();
			Vector<String> itemV = new Vector<String>();
			itemV.add((String) info.get(0));
			itemV.add((String) info.get(1));
			itemV.add((String) info.get(2));
			itemV.add((String) info.get(3));
			dftm.addRow(itemV);
		}
			final JPanel panel = new JPanel();
			final FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.setLayout(flowLayout);
			getContentPane().add(panel, BorderLayout.SOUTH);

			final JButton exitButton = new JButton();
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			exitButton.setText("退出");
			panel.add(exitButton);

			final JButton selectAllButton = new JButton();
			selectAllButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					table.selectAll();
				}
			});
			selectAllButton.setText("全选");
			panel.add(selectAllButton);

			final JButton addButton = new JButton();
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRows().length == 0) {
						JOptionPane.showMessageDialog(null, "请选择要添加的项目！", "友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					setVisible(false);
				}
			});
			addButton.setText("添加");
			panel.add(addButton);

			final JLabel bottomLabel = new JLabel();
			bottomLabel.setPreferredSize(new Dimension(10, 30));
			panel.add(bottomLabel);

			final JLabel leftLabel = new JLabel();
			leftLabel.setPreferredSize(new Dimension(20, 20));
			getContentPane().add(leftLabel, BorderLayout.WEST);

			final JLabel rightLabel = new JLabel();
			rightLabel.setPreferredSize(new Dimension(20, 20));
			getContentPane().add(rightLabel, BorderLayout.EAST);

			final JLabel topLabel = new JLabel();
			topLabel.setPreferredSize(new Dimension(20, 20));
			getContentPane().add(topLabel, BorderLayout.NORTH);
		}
	//

	public JTable getTable() {
		return table;
	}

}
