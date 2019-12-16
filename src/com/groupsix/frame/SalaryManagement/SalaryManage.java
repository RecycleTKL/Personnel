package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import com.groupsix.frame.PersonManage.dananManage;

public class SalaryManage extends JInternalFrame {

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
	 */
	public SalaryManage() {
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 950, 757);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		setManage t = new setManage();
		t.setMaximizable(true);
		t.setResizable(true);
		tabbedPane.addTab("’ÀÃ◊π‹¿Ì", null, t, null);
	}

}
