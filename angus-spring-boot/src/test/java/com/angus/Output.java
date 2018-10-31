package com.angus;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author weipeng
 * @date 2018/10/26 17:20
 */
public class Output {
    @Test
    public void test() {
        File file = new File("/Users/weipeng/Downloads/qq_download/叶根根债权明细");
        File[] files = file.listFiles();

        int i = 0;
        RandomAccessFile fr = null;
        RandomAccessFile wr = null;
        for (File file1 : files) {
            try {

                fr = new RandomAccessFile(file1, "r");
                wr = new RandomAccessFile(file1.getName() + ".csv", "rw");

                String s1 = null;
                int j = 0;
                float sum = 0;
                while ((s1 = fr.readLine()) != null) {
//                    bName、bMoney、insureNo
                    if (s1.contains("bName") || s1.contains("bMoney") || s1.contains("insureNo")) {
                        String[] split1 = s1.replaceAll("\"", "").split(":");
                        if (split1.length > 1) {
                            String s2 = split1[1].trim();
                            if (s1.contains("bMoney")) {
                                sum += Float.parseFloat(s2.substring(0, s2.length() - 1));
                            }
                            wr.writeBytes(s2);
                        } else {
                            System.out.println();
                        }
                        j++;
                    }
                    if (j == 3) {
                        wr.writeBytes("\n");
                        j = 0;
                    }
                }
                wr.close();
                fr.close();
                System.out.println(file1.getName() + ":" + sum);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
