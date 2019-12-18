package com.groupsix.frame.PersonInfoManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PersonnelInfoManage() throws ClassNotFoundException  {
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 495, 525);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		StaffListPanel t1 = new StaffListPanel();
		tabbedPane.addTab("�����б�", null, t1, null);
		RecordInfoPanel t = new RecordInfoPanel();
		tabbedPane.addTab("�������ϱ༭", null, t, null);
		
		getContentPane().add(tabbedPane);// ��ѡ������ӵ�ҩƷ�����ڲ���������������
		pack();// ҩƷ�����ڲ������е����������ѡ��С���в���
		setVisible(true);// ʹҩƷ�����ڲ�����ɼ�
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
