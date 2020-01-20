package cn.edu.xjtu.cad.hehe.cai_kg.model;

import java.util.Date;

public class Paper {

    private long id;
    private String title;
    private String href;
    private String keyWords;
    private String html;
    private String abstr;
    private String cn;
    private String category;
    private Date createTime;
    private boolean zy;
    public Paper() {
    }

    public Paper(String href,String keyWords, String html, String abstr,  String cn, String category) {
        this.href = href;
        this.keyWords = keyWords;
        this.html = html;
        this.abstr = abstr;
        this.cn = cn;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }


    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isZy() {
        return zy;
    }

    public void setZy(boolean zy) {
        this.zy = zy;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", cn='" + cn + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
