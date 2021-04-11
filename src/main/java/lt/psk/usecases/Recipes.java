package lt.psk.usecases;

import lt.psk.entities.Elem;
import lt.psk.entities.Recipe;
import lt.psk.persistence.ElemsDAO;
import lt.psk.persistence.RecipesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Model
public class Recipes implements Serializable {

    @Inject
    private RecipesDAO recipesDAO;

    @Inject
    private ElemsDAO elemsDAO;

    private List<Recipe> allRecipes = new ArrayList<>();
    public List<Recipe> getAllRecipes() {
        return allRecipes;
    }

    @PostConstruct
    public void init(){
        loadRecipes();
        //Arrays.fill(this.ingredients, "");
    }

    @ApplicationScoped
    public void loadRecipes() {
        var allRecipes = recipesDAO.loadAll();
//        for(Recipe recipe : allRecipes){
//            recipe.getIngredients().sort(Comparator.comparingInt(Elem::getId));
//        }
        this.allRecipes = allRecipes;
    }

    private Elem elementToCreate = new Elem();
    public Elem getElementToCreate() {
        return elementToCreate;
    }
    public void setElementToCreate(Elem elementToCreate) {
        this.elementToCreate = elementToCreate;
    }

    private String[] ingredients = new String[2];
    public String[] getIngredients() { return ingredients; }
    public void setIngredients(String[] ingredients) { this.ingredients = ingredients; }

    @Transactional
    public String createRecipe() {
        if (ingredients.length < 2) return "Fail: too few ingredients.";

        var recipeToCreate = new Recipe();

        for (String ingredientName : ingredients) {
            var ingredient = elemsDAO.findByName(ingredientName);
            if(ingredient == null){
                return "Fail: Ingredient does not exist";
            }
            recipeToCreate.getIngredients().add(ingredient);
        }
        recipesDAO.persist(recipeToCreate);

        var resultElement = elemsDAO.findByName(elementToCreate.getName());
        if(resultElement == null){
            resultElement = elementToCreate;
            elemsDAO.persist(resultElement);
        }
        recipeToCreate.setResult(resultElement);

        return "elements?faces-redirect=true";
    }
}