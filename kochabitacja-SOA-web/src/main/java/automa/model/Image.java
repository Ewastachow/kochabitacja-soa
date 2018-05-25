package automa.model;

import lombok.Data;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Stateless
@Entity
@Data
@NamedQueries({@NamedQuery(name = Image.IMAGE_BY_ID, query = "SELECT i from Image i where i.id = :id")})
public class Image {

    public static final String IMAGE_BY_ID = "image_by_id";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private byte[] source;

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
}
