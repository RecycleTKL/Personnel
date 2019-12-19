package com.groupsix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.groupsix.frame.PersonInfoManage.PersonnelInfoManage;
import com.groupsix.frame.PersonnelManage.PersonManage;
import com.groupsix.frame.PersonnelManage.jiangcheng;
import com.groupsix.frame.userManage.UserManage;


public class MainFrame extends JFrame {


	private JPanel contentPane;
	private JMenuBar menuBar = null;
	
	private Map<JMenuItem, JInternalFrame> iFrames = null; //�ڲ����弯��
	private int nextFrameX, nextFrameY;	//�ڲ�����λ������
	private JDesktopPane desktopPane = null;
	private JTree tree;
	private JMenuItem menuItem_recordManage;
	private JMenuItem menuItem_userManage;
	private static Date currentDate;
	private static JLabel label_currentTime = null;		//��ǰϵͳʱ��
	private static JLabel label_currentUser = null;		//��ǰ��¼����Ա����
	private JMenuItem menuItem_attendance;
	private JMenuItem menuItem_rewardAndPunish;
	private JMenuItem menuItem_training;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		super("������Դ��Ϣ����ϵͳ");
		initUI();
	}

	public void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1440, 900);
		iFrames = new HashMap<JMenuItem, JInternalFrame>();
		
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_2 = new JMenu("\u4EBA\u4E8B\u7BA1\u7406");
		menu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(menu_2);
		
		menuItem_attendance = new JMenuItem("���ڹ���");
		menuItem_attendance.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_attendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				createIFrame(menuItem_attendance, PersonManage.class);
				PersonManage.setTabPane(0);
			}
		});
		menu_2.add(menuItem_attendance);
		
		menuItem_rewardAndPunish = new JMenuItem("���͹���");
		menuItem_rewardAndPunish.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_rewardAndPunish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				createIFrame(menuItem_rewardAndPunish, PersonManage.class);
				PersonManage.setTabPane(1);
			}
		});
		menu_2.add(menuItem_rewardAndPunish);
		
		menuItem_training = new JMenuItem("��ѵ����");
		menuItem_training.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_training.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_training, PersonManage.class);
				PersonManage.setTabPane(2);
			}
		});
		menu_2.add(menuItem_training);
		
		JMenu menu = new JMenu("��Ϣ���Ϲ���");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(menu);
		
		menuItem_recordManage = new JMenuItem("��������");
		menuItem_recordManage.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_recordManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_recordManage, PersonnelInfoManage.class);
			}
		});
		menu.add(menuItem_recordManage);
		
		JMenu mnNewMenu = new JMenu("ϵͳѡ��");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);
		
		menuItem_userManage = new JMenuItem("ϵͳ�û�����");
		menuItem_userManage.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_userManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_userManage, UserManage.class);
			}
		});
		mnNewMenu.add(menuItem_userManage);
		
		JMenu mnNewMenu_1 = new JMenu("����");
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem = new JMenuItem("\u5173\u4E8E...(\u5F00\u53D1\u4E2D)");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		mnNewMenu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("��ϵ����֧��");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						URI uri = new URI("mailto:1182541745@qq.com");
						desktop.mail(uri);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("����վ��");
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						URL url = new URL("http://www.baidu.com");
						desktop.browse(url.toURI());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}	
		});
		mnNewMenu_1.add(menuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 3));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton button_attendanceManage = new JButton("���ڹ���");
		button_attendanceManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_attendance, PersonManage.class);
				PersonManage.setTabPane(0);
			}
		});
		button_attendanceManage.setFont(new Font("����", Font.PLAIN, 18));
		toolBar.add(button_attendanceManage);
		
		JButton button_trainingManage = new JButton("��ѵ����");
		button_trainingManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createIFrame(menuItem_userManage, PersonManage.class);
				PersonManage.setTabPane(2);
			}
		});
		button_trainingManage.setFont(new Font("����", Font.PLAIN, 18));
		toolBar.add(button_trainingManage);
		
		JButton button_modifyPassword = new JButton("�޸�����");
		button_modifyPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordModifyDialog(label_currentUser.getText()).setVisible(true);
			}
		});
		button_modifyPassword.setFont(new Font("����", Font.PLAIN, 18));
		toolBar.add(button_modifyPassword);
		
		JButton button_exit = new JButton("�˳�");
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button_exit.setFont(new Font("����", Font.PLAIN, 18));
		toolBar.add(button_exit);
		
