package cn.edu.xjtu.cad.hehe.cai_kg.model;

public class TextAnno {
    long id;
    String text;
    String result;
    boolean state;

    public TextAnno() {
    }

    public TextAnno(long id, String text, String result, boolean state) {
        this.id = id;
        this.text = text;
        this.result = result;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
