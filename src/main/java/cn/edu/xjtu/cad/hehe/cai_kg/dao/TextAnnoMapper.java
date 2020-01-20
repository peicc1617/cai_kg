package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import cn.edu.xjtu.cad.hehe.cai_kg.model.TextAnno;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextAnnoMapper {
    @Select("SELECT * FROM textAnno where id = #{id}")
    TextAnno getTextAnno(@Param("id") long id);

    @Update("UPDATE textAnno SET result = #{result},state = #{state} where id = #{id}")
    void updateTextAnno(TextAnno textAnno);


    @Select("SELECT id,state From textAnno ORDER BY state DESC")
    List<TextAnno> getTextAnnoAll();

    @Insert("INSERT INTO textAnno (id,text,result,state) VALUES (#{id},#{text},#{result},#{state})")
    void createTextAnno(TextAnno textAnno);
    @Select("SELECT * From textAnno ")
    List<TextAnno> getTextAnnoAllAll();

    @Select("SELECT count(1) From textAnno where id = #{id}")
    long getIDFromTextAnno(@Param("id") Long id);


    @Select("SELECT t1.id " +
            "FROM `textanno` AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM `textanno`)) AS id) AS t2 " +
            "WHERE t1.id >= t2.id AND t1.state=0 " +
            "ORDER BY t1.id ASC LIMIT 1;")
    long randomTextAnno();

    @Select("SELECT result From textAnno where state = 1 ")
    List<String> getTextAnnoFinished();

    @Select("SELECT result From textAnno where state = 0 ")
    List<String> getTextAnnoUninished();
}
