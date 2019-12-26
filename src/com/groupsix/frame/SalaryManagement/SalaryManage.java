package com.groupsix.frame.SalaryManagement;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;


public class SalaryManage extends JInternalFrame {

	private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
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
	 * @throws Exception 
	 */
	public SalaryManage() throws Exception {
		setTitle("\u5DE5\u8D44\u7BA1\u7406");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 739, 533);
		setResizable(true);
		setClosable(true);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("����", Font.PLAIN, 14));
		ReckoningInfoPanel reckoningInfoPanel = new ReckoningInfoPanel();
		tabbedPane.addTab("���׹���", null, reckoningInfoPanel, null);
		PersonalSetupPanel personalSetupPanel = new PersonalSetupPanel();
		tabbedPane.addTab("��Ա����", null, personalSetupPanel, null);
		
		getContentPane().add(tabbedPane);// ��ѡ������ӵ�ҩƷ�����ڲ���������������
		pack();// ҩƷ�����ڲ������е����������ѡ��С���в���
		setVisible(true);// ʹҩƷ�����ڲ�����ɼ�
	}
	
	public static void setTabPane(int i) {
		tabbedPane.setSelectedIndex(i);
	}

}
