package com.groupsix.frame.SalaryManagement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.groupsix.dao.Dao;
import com.groupsix.dao.model.TbReckoning;

//人员设置模块
public class PersonalSetupManage extends JPanel {
	private JTextArea explainTextArea;
	private Vector<String> listTableColumnV;
	private Vector<Vector<String>> listTableValueV;
	private DefaultTableModel listTableModel;
	private Vector<String> reckoningTableColumnV;
	private Vector<Vector<String>> reckoningTableValueV;
	private DefaultTableModel reckoningTableModel;
	private final Vector<TbReckoning> reckoningV = new Vector<TbReckoning>();
	/**
	 * Create the panel.
	 */
	public PersonalSetupManage() {
		setBounds(100, 100, 907, 755);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		//添加人员
		JButton addbutton = new JButton("\u6DFB\u52A0\u4EBA\u5458");
		panel.add(addbutton);
		
		JButton savebutton = new JButton("\u5220\u9664\u4EBA\u5458");
		panel.add(savebutton);
		
		JButton button = new JButton("\u5168\u90E8\u5220\u9664");
		panel.add(button);
		
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(260);
		add(splitPane);

		final JPanel reckoningPanel = new JPanel();
		reckoningPanel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(reckoningPanel);
		reckoningPanel.setLayout(new BorderLayout());

		final JLabel reckoningLabel = new JLabel();
		reckoningLabel.setPreferredSize(new Dimension(0, 30));
		reckoningLabel.setText("  账套列表：");
		reckoningPanel.add(reckoningLabel, BorderLayout.NORTH);

		final JScrollPane reckoningScrollPane = new JScrollPane();
		reckoningPanel.add(reckoningScrollPane);

		reckoningTableColumnV = new Vector<String>();
		reckoningTableColumnV.add("序号");
		reckoningTableColumnV.add("账套名称");

		reckoningTableValueV = new Vector<Vector<String>>();

		reckoningTableModel = new DefaultTableModel(reckoningTableValueV,
				reckoningTableColumnV);


		final JLabel reckoningRightLabel = new JLabel();
		reckoningRightLabel.setPreferredSize(new Dimension(12, 20));
		reckoningPanel.add(reckoningRightLabel, BorderLayout.EAST);

		final JLabel reckoningLeftLabel = new JLabel();
		reckoningLeftLabel.setPreferredSize(new Dimension(12, 20));
		reckoningPanel.add(reckoningLeftLabel, BorderLayout.WEST);

		final JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		splitPane.setRightComponent(listPanel);
		listPanel.setLayout(new BorderLayout());

		final JLabel listLabel = new JLabel();
		listLabel.setPreferredSize(new Dimension(0, 30));
		listLabel.setText("  人员列表：");
		listPanel.add(listLabel, BorderLayout.NORTH);

		final JScrollPane listScrollPane = new JScrollPane();
		listPanel.add(listScrollPane);

		listTableColumnV = new Vector<String>();
		String listTableColumns[] = { "序号", "档案编号", "姓名", "性别", "部门", "职务" };
		for (int i = 0; i < listTableColumns.length; i++) {
			listTableColumnV.add(listTableColumns[i]);
		}

		listTableValueV = new Vector<Vector<String>>();

		listTableModel = new DefaultTableModel(listTableValueV,
				listTableColumnV);
	}

}
