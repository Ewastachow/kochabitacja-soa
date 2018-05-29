package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Stateless
@Entity
@Data
@NamedQueries({@NamedQuery(name = Image.IMAGE_BY_ID, query = "SELECT i from Image i where i.id = :id")})
public class Image {

    public static final String IMAGE_BY_ID = "image_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

//    @Column(ty = "PERSON_ID")
//    @Lob(type = LobType.BLOB)
    @Lob
    @JsonIgnore
    private byte[] source;


    @JsonIgnore
    @ManyToMany(mappedBy = "images")
    private List<Website> websites;

    public Image() {
    }

    public Image(String name, byte[] source) {
        this.name = name;
        this.source = source;
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

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }
}
