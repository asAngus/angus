package com.cmm.tools.excel;

import java.io.File;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class UpdateXLS1 {
    public static void main(String args[]) {
        try {
            // Excel获得文件
            Workbook wb = Workbook.getWorkbook(new File("file/沟槽开挖.xls"));

            // 打开一个文件的副本，并且指定数据写回到原文件
            WritableWorkbook book = Workbook
                    .createWorkbook(new File("file/test1.xls"), wb);
            WritableSheet sheet = book.getSheet(0);
            Cell cell = null;
            for (int i = 0; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    cell = sheet.getCell(j, i);
                    String contents = cell.getContents();
                    if (contents.equals("{{comName}}")) {
                        CellFeatures cellFeatures = cell.getCellFeatures();
                        jxl.write.Label label4 = new jxl.write.Label(j, i,
                                "第一个公司");
                        if (cellFeatures instanceof WritableCellFeatures) {
                            label4.setCellFeatures(
                                    (WritableCellFeatures) cellFeatures);
                        }
                        CellFormat cellFormat = cell.getCellFormat();
                        label4.setCellFormat(cellFormat);
                        sheet.addCell(label4);
                    }
                }
            }
            // jxl.write.Number number = new jxl.write.Number(2, 3, 24);
            // sheet.addCell(number);
            // jxl.write.Label label = new jxl.write.Label(2, 11,
            // "可以读取Excel 95, 97, 2000文件可以读或写Excel
            // 97及其以后版本的的公式（不过我发现好像有bug）生成Excel 97格式的电子表格 ");
            // sheet.addCell(label);
            // jxl.write.Label label4 = new jxl.write.Label(5, 4,
            // "可以读取Excel 95, 97, 2000文件可以读或写Excel
            // 97及其以后版本的的公式（不过我发现好像有bug）生成Excel 97格式的电子表格 ");
            // sheet.addCell(label4);
            // jxl.write.Label label2 = new jxl.write.Label(1, 11,
            // "2005.11.5-2005.11.25");
            // sheet.addCell(label2);
            // jxl.write.Label label3 = new jxl.write.Label(1, 12,
            // "2005.11.15-2005.11.25");
            // sheet.addCell(label3);
            // 添加一个工作表
            // WritableSheet sheet=book.createSheet("第二页",1);

            // sheet.addCell(new Label(0,0,"第二页的测试数据"));

            book.write();
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}