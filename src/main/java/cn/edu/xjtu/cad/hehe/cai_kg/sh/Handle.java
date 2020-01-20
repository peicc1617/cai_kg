package cn.edu.xjtu.cad.hehe.cai_kg.sh;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 中文分词
 */
public class Handle {
    static class Word {
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

    public static void main(String[] args) throws IOException {
//        saveTrainData("D:\\Users\\hehe\\Desktop\\data\\finished2.txt", "D:\\Users\\hehe\\Desktop\\data\\train2.txt");
//        saveTestData("D:\\cut\\", "D:\\Users\\hehe\\Desktop\\data\\test.txt");

        saveRData("D:\\Users\\hehe\\Desktop\\CRF++-0.58\\train.txt","D:\\Users\\hehe\\Desktop\\data\\r.txt");
    }

    public static void saveTrainData(String raw, String target) throws IOException {
        List<List<Word>> listList = getSentencList(Paths.get(raw));
        Files.write(Paths.get(target), getString(listList.subList(0, listList.size())).getBytes());
    }

    public static void saveTestData(String raw, String target) throws IOException {
        List<List<Word>> listList = new ArrayList<>();
        Files.list(Paths.get(raw))
                .collect(Collectors.toList())
                .subList(0, 1000)
                .forEach(p -> {
                    try {
                        System.out.println(p.getFileName());
                        listList.addAll(getSentencList(p));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Files.write(Paths.get(target), getString(listList.subList(0, listList.size())).getBytes());
    }


    public static List<List<Word>> getSentencList(Path path) throws IOException {
        List<Word> wordList = new ArrayList<>();
        Files.readAllLines(path)
                .forEach(l -> {
                    String[] arr = l.split("\t");
                    char b;
                    String type;
                    if (arr.length > 0) {
                        arr[0] = arr[0].replaceAll(" ", "").replaceAll("　", "");
                        if (arr.length == 1) {
                            for (char c : arr[0].toCharArray()) {
                                wordList.add(new Word(c, null, 'O'));
                            }
                        } else {
                            String str = arr[1];
                            String[] arr2 = str.split("-");
                            if (arr2 == null || arr2.length < 2) {
                                type = "";
                            } else {
                                type = arr2[1];

                            }
                            b = str.charAt(str.length() - 1);
                            int len = arr[0].toCharArray().length;
                            for (int i = 0; i < len; i++) {
                                char c = arr[0].charAt(i);
                                if (c == ' ') {
                                    continue;
                                }
                                if (i == 0 && (b == 'B' || b == 'O')) {
                                    wordList.add(new Word(c, type, 'B'));
                                } else {
                                    wordList.add(new Word(c, type, 'M'));
                                }
                            }
                        }
                    }
                });
        for (int i = 0; i < wordList.size() - 1; i++) {
            Word word = wordList.get(i);
            if (word.b == 'M' && wordList.get(i + 1).b != 'M') {
                word.b = 'E';
            }
        }
        Word word = wordList.get(wordList.size() - 1);
        if (word.b == 'M') {
            word.b = 'E';
        }
        List<List<Word>> listList = new ArrayList<>();
        for (int i = 0, start = 0; i < wordList.size(); i++) {
            switch (wordList.get(i).c) {
                case '.':
                case '!':
                case '?':
                case '。':
                case '！':
                case '？':
                    listList.add(wordList.subList(start, i + 1));
                    start = i + 1;
                    break;
                case ' ':
                default:
                    break;
            }
        }
        return listList;
    }

    public static void saveRData(String raw, String target) throws IOException {
        List<String> strings = readSentenceList(Paths.get(raw));
        Files.write(Paths.get(target), strings);
    }

    public static List<String> readSentenceList(Path path) throws IOException {
        List<List<Word>> listList = new ArrayList<>();

        List<Word> wordList = Files.readAllLines(path).stream().map(l -> {
            String[] arr = l.split("\t");
            char b;
            String type;
            if (arr.length >= 2) {
                if (arr[arr.length-1].equals("O")) {
                    return new Word(l.charAt(0), "", 'O');
                } else {
                    String[] arr2 = arr[arr.length-1].split("-");
                    return new Word(l.charAt(0), arr2[1], arr2[0].charAt(0));
                }
            } else {
                return new Word(true);
            }
        }).collect(Collectors.toList());
        List<Word> list = new ArrayList<>();
        for (Word word : wordList) {
            if (!word.blank) {
                list.add(word);
            } else {
                listList.add(list);
                list = new ArrayList<>();
            }
        }
        List<String> lines = new ArrayList<>();
        listList.stream().map(words -> {
            List<String> eList = new ArrayList<>();
            eList.add(words.stream().map(w -> w.getC() + "").collect(Collectors.joining("")));
            StringBuilder sb = new StringBuilder();
            for (Word word : words) {
                sb.append(word.c);
                if(word.b=='B'){
                    sb = new StringBuilder().append(word.c);
                }
                if (word.b == 'E') {
                    eList.add(sb.toString());
                }
            }
            return eList;
        }).filter(eList -> eList.size() > 2)
                .forEach(eList -> {
                    String line = eList.get(0);
                    for (int i = 1; i < eList.size(); i++) {
                        for (int j = 2; j < eList.size(); j++) {
                            lines.add(eList.get(i) + "\t" + eList.get(j) + "\t" + line);
                        }
                    }
                });
        return lines;
    }

    public static String getString(List<List<Word>> listList) {
        return listList.stream().map(list ->
                list.stream().map(w -> w.toString()
                ).collect(Collectors.joining("\r\n", "", "\r\n"))
        ).collect(Collectors.joining("\r\n"));
    }

}
