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
import java.util.stream.Collectors;

@Singleton
public class AutomaRepository implements Serializable {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager entityManager;

    public boolean updateAutoma(int id, Automa newAutoma) {
        Automa oldAutoma = getAutoma(id);
//        List<State> newStates = newAutoma.getStates();
//        List<State> oldStates = oldAutoma.getStates();
        List<State> statesToRemove = oldAutoma.getStates().stream()
                .filter(state -> newAutoma.getStates().stream()
                        .noneMatch(s2 -> s2.getStateName().equals(state.getStateName())))
                .collect(Collectors.toList());
        List<State> statesToAdd = newAutoma.getStates().stream()
                .filter(state -> oldAutoma.getStates().stream()
                        .noneMatch(s2 -> s2.getStateName().equals(state.getStateName())))
                .collect(Collectors.toList());
        if (oldAutoma == null) return false;
        if(statesToRemove!=null)
            statesToRemove.forEach(state -> {
                state.setAutoma(oldAutoma);
                entityManager.remove(state);
            });
        oldAutoma.setName(newAutoma.getName());
        oldAutoma.setStates(null);
        entityManager.persist(oldAutoma);
        if(statesToAdd!=null)
            statesToAdd.forEach(state -> {
                state.setAutoma(oldAutoma);
                entityManager.persist(state);
            });
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