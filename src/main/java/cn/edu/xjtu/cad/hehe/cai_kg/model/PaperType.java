package cn.edu.xjtu.cad.hehe.cai_kg.model;

public enum  PaperType {
    Conference("会议"),Thesis("期刊"),Periodical("学位论文"),Others("其它");
    private final String txt;
    PaperType(String txt) {
        this.txt = txt;
    }
}
