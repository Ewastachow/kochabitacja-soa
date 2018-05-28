package automa.repository;

import automa.model.Automa;
import automa.model.State;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Singleton
public class AutomaRepository implements Serializable {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager entityManager;

    public boolean updateAutoma(int id, Automa newAutoma) {
        Automa automa = getAutoma(id);
        if (automa == null) return false;
        automa.setName(newAutoma.getName());
        automa.setStates(newAutoma.getStates());
        entityManager.persist(automa);
        if(automa.getStates()!=null)
            newAutoma.getStates().forEach(state -> {
                state.setAutoma(automa);
                entityManager.persist(state);
            });
//        s.setStates(newStudent.getStates());
        return true;
    }



    public List<Automa> getAllAuutomatas() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Automa> cq = cb.createQuery(Automa.class);
        Root<Automa> rootEntry = cq.from(Automa.class);
        CriteriaQuery<Automa> all = cq.select(rootEntry);
        TypedQuery<Automa> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }


    public boolean deleteAutoma(int id) {
        try {
            entityManager.remove(getAutoma(id));
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Automa getAutoma(int id) {
        try {
            Object o = entityManager
                    .createNamedQuery(Automa.AUTOMA_BY_ID, Automa.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return (Automa) o;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createAutoma(@NotNull Automa automa) {
        List<State> states = automa.getStates();
        automa.setStates(null);
        entityManager.persist(automa);
        if(states!=null)
            states.forEach(state -> {
                state.setAutoma(automa);
                entityManager.persist(state);
            });
    }
}