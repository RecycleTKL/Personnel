package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;


public class SalaryManage extends JInternalFrame {

	private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaryManage frame = new SalaryManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public SalaryManage() throws Exception {
		setTitle("\u5DE5\u8D44\u7BA1\u7406");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 739, 533);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 14));
		ReckoningInfoPanel reckoningInfoPanel = new ReckoningInfoPanel();
		tabbedPane.addTab("账套管理", null, reckoningInfoPanel, null);
		PersonalSetupPanel personalSetupPanel = new PersonalSetupPanel();
		tabbedPane.addTab("人员设置", null, personalSetupPanel, null);
		
		getContentPane().add(tabbedPane);// 把选项卡面板添加到药品管理内部窗体的内容面板中
		pack();// 药品管理内部窗体中的组件按其首选大小进行布局
		setVisible(true);// 使药品管理内部窗体可见
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
