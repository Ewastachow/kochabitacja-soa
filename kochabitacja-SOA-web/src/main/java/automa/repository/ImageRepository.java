package automa.repository;

import automa.model.Image;

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
public class ImageRepository implements Serializable {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager entityManager;

    public List<Image> readAllImages(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> rootEntry = cq.from(Image.class);
        CriteriaQuery<Image> all = cq.select(rootEntry);
        TypedQuery<Image> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Image readImage(int id){
        try {
            Object o = entityManager
                    .createNamedQuery(Image.IMAGE_BY_ID, Image.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return (Image) o;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createImage(@NotNull String name, @NotNull byte[] source){
        Image i = new Image(name, source);
        entityManager.persist(i);
    }

    public boolean updateImage(int id, Image newImage) {
        Image s = readImage(id);
        if (s == null) return false;
        s.setName(newImage.getName());
        s.setSource(newImage.getSource());
        entityManager.persist(s);
        return true;
    }

    public boolean deleteImage(int id){
        try {
            entityManager.remove(readImage(id));
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
