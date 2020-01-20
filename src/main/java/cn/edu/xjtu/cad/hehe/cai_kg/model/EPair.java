package cn.edu.xjtu.cad.hehe.cai_kg.model;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RelationshipEntity
public class EPair {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    MyEntity h;

    @EndNode
    MyEntity e;

    RelationType relationType;

    public EPair() {
    }

    public EPair(MyEntity h, MyEntity e) {
        this.h = h;
        this.e = e;
    }


    public static List<EPair> Pairs(MyEntity h, MyEntity e){
        List<EPair> ePairs = new ArrayList<>();
        if(h.type==EntityType.IMC&&e.heIsIM()) {
            ePairs.add(new EPair(h,e,RelationType.NEST));
        }
        if(e.type==EntityType.IMC&&h.heIsIM()) {
            ePairs.add(new EPair(e,h,RelationType.NEST));
        }
        if(h.heIsIM()&&e.heIsIM()) {
            ePairs.add(new EPair(h,e,RelationType.PRE));
            ePairs.add(new EPair(e,h,RelationType.POST));
            ePairs.add(new EPair(h,e,RelationType.MIX));
            ePairs.add(new EPair(e,h,RelationType.MIX));

        }
        if(h.heIsIM()&&e.type==EntityType.EPRO) {
            ePairs.add(new EPair(h,e,RelationType.M_SOLVE_E));
        }
        if(e.heIsIM()&&h.type==EntityType.EPRO) {
            ePairs.add(new EPair(e,h,RelationType.M_SOLVE_E));
        }

        if(h.heIsIM()&&e.type==EntityType.DMD) {
            ePairs.add(new EPair(e,h,RelationType.M_SOLVE_D));
        }
        if(e.heIsIM()&&h.type==EntityType.DMD) {
            ePairs.add(new EPair(e,h,RelationType.M_SOLVE_D));
        }

        if(h.type==EntityType.ORG&&e.type==EntityType.PRO) {
            ePairs.add(new EPair(h,e,RelationType.O_HAVE_P));
        }
        if(e.type==EntityType.ORG&&h.type==EntityType.PRO) {
            ePairs.add(new EPair(e,h,RelationType.O_HAVE_P));
        }

        if(h.type==EntityType.ORG&&e.type==EntityType.EPRO) {
            ePairs.add(new EPair(h,e,RelationType.O_HAVE_E));
        }
        if(e.type==EntityType.ORG&&h.type==EntityType.EPRO) {
            ePairs.add(new EPair(e,h,RelationType.O_HAVE_E));
        }

        if(h.type==EntityType.ORG&&e.type==EntityType.ACT) {
            ePairs.add(new EPair(h,e,RelationType.O_DEVELOP_A));
        }
        if(e.type==EntityType.ORG&&h.type==EntityType.ACT) {
            ePairs.add(new EPair(e,h,RelationType.O_DEVELOP_A));
        }

        if(h.type==EntityType.ORG&&e.heIsIM()) {
            ePairs.add(new EPair(h,e,RelationType.O_USE_M));
        }
        if(e.type==EntityType.ORG&&h.heIsIM()) {
            ePairs.add(new EPair(e,h,RelationType.O_USE_M));
        }

        if(h.type==EntityType.ORG&&e.type==EntityType.DMD) {
            ePairs.add(new EPair(h,e,RelationType.O_HAVE_D));
        }
        if(e.type==EntityType.ORG&&h.type==EntityType.DMD) {
            ePairs.add(new EPair(e,h,RelationType.O_HAVE_D));
        }

        if(h.type==EntityType.ACT&&e.type==EntityType.EPRO) {
            ePairs.add(new EPair(h,e,RelationType.A_SOLVE_E));
        }
        if(e.type==EntityType.ACT&&h.type==EntityType.EPRO) {
            ePairs.add(new EPair(e,h,RelationType.A_SOLVE_E));
        }

        if(h.type==EntityType.ACT&&e.type==EntityType.DMD) {
            ePairs.add(new EPair(h,e,RelationType.A_SOLVE_D));
        }
        if(e.type==EntityType.ACT&&h.type==EntityType.DMD) {
            ePairs.add(new EPair(e,h,RelationType.A_SOLVE_D));
        }

        if(h.type==EntityType.ACT&&e.heIsIM()) {
            ePairs.add(new EPair(h,e,RelationType.A_USE_M));
        }
        if(e.type==EntityType.ACT&&h.heIsIM()) {
            ePairs.add(new EPair(e,h,RelationType.A_USE_M));
        }

        if(h.type==EntityType.PRO&&e.type==EntityType.EPRO) {
            ePairs.add(new EPair(h,e,RelationType.P_HAVE_E));
        }
        if(e.type==EntityType.PRO&&h.type==EntityType.EPRO) {
            ePairs.add(new EPair(e,h,RelationType.P_HAVE_E));
        }

        if(h.type==EntityType.PRO&&e.type==EntityType.DMD) {
            ePairs.add(new EPair(h,e,RelationType.P_HAVE_D));
        }
        if(e.type==EntityType.PRO&&h.type==EntityType.DMD) {
            ePairs.add(new EPair(e,h,RelationType.P_HAVE_D));
        }
        return ePairs;
    }


    public EPair(MyEntity h, MyEntity e, RelationType relationType) {
        this.h = h;
        this.e = e;
        this.relationType = relationType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyEntity getH() {
        return h;
    }

    public void setH(MyEntity h) {
        this.h = h;
    }

    public MyEntity getE() {
        return e;
    }

    public void setE(MyEntity e) {
        this.e = e;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return   h + "\t"+relationType.toString() +"\t"+ e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EPair ePair = (EPair) o;
        return h.equals(ePair.h) &&
                e.equals(ePair.e) &&
                relationType == ePair.relationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, e, relationType);
    }
}
