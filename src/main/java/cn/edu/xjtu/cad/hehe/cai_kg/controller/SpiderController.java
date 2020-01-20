package cn.edu.xjtu.cad.hehe.cai_kg.controller;

import cn.edu.xjtu.cad.hehe.cai_kg.service.SpiderService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    SpiderService spiderService;

    /**
     * 停止爬虫
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "")
    public void stopSpider(){
    }

    /**
     * 开启爬虫
     */
    @RequestMapping(method = RequestMethod.POST,value = "")
    public void startSpider(){
        spiderService.startSpider();
    }


    @RequestMapping(method = RequestMethod.GET,value = "")
    public JSONArray getSpiderShow(){
        return spiderService.getSpiderShow();
    }

}
