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
	
	private Map<JMenuItem, JInternalFrame> iFrames = null; //内部窗体集合
	private int nextFrameX, nextFrameY;	//内部窗体位置坐标
	private JDesktopPane desktopPane = null;
	private JTree tree;
	private JMenuItem menuItem_recordManage;
	private JMenuItem menuItem_userManage;
	private static Date currentDate;
	private static JLabel label_currentTime = null;		//当前系统时间
	private static JLabel label_currentUser = null;		//当前登录操作员名称
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
		super("人力资源信息管理系统");
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
		
		menuItem_attendance = new JMenuItem("考勤管理");
		menuItem_attendance.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_attendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				createIFrame(menuItem_attendance, PersonManage.class);
				PersonManage.setTabPane(0);
			}
		});
		menu_2.add(menuItem_attendance);
		
		menuItem_rewardAndPunish = new JMenuItem("奖惩管理");
		menuItem_rewardAndPunish.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_rewardAndPunish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				createIFrame(menuItem_rewardAndPunish, PersonManage.class);
				PersonManage.setTabPane(1);
			}
		});
		menu_2.add(menuItem_rewardAndPunish);
		
		menuItem_training = new JMenuItem("培训管理");
		menuItem_training.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_training.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_training, PersonManage.class);
				PersonManage.setTabPane(2);
			}
		});
		menu_2.add(menuItem_training);
		
		JMenu menu = new JMenu("信息资料管理");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(menu);
		
		menuItem_recordManage = new JMenuItem("档案管理");
		menuItem_recordManage.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_recordManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_recordManage, PersonnelInfoManage.class);
			}
		});
		menu.add(menuItem_recordManage);
		
		JMenu mnNewMenu = new JMenu("系统选项");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);
		
		menuItem_userManage = new JMenuItem("系统用户管理");
		menuItem_userManage.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_userManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_userManage, UserManage.class);
			}
		});
		mnNewMenu.add(menuItem_userManage);
		
		JMenu mnNewMenu_1 = new JMenu("帮助");
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem = new JMenuItem("\u5173\u4E8E...(\u5F00\u53D1\u4E2D)");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		mnNewMenu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("联系技术支持");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
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
		
		JMenuItem menuItem_2 = new JMenuItem("访问站点");
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		menuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
		
		JButton button_attendanceManage = new JButton("考勤管理");
		button_attendanceManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createIFrame(menuItem_attendance, PersonManage.class);
				PersonManage.setTabPane(0);
			}
		});
		button_attendanceManage.setFont(new Font("黑体", Font.PLAIN, 18));
		toolBar.add(button_attendanceManage);
		
		JButton button_trainingManage = new JButton("培训管理");
		button_trainingManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createIFrame(menuItem_userManage, PersonManage.class);
				PersonManage.setTabPane(2);
			}
		});
		button_trainingManage.setFont(new Font("黑体", Font.PLAIN, 18));
		toolBar.add(button_trainingManage);
		
		JButton button_modifyPassword = new JButton("修改密码");
		button_modifyPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordModifyDialog(label_currentUser.getText()).setVisible(true);
			}
		});
		button_modifyPassword.setFont(new Font("黑体", Font.PLAIN, 18));
		toolBar.add(button_modifyPassword);
		
		JButton button_exit = new JButton("退出");
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button_exit.setFont(new Font("黑体", Font.PLAIN, 18));
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
		
		JLabel label = new JLabel("当前用户：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("等线", Font.PLAIN, 16));
		toolBar_1.add(label);
		
		label_currentUser = new JLabel("获取中");
		label_currentUser.setFont(new Font("等线", Font.PLAIN, 16));
		toolBar_1.add(label_currentUser);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_1);
		
		JLabel label_2 = new JLabel("当前系统时间：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("等线", Font.PLAIN, 16));
		toolBar_1.add(label_2);
		
		label_currentTime = new JLabel("获取中");
		label_currentTime.setFont(new Font("等线", Font.PLAIN, 16));
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
		label_4.setFont(new Font("等线", Font.PLAIN, 16));
		toolBar_1.add(label_4);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_2);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		toolBar_1.add(horizontalGlue_5);
		
		JLabel label_5 = new JLabel("地址：肇庆学院");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("等线", Font.PLAIN, 16));
		toolBar_1.add(label_5);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(90, 130, 189));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(200, 0));
		contentPane.add(panel, BorderLayout.WEST);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// 创建树的根结点

		DefaultMutableTreeNode recordNode = new DefaultMutableTreeNode(
				"档案中心");// 创建树的一级子结点
		recordNode.add(new DefaultMutableTreeNode("档案添加"));// 创建树的叶子结点并添加到一级子结点
		recordNode.add(new DefaultMutableTreeNode("档案查询与修改"));
		root.add(recordNode);// 向根结点添加一级子结点

		DefaultMutableTreeNode affairsNode = new DefaultMutableTreeNode(
				"人事管理");
		affairsNode.add(new DefaultMutableTreeNode("考勤管理"));
		affairsNode.add(new DefaultMutableTreeNode("奖惩管理"));
		affairsNode.add(new DefaultMutableTreeNode("培训管理"));
		root.add(affairsNode);

		DefaultMutableTreeNode treatmentNode = new DefaultMutableTreeNode(
				"待遇管理");
		treatmentNode.add(new DefaultMutableTreeNode("账套管理"));
		treatmentNode.add(new DefaultMutableTreeNode("人员工资设置"));
		root.add(treatmentNode);
		
