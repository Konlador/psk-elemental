package lt.psk.persistence;

import lt.psk.entities.Recipe;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class RecipesDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(Recipe recipe) {
        this.em.persist(recipe);
    }

    public List<Recipe> loadAll() { return em.createNamedQuery("Recipe.findAll", Recipe.class).getResultList(); }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Recipe findOne(Integer id) { return em.find(Recipe.class, id); }
}