//			URL picURL = MainFrame.class.getResource("/res/landscape.jpg");
//			String picPath = MainFrame.class.getResource("/res/landscape.jpg").getFile();
//			System.out.println(picPath);
//			picPath=URLDecoder.decode(picPath,"utf-8");
//			Image image = ImageIO.read(new File(picPath));
			
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
			
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		contentPane.add(toolBar_1, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("��ǰ�û���");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label);
		
		label_currentUser = new JLabel("��ȡ��");
		label_currentUser.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label_currentUser);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_1);
		
		JLabel label_2 = new JLabel("��ǰϵͳʱ�䣺");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label_2);
		
		label_currentTime = new JLabel("��ȡ��");
		label_currentTime.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label_currentTime);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_1);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_3);
		
		JLabel label_4 = new JLabel("@copyright 2017-2020");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label_4);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_2);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_5);
		
		JLabel label_5 = new JLabel("��ַ������ѧԺ");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("����", Font.PLAIN, 16));
		toolBar_1.add(label_5);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(90, 130, 189));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(200, 0));
		contentPane.add(panel, BorderLayout.WEST);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// �������ĸ����

		DefaultMutableTreeNode recordNode = new DefaultMutableTreeNode(
				"��������");// ��������һ���ӽ��
		recordNode.add(new DefaultMutableTreeNode("�������"));// ��������Ҷ�ӽ�㲢��ӵ�һ���ӽ��
		recordNode.add(new DefaultMutableTreeNode("������ѯ���޸�"));
		root.add(recordNode);// ���������һ���ӽ��

		DefaultMutableTreeNode affairsNode = new DefaultMutableTreeNode(
				"���¹���");
		affairsNode.add(new DefaultMutableTreeNode("���ڹ���"));
		affairsNode.add(new DefaultMutableTreeNode("���͹���"));
		affairsNode.add(new DefaultMutableTreeNode("��ѵ����"));
		root.add(affairsNode);

		DefaultMutableTreeNode treatmentNode = new DefaultMutableTreeNode(
				"��������");
		treatmentNode.add(new DefaultMutableTreeNode("���׹���"));
		treatmentNode.add(new DefaultMutableTreeNode("��Ա��������"));
		root.add(treatmentNode);
		
//		DefaultMutableTreeNode frameNode = new DefaultMutableTreeNode(
//				"��֯�ܹ�");
//		//recordNode.add(new DefaultMutableTreeNode("�����Ϣ����"));
//		frameNode.add(new DefaultMutableTreeNode("��λ����"));
//		frameNode.add(new DefaultMutableTreeNode("��λ����"));
//		root.add(frameNode);

		DefaultMutableTreeNode toolNode = new DefaultMutableTreeNode("ϵͳ�û�����");
		toolNode.add(new DefaultMutableTreeNode("����û�"));
		toolNode.add(new DefaultMutableTreeNode("�޸��û�"));
		toolNode.add(new DefaultMutableTreeNode("ɾ���û�"));
		root.add(toolNode);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);// ͨ���������󴴽���ģ�Ͷ���
		
		tree = new JTree(treeModel);
		tree.setBackground(Color.WHITE);// �������ı���ɫ
		tree.setRootVisible(false);// ���ò���ʾ���ĸ���㣬Ĭ��Ϊ��ʾ����true
		tree.setRowHeight(24);
		tree.setFont(new Font("����", Font.BOLD, 15));// ���ý���������ʽ
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// ����һ�����Ļ��ƶ���
		renderer.setLeafIcon(null);// ����Ҷ�ӽ�㲻����ͼ��
		renderer.setClosedIcon(null);// ���ý���۵�ʱ������ͼ��
		renderer.setOpenIcon(null);// ���ý��չ��ʱ������ͼ��
		tree.setCellRenderer(renderer);// �����Ļ��ƶ������õ�����
		int count = root.getChildCount();// ���һ����������
		for (int i = 0; i < count; i++) {// ��������һ�����
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// ���ָ������λ�õ�һ��������
			TreePath path = new TreePath(node.getPath());// ��ý������·��
			tree.expandPath(path);// չ���ý��
		}
//		tree.addTreeSelectionListener(new SelectNodeActionListener());
		panel.add(tree);
	}
	
