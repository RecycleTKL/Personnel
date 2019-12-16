package com.groupsix.frame.PersonManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class jiangcheng extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jiangcheng frame = new jiangcheng();
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
	public jiangcheng() {
		setBounds(100, 100, 907, 755);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u65B0\u5EFA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(367, 13, 70, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FDD\u5B58");
		button_1.setBounds(443, 13, 70, 27);
		getContentPane().add(button_1);

	}

}
