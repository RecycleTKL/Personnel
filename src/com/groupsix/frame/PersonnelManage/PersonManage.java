package com.groupsix.frame.PersonnelManage;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import com.groupsix.frame.PersonInfoManage.staffManage;

import java.awt.BorderLayout;

public class PersonManage extends JInternalFrame {

	private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

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
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("����", Font.PLAIN, 14));
		final kaoqinManage t1 = new kaoqinManage();
		tabbedPane.addTab("���ڹ���", null, t1, null);
		final jiangcheng t2 = new jiangcheng();
		tabbedPane.addTab("���͹���", null, t2, null);
		final TrainingPanel t3 = new TrainingPanel();
		tabbedPane.addTab("��ѵ����", null, t3, null);
		getContentPane().add(tabbedPane);// ��ѡ�������ӵ�ҩƷ�����ڲ���������������
		
		pack();// ҩƷ�����ڲ������е����������ѡ��С���в���
		setVisible(true);// ʹҩƷ�����ڲ�����ɼ�
	}

	public static void setTabPane(int i) {
		// TODO �Զ����ɵķ������
		tabbedPane.setSelectedIndex(i);
	}

}