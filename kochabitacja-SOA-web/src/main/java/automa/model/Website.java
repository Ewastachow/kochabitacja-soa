package automa.model;

import lombok.Data;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Entity
@Data
@NamedQueries({@NamedQuery(name = Website.Website_BY_ID, query = "SELECT a from Website a where a.id = :id")})
public class Website {

    public static final String Website_BY_ID = "website_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Image_website",
            joinColumns = { @JoinColumn(name = "website_id") },
            inverseJoinColumns = { @JoinColumn(name = "image_id") }
    )
    private List<Image> images;

    public Website() {
        this.images = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Contribiutor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", automas=" + images +
                '}';
    }
}
