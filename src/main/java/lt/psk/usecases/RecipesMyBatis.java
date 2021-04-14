package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.mybatis.dao.RecipeElemMapper;
import lt.psk.mybatis.model.Elem;
import lt.psk.mybatis.model.Recipe;
import lt.psk.mybatis.dao.ElemMapper;
import lt.psk.mybatis.dao.RecipeMapper;
import lt.psk.mybatis.model.RecipeElem;

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
public class RecipesMyBatis implements Serializable {

    @Inject
    private RecipeMapper recipeMapper;

    @Inject
    private ElemMapper elemMapper;

    @Inject
    private RecipeElemMapper recipeElemMapper;

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
        var allRecipes = recipeMapper.selectAll();
        for(Recipe recipe : allRecipes){
            recipe.getIngredients().sort(Comparator.comparingInt(Elem::getId));
        }
        this.allRecipes = allRecipes;
    }

    @Transactional
    public String createRecipe() {
        if (ingredients.length < 2) return "Fail: too few ingredients.";

        var recipeToCreate = new Recipe();
        recipeToCreate.setIngredients(new ArrayList<>());

        for (String ingredientName : ingredients) {
            var ingredient = elemMapper.selectByName(ingredientName);
            if(ingredient == null){
                return "Fail: Ingredient does not exist";
            }
            recipeToCreate.getIngredients().add(ingredient);
        }
        recipeToCreate.getIngredients().sort(Comparator.comparingInt(Elem::getId));

        // check there is no other recipe with exact same ingredients
        var recipes = recipeMapper.selectAll();
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

        var resultElement = elemMapper.selectByName(elementToCreate.getName());
        if(resultElement == null){
            resultElement = elementToCreate;
            elemMapper.insert(resultElement);
        }
        recipeToCreate.setResultId(resultElement.getId());

        recipeMapper.insert(recipeToCreate);

        // insert relationships
        for (Elem ingredient : recipeToCreate.getIngredients()) {
            var recipeElem = new RecipeElem();
            recipeElem.setRecipeId(recipeToCreate.getId());
            recipeElem.setIngredientsId(ingredient.getId());
            recipeElemMapper.insert(recipeElem);
        }

        return "elements?faces-redirect=true";
    }
}