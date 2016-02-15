package com.cmm.tools.svn.log.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("logentry")
public class Logentry implements Comparable<Logentry> {
    @XStreamAsAttribute
    private String revision;
    private String date;
    private String msg;
    private String author;
    private List<Path> paths;

    // <log>
    // <logentry revision="8035">
    // <author>devtyk</author>
    // <date>2015-12-03T04:43:21.567371Z</date>
    // <paths>
    // <path kind=""
    // action="M">/wowealth/ejbs/clientweb/pc/v2/jsp/dqTradeHistory.jsp</path>
    // </paths>
    // <msg></msg>
    // </logentry>
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int compareTo(Logentry o) {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd G HH:mm:ss.S z");
        try {
            return format.parse(this.date).compareTo(format.parse(o.date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return the revision
     */
    public String getRevision() {
        return revision;
    }

    /**
     * @param revision
     *            the revision to set
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the paths
     */
    public List<Path> getPaths() {
        return paths;
    }

    /**
     * @param paths
     *            the paths to set
     */
    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

}