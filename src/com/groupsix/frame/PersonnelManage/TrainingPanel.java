package com.groupsix.frame.PersonnelManage;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrainingPanel extends JPanel{
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainingPanel frame = new TrainingPanel();
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
	public TrainingPanel() {
		setBounds(100, 100, 907, 755);
		setLayout(null);
		
		JButton button = new JButton("\u65B0\u5EFA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				new peixun_add().setVisible(true);
			}
		});
		button.setBounds(326, 13, 80, 27);
		add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbClassName = "com.mysql.cj.jdbc.Driver";
				String dbUrl = "jdbc:mysql://rm-wz9lq6k6utik309l04o.mysql.rds.aliyuncs.com:3306/db_person";
				String dbUser = "studio";
				String dbPwd = "Mystudi0";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				Vector rowData = new Vector();
				Vector<String> columnName = new Vector<String>();
				
				try {
					Class.forName(dbClassName);				
				 	conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				 	columnName.add("���");
				 	columnName.add("��ѵ����");
				 	columnName.add("��ѵ����");
				 	columnName.add("��ѵ����");
				 	columnName.add("��ѵ��ʼʱ��");
				 	columnName.add("��ѵ����ʱ��");
				 	columnName.add("��ѵ��λ");
				 	columnName.add("��ѵ��ʦ");
				 	columnName.add("��ѵ�ص�");			 	
				 	String sql="select * from tb_bring_up_content";
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
				 		rowData.add(V);
				 	}
				 	DefaultTableCellRenderer de=new DefaultTableCellRenderer();
		            table.setDefaultRenderer(Object.class, de);
		            table=new JTable(rowData, columnName);
		            table.updateUI();
		            scrollPane.setViewportView(table);
		            this.setVisible(true);
		            
		            stmt.close();
		            conn.close();
				}catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
			} 

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}

		});
		button_1.setBounds(430, 13, 80, 27);
		add(button_1);
		
		
		scrollPane.setBounds(14, 68, 863, 557);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);

	}

	public void setMaximizedBounds(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
