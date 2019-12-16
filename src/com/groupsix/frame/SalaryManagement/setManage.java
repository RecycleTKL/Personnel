package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class setManage extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setManage frame = new setManage();
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
	public setManage() {
		setBounds(100, 100, 907, 755);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u65B0\u5EFA\u8D26\u5957");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new newsetManage().setVisible(true);
				//textField.setText(newsetManage.get_Explained().toString());
			}
		});
		button.setBounds(76, 13, 100, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539\u8D26\u5957");
		button_1.setBounds(177, 13, 100, 27);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5220\u9664\u8D26\u5957");
		button_2.setBounds(279, 13, 100, 27);
		getContentPane().add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 61, 290, 516);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JButton button_3 = new JButton("\u6DFB\u52A0\u9879\u76EE");
		button_3.setBounds(405, 13, 100, 27);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u5220\u9664\u9879\u76EE");
		button_4.setBounds(509, 13, 100, 27);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\u4FEE\u6539\u91D1\u989D");
		button_5.setBounds(612, 13, 100, 27);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("\u4FDD\u5B58");
		button_6.setBounds(752, 13, 70, 27);
		getContentPane().add(button_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(318, 61, 559, 516);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setColumnHeaderView(table_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 590, 863, 116);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u5957\u8BF4\u660E\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(14, 13, 80, 20);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(99, 11, 750, 92);
		panel.add(textField);
		textField.setColumns(10);

	}
}