//		DefaultMutableTreeNode frameNode = new DefaultMutableTreeNode(
//				"组织架构");
//		//recordNode.add(new DefaultMutableTreeNode("入库信息管理"));
//		frameNode.add(new DefaultMutableTreeNode("岗位管理"));
//		frameNode.add(new DefaultMutableTreeNode("岗位资料"));
//		root.add(frameNode);

		DefaultMutableTreeNode toolNode = new DefaultMutableTreeNode("系统用户管理");
		toolNode.add(new DefaultMutableTreeNode("添加用户"));
		toolNode.add(new DefaultMutableTreeNode("修改用户"));
		toolNode.add(new DefaultMutableTreeNode("删除用户"));
		root.add(toolNode);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);// 通过树结点对象创建树模型对象
		
		tree = new JTree(treeModel);
		tree.setBackground(Color.WHITE);// 设置树的背景色
		tree.setRootVisible(false);// 设置不显示树的根结点，默认为显示，即true
		tree.setRowHeight(24);
		tree.setFont(new Font("宋体", Font.BOLD, 15));// 设置结点的字体样式
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// 创建一个树的绘制对象
		renderer.setLeafIcon(null);// 设置叶子结点不采用图标
		renderer.setClosedIcon(null);// 设置结点折叠时不采用图标
		renderer.setOpenIcon(null);// 设置结点展开时不采用图标
		tree.setCellRenderer(renderer);// 将树的绘制对象设置到树中
		int count = root.getChildCount();// 获得一级结点的数量
		for (int i = 0; i < count; i++) {// 遍历树的一级结点
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// 获得指定索引位置的一级结点对象
			TreePath path = new TreePath(node.getPath());// 获得结点对象的路径
			tree.expandPath(path);// 展开该结点
		}
//		tree.addTreeSelectionListener(new SelectNodeActionListener());
		panel.add(tree);
	}
	
//	private class SelectNodeActionListener implements TreeSelectionListener {// 选择树结点动作事件的监听
//		@Override
//		public void valueChanged(TreeSelectionEvent e) {
//			// TODO 自动生成的方法存根
//			//desktopPane.removeAll();	//让操作过程中桌面面板上只留下一个操作窗，暂保留
//			TreePath treePath = e.getPath();
//			if (treePath.getPathCount() == 2) {
//				//选取到了二级结点，不做任何操作
//			} else {	//选取到可操作叶子结点
//				String selectedNode = treePath.getLastPathComponent().toString();
//				String parentNode = treePath.getParentPath().getLastPathComponent().toString();
//				
//				if (parentNode.equals("客户信息管理")) {
//					if (selectedNode.equals("添加客户信息")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(0);
//					} else if (selectedNode.equals("修改客户信息")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(1);
//					} else if (selectedNode.equals("删除客户信息")) {
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(2);
//					} else {// 查询客户信息
//						createIFrame(menuItem_clientManage, ClientManage.class);
//						ClientManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("药品信息管理")) {
//					if (selectedNode.equals("添加药品信息")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(0);
//					} else if (selectedNode.equals("修改药品信息")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(1);
//					} else if (selectedNode.equals("删除药品信息")) {
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(2);
//					} else {// 查询药品信息
//						createIFrame(menuItem_medicineManage, MedicineManage.class);
//						MedicineManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("经办人信息管理")) {
//					if (selectedNode.equals("添加经办人信息")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(0);
//					} else if (selectedNode.equals("修改经办人信息")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(1);
//					} else if (selectedNode.equals("删除经办人信息")) {
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(2);
//					} else {// 查询经办人信息
//						createIFrame(menuItem_agencyManage, AgencyManage.class);
//						AgencyManage.setTabPane(2);
//					}
//				} else if (parentNode.equals("单据信息管理")) {
//					if (selectedNode.equals("销售信息管理")) {
//						createIFrame(menuItem_orderQuery, OrderQuery.class);
//					} else {//销售排行
//						createIFrame(menuItem_orderRanking, OrderRankingList.class);
//					}
//				} else {	// 系统用户管理
//					if (selectedNode.equals("添加用户")) {
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(0);
//					} else if (selectedNode.equals("修改用户")) {
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(1);
//					} else {// 删除用户
//						createIFrame(menuItem_userManage, UserManage.class);
//						UserManage.setTabPane(2);
//					}
//				}
//			}
//			SwingUtilities.updateComponentTreeUI(desktopPane);
//		}
//	}
	
	/*	修改密码响应操作参考
		desktopPane.removeAll();
		SwingUtilities
				.updateComponentTreeUI(desktopPane);
		UpdatePasswordDialog updatePasswordDialog = new UpdatePasswordDialog();
		updatePasswordDialog.setRecord(record);
		updatePasswordDialog.setVisible(true);
	*/
	
	public static JLabel getCurrentUserLabel() {		// 获得“操作员”标签的方法
		if (label_currentUser == null) {				// “操作员”标签对象为空
			label_currentUser = new JLabel("操作员：");	// 创建“操作员”标签
		}
		return label_currentUser;
	}
	
	// 启动销售时间线程
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
	 * 创建内部窗体的方法，该方法使用反射技术获取内部窗体的构造方法，从而创建内部窗体。
	 * 
	 * @param item：激活该内部窗体的菜单项
	 * @param clazz：内部窗体的Class对象
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
