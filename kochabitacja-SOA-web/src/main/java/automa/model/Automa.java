package automa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Stateless
@Entity
@Data
@NamedQueries({@NamedQuery(name = Automa.AUTOMA_BY_ID, query = "SELECT a from Automa a where a.id = :id")})
public class Automa {

    public static final String AUTOMA_BY_ID = "automa_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "automa", cascade = CascadeType.ALL)
    private List<State> states;

    public Automa() {
        this.states = new ArrayList<>();
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

    public List<automa.model.State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "Automa{" +
                "name='" + name + '\'' +
                '}';
    }
}
