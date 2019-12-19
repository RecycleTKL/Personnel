package com.groupsix.frame.SalaryManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;


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
		ReckoningInfoPanel reckoningInfoPanel = new ReckoningInfoPanel();
		tabbedPane.addTab("���׹���", null, reckoningInfoPanel, null);
		
		PersonalSetupPanel personalSetupPanel = new PersonalSetupPanel();
		tabbedPane.addTab("��Ա����", null, personalSetupPanel, null);
	}

}
