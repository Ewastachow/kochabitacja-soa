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
//@RequiredArgsConstructor
//@NoArgsConstructor
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


//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "automas", cascade = CascadeType.ALL)
//    private List<Contribiutor> contribiutors;

    public Automa() {
//        this.contribiutors = new ArrayList<>();
        this.states = new ArrayList<>();
    }

//    public Automa(String name) {
//        this.name = name;
//    }
//
//    public Automa(String name, List<automa.model.State> states) {
//        this.name = name;
////        this.states = states;
//    }

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

//    public List<Contribiutor> getContribiutors() {
//        return contribiutors;
//    }
//
//    public void setContribiutors(List<Contribiutor> contribiutors) {
//        this.contribiutors = contribiutors;
//    }

    @Override
    public String toString() {
        return "Automa{" +
                "name='" + name + '\'' +
//                ", states=" + states +
                '}';
    }
}
