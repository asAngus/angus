/**
 * Title: DBImport.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: cloudfn<br/>
 *
 */
package com.cmm.tools.dbimport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Title: DBImport<br/>
 * Description: <br/>
 * Company: cloudfn<br/>
 * 
 * @author weipeng
 * @date 2016年6月28日上午10:29:35
 *
 */
public class DBImport {

    /**
     * Title: main<br/>
     * Description: <br/>
     * 
     * @author weipeng
     * @date 2016年6月28日上午10:29:35
     *
     * @param args
     */
    public static void main(String[] args) {
        // 设定数据库驱动，数据库连接地址、端口、名称，用户名，密码
        String driverName = "oracle.jdbc.driver.OracleDriver";
         //String url = "jdbc:oracle:thin:@112.96.28.45:8006/wowealth"; // 开发
        String url = "jdbc:oracle:thin:@112.96.28.45:8004/wowealth"; // 测试
        String user = "payadm"; // aa为用户名
        String password = "m03&23f^"; // 测试
         //String password = "Z9!&81f^o"; // 开发

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 数据库连接对象
        Connection conn = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./test.cvs"));
            String readLine = reader.readLine();
            Map<String, List<String>> tmp = new HashMap<>();
            while (readLine != null) {
                String[] split = readLine.split(",");

                List<String> list = tmp.get(split[0]);
                if (list == null) {
                    list = new ArrayList<>();
                    tmp.put(split[0], list);
                }
                list.add(readLine);
                readLine = reader.readLine();
            }
            // 反射Oracle数据库驱动程序类
            Class.forName(driverName);

            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, password);

            // 输出数据库连接
            System.out.println(conn);

            // 定制sql命令
            String sql = "Insert into MKMOUTSCOREPRDDETAIL (PRD_ID,CODE_STS,FAV_ACCOUNT,FAV_CODE,TM_SMP) values (?,?,?,?,to_char(sysdate,'yyyymmddhhmiss'))";
            String del = "delete MKMOUTSCOREPRDDETAIL where prd_id=?";
            // 创建该连接下的PreparedStatement对象
            pstmt = conn.prepareStatement(sql);
            PreparedStatement delPstmt = conn.prepareStatement(del);
            Set<Entry<String, List<String>>> entrySet = tmp.entrySet();
            for (Entry<String, List<String>> entry : entrySet) {
                String key = entry.getKey();
                delPstmt.setString(1, key);
                delPstmt.executeUpdate();
                List<String> value = entry.getValue();

                for (String string : value) {
                    int i = 1;
                    String[] split = string.split(",");
                    for (String valueStr : split) {
                        pstmt.setString(i++, valueStr);
                    }
                    pstmt.addBatch();
                }

            }

            // 执行查询语句，将数据保存到ResultSet对象中
            pstmt.executeBatch();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                reader.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
