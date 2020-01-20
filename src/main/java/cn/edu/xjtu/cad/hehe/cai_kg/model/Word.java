package cn.edu.xjtu.cad.hehe.cai_kg.model;

public class Word {
    char c;
    String l;
    char b;
    boolean blank;

    public Word(char c, String l, char b) {
        this.c = c;
        this.l = l;
        this.b = b;
    }

    public Word(boolean blank) {
        this.blank = blank;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public char getB() {
        return b;
    }

    public void setB(char b) {
        this.b = b;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!blank) {
            sb.append(c).append('\t').append(b);
            if (l != null) {
                sb.append('_').append(l);
            }
        }

        return sb.toString();
    }

}
