package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Entity
@Data
//@RequiredArgsConstructor
//@NoArgsConstructor
@NamedQueries({@NamedQuery(name = Website.Website_BY_ID, query = "SELECT a from Website a where a.id = :id")})
public class Website {

    public static final String Website_BY_ID = "website_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

//    @JsonIgnore
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

//    public Contribiutor(String name) {
//        this.name = name;
//    }
//
//    public Contribiutor(String name, List<Automa> automas) {
//        this.name = name;
//        this.automas = automas;
//    }

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

//    public List<Automa> getAutomas() {
//        return automas;
//    }
//
//    public void setAutomas(List<Automa> automas) {
//        this.automas = automas;
//    }
}
