package com.wp.test.weipeng;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Hudan
 * @version 1.0
 * @desc mybatis的generator自动生成mybatis相关代码时, 增加对应注释
 * @filename MyCommentGenerator.java
 * @copyright www.symdata.cn
 * @date 2018/08/25
 */
public class MyCommentGenerator implements CommentGenerator {

    /**
     * 开始的分隔符，例如mysql为`，sqlserver为[
     */
    private String beginningDelimiter = "";
    /**
     * 结束的分隔符，例如mysql为`，sqlserver为]
     */
    private String endingDelimiter = "";
    /**
     * 系统属性配置
     */
    private Properties systemPro;
    /**
     * 当前时间串
     */
    private String currentDateStr;

    public MyCommentGenerator() {
        super();

        // 运行系统参数对象
        systemPro = System.getProperties();
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    /**
     * @param properties 配置属性信息
     * @desc 获取mybatis-generator的context节点下的property属性,并进行相关配置
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.systemPro.putAll(properties);
        String beginningDelimiter = properties.getProperty("beginningDelimiter");
        if (StringUtility.stringHasValue(beginningDelimiter)) {
            this.beginningDelimiter = beginningDelimiter;
        }
        String endingDelimiter = properties.getProperty("endingDelimiter");
        if (StringUtility.stringHasValue(endingDelimiter)) {
            this.endingDelimiter = endingDelimiter;
        }
    }

    /**
     * @param name 分隔符
     * @return 获取数据库字段的分割符名称
     * @desc 获取generatorConfig.xml文件中context节点下的property属性中 列名/表名分隔符
     */
    private String getDelimiterName(String name) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(beginningDelimiter);
        nameBuilder.append(name);
        nameBuilder.append(endingDelimiter);
        return nameBuilder.toString();
    }

