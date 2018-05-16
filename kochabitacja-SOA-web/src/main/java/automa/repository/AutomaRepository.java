package automa.repository;

import automa.model.Automa;

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

    public boolean updateAutoma(int id, Automa newStudent) {
        Automa s = getOneStudent(id);
        if (s == null) return false;
        s.setName(newStudent.getName());
        entityManager.persist(s);
        return true;
    }



    public List<Automa> geAllAutomatasList() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Automa> cq = cb.createQuery(Automa.class);
        Root<Automa> rootEntry = cq.from(Automa.class);
        CriteriaQuery<Automa> all = cq.select(rootEntry);
        TypedQuery<Automa> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }


    public boolean deleteStudent(int id) {
        try {
            entityManager.remove(getOneStudent(id));
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Automa getOneStudent(int id) {
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

    public void addStudent(@NotNull String lastName) {
        Automa s = new Automa(lastName);
        entityManager.persist(s);
    }
}