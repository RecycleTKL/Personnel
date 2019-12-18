package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class PersonnelInfoManage extends JInternalFrame {

	private static JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonnelInfoManage frame = new PersonnelInfoManage();
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
	public PersonnelInfoManage() {
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 950, 757);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		dananManage t1 = new dananManage();
		tabbedPane.addTab("档案管理", null, t1, null);
		staffManage t = new staffManage();
		tabbedPane.addTab("员工管理", null, t, null);
		getContentPane().add(tabbedPane);// 把选项卡面板添加到药品管理内部窗体的内容面板中
		
		pack();// 药品管理内部窗体中的组件按其首选大小进行布局
		setVisible(true);// 使药品管理内部窗体可见
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
