//package lt.psk.usecases;
//
//import lt.psk.entities.Elem;
//import lt.psk.persistence.ElemsDAO;
//import lt.psk.persistence.RecipesDAO;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.inject.Model;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Model
//public class Research implements Serializable {
//
//    @Inject
//    private ElemsDAO elemsDAO;
//
//    @Inject
//    private RecipesDAO recipesDAO;
//
//    private Elem elementToCreate = new Elem();
//
//    public Elem getElementToCreate() {
//        return elementToCreate;
//    }
//
//    public void setElementToCreate(Elem elementToCreate) {
//        this.elementToCreate = elementToCreate;
//    }
//
//
//    private List<Elem> ingredients = new ArrayList<Elem>();
//
//    public List<Elem> getIngredients() { return selectedIngredients; }
//
//    @Transactional
//    public void addIngredient(Elem elem) {
//        if (ingredients.contains(elem) || ingredients.size() >= 2) return;
//
//        ingredients.add(elem);
//    }
//
//    public void removeElem(Elem elem) {
//        if (ingredients.contains(elem)) {
//            ingredients.remove(elem);
//        }
//    }
//
//    @Transactional
//    public String createElement() {
//        // if there is alreadty no existing recipe with these elements
//        // and the new elment is valid
//        // create a new recipe and maybe a new element
//        this.elemsDAO.persist(elementToCreate);
//        elementToCreate = new Elem();
//        return "success";
//    }
//}