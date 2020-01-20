package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import cn.edu.xjtu.cad.hehe.cai_kg.model.WordCloud;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordCloudMapper {
    @Insert("insert into word_cloud (word,releatedWord) values (#{word},#{toString})")
    void add(String word, String toString);

    @Select("select * from word_cloud order by createTime limit 2000 ")
    List<WordCloud> getAll();

    @Insert("insert into key_word (word) values (#{word})")
    void set(String word);

    @Select("select count(1) from key_word where word = #{word}")
    boolean contains(String word);
}
