package cn.edu.xjtu.cad.hehe.cai_kg.sh;

import com.hankcs.hanlp.model.crf.CRFSegmenter;

import java.io.IOException;

public class Segment {
    public static void main(String[] args) throws IOException {
        CRFSegmenter segmenter = new CRFSegmenter(null);
        segmenter.train("D:\\Users\\Hehel\\Desktop\\199801.txt", "D:/");
    }
}
