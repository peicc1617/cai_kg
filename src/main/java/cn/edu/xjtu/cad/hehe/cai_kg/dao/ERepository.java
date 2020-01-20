package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import cn.edu.xjtu.cad.hehe.cai_kg.model.MyEntity;
import cn.edu.xjtu.cad.hehe.cai_kg.model.EntityType;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface                                                                                                                                                                                                                                                                 ERepository extends Neo4jRepository<MyEntity, String> {

    @Query("create (n:IM {name:{name}}) ")
    void addIMEntity( @Param("name")String name);

    @Query("create (n:IMM {name:{name}})")
    void addIMMEntity( @Param("name")String name);

    @Query("create (n:IMC {name:{name}}) ")
    void addIMCEntity( @Param("name")String name);

    @Query("create (n:ORG {name:{name}}) ")
    void addORGEntity( @Param("name")String name);

    @Query("create (n:PRO {name:{name}}) ")
    void addPROEntity( @Param("name")String name);

    @Query("create (n:EPRO {name:{name}})")
    void addEPROEntity( @Param("name")String name);

    @Query("create (n:ACT {name:{name}})")
    void addACTEntity( @Param("name")String name);

    @Query("create (n:DMD {name:{name}})")
    void addDMDEntity( @Param("name")String name);

    @Query("match (n:IM) return count(n)")
    Long countIM();

    @Query("match (n:ACT) return count(n)")
    Long countACT();

    @Query("match (n:IMC) return count(n)")
    Long countIMC();

    @Query("match (n:IMM) return count(n)")
    Long countIMM();

    @Query("match (n:ORG) return count(n)")
    Long countORG();

    @Query("match (n:PRO) return count(n)")
    Long countPRO();

    @Query("match (n:ERO) return count(n)")
    Long countEPRO();

    @Query("match (n:DMD) return count(n)")
    Long countDMD();
}
