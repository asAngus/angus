/**
 * Title: Log.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: cloudfn<br/>
 *
 */
package com.cmm.tools.svn.log.model;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Title: Log<br/>
 * Description: <br/>
 * Company: cloudfn<br/>
 * 
 * @author weipeng
 * @date 2016�?1??20?��???2:35:50
 *
 */
@XStreamAlias("log")
public class Log {
    @XStreamImplicit
    private List<Logentry> logentry;

    public static String toXml(Object obj) {
        XStream xstream = new XStream(new DomDriver("utf8"));
        xstream.processAnnotations(obj.getClass()); // �???obj类中??注解
        /*
         * // 以�??缩�???��?�???XML StringWriter sw = new StringWriter();
         * xstream.marshal(obj, new CompactWriter(sw)); return sw.toString();
         */
        // 以�?��??????��?�???XML
        return xstream.toXML(obj);
    }

    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        xstream.registerConverter(new PathConverter());
        xstream.alias("path", Path.class);
        @SuppressWarnings("unchecked")
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    /**
     * @return the logentry
     */
    public List<Logentry> getLogentry() {
        return logentry;
    }

    /**
     * @param logentry the logentry to set
     */
    public void setLogentry(List<Logentry> logentry) {
        this.logentry = logentry;
    }
}


