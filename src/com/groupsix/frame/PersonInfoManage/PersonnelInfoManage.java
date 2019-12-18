package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class PersonnelInfoManage extends JInternalFrame {

	private static JTabbedPane tabbedPane;

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
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 950, 757);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		dananManage t1 = new dananManage();
		tabbedPane.addTab("��������", null, t1, null);
		staffManage t = new staffManage();
		tabbedPane.addTab("Ա������", null, t, null);
		getContentPane().add(tabbedPane);// ��ѡ������ӵ�ҩƷ�����ڲ���������������
		
		pack();// ҩƷ�����ڲ������е����������ѡ��С���в���
		setVisible(true);// ʹҩƷ�����ڲ�����ɼ�
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
