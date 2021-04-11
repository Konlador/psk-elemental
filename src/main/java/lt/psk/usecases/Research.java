package lt.psk.usecases;

import lt.psk.entities.Elem;
import lt.psk.persistence.ElemsDAO;
import lt.psk.persistence.RecipesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Research implements Serializable {

    @Inject
    private ElemsDAO elemsDAO;

    @Inject
    private RecipesDAO recipesDAO;

    private List<Elem> selectedElems;

    private Elem researchResult = new Elem();

    public void addElem(Elem elem) {
        if (!selectedElems.contains(elem)) {
            selectedElems.add(elem);
        }
    }

    public void removeElem(Elem elem) {
        if (selectedElems.contains(elem)) {
            selectedElems.remove(elem);
        }
    }

    @Transactional
    public void createElement() {
        // if there is alreadty no existing recipe with these elements
        // and the new elment is valid
        // create a new recipe and maybe a new element
    }
}