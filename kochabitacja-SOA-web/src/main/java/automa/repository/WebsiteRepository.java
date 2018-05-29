package automa.repository;

import automa.model.Image;
import automa.model.Website;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Singleton
public class WebsiteRepository implements Serializable {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager entityManager;

    public void createWebsite(Website contribiutor) {
        List<Image> automas = contribiutor.getImages();
        contribiutor.setImages(null);
        entityManager.persist(contribiutor);
        if(automas!=null)
            automas.forEach(automas2 -> {
                automas2.getWebsites().add(contribiutor);
                entityManager.persist(automas2);
            });
    }

    public Website getWebsite(int id) {
        try {
            Object o = entityManager
                    .createNamedQuery(Website.Website_BY_ID, Website.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return (Website) o;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Website> getAllWebsite() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Website> cq = cb.createQuery(Website.class);
        Root<Website> rootEntry = cq.from(Website.class);
        CriteriaQuery<Website> all = cq.select(rootEntry);
        TypedQuery<Website> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
