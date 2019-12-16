package com.groupsix.main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.groupsix.connection.connectionmysql;
import com.groupsix.frame.PersonManage.PersonManage;
import com.groupsix.frame.SalaryManagement.SalaryManage;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Main{
	private JFrame frame;
	JInternalFrame t;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		t.setClosable(true);
		t.setMaximizable(true);
		t.setIconifiable(true);
		frame.getContentPane().add(t, BorderLayout.CENTER);
		t.getContentPane().setLayout(null);
		PersonManage t =  new PersonManage();
		frame.getContentPane().add(t,BorderLayout.CENTER);
		
	}

}
