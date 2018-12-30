package com.wp.test.weipeng;

import org.apache.commons.lang3.BooleanUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hudan
 * @version 1.0
 * @desc 字符串处理工具类, 集成自apache的lang3包下的StringUtils
 * @filename StringUtils.java
 * @copyright www.symdata.cn
 * @date 2016/11/13
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\t|\r|\n");
    private static final Pattern ABBR_PATTERN = Pattern.compile("<([a-zA-Z]+)[^<>]*>");

    /**
     * @param str 指定字符串
     * @return boolean
     * @desc 判断指定的字符串是否为空
     */
    public static boolean isNull(String str) {
        return StringUtils.isBlank(str)
                || "null".equals(str.toLowerCase())
                || "".equals(str.trim())
                || str.trim().length() == 0
                || "undefined".equals(str);
    }

    /**
     * @param str 指定字符串
     * @return boolean
     * @desc 判断指定的字符串是否为空或者为字符串0
     */
    public static boolean isZero(String str) {
        return isNull(str) || "0".equals(str.trim());
    }

    /**
     * @param str        需要进行去除首尾两端的空格的字符串
     * @param defaultVal 默认值
     * @return String
     * @desc 将String类型数据去除首尾两端的空格之后, 转换成字符串
     */
    public static String trim(String str, String defaultVal) {
        if (isNull(str)) {
            return defaultVal;
        }
        return trim(str);
    }

    /**
     * @param str 需要剔除括号及括号里面的内容的字符串
     * @return String
     * @desc 将字符串中的括号及括号里面的内容去掉, 括号包括() [] {} （） 这四种类型的括号
     */
    public static String replaceBracket(String str) {
        str = str.replaceAll("\\(.*?\\)|\\{.*?}|\\[.*?]|（.*?）", "");
        return trim(str);
    }

    /**
     * @param str    需要剔除括号里面的内容的字符串
     * @param target 目标字符串
     * @param reg    正则表达式,用于替换括号里需要替换的匹配表达式
     * @return String
     * @desc 将字符串中的括号里面的内容去掉, 括号包括() [] {} （） 这四种类型的括号替换成目标字符串
     */
    public static String replaceBracket(String str, String target, String reg) {
        if (isNull(reg)) {
            reg = "\\d+";
        }

        str = str.replaceAll(reg, target);
        return trim(str);
    }

    /**
     * @param val 需要转换成Integer类型的数字字符串
     * @return Integer
     * @desc 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * @param val 需要转换成Long类型的数字字符串
     * @return Long
     * @desc 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * @param val 需要转换成Float类型的数字字符串
     * @return Float
     * @desc 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * @param val 需要转换成Double类型的数字字符串
     * @return Double
     * @desc 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * @param val 需要转换成boolea类型的数据对象
     * @return Boolean
     * @desc 转换为Boolean类型(true, on, y, t, yes or 1 ( case insensitive) will return true. Otherwise, false is returned)
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * @param val        需要转换成boolea类型的数据对象
     * @param defaultVal 默认值
     * @return
     * @desc 转换为Boolean类型(true, on, y, t, yes or 1 ( case insensitive) will return true. Otherwise, false is returned)
     */
    public static Boolean toBoolean(final Object val, boolean defaultVal) {
        if (val == null) {
            return defaultVal;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * @param str   需要分割的字符串
     * @param split 分隔符
     * @return List
     * @desc 按照指定的分隔符将字符串分割后存入字符串List
     */
    public static List<String> toList(String str, String split) {
        if (isEmpty(str)) {
            return null;
        }
        // 按照指定分隔符进行分割成字符串数组
        String[] sArr = str.split(split);
        return Arrays.asList(sArr);
    }

    /**
     * @param str    需要转换的字符串
     * @param pSplit 外层分隔符
     * @param sSplit 内层分隔符
     * @return Map
     * @desc 将字符串转换成Map对象
     */
    public static Map<String, Object> toMap(String str, String pSplit, String sSplit) {
        if (isNull(str)) {
            return null;
        }

        String[] strArr = str.split(pSplit, -1);
        Map<String, Object> dataMap = new HashMap<>(strArr.length);

        for (String sonStr : strArr) {
            String[] sonArr = sonStr.split(sSplit, -1);
            if ("status".equals(sonArr[0])) {
                dataMap.put(sonArr[0], toList(sonArr[1], ","));
            } else {
                dataMap.put(sonArr[0], sonArr[1]);
            }
        }
        return dataMap;
    }

    /**
     * @param str 需要进行转换测字符串
     * @return byte[]
     * @desc 转换为字节数组
     */
    public static byte[] toBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     * @desc 得到一个字符串的长度, 显示的长度, 一个汉字或日韩文长度为2, 英文字符长度为1
     */
    public static int calcStrLen(String s) {
        int len = 0;
        if (isEmpty(s)) {
            return len;
        }

        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度,如果含中文字符,则每个中文字符长度为2,否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 如果为UTF-8编号,则中文字符长度为3,否则为2
                len += 3;
            } else {
                // 其他字符长度为1
                len += 1;
            }
        }
        return len;
    }

    /**
     * @param str 需要替换的字符串
     * @return String
     * @desc 替换字符串中的制表符、换行符为空格
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = NUMBER_PATTERN.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * @param str     需要替换的字符串
     * @param srcRep  需要替换哪些字符串
     * @param destRep 需要替换成哪些字符串
     * @return String
     * @desc 替换字符串中的指定字符串为指定字符串
     */
    public static String replaceSrcTpDest(String str, String srcRep, String destRep) {
        return str.replace(srcRep, destRep);
    }

    /**
     * @param html 需要进行替换的带html标签的字符串
     * @return String
     * @desc 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    /**
     * @param html 需要进行替换的带html标签的字符串
     * @return String
     * @desc 替换为手机识别的HTML, 去掉样式及属性, 保留回车.
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * @param param  目标字符串
     * @param length 截取长度
     * @return String
     * @desc 缩略字符串（不区分中英文字符）
     */
    public static String abbr2(String param, int length) {
        if (param == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        int n = 0;
        char temp;
        boolean isCode = false;                // 是不是HTML代码
        boolean isHTML = false;                // 是不是HTML特殊字符,如&nbsp;
        for (int i = 0; i < param.length(); i++) {
            temp = param.charAt(i);
            if (temp == '<') {
                isCode = true;
            } else if (temp == '&') {
                isHTML = true;
            } else if (temp == '>' && isCode) {
                n = n - 1;
                isCode = false;
            } else if (temp == ';' && isHTML) {
                isHTML = false;
            }
            try {
                if (!isCode && !isHTML) {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (n <= length - 3) {
                result.append(temp);
            } else {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String tmpResult = result.toString().replaceAll("(>)[^<>]*(<?)",
                "$1$2");
        // 去掉不需要结素标记的HTML标记
        tmpResult = tmpResult
                .replaceAll(
                        "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
                        "");

        // 去掉成对的HTML标记
        tmpResult = tmpResult.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
                "$2");

        // 用正则表达式取出标记
        Matcher m = ABBR_PATTERN.matcher(tmpResult);
        List<String> endHTML = new ArrayList<>();
        while (m.find()) {
            endHTML.add(m.group(1));
        }

        // 补全不成对的HTML标记
        for (int i = endHTML.size() - 1; i >= 0; i--) {
            result.append("</");
            result.append(endHTML.get(i));
            result.append(">");
        }
        return result.toString();
    }

    /**
     * @param str  需要进行截取的字符串
     * @param trim 需要去掉的字符串
     * @return String 处理后的字符串
     * @desc 去掉指定字符串的开头的指定字符
     */
    public static String sideTrimStart(String str, String trim) {
        // null或者空字符串的时候不处理
        if (isNull(str) || isNull(trim)) {
            return str;
        }

        // 结束位置
        int epos;

        // 正规表达式
//		String regpattern = "[" + trim + "]*+";
        String regpattern = "" + trim + "*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉开头的指定字符
        Matcher matcher = pattern.matcher(str);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            str = str.substring(epos);
        }

        // 返回处理后的字符串
        return str;
    }

    /**
     * @param str  需要进行截取的字符串
     * @param trim 需要去掉的指定字符
     * @return String
     * @desc 去掉指定字符串末尾的指定字符
     */
    public static String sideTrimEnd(String str, String trim) {
        // null或者空字符串的时候不处理
        if (isNull(str) || isNull(trim)) {
            return str;
        }

        // 结束位置
        int epos;

        // 正规表达式
//		String regpattern = "[" + trim + "]*+";
        String regpattern = "" + trim + "*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(str).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            str = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }
        // 返回处理后的字符串
        return str;
    }

    /**
     * @param str  需要进行截取的字符串
     * @param trim 需要去掉的字符串
     * @return String 处理后的字符串
     * @desc 去掉指定字符串的开头和结尾的指定字符
     */
    public static String sideTrim(String str, String trim) {
        // null或者空字符串的时候不处理
        if (isNull(str) || isNull(trim)) {
            return str;
        }

        // 结束位置
        int epos;

        // 正规表达式
//		String regpattern = "[" + trim + "]*+";
        String regpattern = "" + trim + "*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(str).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            str = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(str);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            str = str.substring(epos);
        }

        // 返回处理后的字符串
        return str;
    }

    /**
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * @desc 驼峰命名法工具
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * @desc 驼峰命名法工具
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * @desc 驼峰命名法工具
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * @param objectString 对象串
     *                     例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     * @desc 转换为JS获取对象值, 生成三目运算返回结果
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String[] vals = split(objectString, ".");

        for (String val : vals) {
            sb.append(".").append(val);
            result.append("!").append(sb.substring(1)).append("?'':");
        }
        result.append(sb.substring(1));
        return result.toString();
    }

    /**
     * @param str 字母字符串
     * @return String
     * @desc 首字母转小写
     */
    public static String toLowerCaseFirstChar(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }

    /**
     * @param str 字母字符串
     * @return String
     * @desc 首字母转大写
     */
    public static String toUpperCaseFristChar(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.replaceBlank("just do it!"));

        String str = "A1,A2,A3,B1,B3,B5,C1,C4,C6,C7,C0";
        List<String> list = StringUtils.toList(str, ",");
        System.out.println(list);

//		String a ="我是一个人（中国人）aaa[真的]bbbb{确定}";
        String a = "fdsa fdsafdasfdasAAAAA(1,2,3)BBBBB";
        System.out.println(replaceBracket(a));
        System.out.println("---------------------------------");
        String s = "我是一个人（中国人）aaa[真的]bbbb{确定}";
        s = s.replaceAll("\\(.*?\\)|\\{.*?}|\\[.*?]|（.*?）", "");
        System.out.println(s);

        System.out.println("===========================================");
        String str3 = ",1fdsafdas范德萨,";
        System.out.println(sideTrimStart(str3, ","));
        System.out.println(sideTrimEnd(str3, ","));
        System.out.println(sideTrim(str3, ","));

        String str4 = "BU_PA_EMTP_CODE";
        List<String> tablePrefixs = new ArrayList<>();
        tablePrefixs.add("BU_");
        tablePrefixs.add("PA_");

        for (String tablePrefix : tablePrefixs) {
            if (str4.toUpperCase().startsWith(tablePrefix)) {
                tablePrefix = sideTrimStart(str4, tablePrefix);
                System.out.println(StringUtils.toCapitalizeCamelCase(tablePrefix));
            }
        }
        System.out.println(toCamelCase("role_name"));
    }
}