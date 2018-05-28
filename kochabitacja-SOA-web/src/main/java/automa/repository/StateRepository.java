package automa.repository;

import automa.model.Automa;
import automa.model.State;

import javax.annotation.Resource;
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
public class StateRepository implements Serializable {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager entityManager;

    public void createState(@NotNull State state, Automa automa){
        state.setAutoma(automa);
        entityManager.persist(state);
    }

    public List<State> readAllStates(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<State> cq = cb.createQuery(State.class);
        Root<State> rootEntry = cq.from(State.class);
        CriteriaQuery<State> all = cq.select(rootEntry);
        TypedQuery<State> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public State readState(int id){
        try {
            Object o = entityManager
                    .createNamedQuery(State.STATE_BY_ID, State.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return (State) o;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean updateState(int id, State newState) {
        State s = readState(id);
        if (s == null) return false;
        s.setStateName(newState.getStateName());
        s.setAutoma(newState.getAutoma());
        entityManager.persist(s);
        return true;
    }

    public boolean deleteState(int id, Automa automa){
        try {
            System.out.println("id   srsly???    "+id);
            State s = readState(id);
            s.setAutoma(automa);
            System.out.println("srsly???   "+s);
            automa.setStates(automa.getStates().stream().filter(e -> e.getId()!=id).collect(Collectors.toList()));
//            entityManager.remove(s);
            entityManager.persist(automa);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
