package cn.edu.xjtu.cad.hehe.cai_kg.model;

import java.util.Date;

public class WordCloud {
    private long id;
    private String word;
    private String releatedWord;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getReleatedWord() {
        return releatedWord;
    }

    public void setReleatedWord(String releatedWord) {
        this.releatedWord = releatedWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
