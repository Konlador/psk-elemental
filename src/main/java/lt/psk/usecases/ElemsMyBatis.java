package lt.psk.usecases;

import lombok.Getter;
import lt.psk.mybatis.model.Elem;
import lt.psk.mybatis.dao.ElemMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Model
public class ElemsMyBatis implements Serializable {

    @Inject
    private ElemMapper elemMapper;

    @Getter
    private Map<String, List<Elem>> categorizedElems;

    @PostConstruct
    public void init(){
        loadElems();
    }

    public void loadElems() {
        var allElems = elemMapper.selectAll();
        var groups = allElems.stream().collect(groupingBy(elem -> elem.getCategory()));
        this.categorizedElems = sortByValue(groups);
    }

    private static Map<String, List<Elem>> sortByValue(Map<String, List<Elem>> unsortMap) {

        List<Map.Entry<String, List<Elem>>> list =
                new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, (o1, o2) -> {
            var minId1 = o1.getValue().stream().mapToInt(Elem::getId).min().getAsInt();
            var minId2 = o2.getValue().stream().mapToInt(Elem::getId).min().getAsInt();
            return minId1 - minId2;
        });

        Map<String, List<Elem>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<Elem>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}