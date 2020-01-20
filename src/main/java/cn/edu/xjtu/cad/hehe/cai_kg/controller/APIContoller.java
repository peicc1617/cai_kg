package cn.edu.xjtu.cad.hehe.cai_kg.controller;

import cn.edu.xjtu.cad.hehe.cai_kg.model.*;
import cn.edu.xjtu.cad.hehe.cai_kg.service.DataService;
import cn.edu.xjtu.cad.hehe.cai_kg.service.FileService;
import cn.edu.xjtu.cad.hehe.cai_kg.service.Neo4JService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class APIContoller {


    @Autowired
    DataService dataService;

    @Autowired
    FileService fileService;

    @Autowired
    Neo4JService neo4JService;

    /**
     * 根据ID获取标注数据
     *
     * @param textID
     * @return
     */
    @RequestMapping("/textData/anno/{textID}.json")
    public TextAnno getTextAnno(@PathVariable long textID) {
        TextAnno textAnno = dataService.getTextAnno(textID);
        return textAnno;
    }


    /**
     * 预处理上传的文件得到相应的标注语料
     * @param fileUpload
     * @return
     * @throws IOException
     */
    @RequestMapping("/relation/preProcess")
    public JSONObject preprocess2Relation(@RequestParam("file") MultipartFile fileUpload) throws IOException {
        List<List<Word>> listList = getListList(new String(fileUpload.getBytes()));
        List<String> sentenceList = readSentenceList(listList);
        String fileString = sentenceList.stream().collect(Collectors.joining("\n"));
        String key = fileService.putWithoutKey("txt", fileString.getBytes());
        JSONObject res = new JSONObject();
        res.put("url", "/file/" + key + ".html");
        return res;
    }
    @RequestMapping("/relation/process")
    public JSONObject process2Relation(@RequestParam("file") MultipartFile fileUpload) throws IOException {
        List<List<Word>> listList = getListList(new String(fileUpload.getBytes()));
        List<EPair> ePairs = getRDF(listList);
        String key = fileService.putWithoutKey("txt", ePairs.stream().map(p->p.toString()).collect(Collectors.joining("\n")).getBytes());
        JSONObject res = new JSONObject();
        res.put("url", "/file/" + key + ".html");
        return res;
    }


    private List<List<Word>> getListList(String string) {
        List<List<Word>> listList = new ArrayList<>();
        List<Word> wordList = Arrays.stream(string.split("\n")).map(l -> {
            String[] arr = l.trim().split("\t");
            if (arr.length >= 2) {
                if (arr[arr.length - 1].equals("O")) {
                    return new Word(l.charAt(0), "", 'O');
                } else {
                    String[] arr2 = arr[arr.length - 1].split("-");
                    return new Word(l.charAt(0), arr2[1], arr2[0].charAt(0));
                }
            } else {
                return new Word(true);
            }
        }).collect(Collectors.toList());
        List<Word> list = new ArrayList<>();
        for (Word word : wordList) {
            if (!word.isBlank()) {
                list.add(word);
            } else {
                listList.add(list);
                list = new ArrayList<>();
            }
        }
        return listList;
    }

    private List<String> readSentenceList(List<List<Word>> listList) {

        List<String> lines = new ArrayList<>();
        listList.stream().map(words -> {
            List<String> eList = new ArrayList<>();
            eList.add(words.stream().map(w -> w.getC() + "").collect(Collectors.joining("")));
            StringBuilder sb = new StringBuilder();
            for (Word word : words) {
                sb.append(word.getC());
                switch (word.getB()) {
                    case 'E':
                        eList.add(sb.toString());
                        break;
                    case 'B':
                        sb = new StringBuilder().append(word.getC());
                        break;
                    default:
                        break;
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

    private List<EPair> getRDF(List<List<Word>> listList){
        Set<EPair> ePairs = new HashSet<>();
        Set<MyEntity> entities = new HashSet<>();
        listList.stream().map(words -> {
            List<MyEntity> eList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (Word word : words) {
                sb.append(word.getC());
                switch (word.getB()) {
                    case 'E':
                        MyEntity myEntity = new MyEntity(sb.toString(), EntityType.valueOf(word.getL()));
                        eList.add(myEntity);
                        entities.add(myEntity);
                        break;
                    case 'B':
                        sb = new StringBuilder().append(word.getC());
                        break;
                    default:
                        break;
                }
            }
            return eList;
        }).filter(eList -> eList.size() >= 2)
                .forEach(eList -> {
                    for (int i = 0; i < eList.size(); i++) {
                        for (int j = i+1; j < eList.size(); j++) {
                            if(!eList.get(i).equals(eList.get(j))){
                                ePairs.addAll(EPair.Pairs(eList.get(i),eList.get(j)));
                            }
                        }
                    }
                });
//        neo4JService.saveAll(entities,ePairs);
        return new ArrayList<>(ePairs);
    }

    /**
     * 更新标注
     *
     * @param textAnno
     */
    @RequestMapping("/textData/anno")
    public void updateTextAnno(@Param("textAnno") TextAnno textAnno) {
        dataService.updateTextAnno(textAnno);
    }


}
