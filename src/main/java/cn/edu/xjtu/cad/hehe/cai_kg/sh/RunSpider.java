package cn.edu.xjtu.cad.hehe.cai_kg.sh;

import cn.edu.xjtu.cad.hehe.cai_kg.model.TextAnno;
import cn.edu.xjtu.cad.hehe.cai_kg.service.DataService;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.Utils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//
///**
// * 直接运行爬虫程序
// */
//@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan({"cn.edu.xjtu.cad.hehe.cai_kg.controller",
//        "cn.edu.xjtu.cad.hehe.cai_kg.service",
//        "cn.edu.xjtu.cad.hehe.cai_kg.spider",
//        "cn.edu.xjtu.cad.hehe.cai_kg.config"
//})
//@MapperScan("cn.edu.xjtu.cad.hehe.cai_kg.dao")
public class RunSpider  implements CommandLineRunner {


//
//    @Autowired
//    Spider wordCloudSpider;

    @Autowired
    Spider paperURLSpider;

    @Autowired
    Spider paperInfoSpider;

    @Autowired
    DataService dataService;

    Logger LOGGER = LoggerFactory.getLogger(RunSpider.class);
    public static void main(String[] args) {
        SpringApplication.run(RunSpider.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        paperURLSpider.start();
//        paperInfoSpider.start();
//中文分词结果写入数据库
//        List<TextAnno> textAnnos = new ArrayList<>();
//        Files.list(Paths.get("D:\\Users\\Hehel\\Desktop\\data\\ann")).forEach(p->{
//            long id = Long.parseLong(p.getFileName().toString().replace(".txt",""));
//            try {
//                String str = new String(Files.readAllBytes(p));
//                TextAnno textAnno = new TextAnno(id,str,str,false);
//                dataService.createTextAnnp(textAnno);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        List<Long> ids = dataService.getThesisID();
        for (Long id : ids) {
            boolean is = dataService.valAbstr(id);
            if(!is){
                String a =dataService.getAbstrFromHTML(id);

                if(a!=null){
                    LOGGER.info("开始处理ID="+id);
                    a = Utils.halfWidth2FullWidth(a.replaceAll(" ","")).replaceAll("\r","").replaceAll("\n","");
                    StringBuilder sb = new StringBuilder();
                    for (char c : a.toCharArray()) {
                        sb.append(c).append('\n');
                    }
                    sb.deleteCharAt(sb.length()-1);
                    dataService.createTextAnnp(new TextAnno(id,a,sb.toString(),false));
                }else {
                    LOGGER.info("已处理ID="+id);
                }
            }else {
                LOGGER.info("已处理ID="+id);
            }


        }
    }
}