    /**
     * @param compilationUnit Mybatis生成器编辑单元
     * @desc 给mybatis生成的java文件头部增加注释(版权信息, 公司信息等)
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
//		compilationUnit.addFileCommentLine("");
    }

    /**
     * @param topLevelClass     生成的主类对象
     * @param introspectedTable 数据库表对象信息
     * @desc 给mybatis自动生成的Model类添加类注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        // 这里需要更改generator1.3.4以上的源码
        topLevelClass.addJavaDocLine(" * @desc " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * @filename " + topLevelClass.getType().getShortName() + ".java");
        topLevelClass.addJavaDocLine(" * @copyright www.symdata.cn ");
        topLevelClass.addJavaDocLine(" * @author " + systemPro.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * @version 1.0 ");
        topLevelClass.addJavaDocLine(" * @date " + currentDateStr);
        topLevelClass.addJavaDocLine(" */ ");
    }

    /**
     * @param innerClass        生成的Java模型类
     * @param introspectedTable 数据库表对象信息
     * @desc 添加Java类的类注释, 这里主要是采用的数据库中的表说明和注释信息作为说明文字
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**");
        sb.append(" * @desc " + introspectedTable.getFullyQualifiedTable());
        sb.append(" * @filename " + innerClass.getClass().getName() + ".java");
        sb.append(" * @copyright www.symdata.cn");
        sb.append(" * @author " + systemPro.getProperty("user.name"));
        sb.append(" * @version 1.0");
        sb.append(" * @date " + currentDateStr);
        sb.append(" */");
        innerClass.addJavaDocLine(sb.toString());
    }

    /**
     * @param innerClass        生成的Java模型类
     * @param introspectedTable 数据库表对象信息
     * @param markAsDoNotDelete 声明的非删除标记
     * @desc Example使用
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**");
        sb.append(" * @desc " + introspectedTable.getFullyQualifiedTable());
        sb.append(" * @filename " + innerClass.getClass().getName() + ".java");
        sb.append(" * @copyright www.symdata.cn");
        sb.append(" * @author " + systemPro.getProperty("user.name"));
        sb.append(" * @version 1.0");
        sb.append(" * @date " + currentDateStr);
        addJavadocTag(innerClass, markAsDoNotDelete);
        innerClass.addJavaDocLine(" */");
    }

    /**
     * @param innerEnum         泛型类
     * @param introspectedTable 数据库表对象信息
     * @desc 给生成的泛型类增加注释信息
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

        innerEnum.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());
        innerEnum.addJavaDocLine(" */");
    }

    /**
     * @param method            方法名
     * @param introspectedTable 数据库表对象信息
     * @desc 给生成的构造方法添加注释
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 由于采用lombok插件,所以不需要生成构造函数 update by Hudan 2018-08-25
//		method.addJavaDocLine("/**");
//		if (StringUtility.stringHasValue(method.getName())) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(" * @desc 构造方法 ");
//			sb.append(method.getName());
//			method.addJavaDocLine(sb.toString());
//		}
//		method.addJavaDocLine(" */");
    }

    /**
     * @param field             字段
     * @param introspectedTable 数据库表对象信息
     * @desc Example使用 给java属性添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        sb.append("/** ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" */");
        field.addJavaDocLine(sb.toString());

    }

    /**
     * @param field              指定的字段
     * @param introspectedTable  数据库表对象信息
     * @param introspectedColumn 字段的完整信息
     * @desc 给字段添加数据库备注
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            StringBuilder sb = new StringBuilder();
            sb.append("/** ");
            sb.append(introspectedColumn.getRemarks());
            sb.append(" */");
            field.addJavaDocLine(sb.toString());
        }

        // 添加注解
        if (field.isTransient()) {
            // @Column
            field.addAnnotation("@Transient");
        }
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            if (introspectedColumn == column) {
                field.addAnnotation("@Id");
                break;
            }
        }
        String column = introspectedColumn.getActualColumnName();
        if (StringUtility.stringContainsSpace(column) || introspectedTable.getTableConfiguration().isAllColumnDelimitingEnabled()) {
            column = introspectedColumn.getContext().getBeginningDelimiter()
                    + column
                    + introspectedColumn.getContext().getEndingDelimiter();
        }
        if (!column.equals(introspectedColumn.getJavaProperty())) {
            //@Column
            field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
        } else if (StringUtility.stringHasValue(beginningDelimiter) || StringUtility.stringHasValue(endingDelimiter)) {
            field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
        }
        if (introspectedColumn.isIdentity()) {
            if (introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement().equals("JDBC")) {
                field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
            } else {
                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
        } else if (introspectedColumn.isSequenceColumn()) {
            field.addAnnotation("@SequenceGenerator(name=\"\",sequenceName=\"" + introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement() + "\")");
        }
    }

    /**
     * @param method             方法名
     * @param introspectedTable  数据库表对象信息
     * @param introspectedColumn 字段
     * @desc 给getter方法添加注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" * @desc 获取");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());
        }
        sb.setLength(0);
        sb.append(" * @return ");
        sb.append(StringUtils.toCamelCase(introspectedColumn.getActualColumnName()));
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" - ");
            sb.append(introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    /**
     * @param method             方法名
     * @param introspectedTable  数据库表对象信息
     * @param introspectedColumn 字段
     * @desc 给setter方法添加注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" * @desc 设置");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());
        }
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    /**
     * @param rootElement mapper文件的xml根节点
     * @desc 生成xml根结点的注释
     */
    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    /**
     * @param xmlElement mapper文件的xml子节点
     * @desc 给生成的 mapper的XML 文件增加注释
     */
    @Override
    public void addComment(XmlElement xmlElement) {
        xmlElement.addElement(new TextElement("<!--"));
        StringBuilder sb = new StringBuilder();
        sb.append("  WARNING - ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));
        xmlElement.addElement(new TextElement("-->"));
    }

    /**
     * @param javaElement       xml节点对象
     * @param markAsDoNotDelete 是否标记为不删除标识
     * @desc 删除标记; 增加mybatis-generaotr插件的标记信息, 用于再次生成自动生成代码时,方便删除自动生成的代码;
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        javaElement.addJavaDocLine(sb.toString());
    }
}