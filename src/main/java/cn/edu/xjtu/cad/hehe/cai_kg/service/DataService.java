package cn.edu.xjtu.cad.hehe.cai_kg.service;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.PaperMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.dao.TextAnnoMapper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.PaperType;
import cn.edu.xjtu.cad.hehe.cai_kg.model.TextAnno;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    PaperMapper paperMapper;
    @Autowired
    TextAnnoMapper textAnnoMapper;

    private static Map<String, String> cnMap = new HashMap<String, String>() {{
//        put("T.*","工艺技术");
////        put("A.*","马克思主义、列宁主义、毛泽东思想、邓小平理论");
////        put("B.*","哲学、宗教");
//        put("C.*","社会科学总论");
////        put("D.*","政治、法律");
//        put("E.*","军事");
//        put("F.*","经济");
////        put("G.*","文化、科学、教育、体育");
////        put("H.*","语言、文字");
////        put("I.*","文学");
////        put("J.*","艺术");
//        put("K.*","历史、地理");
//        put("N.*","自然科学总论");
//        put("O.*","数理科学和化学");
//        put("P.*","天文学、地球科学");
//        put("Q.*","生物科学");
//        put("R.*","医药、卫生");
//        put("S.*","农业科学");
//        put("T.*","工业技术");
//        put("U.*","交通运输");
//        put("V.*","航空、航天");
//        put("X.*","环境科学、安全科学");
//        put("Z.*","综合性图书");
        put("T-0.*", "工业技术理论");
        put("TB.*", "一般工业技术");
        put("TD.*", "矿业工程");
        put("TE.*", "石油、天然气");
        put("TF.*", "冶金工业");
        put("TG.*", "金属学与金属工艺");
        put("TH.*", "机械仪表工业");
        put("TJ.*", "武器工业");
        put("TK.*", "能源与动力工程");
        put("TP.*", "自动化技术");
        put("TS.*", "轻工业、手工业");
    }};

    public Map<String, Object> paperCount() {
        List<Paper> paperList = paperMapper.getAllByZy(true);
        long all = paperMapper.paperCount();//总数
        long has = paperMapper.paperCountByZy(true);
        long to = all - has;
        //已经爬取的分类
        List<Paper> tList = new ArrayList<>();
        List<Paper> oList = new ArrayList<>();
        List<Paper> cList = new ArrayList<>();
        List<Paper> pList = new ArrayList<>();
        long tCnt = paperMapper.paperCountType(PaperType.Thesis),
                cCnt = paperMapper.paperCountType(PaperType.Conference),
                pCnt = paperMapper.paperCountType(PaperType.Periodical);
        long oCnt = all - tCnt - pCnt - cCnt;
        //首先按照类别分类
        Collections.sort(paperList, (p1, p2) -> p1.getCreateTime().before(p2.getCreateTime()) ? -1 : 1);

        Map<String, Integer> domCnt = new HashMap<>();
        Map<String, int[]> domTypeCnt = new HashMap<>();
        if (paperList != null) {
            for (Paper paper : paperList) {
                String href = paper.getHref();
                if (href != null && href.trim().length() > 0) {
                    if (href.contains(PaperType.Thesis.toString())) {
                        tList.add(paper);
                    } else if (href.contains(PaperType.Conference.toString())) {
                        cList.add(paper);
                    } else if (href.contains(PaperType.Periodical.toString())) {
                        pList.add(paper);
                    } else {
                        oList.add(paper);
                    }
                }
                String cn = paper.getCn();
                if (cn != null && cn.trim().length() > 0) {
                    for (String s : cn.split(" ")) {
                        if (s != null && s.trim().length() > 0) {
                            String c = s.trim();
                            for (Map.Entry<String, String> entry : cnMap.entrySet()) {
                                if (c.matches(entry.getKey())) {
                                    if (!domTypeCnt.containsKey(entry.getValue())) {
                                        domTypeCnt.put(entry.getValue(), new int[]{0, 0, 0, 0});
                                    } else {
                                        if (href.contains(PaperType.Thesis.toString())) {
                                            domTypeCnt.get(entry.getValue())[0]++;
                                        } else if (href.contains(PaperType.Conference.toString())) {
                                            domTypeCnt.get(entry.getValue())[1]++;
                                        } else if (href.contains(PaperType.Periodical.toString())) {
                                            domTypeCnt.get(entry.getValue())[2]++;
                                        } else {
                                            domTypeCnt.get(entry.getValue())[3]++;
                                        }
                                    }
                                    domCnt.put(entry.getValue(), domCnt.getOrDefault(entry.getValue(), 0) + 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        domTypeCnt.forEach((k, v) -> System.out.println(k + ":" + Arrays.toString(v) + '\r'));
        Object[][] domData = new Object[domCnt.size()][2];
        Object[][] domTicks = new Object[domCnt.size()][2];
        List<Map.Entry<String, Integer>> tempList = new ArrayList<>(domCnt.entrySet());
        Collections.sort(tempList, Comparator.comparingInt(Map.Entry::getValue));
        int temp = 0;
        for (Map.Entry<String, Integer> entry : tempList) {
            domData[temp][1] = temp;
            domTicks[temp][0] = temp;
            domData[temp][0] = entry.getValue();
            domTicks[temp][1] = entry.getKey();
            temp++;
        }
        long startDate = paperList.get(0).getCreateTime().getTime();
        long endDate = paperList.get(paperList.size() - 1).getCreateTime().getTime();

        Map<String, Object> res = new HashMap<>();
        res.put("all", all);
        res.put("to", to);
        res.put("has", has);
        res.put("tCnt", tList.size());
        res.put("cCnt", cList.size());
        res.put("pCnt", pList.size());
        res.put("oCnt", oList.size());
        res.put("tTask", (double) tList.size() / tCnt * 100);
        res.put("pTask", (double) pList.size() / pCnt * 100);
        res.put("cTask", (double) cList.size() / cCnt * 100);
        res.put("oTask", (double) oList.size() / oCnt * 100);
        res.put("tTime", JSON.toJSONString(getTimes(tList, startDate, endDate)));
        res.put("cTime", JSON.toJSONString(getTimes(cList, startDate, endDate)));
        res.put("pTime", JSON.toJSONString(getTimes(pList, startDate, endDate)));
        res.put("oTime", JSON.toJSONString(getTimes(oList, startDate, endDate)));
        res.put("domData", JSON.toJSONString(domData));
        for (Object[] domDatum : domData) {
            System.out.println(domDatum[0]);
        }
        res.put("domTicks", JSON.toJSONString(domTicks));
        return res;
    }

    private int[][] getTimes(List<Paper> paperList, long startDate, long endDate) {
        int d = 24;
        long delta = (long) Math.floor((endDate - startDate) / (double) d);

        int[][] time = new int[d][2];
        for (int i = 0; i < d; i++) {
            time[i][0] = i;
        }
        for (Paper paper : paperList) {
            int i = (int) ((paper.getCreateTime().getTime() - startDate - 1) / delta);
            if (i >= d) {
                i = d - 1;
            }
            time[i][1]++;
        }
//        System.out.println("---------------------------------------");
//        for (int[] ints : time) {
//            System.out.println(ints[1]+"\r");
//        }
        return time;
    }


    /**
     * 文献总数统计
     *
     * @param val 查询已经爬取的文献，false 查询所有文献
     * @return 总数long
     */
    public long paperCount(boolean val) {
        return paperMapper.paperCountByZy(val);
    }

    /**
     * 根据分类号得到分类
     *
     * @param cnText
     * @return
     */
    String getCA(String cnText) {
        String ca = null;
        if (cnText != null && cnText.trim().length() > 0) {
            List<String> cnList = Arrays.stream(cnText.split(" ")).filter(k -> k.trim().length() > 0).collect(Collectors.toList());
            List<String> caList = cnList.stream().map(cn -> {
                for (String key : cnMap.keySet()) {
                    if (cn.toUpperCase().contains(key)) {
                        return cnMap.get(key);
                    }
                }
                return null;
            }).filter(e -> e != null).collect(Collectors.toList());
            ca = caList.stream().collect(Collectors.joining(";"));
        }
        return ca;
    }

    public TextAnno getTextAnno(long textID) {
        return textAnnoMapper.getTextAnno(textID);
    }

    public List<TextAnno> getTextAnnoAllAll() {
        return textAnnoMapper.getTextAnnoAllAll();
    }

    public List<TextAnno> getTextAnnoAll() {
        return textAnnoMapper.getTextAnnoAll();
    }

    public void updateTextAnno(TextAnno textAnno) {
        textAnnoMapper.updateTextAnno(textAnno);
    }

    public void createTextAnnp(TextAnno textAnno) {
        textAnnoMapper.createTextAnno(textAnno);
    }

    public List<Long> getThesisID() {
        return paperMapper.getThesisID();
    }

    public String getAbstrFromHTML(Long id) {
        String html = paperMapper.getHTML(id);
        if (html != null) {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select(".row.clear .text");
            switch (elements.size()) {
                case 2:
                    return elements.get(1).text();
                case 1:
                    return elements.get(0).text();
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public boolean valAbstr(Long id) {
        return textAnnoMapper.getIDFromTextAnno(id) > 0;
    }

    public long randomTextAnno(){
        return textAnnoMapper.randomTextAnno();
    }

    public List<String> getTextAnnoFinished() {
        return textAnnoMapper.getTextAnnoFinished();

    }

    public List<String> getTextAnnoUninished() {
        return textAnnoMapper.getTextAnnoUninished();
    }
}
