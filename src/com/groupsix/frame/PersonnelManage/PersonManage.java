package com.groupsix.frame.PersonnelManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import com.groupsix.frame.PersonInfoManage.dananManage;

import java.awt.BorderLayout;

public class PersonManage extends JInternalFrame {

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
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 950, 757);
		setResizable(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		kaoqinManage t1 = new kaoqinManage();
		//t1.setMaximizable(true);
		//t1.setResizable(true);
		tabbedPane.addTab("考勤管理", null, t1, null);
		jiangcheng t2 = new jiangcheng();
		tabbedPane.addTab("奖惩管理", null, t2, null);
		TrainingPanel t3 = new TrainingPanel();
		tabbedPane.addTab("培训管理", null, t3, null);
	}

}
