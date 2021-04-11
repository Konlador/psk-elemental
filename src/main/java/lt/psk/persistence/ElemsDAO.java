package lt.psk.persistence;

import lt.psk.entities.Elem;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ElemsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Elem> loadAll() {
        return em.createNamedQuery("Elem.findAll", Elem.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Elem element){
        this.em.persist(element);
    }
}