package cn.edu.xjtu.cad.hehe.cai_kg.controller;


import cn.edu.xjtu.cad.hehe.cai_kg.model.TextAnno;
import cn.edu.xjtu.cad.hehe.cai_kg.model.TimeFile;
import cn.edu.xjtu.cad.hehe.cai_kg.service.Neo4JService;
import cn.edu.xjtu.cad.hehe.cai_kg.service.DataService;
import cn.edu.xjtu.cad.hehe.cai_kg.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    @Autowired
    DataService dataService;

    @Autowired
    FileService fileService;

    @Autowired
    Neo4JService neo4JService;

    @RequestMapping("/data/index.html")
    public String data(Model model) {
        return "/data/index";
    }

    @RequestMapping("/data/paper.html")
    public String paperData(Model model) {
        Map<String,Object> res=  dataService.paperCount();
        model.addAllAttributes(res);
        return "/paperData";
    }
    @RequestMapping("/kg.html")
    public String kg(Model model) {
        model.addAttribute("cnt",neo4JService.getCount());
        return "kg";
    }

    @RequestMapping("/kgSearch.html")
    public String kgSearch() {
        return "kgSearch";
    }

    @RequestMapping("/textData/random")
    public ModelAndView randomDataAnno(){
        long id = dataService.randomTextAnno();
        ModelAndView modelAndView = new ModelAndView("redirect:/textData/"+id+".html");
        return modelAndView;
    }
    @RequestMapping("/textData/{textID}.html")
    public String dataAnno(Model model,@PathVariable long textID){
        model.addAttribute("textID",textID);
        return "textAnno";
    }
    @RequestMapping("/relation/index.html")
    public String relation(Model model){
        return "relation";
    }

    @RequestMapping("/textData/list.html")
    public String dataAnnoList(Model model){
        List<TextAnno> textAnnos = dataService.getTextAnnoAll();
        Map<Boolean,List<TextAnno>> group = textAnnos.stream().collect(Collectors.groupingBy(TextAnno::isState));
        model.addAttribute("finished",group.getOrDefault(true,new ArrayList<>()).size());
        model.addAttribute("unfinished",group.getOrDefault(false,new ArrayList<>()).size());
        model.addAttribute("textList",dataService.getTextAnnoAll());
        return "textList";
    }


    @RequestMapping(value = "/refer/{referName}.html",method = RequestMethod.GET)
    public String refer(Model model, @PathVariable("referName") String referName) {
        model.addAttribute("referName",referName);
        model.addAttribute("referID",referName);
        return "refer";
    }

    /**
     * 下载所有已完成标注的数据
     * @param response
     * @return
     */
    @RequestMapping("/textData/anno/finished")
    public ModelAndView downLoadFinished(HttpServletResponse response) {
        List<String> results = dataService.getTextAnnoFinished();
        String key = fileService.putWithoutKey("finished",".txt",process(results).getBytes());
        ModelAndView modelAndView = new ModelAndView("redirect:/file/"+key+".html");
        return modelAndView;
    }



    /**
     * 请求下载未标注数据
     * @return
     */
    @RequestMapping("/textData/anno/unfinished")
    public ModelAndView downLoadUnfinished() {
        List<String> results = dataService.getTextAnnoUninished();
        String key = fileService.putWithoutKey("unfinished",".txt",process(results).getBytes());
        ModelAndView modelAndView = new ModelAndView("redirect:/file/"+key+".html");
        return modelAndView;
    }

    /**
     * 处理数据
     * @param results
     * @return
     */
    private String process(List<String> results){
        StringBuilder sb = new StringBuilder();
        for (String result : results) {
            for (String s : result.split("\n")) {
                sb.append(s).append('\n');
                switch (s.charAt(0)) {
                    case '.':
                    case '!':
                    case '?':
                    case '。':
                    case '！':
                    case '？':
                        sb.append("\n");
                        break;
                    default:
                        break;
                }

            }
        }
        return sb.toString();
    }


    @RequestMapping(value = "/file/{fileKey}.html",method = RequestMethod.GET)
    public void getFile(@PathVariable("fileKey")String fileKey, HttpServletResponse response){
//        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        TimeFile timeFile = fileService.get(fileKey);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        if(timeFile==null){
            pw.print("文件不存在或者已经失效");
        }else {
            response.addHeader("Content-Disposition", "attachment;fileName="+timeFile.getFullFileName());
            pw.print(new String(timeFile.getBytes()));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(pw!=null){
                pw.close();
            }
        }

    }
}