//	private class SelectNodeActionListener implements TreeSelectionListener {// ѡ������㶯���¼��ļ���
//		@Override
//		public void valueChanged(TreeSelectionEvent e) {
//			// TODO �Զ����ɵķ������
//			//desktopPane.removeAll();	//�ò������������������ֻ����һ�����������ݱ���
//			TreePath treePath = e.getPath();
//			if (treePath.getPathCount() == 2) {
//				//ѡȡ���˶�����㣬�����κβ���
//			} else {	//ѡȡ���ɲ���Ҷ�ӽ��
//				String selectedNode = treePath.getLastPathComponent().toString();
//				String parentNode = treePath.getParentPath().getLastPathComponent().toString();
//				
//				if (parentNode.equals("�ͻ���Ϣ����")) {
//					if (selectedNode.equals("��ӿͻ���Ϣ")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(0);
//					} else if (selectedNode.equals("�޸Ŀͻ���Ϣ")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(1);
//					} else if (selectedNode.equals("ɾ���ͻ���Ϣ")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(2);
//					} else {// ��ѯ�ͻ���Ϣ
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("ҩƷ��Ϣ����")) {
//					if (selectedNode.equals("���ҩƷ��Ϣ")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(0);
//					} else if (selectedNode.equals("�޸�ҩƷ��Ϣ")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(1);
//					} else if (selectedNode.equals("ɾ��ҩƷ��Ϣ")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(2);
//					} else {// ��ѯҩƷ��Ϣ
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("��������Ϣ����")) {
//					if (selectedNode.equals("��Ӿ�������Ϣ")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(0);
//					} else if (selectedNode.equals("�޸ľ�������Ϣ")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(1);
//					} else if (selectedNode.equals("ɾ����������Ϣ")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(2);
//					} else {// ��ѯ��������Ϣ
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("������Ϣ����")) {
//					if (selectedNode.equals("������Ϣ����")) {
//						createIFrame(menuItem_orderQuery, OrderQuery.class);
//					} else {//��������
//						createIFrame(menuItem_orderRanking, OrderRankingList.class);
//					}
//				} else {	// ϵͳ�û�����
//					if (selectedNode.equals("����û�")) {
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(0);
//					} else if (selectedNode.equals("�޸��û�")) {
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(1);
//					} else {// ɾ���û�
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(2);
//					}
//				}
//			}
//			SwingUtilities.updateComponentTreeUI(desktopPane);
//		}
//	}
	
	/*	�޸�������Ӧ�����ο�
		desktopPane.removeAll();
		SwingUtilities
				.updateComponentTreeUI(desktopPane);
		UpdatePasswordDialog updatePasswordDialog = new UpdatePasswordDialog();
		updatePasswordDialog.setRecord(record);
		updatePasswordDialog.setVisible(true);
	*/
	
	public static JLabel getCurrentUserLabel() {		// ��á�����Ա����ǩ�ķ���
		if (label_currentUser == null) {				// ������Ա����ǩ����Ϊ��
			label_currentUser = new JLabel("����Ա��");	// ����������Ա����ǩ
		}
		return label_currentUser;
	}
	
	// ��������ʱ���߳�
	public static void initTimeField() {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						currentDate = new Date();
						label_currentTime.setText(currentDate.toLocaleString());
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/**
	 * �����ڲ�����ķ������÷���ʹ�÷��似����ȡ�ڲ�����Ĺ��췽�����Ӷ������ڲ����塣
	 * 
	 * @param item��������ڲ�����Ĳ˵���
	 * @param clazz���ڲ������Class����
	 */
	private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = clazz.getConstructors()[0];
		JInternalFrame iFrame = iFrames.get(item);
		try {
			if (iFrame == null || iFrame.isClosed()) {
				iFrame = (JInternalFrame) constructor.newInstance(new Object[] {});
				iFrames.put(item, iFrame);
				iFrame.setFrameIcon(item.getIcon());
				iFrame.setLocation(nextFrameX, nextFrameY);
				int frameH = iFrame.getPreferredSize().height;
				int panelH = iFrame.getContentPane().getPreferredSize().height;
				int fSpacing = frameH - panelH;
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPane.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPane.getHeight())
					nextFrameY = 0;
				desktopPane.add(iFrame);
				iFrame.setResizable(true);
				iFrame.setMaximizable(true);
				iFrame.setMaximum(true);
				iFrame.setVisible(true);
			}
			iFrame.setSelected(true);
			
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameActivated(InternalFrameEvent e) {
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}
	
}
