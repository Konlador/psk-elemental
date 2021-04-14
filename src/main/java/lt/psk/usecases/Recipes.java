package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
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
import java.util.Comparator;
import java.util.List;

@Model
public class Recipes implements Serializable {

    @Inject
    private RecipesDAO recipesDAO;

    @Inject
    private ElemsDAO elemsDAO;

    @Getter @Setter
    private Elem elementToCreate = new Elem();

    @Getter @Setter
    private String[] ingredients = new String[2];

    @Getter
    private List<Recipe> allRecipes = new ArrayList<>();

    @PostConstruct
    public void init(){
        loadRecipes();
    }

    @ApplicationScoped
    public void loadRecipes() {
        var allRecipes = recipesDAO.loadAll();
        for(Recipe recipe : allRecipes){
            recipe.getIngredients().sort(Comparator.comparingInt(Elem::getId));
        }
        this.allRecipes = allRecipes;
    }

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
        recipeToCreate.getIngredients().sort(Comparator.comparingInt(Elem::getId));

        // check there is no other recipe with exact same ingredients
        var recipes = recipesDAO.loadAll();
        for (var recipe: recipes) {
            recipe.getIngredients().sort(Comparator.comparingInt(Elem::getId));
            if(recipeToCreate.getIngredients().size() != recipe.getIngredients().size()) continue;

            var sameIngredients = true;
            for(int i = 0; i < recipeToCreate.getIngredients().size(); i++) {
                if(recipe.getIngredients().get(i).getId() != recipeToCreate.getIngredients().get(i).getId()){
                    sameIngredients = false;
                    break;
                }
            }
            if (sameIngredients){
                return "Fail: recipe already exists with the ingredients";
            }
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