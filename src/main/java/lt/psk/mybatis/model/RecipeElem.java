package lt.psk.mybatis.model;

public class RecipeElem {

    private Integer recipeId;

    private Integer ingredientsId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Integer ingredientsId) {
        this.ingredientsId = ingredientsId;
    }
}