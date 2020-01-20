package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import cn.edu.xjtu.cad.hehe.cai_kg.model.Paper;
import cn.edu.xjtu.cad.hehe.cai_kg.model.PaperType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PaperMapper {

    @Insert("INSERT INTO paper (`title`,`href`,`info`,`keyWords`,`html`,`searchKey`,`abstr`) values (#{title},#{href},#{info},#{keyWords},#{html},#{searchKey},#{abstr})")
    int add(Paper paper);

    @Insert("update paper_index set `keyWords` = #{keyWords},`cn` = #{cn},`category` = #{category}, `zy` = 1 where id = #{id} ")
    void updatePaper(Paper paper);

    @Select("select id from paper_index where href = #{href}")
    long getPaperID(String href);

    @Select("INSERT INTO paper_abstr (`id`,`abstr`) values (#{id},#{abstr})" )
    void savePaperAbstr(@Param("id")long id, @Param("abstr") String  abstr);

    @Select("INSERT INTO paper_html (`id`,`html`) values (#{id},#{html})" )
    void savePaperHtml(@Param("id")long id, @Param("html") String  html);

    /**
     * 判断数据库中是否包含该条文献链接
     * @param href
     * @return
     */
    @Select("SELECT count(1) FROM paper_index where `href` = #{href}")
    boolean has(String href);

    @Insert("INSERT INTO paper_index (`title`,`href`) values (#{title},#{href})")
    void addURL(Paper paper);

    @Select("SELECT href FROM paper_index where `zy` = 0")
    List<String> getAllTo();

    @Select("SELECT * FROM paper_index where `zy` = #{zy}")
    List<Paper> getAllByZy(@Param("zy") boolean zy);

    @Select("SELECT * FROM paper_index ")
    List<Paper> getAll();

    /**
     * 查询文献总数
     * @return
     */
    @Select("SELECT COUNT(1) FROM paper_index ")
    long paperCount();


    /**
     * 查询文献总数
     * @param zy 是否已经爬取的
     * @return
     */
    @Select("SELECT COUNT(1) FROM paper_index where `zy` = #{zy}")
    long paperCountByZy(@Param("zy")boolean zy);



    /**
     * 根据类型查询文献总数
     * @param paperType 文献总数
     * @return
     */
    @Select("SELECT COUNT(1) FROM paper_index WHERE `href` LIKE '%${paperType}%'")
    long paperCountType(@Param("paperType") PaperType paperType);

    @Select("SELECT id FROM paper_index WHERE  paper_index.href LIKE '%/Thesis/%' AND paper_index.category != ''")
    List<Long> getThesisID();

    @Select("SELECT html FROM paper_html WHERE id = #{id}")
    String getHTML(@Param("id") Long id);

}
