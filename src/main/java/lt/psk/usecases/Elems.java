package lt.psk.usecases;

import lt.psk.entities.Elem;
import lt.psk.persistence.ElemsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Model
public class Elems implements Serializable {

    @Inject
    private ElemsDAO elemsDAO;

    private Elem elementToCreate = new Elem();

    private Map<String, List<Elem>> categorizedElems;
    public Map<String, List<Elem>> getCategorizedElems() { return categorizedElems; }

    @PostConstruct
    public void init(){
        loadElems();
    }

    public void loadElems() {
        var allElems = elemsDAO.loadAll();
        var groups = allElems.stream().collect(groupingBy(elem -> elem.getCategory()));
        this.categorizedElems = sortByValue(groups);
    }

    @Transactional
    public String createElement() {
        this.elemsDAO.persist(elementToCreate);
        return "success";
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