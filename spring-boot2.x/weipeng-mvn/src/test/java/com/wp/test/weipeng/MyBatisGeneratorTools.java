package com.wp.test.weipeng;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hudan
 * @version 1.0
 * @desc mybatis 自动生成代码工具类,用于处理根据指定的generator.xml配置文件,生成相应的Mybatis相关代码
 * @filename MyBatisGeneratorTools.java
 * @copyright www.symdata.cn
 * @date 2018/08/25
 */
public class MyBatisGeneratorTools {

    /**
     * @desc 读取指定的配置文件信息, 调用mybatis的generator自动生成mybatis对应的entity、dao、mapper文件
     */
    public static void generatorMyBatisFiles(String genCfgFile) {
        // 读取配置文件; 默认src的一级目录下
        if (StringUtils.isEmpty(genCfgFile)) {
            genCfgFile = "/generatorConfig.xml";
        }
        File configFile = new File(Thread.currentThread().getClass().getResource(genCfgFile).getFile());

        try {
            InputStream fis = new FileInputStream(configFile);
            generatorMyBatisFiles(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc 将配置文件读入InputStream流后, 调用mybatis的generator自动生成mybatis对应的entity、dao、mapper文件
     */
    private static void generatorMyBatisFiles(InputStream inputStream) {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config;
        try {
            config = cp.parseConfiguration(inputStream);

            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator;
            try {
                myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);

                // 打印结果
                for (String str : warnings) {
                    System.out.println(str);
                }
            } catch (SQLException | IOException | InterruptedException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        } catch (IOException | XMLParserException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成!");
    }
}