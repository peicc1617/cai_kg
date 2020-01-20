package cn.edu.xjtu.cad.hehe.cai_kg.service;

import cn.edu.xjtu.cad.hehe.cai_kg.dao.ERepository;
import cn.edu.xjtu.cad.hehe.cai_kg.dao.RRepository;
import cn.edu.xjtu.cad.hehe.cai_kg.model.EPair;
import cn.edu.xjtu.cad.hehe.cai_kg.model.MyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Neo4JService {

    @Autowired
    ERepository eRepository;
    @Autowired
    RRepository rRepository;

    public Map<String,Long> getCount(){
        Map<String,Long> map = new HashMap<>();
        map.put("IM",eRepository.countIM());
        map.put("ACT",eRepository.countACT());
        map.put("IMC",eRepository.countIMC());
        map.put("IMM",eRepository.countIMM());
        map.put("ORG",eRepository.countORG());
        map.put("PRO",eRepository.countPRO());
        map.put("EPRO",eRepository.countEPRO());
        map.put("DMD",eRepository.countDMD());
        long eCNT = map.values().stream().mapToLong(e->new Long(e)).sum();
        map.put("eCNT",eCNT);

        map.put("MIX",rRepository.countMIX());
        map.put("POST",rRepository.countPOST());
        map.put("PRE",rRepository.countPRE());
        map.put("NEST",rRepository.countNEST());
        map.put("M_SOLVE_E",rRepository.countM_SOLVE_E());
        map.put("M_SOLVE_D",rRepository.countM_SOLVE_D());
        map.put("O_HAVE_P",rRepository.countO_HAVE_P());
        map.put("O_DEVELOP_A",rRepository.countO_DEVELOP_A());
        map.put("O_HAVE_E",rRepository.countO_HAVE_E());
        map.put("O_USE_M",rRepository.countO_USE_M());
        map.put("O_HAVE_D",rRepository.countO_HAVE_D());
        map.put("A_SOLVE_E",rRepository.countA_SOLVE_E());
        map.put("A_SOLVE_D",rRepository.countA_SOLVE_D());
        map.put("A_USE_M",rRepository.countA_USE_M());
        map.put("P_HAVE_E",rRepository.countP_HAVE_E());
        map.put("P_HAVE_D",rRepository.countP_HAVE_D());
        long rCNT = map.values().stream().mapToLong(e->new Long(e)).sum()-2*eCNT;
        map.put("rCNT",rCNT);
        return map;

    }

    public void saveAll(Set<MyEntity> myEntityList, Set<EPair> ePairList){
        myEntityList.forEach(e->{
            if(!eRepository.findById(e.getName()).isPresent()){
                switch (e.getType()){
                    case IM:
                        eRepository.addIMEntity(e.getName());
                        break;
                    case ACT:
                        eRepository.addACTEntity(e.getName());
                        break;
                    case DMD:
                        eRepository.addDMDEntity(e.getName());
                        break;
                    case IMC:
                        eRepository.addIMCEntity(e.getName());
                        break;
                    case IMM:
                        eRepository.addIMMEntity(e.getName());
                        break;
                    case ORG:
                        eRepository.addORGEntity(e.getName());
                        break;
                    case PRO:
                        eRepository.addPROEntity(e.getName());
                        break;
                    case EPRO:
                        eRepository.addEPROEntity(e.getName());
                        break;
                        default:
                            break;
                }
            }
        });
        ePairList.forEach(p->{
            switch (p.getRelationType()){
                case MIX:
                    rRepository.addMIX(p.getH().getName(),p.getE().getName());
                    break;
                case POST:
                    rRepository.addPOST(p.getH().getName(),p.getE().getName());
                    break;
                case PRE:
                    rRepository.addPRE(p.getH().getName(),p.getE().getName());
                    break;
                case NEST:
                    rRepository.addNEST(p.getH().getName(),p.getE().getName());
                    break;
                case M_SOLVE_E:
                    rRepository.addM_SOLVE_E(p.getH().getName(),p.getE().getName());
                    break;
                case M_SOLVE_D:
                    rRepository.addM_SOLVE_D(p.getH().getName(),p.getE().getName());
                    break;
                case O_HAVE_P:
                    rRepository.addO_HAVE_P(p.getH().getName(),p.getE().getName());
                    break;
                case O_DEVELOP_A:
                    rRepository.addO_DEVELOP_A(p.getH().getName(),p.getE().getName());
                    break;
                case O_HAVE_E:
                    rRepository.addO_HAVE_E(p.getH().getName(),p.getE().getName());
                    break;
                case O_USE_M:
                    rRepository.addO_USE_M(p.getH().getName(),p.getE().getName());
                    break;
                case O_HAVE_D:
                    rRepository.addO_HAVE_D(p.getH().getName(),p.getE().getName());
                    break;
                case A_SOLVE_E:
                    rRepository.addA_SOLVE_E(p.getH().getName(),p.getE().getName());
                    break;
                case A_SOLVE_D:
                    rRepository.addA_SOLVE_D(p.getH().getName(),p.getE().getName());
                    break;
                case A_USE_M:
                    rRepository.addA_USE_M(p.getH().getName(),p.getE().getName());
                    break;
                case P_HAVE_E:
                    rRepository.addP_HAVE_E(p.getH().getName(),p.getE().getName());
                    break;
                case P_HAVE_D:
                    rRepository.addP_HAVE_D(p.getH().getName(),p.getE().getName());
                    break;
                    default:
                        break;
            }
        });
    }

}
