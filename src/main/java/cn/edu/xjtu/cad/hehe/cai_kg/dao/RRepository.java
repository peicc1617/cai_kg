package cn.edu.xjtu.cad.hehe.cai_kg.dao;

import cn.edu.xjtu.cad.hehe.cai_kg.model.MyEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RRepository extends Neo4jRepository<MyEntity, Long> {

    @Query("MATCH (h),(e) WHERE h:IMC AND h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:NEST]->(e)")
    void addNEST(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h),(e) WHERE (h:IMM OR h:IM OR h:IMC) AND h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:PRE]->(e)")
    void addPRE(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h),(e) WHERE (h:IMM OR h:IM OR h:IMC) AND h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:POST]->(e)")
    void addPOST(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h),(e) WHERE (h:IMM OR h:IM OR h:IMC) AND h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:MIX]->(e)")
    void addMIX(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h),(e:EPRO) WHERE (h:IMM OR h:IM OR h:IMC) AND h.name = {hName} AND e.name = {eName} CREATE (h)-[r:M_SOLVE_E]->(e)")
    void addM_SOLVE_E(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h),(e:DMD) WHERE (h:IMM OR h:IM OR h:IMC) AND h.name = {hName} AND e.name = {eName} CREATE (h)-[r:M_SOLVE_D]->(e)")
    void addM_SOLVE_D(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ORG),(e:PRO) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:O_HAVE_P]->(e)")
    void addO_HAVE_P(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ORG),(e:ACT) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:O_DEVELOP_A]->(e)")
    void addO_DEVELOP_A(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ORG),(e:EPRO) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:O_HAVE_E]->(e)")
    void addO_HAVE_E(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ORG),(e) WHERE h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:O_USE_M]->(e)")
    void addO_USE_M(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ORG),(e:DMD) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:O_HAVE_D]->(e)")
    void addO_HAVE_D(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ACT),(e:EPRO) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:A_SOLVE_E]->(e)")
    void addA_SOLVE_E(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ACT),(e:DMD) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:A_SOLVE_D]->(e)")
    void addA_SOLVE_D(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:ACT),(e) WHERE h.name = {hName} AND (e:IMM OR e:IM OR e:IMC) AND e.name = {eName} CREATE (h)-[r:A_USE_M]->(e)")
    void addA_USE_M(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:PRO),(e:EPRO) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:P_HAVE_E]->(e)")
    void addP_HAVE_E(@Param("hName")String hName,@Param("eName") String eName);

    @Query("MATCH (h:PRO),(e:DMD) WHERE h.name = {hName} AND e.name = {eName} CREATE (h)-[r:P_HAVE_D]->(e)")
    void addP_HAVE_D(@Param("hName")String hName,@Param("eName") String eName);


    @Query("MATCH ()-[r:MIX]->() return COUNT(r);")
    Long countMIX();

    @Query("MATCH ()-[r:POST]->() return COUNT(r);")
    Long countPOST();

    @Query("MATCH ()-[r:PRE]->() return COUNT(r);")
    Long countPRE();

    @Query("MATCH ()-[r:NEST]->() return COUNT(r);")
    Long countNEST();

    @Query("MATCH ()-[r:M_SOLVE_E]->() return COUNT(r);")
    Long countM_SOLVE_E();

    @Query("MATCH ()-[r:M_SOLVE_D]->() return COUNT(r);")
    Long countM_SOLVE_D();

    @Query("MATCH ()-[r:O_HAVE_P]->() return COUNT(r);")
    Long countO_HAVE_P();

    @Query("MATCH ()-[r:O_DEVELOP_A]->() return COUNT(r);")
    Long countO_DEVELOP_A();

    @Query("MATCH ()-[r:O_HAVE_E]->() return COUNT(r);")
    Long countO_HAVE_E();

    @Query("MATCH ()-[r:O_USE_M]->() return COUNT(r);")
    Long countO_USE_M();

    @Query("MATCH ()-[r:O_HAVE_D]->() return COUNT(r);")
    Long countO_HAVE_D();

    @Query("MATCH ()-[r:A_SOLVE_E]->() return COUNT(r);")
    Long countA_SOLVE_E();

    @Query("MATCH ()-[r:A_SOLVE_D]->() return COUNT(r);")
    Long countA_SOLVE_D();

    @Query("MATCH ()-[r:P_HAVE_E]->() return COUNT(r);")
    Long countP_HAVE_E();

    @Query("MATCH ()-[r:A_USE_M]->() return COUNT(r);")
    Long countA_USE_M();

    @Query("MATCH ()-[r:P_HAVE_D]->() return COUNT(r);")
    Long countP_HAVE_D();
}
