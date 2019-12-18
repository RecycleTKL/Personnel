package com.groupsix.frame.userManage;

import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.groupsix.frame.userManage.*;


public class UserManage extends JInternalFrame {

	private static JTabbedPane tabPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
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
	public UserManage() {
		super("用户信息管理");
		initUI();
	}

	public void initUI() {
		try {
			setResizable(true);
			setSelected(true);
			setIconifiable(true);// 可以图标�?
			setClosable(true);
			//setBounds(100, 100, 1000, 637);
			
			tabPane = new JTabbedPane();
			tabPane.setFont(new Font("宋体", Font.PLAIN, 14));
			final AddUserPanel addPanel = new AddUserPanel();// 用户修改面板
			final ModifyUserPanel modifyPanel = new ModifyUserPanel();// 用户添加面板
			final SearchAndDeleteUserPanel searchAndDeletePanel = new SearchAndDeleteUserPanel(); //用户查询删除面板
			tabPane.addTab("用户信息添加", null, addPanel, "信息添加");// 把用户添加面板添加到选项卡面板中
			tabPane.addTab("用户信息修改", null, modifyPanel, "信息修改");// 把用户修改面板添加到选项卡面板中
			tabPane.addTab("用户信息查询与删除", null, searchAndDeletePanel, "信息查询与删除");// 把用户查询删除面板添加到选项卡面板中
			getContentPane().add(tabPane);// 把�?�项卡面板添加到用户管理内部窗体的内容面板中
			tabPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					//initOperation
					addPanel.initRecordNameBox();
					//modifyPanel.initAnoBox();
				}
			});
			pack();// 用户管理内部窗体中的组件按其首�?�大小进行布�?
			setVisible(true);// 使用户管理内部窗体可�?
			
			
		} catch (PropertyVetoException e) {
			// TODO 自动生成�? catch �?
			e.printStackTrace();
		}
	}
	
	public static void setTabPane(int index) {
		tabPane.setSelectedIndex(index);
	}
}
