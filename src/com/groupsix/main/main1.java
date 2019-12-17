package com.groupsix.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.groupsix.dao.Dao;
import com.groupsix.frame.SalaryManagement.SalaryManage;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main1 window = new main1();
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
	public main1() {
		initialize();
		try {
			ResultSet rs = Dao.findForResultSet("select * from tb_reckoning");
			rs.next();
			System.out.println(rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SalaryManage i = new SalaryManage();
		i.setNormalBounds(new Rectangle(100, 100, 950, 757));
		frame.getContentPane().add(i,BorderLayout.CENTER);
		i.setVisible(true);
	}

}
