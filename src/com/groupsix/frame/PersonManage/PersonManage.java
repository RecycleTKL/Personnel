package com.groupsix.frame.PersonManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

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
		dananManage t = new dananManage();
		tabbedPane.addTab("��������", null, t, null);
		
		kaoqinManage t1 = new kaoqinManage();
		//t1.setMaximizable(true);
		//t1.setResizable(true);
		tabbedPane.addTab("���ڹ���", null, t1, null);
		jiangcheng t2 = new jiangcheng();
		t2.setMaximizable(true);
		t2.setResizable(true);
		tabbedPane.addTab("���͹���", null, t2, null);
		peixunManage t3 = new peixunManage();
		t3.setMaximizable(true);
		t3.setResizable(true);
		tabbedPane.addTab("��ѵ����", null, t3, null);
	}

}
