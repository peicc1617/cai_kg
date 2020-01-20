package cn.edu.xjtu.cad.hehe.cai_kg.model;

import java.util.UUID;

public class TimeFile {
    String fileName;
    String fileType;
    byte[] bytes;
    long expiredTime;
    final static long defaultExpiredDuration = 30*60*1000;

    public TimeFile(String fileType,byte[] bytes){
        this(UUID.randomUUID().toString(),fileType,bytes);
    }
    public TimeFile(String fileName,String fileType, byte[] bytes) {
        this(fileName,fileType,bytes,System.currentTimeMillis()+defaultExpiredDuration);
    }
    public TimeFile(String fileName, String fileType,byte[] bytes, long expiredTime) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.bytes = bytes;
        this.expiredTime = expiredTime;
    }

    public String getFullFileName(){
        return new StringBuilder(fileName).append('.').append(fileType).toString();
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public static long getDefaultExpiredDuration() {
        return defaultExpiredDuration;
    }
}
