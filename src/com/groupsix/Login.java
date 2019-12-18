package com.groupsix;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import com.groupsix.Login;
import com.groupsix.LoginPanel;
import com.groupsix.dao.Dao;
import com.groupsix.MainFrame;

public class Login extends JFrame {
	private JTextField textField_username;
	private JPasswordField passwordField_username;
	private static String str_username;
	private static MainFrame mainFrame;
	private static Login loginFrame;

	Login(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/res/main.jpg")));
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("人力资源管理系统 - 登录");
		getContentPane().setLayout(null);
		
		LoginPanel panel = new LoginPanel();
		panel.setForeground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 463, 278);
		//panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_confirm = new JButton("");
		btn_confirm.setPressedIcon(new ImageIcon(Login.class.getResource("/res/loginbtnprs.png")));
		btn_confirm.setBorderPainted(false);
		btn_confirm.setContentAreaFilled(false);
		btn_confirm.setOpaque(false);
		btn_confirm.setIcon(new ImageIcon(Login.class.getResource("/res/loginbtn.png")));
		btn_confirm.setIconTextGap(0);
		btn_confirm.setBorder(null);
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					str_username = textField_username.getText();// 获得“用户名”文本框中的内容
					String passStr = new String(passwordField_username.getPassword());// 获得“密码”文本框中的内容
					if(str_username.equals("") || passStr.equals("")) {
						JOptionPane.showMessageDialog(Login.this, "用户名和密码不能为空!", "提示",
								JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						if (!Dao.checkLogin(str_username, passStr)) {// 验证用户名、密码失败
							JOptionPane.showMessageDialog(Login.this, "用户名或密码不正确!", "登录失败",
									JOptionPane.ERROR_MESSAGE);// 弹出“登录失败”对话框
							return;
						}
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				loginFrame.setVisible(false);// 使登录窗体不可见
			}
			
		});
		btn_confirm.setFont(new Font("华文行楷", Font.PLAIN, 24));
		btn_confirm.setBounds(137, 207, 190, 40);
		panel.add(btn_confirm);
		
		JLabel label_name = new JLabel("用户名：");
		label_name.setForeground(new Color(0, 0, 0));
		label_name.setHorizontalAlignment(SwingConstants.RIGHT);
		label_name.setFont(new Font("华文行楷", Font.BOLD, 18));
		label_name.setBounds(89, 108, 82, 30);
		panel.add(label_name);
		
		JLabel label_password = new JLabel("密码：");
		label_password.setForeground(new Color(0, 0, 0));
		label_password.setHorizontalAlignment(SwingConstants.RIGHT);
		label_password.setFont(new Font("华文行楷", Font.BOLD, 18));
		label_password.setBounds(89, 148, 82, 30);
		panel.add(label_password);
		
		textField_username = new JTextField();
		textField_username.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField_username.setFont(new Font("宋体", Font.BOLD, 18));
		textField_username.setBounds(165, 108, 190, 30);
		textField_username.setOpaque(false);
		textField_username.addKeyListener(new KeyAdapter() {// 为文本框添加键盘时间的监听
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')// 按下的按键是回车时
					passwordField_username.grabFocus();
			}
		});
		panel.add(textField_username);
		textField_username.setColumns(10);
		
		passwordField_username = new JPasswordField();
		passwordField_username.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		passwordField_username.setFont(new Font("宋体", Font.BOLD, 18));
		passwordField_username.setBounds(165, 150, 190, 30);
		passwordField_username.setOpaque(false);
		passwordField_username.addKeyListener(new KeyAdapter() {// 为文本框添加键盘时间的监听
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')// 按下的按键是回车时
					btn_confirm.doClick();// “登录”按钮执行点击事件
			}
		});
		panel.add(passwordField_username);
		
		setBounds(700, 400, 469, 307);
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				loginFrame = new Login();
				loginFrame.setVisible(true);
			}
		});
	}
	
	public static String getCurrentUserName() {		// 获得登录用户名
		return str_username;
	}
	
}

class LoginPanel extends JPanel {// 登录面板
	
	public int width, height;// 面板的宽高
	private Image img;// 登录面板的背景图片

	public LoginPanel() {// 登录面板的构造方法
		super();// 调用父类JPanel的构造器
		URL url = getClass().getResource("/res/login.jpg");// 获得登录面板背景图片的路径
		img = new ImageIcon(url).getImage();// 获得登录面板的背景图片
	}

	protected void paintComponent(Graphics g) {// 重写绘制组件方法
		super.paintComponent(g);// 绘制组件
		g.drawImage(img, 0, 0, this);// 绘制图片
	}
}
