package com.groupsix;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.groupsix.dao.Dao;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordModifyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField_oldpw;
	private JPasswordField passwordField_newpw;
	private JPasswordField passwordField_checkpw;
	private static String user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordModifyDialog dialog = new PasswordModifyDialog(user);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordModifyDialog(String user) {
		this.user = user;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(PasswordModifyDialog.class.getResource("/res/password.png")));
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setTitle("�޸�����");
		setBounds(100, 100, 505, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("����", Font.PLAIN, 14));
			label.setBounds(26, 41, 56, 15);
			contentPanel.add(label);
		}
		{
			passwordField_oldpw = new JPasswordField();
			passwordField_oldpw.setFont(new Font("����", Font.PLAIN, 14));
			passwordField_oldpw.setBounds(87, 34, 309, 30);
			contentPanel.add(passwordField_oldpw);
			passwordField_oldpw.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("����", Font.PLAIN, 14));
			label.setBounds(26, 105, 56, 15);
			contentPanel.add(label);
		}
		{
			passwordField_newpw = new JPasswordField();
			passwordField_newpw.setFont(new Font("����", Font.PLAIN, 14));
			passwordField_newpw.setBounds(87, 98, 309, 30);
			contentPanel.add(passwordField_newpw);
			passwordField_newpw.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("����", Font.PLAIN, 14));
			label.setBounds(10, 169, 72, 15);
			contentPanel.add(label);
		}
		{
			passwordField_checkpw = new JPasswordField();
			passwordField_checkpw.setFont(new Font("����", Font.PLAIN, 14));
			passwordField_checkpw.setBounds(87, 162, 309, 30);
			contentPanel.add(passwordField_checkpw);
			passwordField_checkpw.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton button_modify = new JButton("\u4FEE\u6539\u5BC6\u7801");
				button_modify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String newpw = passwordField_newpw.getText();
						String checkpw = passwordField_checkpw.getText();
						if (newpw.equals(checkpw)) {
							String oldpw = passwordField_oldpw.getText();
							int res = Dao.modifyPassword(user, oldpw, newpw);// ��ø�������ļ�¼����
							if (res <= 0) {// �޸�����ļ�¼����������0
								JOptionPane.showMessageDialog(getContentPane(), "�����޸�ʧ�ܣ�����������Ƿ���ȷ��");
								return;
							}
							JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ���");
							dispose();
						} else {// �������롱������е��ı������롰ȷ�������롱������е��ı����ݲ���ͬ
							JOptionPane.showMessageDialog(getContentPane(), "������������벻һ�£����������롣");
						}
					}
				});
				button_modify.setFont(new Font("����", Font.PLAIN, 14));
				button_modify.setActionCommand("OK");
				buttonPane.add(button_modify);
				getRootPane().setDefaultButton(button_modify);
			}
			{
				JButton button_reset = new JButton("\u91CD\u7F6E\u754C\u9762");
				button_reset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						passwordField_oldpw.setText(null);
						passwordField_newpw.setText(null);
						passwordField_checkpw.setText(null);
					}
				});
				button_reset.setFont(new Font("����", Font.PLAIN, 14));
				button_reset.setActionCommand("Cancel");
				buttonPane.add(button_reset);
			}
		}
	}

}
