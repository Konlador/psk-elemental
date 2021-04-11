package lt.psk.persistence;

import lt.psk.entities.Elem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ElemsDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(Elem element){
        this.em.persist(element);
    }

    public List<Elem> loadAll() {
        return em.createNamedQuery("Elem.findAll", Elem.class).getResultList();
    }

    public Elem findByName(String name){
        var list = em.createNamedQuery("Elem.findByName", Elem.class)
                .setParameter("name", name)
                .getResultList();
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}