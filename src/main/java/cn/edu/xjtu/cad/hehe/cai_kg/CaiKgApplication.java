package cn.edu.xjtu.cad.hehe.cai_kg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"cn.edu.xjtu.cad.hehe.cai_kg.controller",
        "cn.edu.xjtu.cad.hehe.cai_kg.service",
        "cn.edu.xjtu.cad.hehe.cai_kg.spider",
        "cn.edu.xjtu.cad.hehe.cai_kg.config"
})
@MapperScan("cn.edu.xjtu.cad.hehe.cai_kg.dao")
public class CaiKgApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiKgApplication.class, args);
    }

}
