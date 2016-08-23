package com.cmm.tools.excel;

import java.io.IOException;
import java.util.List;

/**
 * @author Hongten
 * @created 2014-5-21
 */
public class Client {

    public static void main(String[] args) throws IOException {
        // String excel2003_2007 = Common.STUDENT_INFO_XLS_PATH;
        // String excel2010 = Common.STUDENT_INFO_XLSX_PATH;
        // read the 2003-2007 excel
        // new ReadExcel().readExcel("file/¹µ²Û¿ªÍÚ");

        System.out.println("======================================");
        // read the 2010 excel
        new ReadExcel().readExcel("file/¹µ²Û¿ªÍÚ.xlsx");

    }
}