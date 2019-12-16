package com.groupsix.frame.PersonManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.groupsix.connection.connectionmysql;
import com.mysql.cj.jdbc.Blob;
import com.sun.prism.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class dananManage extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtYyyymmdd;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField tf2_1;
	private JTextField tf2_2;
	private JTextField tf2_3;
	private JTextField tf2_4;
	private JTextField tf2_5;
	private JTextField tf2_6;
	private JTextField tf2_7;
	private JTextField tf2_8;
	private JTextField tf2_9;
	private JTextField tf2_10;
	private JTextField tf2_11;
	private JTextField tf3_1;
	private JTextField tf3_2;
	private JTextField tf3_3;
	private JTextField tf3_4;
	private JTextField tf3_5;
	private JTextField tf3_6;
	private JTextField tf3_7;
	private JTextField tf3_8;
	private JTextField tf3_9;
	private JTextField tf3_10;
	private JTextField tf3_11;
	private JTextField tf3_12;
	private JTextField tf3_13;
	private JTextField tf3_14;
	private JTextField tf2_12;
	private JTextField tf2_13;
	private JTextField tf2_14;
	private JTextField tf2_15;
	private JRadioButton radioButton,radioButton_1,radioButton_3,radioButton_4,radioButton_5,radioButton_6;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	JComboBox comboBox_3 = new JComboBox();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	JComboBox comboBox_4 = new JComboBox();
	JComboBox comboBox_5 = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dananManage frame = new dananManage();
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
	public dananManage() {
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 907, 755);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						baocunActionPerformed();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
			}
		});
		button.setBounds(321, 13, 63, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setBounds(440, 13, 63, 27);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				dispose();
			}
		});
		getContentPane().add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 44, 861, 662);
		panel.setToolTipText("\u8BF7\u9009\u62E9");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u6863\u6848\u7F16\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(14, 13, 80, 24);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(97, 13, 98, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 42, 833, 202);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("\u6863\u6848\u4FE1\u606F");
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(0, 0, 72, 24);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("\u59D3    \u540D\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 31, 80, 24);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 28, 98, 24);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6027    \u522B\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(208, 31, 80, 24);
		panel_1.add(label_3);
		
		radioButton = new JRadioButton("\u7537");
		buttonGroup.add(radioButton);
		radioButton.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton.setBounds(289, 28, 50, 27);
		panel_1.add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		buttonGroup.add(radioButton_1);
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton_1.setBounds(345, 28, 50, 27);
		panel_1.add(radioButton_1);
		
		JLabel label_4 = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(423, 31, 80, 24);
		panel_1.add(label_4);
		
		txtYyyymmdd = new JTextField("yyyy-mm-dd");
		txtYyyymmdd.setHorizontalAlignment(SwingConstants.LEFT);
		txtYyyymmdd.setBounds(503, 28, 118, 24);
		panel_1.add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel label_5 = new JLabel("\u6C11    \u65CF\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 65, 80, 24);
		panel_1.add(label_5);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "  \u6C49\u65CF", " \u5C11\u6570\u6C11\u65CF"}));
		comboBox.setBounds(86, 62, 80, 24);
		panel_1.add(comboBox);
		
		JLabel label_6 = new JLabel("\u7C4D    \u8D2F\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(208, 65, 80, 24);
		panel_1.add(label_6);
		
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "  \u5E7F\u4E1C", "  \u5E7F\u897F", "  "}));
		comboBox_1.setBounds(289, 63, 72, 24);
		panel_1.add(comboBox_1);
		
		JLabel label_7 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(423, 65, 80, 24);
		panel_1.add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(503, 65, 118, 24);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_8 = new JLabel("\u5B66    \u5386\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(10, 99, 80, 24);
		panel_1.add(label_8);
		
		
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 15));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "  \u535A\u58EB", "  \u7855\u58EB", "  \u672C\u79D1", "  \u4E13\u79D1", "  \u9AD8\u4E2D"}));
		comboBox_2.setBounds(86, 99, 80, 24);
		panel_1.add(comboBox_2);
		
		JLabel label_9 = new JLabel("\u4E13    \u4E1A\uFF1A");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(208, 102, 80, 24);
		panel_1.add(label_9);
		
		textField_4 = new JTextField();
		textField_4.setBounds(289, 99, 106, 24);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_10 = new JLabel("\u515A    \u5458\uFF1A");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(423, 102, 80, 24);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("\u5916\u8BED\u8BED\u79CD\uFF1A");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(10, 139, 80, 24);
		panel_1.add(label_11);
		
		textField_5 = new JTextField();//语种
		textField_5.setBounds(86, 136, 98, 24);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_12 = new JLabel("\u5916\u8BED\u6C34\u5E73\uFF1A");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setBounds(208, 139, 80, 24);
		panel_1.add(label_12);
		
		textField_6 = new JTextField();
		textField_6.setBounds(289, 134, 106, 24);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_13 = new JLabel("\u5A5A\u59FB\u72B6\u51B5\uFF1A");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(423, 137, 80, 24);
		panel_1.add(label_13);
		
		radioButton_3 = new JRadioButton("\u672A\u5A5A");
		buttonGroup_2.add(radioButton_3);
		radioButton_3.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton_3.setBounds(503, 133, 50, 27);
		panel_1.add(radioButton_3);
		
		radioButton_4 = new JRadioButton("\u5DF2\u5A5A");
		buttonGroup_2.add(radioButton_4);
		radioButton_4.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton_4.setBounds(559, 134, 55, 27);
		panel_1.add(radioButton_4);
		
		JLabel label_14 = new JLabel("\u90AE\u653F\u7F16\u7801\uFF1A");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setBounds(10, 172, 80, 24);
		panel_1.add(label_14);
		
		textField_7 = new JTextField();
		textField_7.setBounds(86, 169, 98, 24);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label_15 = new JLabel("\u6237\u7C4D\u5730\u5740\uFF1A");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setBounds(208, 172, 80, 24);
		panel_1.add(label_15);
		
		textField_8 = new JTextField();
		textField_8.setBounds(289, 168, 327, 24);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		radioButton_5 = new JRadioButton("\u662F");
		buttonGroup_1.add(radioButton_5);
		radioButton_5.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton_5.setBounds(503, 98, 50, 27);
		panel_1.add(radioButton_5);
		
		radioButton_6 = new JRadioButton("\u5426");
		buttonGroup_1.add(radioButton_6);
		radioButton_6.setFont(new Font("宋体", Font.PLAIN, 11));
		radioButton_6.setBounds(559, 98, 50, 27);
		panel_1.add(radioButton_6);
		
		JLabel photolabel = new JLabel("\u70B9\u51FB\u6B64\u5904\u4E0A\u4F20\u56FE\u7247");
		photolabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				photolabel.setBorder(new TitledBorder(null, "",
					    TitledBorder.DEFAULT_JUSTIFICATION,
					    TitledBorder.DEFAULT_POSITION, null, null));// 设置边框
					  photolabel.setPreferredSize(new Dimension(120, 140));// 设置显示照片的大小
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select an image");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif","jpg");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
				    System.out.println(jfc.getSelectedFile().getPath());
				}

				try {

				    String path=jfc.getSelectedFile().getPath();
				    File sourceimage = new File(path);
				    BufferedImage image = ImageIO.read(sourceimage);
				    photolabel.setIcon(new ImageIcon(image));


				} catch (IOException ex) {

				}
			}
		});
		photolabel.setHorizontalAlignment(SwingConstants.CENTER);
		photolabel.setBounds(652, 31, 171, 161);
		panel_1.add(photolabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(14, 257, 833, 176);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_16 = new JLabel("\u804C\u52A1\u4FE1\u606F");
		label_16.setForeground(Color.BLUE);
		label_16.setBounds(0, 0, 72, 24);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("\u90E8    \u95E8\uFF1A");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setBounds(10, 23, 80, 24);
		panel_2.add(label_17);
		
		
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "1", "2", "\u4EBA\u4E8B\u7BA1\u7406\u90E8"}));
		comboBox_3.setBounds(90, 20, 72, 24);
		panel_2.add(comboBox_3);
		
		JLabel label_18 = new JLabel("\u804C    \u52A1\uFF1A");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setBounds(215, 23, 80, 24);
		panel_2.add(label_18);
		
		
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "1", "2", "\u6279\u51C6\u4EBA"}));
		comboBox_4.setBounds(296, 20, 72, 24);
		panel_2.add(comboBox_4);
		
		JLabel label_19 = new JLabel("\u5165\u804C\u65E5\u671F\uFF1A");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setBounds(10, 51, 80, 24);
		panel_2.add(label_19);
		
		tf2_1 = new JTextField();
		tf2_1.setBounds(90, 51, 86, 24);
		panel_2.add(tf2_1);
		tf2_1.setColumns(10);
		
		JLabel label_20 = new JLabel("\u7528\u5DE5\u5F62\u5F0F\uFF1A");
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(215, 51, 80, 24);
		panel_2.add(label_20);
		
		
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "1", "2"}));
		comboBox_5.setToolTipText("");
		comboBox_5.setBounds(296, 48, 72, 24);
		panel_2.add(comboBox_5);
		
		JLabel label_21 = new JLabel("\u79BB\u804C\u65E5\u671F\uFF1A");
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setBounds(425, 51, 80, 24);
		panel_2.add(label_21);
		
		tf2_2 = new JTextField();
		tf2_2.setBounds(507, 48, 86, 24);
		panel_2.add(tf2_2);
		tf2_2.setColumns(10);
		
		JLabel label_22 = new JLabel("\u79BB\u804C\u539F\u56E0\uFF1A");
		label_22.setHorizontalAlignment(SwingConstants.RIGHT);
		label_22.setBounds(640, 51, 80, 24);
		panel_2.add(label_22);
		
		tf2_3 = new JTextField();
		tf2_3.setBounds(721, 48, 86, 24);
		panel_2.add(tf2_3);
		tf2_3.setColumns(10);
		
		JLabel label_23 = new JLabel("\u5408\u540C\u5F00\u59CB\uFF1A");
		label_23.setHorizontalAlignment(SwingConstants.RIGHT);
		label_23.setBounds(10, 82, 80, 24);
		panel_2.add(label_23);
		
		tf2_4 = new JTextField();
		tf2_4.setBounds(90, 82, 86, 24);
		panel_2.add(tf2_4);
		tf2_4.setColumns(10);
		
		JLabel label_24 = new JLabel("\u5408\u540C\u7ED3\u675F\uFF1A");
		label_24.setHorizontalAlignment(SwingConstants.RIGHT);
		label_24.setBounds(215, 82, 80, 24);
		panel_2.add(label_24);
		
		tf2_5 = new JTextField();
		tf2_5.setBounds(296, 82, 86, 24);
		panel_2.add(tf2_5);
		tf2_5.setColumns(10);
		
		JLabel label_25 = new JLabel("\u8F6C\u6B63\u65E5\u671F\uFF1A");
		label_25.setHorizontalAlignment(SwingConstants.RIGHT);
		label_25.setBounds(425, 82, 80, 24);
		panel_2.add(label_25);
		
		tf2_6 = new JTextField();
		tf2_6.setBounds(507, 82, 86, 24);
		panel_2.add(tf2_6);
		tf2_6.setColumns(10);
		
		JLabel label_26 = new JLabel("\u8F6C\u6B63\u5DE5\u9F84\uFF1A");
		label_26.setHorizontalAlignment(SwingConstants.RIGHT);
		label_26.setBounds(640, 82, 80, 24);
		panel_2.add(label_26);
		
		tf2_7 = new JTextField();
		tf2_7.setBounds(721, 82, 86, 24);
		panel_2.add(tf2_7);
		tf2_7.setColumns(10);
		
		JLabel label_27 = new JLabel("\u53D1\u5361\u94F6\u884C\uFF1A");
		label_27.setHorizontalAlignment(SwingConstants.RIGHT);
		label_27.setBounds(10, 116, 80, 24);
		panel_2.add(label_27);
		
		tf2_8 = new JTextField();
		tf2_8.setBounds(90, 116, 86, 24);
		panel_2.add(tf2_8);
		tf2_8.setColumns(10);
		
		JLabel label_28 = new JLabel("\u793E\u4F1A\u4FDD\u9669\uFF1A");
		label_28.setHorizontalAlignment(SwingConstants.RIGHT);
		label_28.setBounds(215, 116, 80, 24);
		panel_2.add(label_28);
		
		tf2_9 = new JTextField();
		tf2_9.setBounds(296, 116, 86, 24);
		panel_2.add(tf2_9);
		tf2_9.setColumns(10);
		
		JLabel label_29 = new JLabel("\u5931\u4E1A\u4FDD\u9669\uFF1A");
		label_29.setHorizontalAlignment(SwingConstants.RIGHT);
		label_29.setBounds(425, 116, 80, 24);
		panel_2.add(label_29);
		
		tf2_10 = new JTextField();
		tf2_10.setBounds(507, 116, 86, 24);
		panel_2.add(tf2_10);
		tf2_10.setColumns(10);
		
		JLabel label_30 = new JLabel("\u517B\u8001\u4FDD\u9669\uFF1A");
		label_30.setHorizontalAlignment(SwingConstants.RIGHT);
		label_30.setBounds(640, 116, 80, 24);
		panel_2.add(label_30);
		
		tf2_11 = new JTextField();
		tf2_11.setBounds(721, 116, 86, 24);
		panel_2.add(tf2_11);
		tf2_11.setColumns(10);
		
		JLabel label_42 = new JLabel("\u4FE1\u7528\u5361\u53F7\uFF1A");
		label_42.setHorizontalAlignment(SwingConstants.RIGHT);
		label_42.setBounds(10, 146, 80, 24);
		panel_2.add(label_42);
		
		tf2_12 = new JTextField();
		tf2_12.setBounds(90, 146, 86, 24);
		panel_2.add(tf2_12);
		tf2_12.setColumns(10);
		
		JLabel label_43 = new JLabel("\u533B\u7597\u4FDD\u9669\uFF1A");
		label_43.setHorizontalAlignment(SwingConstants.RIGHT);
		label_43.setBounds(215, 146, 80, 24);
		panel_2.add(label_43);
		
		tf2_13 = new JTextField();
		tf2_13.setBounds(296, 146, 86, 24);
		panel_2.add(tf2_13);
		tf2_13.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u5DE5\u4F24\u4FDD\u9669\uFF1A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(425, 146, 80, 24);
		panel_2.add(lblNewLabel_3);
		
		tf2_14 = new JTextField();
		tf2_14.setBounds(507, 146, 86, 24);
		panel_2.add(tf2_14);
		tf2_14.setColumns(10);
		
		JLabel label_44 = new JLabel("\u516C\u79EF\u91D1\u53F7\uFF1A");
		label_44.setHorizontalAlignment(SwingConstants.RIGHT);
		label_44.setBounds(640, 146, 80, 24);
		panel_2.add(label_44);
		
		tf2_15 = new JTextField();
		tf2_15.setBounds(721, 146, 86, 24);
		panel_2.add(tf2_15);
		tf2_15.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(14, 446, 833, 202);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_31 = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		label_31.setForeground(Color.BLUE);
		label_31.setBounds(0, 0, 72, 18);
		panel_3.add(label_31);
		
		JLabel lblNewLabel = new JLabel("\u79FB\u52A8\u7535\u8BDD\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 24, 80, 24);
		panel_3.add(lblNewLabel);
		
		tf3_1 = new JTextField();
		tf3_1.setBounds(82, 24, 86, 24);
		panel_3.add(tf3_1);
		tf3_1.setColumns(10);
		
		JLabel label_32 = new JLabel("\u56FA\u5B9A\u7535\u8BDD\uFF1A");
		label_32.setHorizontalAlignment(SwingConstants.RIGHT);
		label_32.setBounds(212, 24, 80, 24);
		panel_3.add(label_32);
		
		tf3_2 = new JTextField();
		tf3_2.setBounds(292, 24, 86, 24);
		panel_3.add(tf3_2);
		tf3_2.setColumns(10);
		
		JLabel lblQq = new JLabel("  QQ  \uFF1A");
		lblQq.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQq.setBounds(420, 24, 80, 24);
		panel_3.add(lblQq);
		
		tf3_3 = new JTextField();
		tf3_3.setBounds(499, 24, 86, 24);
		panel_3.add(tf3_3);
		tf3_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" E_mail :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(636, 24, 80, 24);
		panel_3.add(lblNewLabel_1);
		
		tf3_4 = new JTextField();
		tf3_4.setBounds(722, 24, 86, 24);
		panel_3.add(tf3_4);
		tf3_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7B2C\u4E8C\u5B66\u5386\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(0, 60, 80, 24);
		panel_3.add(lblNewLabel_2);
		
		tf3_5 = new JTextField();
		tf3_5.setBounds(82, 61, 86, 24);
		panel_3.add(tf3_5);
		tf3_5.setColumns(10);
		
		JLabel label_33 = new JLabel("\u7B2C\u4E8C\u4E13\u4E1A\uFF1A");
		label_33.setHorizontalAlignment(SwingConstants.RIGHT);
		label_33.setBounds(212, 60, 80, 24);
		panel_3.add(label_33);
		
		tf3_6 = new JTextField();
		tf3_6.setBounds(292, 61, 86, 24);
		panel_3.add(tf3_6);
		tf3_6.setColumns(10);
		
		JLabel label_34 = new JLabel("\u6BD5\u4E1A\u65E5\u671F\uFF1A");
		label_34.setHorizontalAlignment(SwingConstants.RIGHT);
		label_34.setBounds(420, 60, 80, 24);
		panel_3.add(label_34);
		
		tf3_7 = new JTextField();
		tf3_7.setBounds(499, 61, 86, 24);
		panel_3.add(tf3_7);
		tf3_7.setColumns(10);
		
		JLabel label_35 = new JLabel("\u6BD5\u4E1A\u5B66\u6821\uFF1A");
		label_35.setHorizontalAlignment(SwingConstants.RIGHT);
		label_35.setBounds(636, 60, 80, 24);
		panel_3.add(label_35);
		
		tf3_8 = new JTextField();
		tf3_8.setBounds(722, 61, 86, 24);
		panel_3.add(tf3_8);
		tf3_8.setColumns(10);
		
		JLabel label_36 = new JLabel("\u7535\u8111\u6C34\u5E73\uFF1A");
		label_36.setHorizontalAlignment(SwingConstants.RIGHT);
		label_36.setBounds(0, 95, 80, 24);
		panel_3.add(label_36);
		
		tf3_9 = new JTextField();
		tf3_9.setBounds(81, 95, 86, 24);
		panel_3.add(tf3_9);
		tf3_9.setColumns(10);
		
		JLabel label_37 = new JLabel("\u7231    \u597D\uFF1A");
		label_37.setHorizontalAlignment(SwingConstants.RIGHT);
		label_37.setBounds(212, 95, 80, 24);
		panel_3.add(label_37);
		
		tf3_10 = new JTextField();
		tf3_10.setBounds(292, 95, 516, 24);
		panel_3.add(tf3_10);
		tf3_10.setColumns(10);
		
		JLabel label_38 = new JLabel("\u5165\u515A\u65E5\u671F\uFF1A");
		label_38.setHorizontalAlignment(SwingConstants.RIGHT);
		label_38.setBounds(0, 132, 80, 24);
		panel_3.add(label_38);
		
		tf3_11 = new JTextField();
		tf3_11.setBounds(82, 132, 86, 24);
		panel_3.add(tf3_11);
		tf3_11.setColumns(10);
		
		JLabel label_39 = new JLabel("\u7279    \u957F\uFF1A");
		label_39.setHorizontalAlignment(SwingConstants.RIGHT);
		label_39.setBounds(212, 132, 80, 24);
		panel_3.add(label_39);
		
		tf3_12 = new JTextField();
		tf3_12.setBounds(292, 132, 516, 24);
		panel_3.add(tf3_12);
		tf3_12.setColumns(10);
		
		JLabel label_40 = new JLabel("\u90AE\u653F\u7F16\u7801\uFF1A");
		label_40.setHorizontalAlignment(SwingConstants.RIGHT);
		label_40.setBounds(0, 164, 80, 24);
		panel_3.add(label_40);
		
		tf3_13 = new JTextField();
		tf3_13.setBounds(82, 164, 86, 24);
		panel_3.add(tf3_13);
		tf3_13.setColumns(10);
		
		JLabel label_41 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740\uFF1A");
		label_41.setHorizontalAlignment(SwingConstants.RIGHT);
		label_41.setBounds(212, 164, 80, 24);
		panel_3.add(label_41);
		
		tf3_14 = new JTextField();
		tf3_14.setBounds(292, 164, 516, 24);
		panel_3.add(tf3_14);
		tf3_14.setColumns(10);

	}
	/**
	 * 保存档案管理信息处理事件
	 * @throws ClassNotFoundException 
	 */
	private void baocunActionPerformed() throws ClassNotFoundException {
		String dbClassName = "com.mysql.cj.jdbc.Driver";// MySQL数据库驱动类的名称
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/java?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true";// 访问MySQL数据库的路径
		String dbUser = "root";// 访问MySQL数据库的用户名
		String dbPwd = "123456";// 访问MySQL数据库的密码
		Connection conn = null;
		PreparedStatement stmt = null;
		String record_number = textField.getText(); 
		String name = textField_1.getText();
		String sex = radioButton.isSelected()?radioButton.getText():radioButton_1.getText();
		String birthday = txtYyyymmdd.getText();
		//SerialBlob photo = new SerialBlob(filter);
		String nation_id = (String) comboBox.getSelectedItem();
		String native_place_id = (String) comboBox_1.getSelectedItem();
		String id_card = textField_3.getText();
		String school_age = (String) comboBox_2.getSelectedItem();
		String specialty = textField_4.getText();
		String party_member = radioButton_5.isSelected()?radioButton_5.getText():radioButton_6.getText();
		String foreign_language = textField_5.getText();
		String grade = textField_6.getText();
		String marriaged = radioButton_3.isSelected()?radioButton_3.getText():radioButton_4.getText();
		String postalcode = textField_7.getText();
		String address = textField_8.getText();
		
		String dept_id = (String) comboBox_3.getSelectedItem();
		String duty_id = (String) comboBox_4.getSelectedItem();
		String accession_date = tf2_1.getText();
		String accession_form_id = (String) comboBox_5.getSelectedItem();
		String dimission_date = tf2_2.getText();
		String dimission_reason = tf2_3.getText();
		String pact_start_date = tf2_4.getText();
		String pact_end_date = tf2_5.getText();
		String first_pact_date = tf2_6.getText();
		String first_pact_age = tf2_7.getText();
		String bank_name = tf2_8.getText();
		String society_safety_NO = tf2_9.getText();
		String dole_safety_NO = tf2_10.getText();
		String annuity_safety_NO = tf2_11.getText();
		String bank_NO = tf2_12.getText();
		String medicare_safety_NO = tf2_13.getText();
		String compo_safety_NO = tf2_14.getText();
		String accumulation_fund_NO = tf2_15.getText();
				
		
		String handset = tf3_1.getText();
		String telephone = tf3_2.getText();
		String QQ = tf3_3.getText();
		String E_mail = tf3_4.getText();
		String second_school_age = tf3_5.getText();
		String second_specialty = tf3_6.getText();
		String graduate_date = tf3_7.getText();
		String graduate_school = tf3_8.getText();
		String computer_grade = tf3_9.getText();
		String likes = tf3_10.getText();
		String party_number_date = tf3_11.getText();
		String ones_strong_suit = tf3_12.getText();
		String postalcode1 = tf3_13.getText();
		String address1 = tf3_14.getText();
		if("".equals(name.trim())||"".equals(birthday.trim())||"".equals(id_card.trim())||"".equals(specialty.trim())
				||"".equals(foreign_language.trim())||"".equals(grade.trim())||"".equals(postalcode1.trim())||"".equals(native_place_id.trim())) {
			JOptionPane.showMessageDialog(null,"请填完所以信息!","提示",JOptionPane.INFORMATION_MESSAGE);
			return ;
		}	
		else {
			java.util.Date d = null;
			try {
				d = format.parse(birthday);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"日期填写形式不对!","提示",JOptionPane.INFORMATION_MESSAGE);
				return ;
			}
		}
		try {
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	//插入档案信息表
			String sql = "insert into tb_record(record_number,name,sex,birthday,photo,ID_card,marriaged,nation_id,native_place_id,address,postalcode,party_member,"
					+ "school_age,specialty,foreign_language,grade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
		 	stmt.setString(1, record_number);
            stmt.setString(2, name); 
            stmt.setString(3, sex); 
            stmt.setString(4, birthday);
            stmt.setString(5, record_number);
            stmt.setString(6, id_card);
            stmt.setString(7, marriaged);
            stmt.setString(8, nation_id);
            stmt.setString(9, native_place_id);
            stmt.setString(10, address1);
            stmt.setString(11, postalcode1); 
            stmt.setString(12, party_member); 
            stmt.setString(13, school_age);
            stmt.setString(14, specialty);
            stmt.setString(15, foreign_language);
            stmt.setString(16, grade);
            stmt.executeUpdate();
            //插入职务信息表
            String sql1 = "insert into tb_duty_info(dept_id,duty_id,accession_date,accession_form_id,dimission_date,dimission_reason,first_pact_date,pact_start_date,pact_end_date,"
            		+ "bank_name,bank_NO,society_safety_NO,annuity_safety_NO,dole_safety_NO,medicare_safety_NO,compo_safety_NO,accumulation_fund_NO,first_pact_age) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            stmt = conn.prepareStatement(sql1);
            stmt.setString(1, dept_id);
            stmt.setString(2, duty_id); 
            stmt.setString(3, accession_date); 
            stmt.setString(4, accession_form_id);
            stmt.setString(5, dimission_date);
            stmt.setString(6, dimission_reason);
            stmt.setString(7, first_pact_date);
            stmt.setString(8, pact_start_date);
            stmt.setString(9, pact_end_date);
            stmt.setString(10, bank_name);
            stmt.setString(11, bank_NO); 
            stmt.setString(12, society_safety_NO); 
            stmt.setString(13, annuity_safety_NO);
            stmt.setString(14, dole_safety_NO);
            stmt.setString(15, medicare_safety_NO);
            stmt.setString(16, compo_safety_NO);
            stmt.setString(17, accumulation_fund_NO);
            stmt.setString(18, first_pact_age);
            stmt.executeUpdate();
            //插入个人信息表
		 	String sql2 = "insert into tb_personal_info(QQ,E_mail,handset,telephone,address,postalcode,second_school_age,second_specialty,graduate_school,"
		 			+ "graduate_date,party_number_date,computer_grade,likes,ones_strong_suit) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
		 	stmt = conn.prepareStatement(sql2);
            stmt.setString(1, QQ);
            stmt.setString(2, E_mail); 
            stmt.setString(3, handset); 
            stmt.setString(4, telephone);
            stmt.setString(5, address1);
            stmt.setString(6, postalcode1);
            stmt.setString(7, second_school_age);
            stmt.setString(8, second_specialty);
            stmt.setString(9, graduate_school);
            stmt.setString(10, graduate_date);
            stmt.setString(11, party_number_date); 
            stmt.setString(12, computer_grade); 
            stmt.setString(13, likes);
            stmt.setString(14, ones_strong_suit);           
		 	stmt.executeUpdate();
		 
            
		 	setNull();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	/**
	 * 清空处理事件
	 */
	private void setNull() {
		textField.setText("");
		textField_1.setText("");
		txtYyyymmdd.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		comboBox.setSelectedItem("");
		comboBox_1.setSelectedItem("");
		comboBox_2.setSelectedItem("");
		comboBox_3.setSelectedItem("");
		comboBox_4.setSelectedItem("");
		comboBox_5.setSelectedItem("");
		tf2_1.setText("");
		tf2_2.setText("");
		tf2_3.setText("");
		tf2_4.setText("");
		tf2_5.setText("");
		tf2_6.setText("");
		tf2_7.setText("");
		tf2_8.setText("");
		tf2_9.setText("");
		tf2_10.setText("");
		tf2_11.setText("");
		tf2_12.setText("");
		tf2_13.setText("");
		tf2_12.setText("");
		tf2_15.setText("");
		tf3_1.setText("");
		tf3_2.setText("");
		tf3_3.setText("");
		tf3_4.setText("");
		tf3_5.setText("");
		tf3_6.setText("");
		tf3_7.setText("");
		tf3_8.setText("");
		tf3_9.setText("");
		tf3_10.setText("");
		tf3_11.setText("");
		tf3_12.setText("");
		tf3_13.setText("");
		tf3_14.setText("");
		
	}
}
