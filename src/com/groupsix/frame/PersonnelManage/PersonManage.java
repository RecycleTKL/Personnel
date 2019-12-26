package com.groupsix.frame.PersonnelManage;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;


import java.awt.BorderLayout;

public class PersonManage extends JInternalFrame {

	private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonManage frame = new PersonManage();
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
	public PersonManage() {
		setTitle("\u4EBA\u4E8B\u4FE1\u606F\u7BA1\u7406");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 950, 757);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 14));
		final kaoqinManage t1 = new kaoqinManage();
		tabbedPane.addTab("考勤管理", null, t1, null);
		final jiangcheng t2 = new jiangcheng();
		tabbedPane.addTab("奖惩管理", null, t2, null);
		final TrainingPanel t3 = new TrainingPanel();
		tabbedPane.addTab("培训管理", null, t3, null);
		getContentPane().add(tabbedPane);// 把选项卡面板添加到药品管理内部窗体的内容面板中
		
		pack();// 药品管理内部窗体中的组件按其首选大小进行布局
		setVisible(true);// 使药品管理内部窗体可见
	}

	public static void setTabPane(int i) {
		// TODO 自动生成的方法存根
		tabbedPane.setSelectedIndex(i);
	}

}
