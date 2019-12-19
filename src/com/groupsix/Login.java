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
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.InsetsUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/res/main.png")));
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������Դ����ϵͳ - ��¼");
		getContentPane().setLayout(null);
		
		LoginPanel panel = new LoginPanel();
		panel.setForeground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 501, 333);
		//panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_confirm = new JButton("\u767B\u5F55");
		btn_confirm.setHorizontalAlignment(SwingConstants.LEFT);
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
					str_username = textField_username.getText();// ��á��û������ı����е�����
					String passStr = new String(passwordField_username.getPassword());// ��á����롱�ı����е�����
					if(str_username.equals("") || passStr.equals("")) {
						JOptionPane.showMessageDialog(Login.this, "�û��������벻��Ϊ��!", "��ʾ",
								JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						if (!Dao.checkLogin(str_username, passStr)) {// ��֤�û���������ʧ��
							JOptionPane.showMessageDialog(Login.this, "�û��������벻��ȷ!", "��¼ʧ��",
									JOptionPane.ERROR_MESSAGE);// ��������¼ʧ�ܡ��Ի���
							return;
						}
					}
					
					mainFrame = new MainFrame();
					MainFrame.getCurrentUserLabel().setText(str_username);
					MainFrame.initTimeField();
					mainFrame.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				loginFrame.setVisible(false);// ʹ��¼���岻�ɼ�
			}
			
		});
		btn_confirm.setFont(new Font("����", Font.PLAIN, 24));
		btn_confirm.setBounds(163, 229, 190, 40);
		panel.add(btn_confirm);
		
		JLabel label_name = new JLabel("�û�����");
		label_name.setForeground(Color.WHITE);
		label_name.setHorizontalAlignment(SwingConstants.RIGHT);
		label_name.setFont(new Font("�����п�", Font.BOLD, 18));
		label_name.setBounds(115, 130, 82, 30);
		panel.add(label_name);
		
		JLabel label_password = new JLabel("���룺");
		label_password.setForeground(Color.WHITE);
		label_password.setHorizontalAlignment(SwingConstants.RIGHT);
		label_password.setFont(new Font("�����п�", Font.BOLD, 18));
		label_password.setBounds(115, 170, 82, 30);
		panel.add(label_password);
		
		textField_username = new JTextField();
		textField_username.setFont(new Font("����", Font.BOLD, 18));
		textField_username.setBounds(191, 130, 190, 30);
		textField_username.addKeyListener(new KeyAdapter() {// Ϊ�ı�����Ӽ���ʱ��ļ���
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')// ���µİ����ǻس�ʱ
					passwordField_username.grabFocus();
			}
		});
		panel.add(textField_username);
		textField_username.setColumns(10);
		
		passwordField_username = new JPasswordField();
		passwordField_username.setFont(new Font("����", Font.BOLD, 18));
		passwordField_username.setBounds(191, 172, 190, 30);
		passwordField_username.addKeyListener(new KeyAdapter() {// Ϊ�ı�����Ӽ���ʱ��ļ���
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')// ���µİ����ǻس�ʱ
					btn_confirm.doClick();// ����¼����ťִ�е���¼�
			}
		});
		panel.add(passwordField_username);
		
		setBounds(700, 400, 553, 410);
	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
					UIManager.put("RootPane.setupButtonVisible", false);
					Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
                    frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
                    UIManager.put("InternalFrame.titleFont", frameTitleFont);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				loginFrame = new Login();
				loginFrame.setVisible(true);
			}
		});
	}
	
	public static String getCurrentUserName() {		// ��õ�¼�û���
		return str_username;
	}
	
}

class LoginPanel extends JPanel {// ��¼���
	
	public int width, height;// ���Ŀ��
	private Image img;// ��¼���ı���ͼƬ

	public LoginPanel() {// ��¼���Ĺ��췽��
		super();// ���ø���JPanel�Ĺ�����
		URL url = getClass().getResource("/res/login.jpg");// ��õ�¼��屳��ͼƬ��·��
		img = new ImageIcon(url).getImage();// ��õ�¼���ı���ͼƬ
	}

	protected void paintComponent(Graphics g) {// ��д�����������
		super.paintComponent(g);// �������
		g.drawImage(img, 0, 0, this);// ����ͼƬ
	}
}
