package lt.psk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "select r from Recipe as r")
})
@Table(name = "RECIPES")
@Getter @Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Elem> ingredients = new ArrayList<>();

    public List<Elem> getIngredients(){return this.ingredients;}
    public void setIngredients(List<Elem> ingredients){this.ingredients = ingredients;}

    @ManyToOne
    private Elem result;

    public Recipe() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
