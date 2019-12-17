package com.groupsix.frame.PersonInfoManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class PersonnelInfoManage extends JInternalFrame {

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
		setBounds(100, 100, 450, 300);

	}

}
