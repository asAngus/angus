package com.wp.test.weipeng;

import org.junit.Test;

/**
 * @author Hudan
 * @version 1.0
 * @desc Mybatis的mybatis-generator-core插件自动生成Mybatis相关代码功能测试
 * @filename GeneratorTest.java
 * @copyright www.symdata.cn
 * @date 2017/2/4
 */
public class TestGenerator {

    public static void main(String[] args) {
        MyBatisGeneratorTools.generatorMyBatisFiles(null);
    }

    @Test
    public void generatorTest() {
        MyBatisGeneratorTools.generatorMyBatisFiles(null);
    }
}