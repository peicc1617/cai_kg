package cn.edu.xjtu.cad.hehe.cai_kg.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Objects;

@NodeEntity
public class MyEntity {
    @Id
    String name;
    @Property(name = "type")
    EntityType type;

    public MyEntity() {
    }

    public MyEntity(String name, EntityType type) {
        this.name = name;
        this.type = type;
    }

    public boolean heIsIM(){
        return type==EntityType.IM||type==EntityType.IMM||type==EntityType.IMC;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  name + '\t' +type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntity myEntity = (MyEntity) o;
        return name.equals(myEntity.name) &&
                type == myEntity.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
