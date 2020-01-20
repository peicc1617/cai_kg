package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMapper {

    @Select("select url from ${tableName} where state =0")
    List<String> getAll(@Param("tableName") String tableName);

    @Select("insert into ${tableName} (url) values (#{url}) ")
    void add(@Param("tableName")String tableName,@Param("url")String url);

    @Select("update ${tableName} set state = 1 where url =#{url} ")
    void delete(@Param("tableName")String tableName,@Param("url")String url);

    @Select("select count(1) from ${tableName} where url =#{url} ")
    boolean contains(@Param("tableName")String tableName,@Param("url")String url);

    @Select("update ${tableName} set state = 0  ")
    void reset(@Param("tableName")String tableName);

    @Select("select count(1) from ${tableName} where state =1")
    int size(@Param("tableName")String tableName);
}
