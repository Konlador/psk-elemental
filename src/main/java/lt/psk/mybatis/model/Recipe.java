package lt.psk.mybatis.model;

import java.util.List;

public class Recipe {

    private Integer id;

    private Integer resultId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    // added manually
    private Elem result;
    private List<Elem> ingredients;

    public Elem getResult() {
        return result;
    }

    public void setResult(Elem result) {
        this.result = result;
    }

    public List<Elem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Elem> ingredients) {
        this.ingredients = ingredients;
    }

}