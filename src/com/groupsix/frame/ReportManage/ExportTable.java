package com.groupsix.frame.ReportManage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;
public class ExportTable {
	/**µ¼³öJTableµ½excel */
    public void exportTable1(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));  
        for(int i=0; i < model.getColumnCount(); i++) {
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        bWriter.newLine();
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
               // bWriter.write(model.getValueAt(i,j).toString());
            	//bWriter.write("1");
            	
            	String a = table.getValueAt(i, j)==null ? "--" : table.getValueAt(i, j).toString();
            	
            	bWriter.write(a);
                bWriter.write("\t");
            }
            bWriter.newLine();
        }
        bWriter.close();
    }

}
