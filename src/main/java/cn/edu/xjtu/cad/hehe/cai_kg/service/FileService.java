package cn.edu.xjtu.cad.hehe.cai_kg.service;

import cn.edu.xjtu.cad.hehe.cai_kg.model.TimeFile;
import cn.edu.xjtu.cad.hehe.cai_kg.spider.Utils;
import org.springframework.stereotype.Service;

import java.time.temporal.Temporal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FileService {


    Map<String, TimeFile> fileMap = new ConcurrentHashMap<>();


    public String putWithoutKey(String fileType,byte[] bytes){
        String fileName = UUID.randomUUID().toString();
        return putWithoutKey(fileName,fileType,bytes);
    }
    public String putWithoutKey(String fileName,String fileType,byte[] bytes){
        String fileKey = UUID.randomUUID().toString();
        fileMap.put(fileKey,new TimeFile(fileName,fileType,bytes));
        return fileKey;
    }

    public void put(String fileKey,String fileName,String fileType,byte[] bytes){
        fileMap.put(fileKey,new TimeFile(fileName,fileType,bytes));
    }

    public void put(String fileKey,String fileType,byte[] bytes){
        fileMap.put(fileKey,new TimeFile(fileType,bytes));
    }

    public void put(String fileKey,String fileName,String fileType,byte[] bytes, long expiredTime){
        fileMap.put(fileKey,new TimeFile(fileName,fileType,bytes,expiredTime));
    }

    /**
     * 获取文件
     * @param fileKey
     * @return
     */
    public TimeFile get(String fileKey){
        TimeFile timeFile = fileMap.getOrDefault(fileKey,null);
        if(timeFile.getExpiredTime()<=System.currentTimeMillis()){
            timeFile=null;
        }
        fileMap.remove(fileKey);
        return timeFile;
    }


}
