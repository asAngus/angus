package com.cmm.tools.word;

/**
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.w3c.dom.Document;

/**
 * Title: Word2Html<br/>
 * Description: <br/>
 * Company: cloudfn<br/>
 * 
 * @author weipeng
 * @date 2016年2月3日上午11:15:43
 *
 */
public class Word2Html {

    public static void main(String argv[]) {
        try {
            convert2Html("/Users/weipeng/Downloads/产品详情页底部风险提示.doc",
                    "/Users/weipeng/Downloads/2.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }

    public static void convert2Html(String fileName, String outPutFile)
            throws Exception {
        Document doc = process(new File(fileName));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        StringBuffer sb = new StringBuffer();
        sb.append("<%@page contentType=\"text/html;charset=GBK\" %>");
        sb.append("\n<%@ include file=\"/common/taglibs.jsp\" %>");
        sb.append("\n<%@ include file=\"/common/hd_meta_include.jsp\" %>");
        String content = new String(out.toByteArray());
        int indexOf = content.indexOf("</body>");
        String substring = content.substring(0, indexOf);
        sb.append(substring);
        sb.append("<%@ include file=\"/common/js_file_include.jsp\"%>");
        sb.append("\n<%@ include file=\"/jsp/app_tj_include.jsp\" %>");
        sb.append("</body>\n</html>");
        writeFile(sb.toString(), outPutFile);
    }

    static Document process(File docFile) throws Exception {
        final HWPFDocumentCore wordDocument = new HWPFDocument(
                new FileInputStream(docFile));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        return wordToHtmlConverter.getDocument();
    }

}