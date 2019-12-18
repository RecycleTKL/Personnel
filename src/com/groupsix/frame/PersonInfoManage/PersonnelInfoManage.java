package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PersonnelInfoManage() throws ClassNotFoundException  {
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 495, 525);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		StaffListPanel t1 = new StaffListPanel();
		tabbedPane.addTab("档案列表", null, t1, null);
		RecordInfoPanel t = new RecordInfoPanel();
		tabbedPane.addTab("档案资料编辑", null, t, null);
		
		getContentPane().add(tabbedPane);// 把选项卡面板添加到药品管理内部窗体的内容面板中
		pack();// 药品管理内部窗体中的组件按其首选大小进行布局
		setVisible(true);// 使药品管理内部窗体可见
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
