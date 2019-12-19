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
		setTitle("修改密码");
		setBounds(100, 100, 505, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(26, 41, 56, 15);
			contentPanel.add(label);
		}
		{
			passwordField_oldpw = new JPasswordField();
			passwordField_oldpw.setFont(new Font("宋体", Font.PLAIN, 14));
			passwordField_oldpw.setBounds(87, 34, 309, 30);
			contentPanel.add(passwordField_oldpw);
			passwordField_oldpw.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(26, 105, 56, 15);
			contentPanel.add(label);
		}
		{
			passwordField_newpw = new JPasswordField();
			passwordField_newpw.setFont(new Font("宋体", Font.PLAIN, 14));
			passwordField_newpw.setBounds(87, 98, 309, 30);
			contentPanel.add(passwordField_newpw);
			passwordField_newpw.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("宋体", Font.PLAIN, 14));
			label.setBounds(10, 169, 72, 15);
			contentPanel.add(label);
		}
		{
			passwordField_checkpw = new JPasswordField();
			passwordField_checkpw.setFont(new Font("宋体", Font.PLAIN, 14));
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
							int res = Dao.modifyPassword(user, oldpw, newpw);// 获得更改密码的记录条数
							if (res <= 0) {// 修改密码的记录条数不大于0
								JOptionPane.showMessageDialog(getContentPane(), "密码修改失败，请检测旧密码是否正确。");
								return;
							}
							JOptionPane.showMessageDialog(getContentPane(), "密码修改成功。");
							dispose();
						} else {// “新密码”密码框中的文本内容与“确认新密码”密码框中的文本内容不相同
							JOptionPane.showMessageDialog(getContentPane(), "两次输入的密码不一致，请重新输入。");
						}
					}
				});
				button_modify.setFont(new Font("宋体", Font.PLAIN, 14));
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
				button_reset.setFont(new Font("宋体", Font.PLAIN, 14));
				button_reset.setActionCommand("Cancel");
				buttonPane.add(button_reset);
			}
		}
	}

}
