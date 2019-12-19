package com.groupsix.frame.ReportManage;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ReportManage extends JInternalFrame {
	private JTable table;
	private JFrame jf;
	private JScrollPane scrollPane = new JScrollPane();
	/**
	 * Create the panel.
	 */
	public ReportManage() {
		setBounds(100, 100, 950, 757);
		setLayout(new BorderLayout(0, 0));
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton Button_baobiao = new JButton("\u751F\u6210\u62A5\u8868");
		Button_baobiao.addActionListener(new ActionListener() {
			private Icon frameIcon;
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(jf, "保存选课表", FileDialog.SAVE);
			     fd.setLocation(500, 350);
			        fd.setVisible(true);  
			        String stringfile = fd.getDirectory()+fd.getFile()+".xls";  
			           try {
			            ExportTable export = new ExportTable();
			            export.exportTable1(table, new File(stringfile));
			            JOptionPane.showMessageDialog(null,"操作结束。","提示",JOptionPane.INFORMATION_MESSAGE, frameIcon);
			           } catch (IOException ex) {
			               ex.printStackTrace();
			           } 
			
				
			}
		});
		Button_baobiao.setBounds(481, 444, 120, 40);
		panel.add(Button_baobiao);
		
		JButton button = new JButton("\u6D4F\u89C8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
			}
		});
		panel.add(button);
		
		add(scrollPane, BorderLayout.CENTER);
		/**
		 * 显示所有信息在表格
		 * @throws ClassNotFoundException
		 */
		
	}
	
	public void init(){
		// TODO 自动生成的方法存根
		Vector rowData = new Vector();
		Vector<String> columnName = new Vector<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			columnName.add("编号");
			columnName.add("姓名");
			columnName.add("部门");
			columnName.add("职务");
			columnName.add("入职时间");
			columnName.add("用工形式");
			columnName.add("发卡银行");
			columnName.add("银行卡号");
			columnName.add("社会保险");
			columnName.add("养老保险");
			columnName.add("医疗保险");
			columnName.add("工伤保险");
			columnName.add("住房公积金号");
			columnName.add("");

			
			String dbClassName = "com.mysql.cj.jdbc.Driver";
			String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
			String dbUser = "studio";
			String dbPwd = "Mystudi0";
			Class.forName(dbClassName);				
		 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		 	String sql = "select tb_duty_info.id,tb_record.`name`,tb_duty_info.dept_id,tb_duty_info.duty_id,tb_duty_info.accession_date,tb_duty_info.accession_form_id,tb_duty_info.bank_name,tb_duty_info.bank_NO,tb_duty_info.society_safety_NO,tb_duty_info.annuity_safety_NO,tb_duty_info.medicare_safety_NO,tb_duty_info.compo_safety_NO,tb_duty_info.accumulation_fund_NO,tb_timecard.account_item_id\r\n" + 
		 			"FROM tb_record,tb_duty_info,tb_timecard\r\n" + 
		 			"WHERE tb_record.id=tb_duty_info.id AND tb_duty_info.id=tb_timecard.id;";
		 	stmt = conn.prepareStatement(sql);
			conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Vector<Object> V = new Vector<>();
				V.add(rs.getString(1));
				V.add(rs.getString(2));
				V.add(rs.getString(3));
				V.add(rs.getString(4));
				V.add(rs.getString(5));
				V.add(rs.getString(6));
				V.add(rs.getString(7));
				V.add(rs.getString(8));
				V.add(rs.getString(9));
				V.add(rs.getString(10));
				V.add(rs.getString(11));
				V.add(rs.getString(12));
				V.add(rs.getString(13));
				rowData.add(V);
			}
			//SetNull();
//			DefaultTableCellRenderer de=new DefaultTableCellRenderer();
//            table.setDefaultRenderer(Object.class, de);
            table=new JTable(rowData, columnName);
            table.updateUI();
            scrollPane.setViewportView(table);
            this.setVisible(true);
		}
        catch(SQLException | ClassNotFoundException e1) {
    		e1.printStackTrace();
    	}
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportManage window = new ReportManage();
					window.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
