package lt.psk.usecases;

import lt.psk.entities.Elem;
import lt.psk.persistence.ElemsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Elems implements Serializable {

    @Inject
    private ElemsDAO elemsDAO;

    private Elem elementToCreate = new Elem();

    private List<Elem> allElems;

    @PostConstruct
    public void init(){
        loadElems();
    }

    public void loadElems() {
        this.allElems = elemsDAO.loadAll();
    }

    public List<Elem> getAllElems() {
        return allElems;
    }

    @Transactional
    public String createElement() {
        this.elemsDAO.persist(elementToCreate);
        return "success";
    }

    public Elem getElementToCreate() {
        return elementToCreate;
    }

    public void setElementToCreate(Elem elementToCreate) {
        this.elementToCreate = elementToCreate;
    }
}