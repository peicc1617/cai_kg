package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeedWordMapper {

    /**
     * 从数据库中查询余下的种子词
     * @param limit
     * @return
     */
    @Select("SELECT FROM seedWord WHERE finish = 1 ORDER BY createTime DESC LIMIT = ${limit}")
    List<String> getSeedWordFromCur(long limit);

    /**
     * 将种子词设置为已完成
     * @param word
     */
    @Update("UPDATE seedWord SET finish = 1 WHERE word = #{word}")
    void finishSeedWord(String word);

}
