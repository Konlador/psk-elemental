package lt.psk.usecases;

import lt.psk.entities.Elem;
import lt.psk.entities.Recipe;
import lt.psk.persistence.ElemsDAO;
import lt.psk.persistence.RecipesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Recipes implements Serializable {

    @Inject
    private RecipesDAO recipesDAO;

    private List<Recipe> allRecipes;

    @PostConstruct
    public void init(){
        loadRecipes();
    }

    public void loadRecipes() {
        this.allRecipes = recipesDAO.loadAll();
    }

    public List<Recipe> getAllRecipes() {
        return allRecipes;
    }
}