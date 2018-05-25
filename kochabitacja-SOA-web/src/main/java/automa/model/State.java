package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({@NamedQuery(name = State.STATE_BY_ID, query = "SELECT s from State s where s.id = :id")})
public class State {

    public static final String STATE_BY_ID = "state_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String stateName;

    @ManyToOne
    @JoinColumn(name="automa_id", nullable = false)
    @JsonIgnore
    private Automa automa;

    public State() {
    }

    public State(String stateName, Automa automa) {
        this.stateName = stateName;
        this.automa = automa;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Automa getAutoma() {
        return automa;
    }

    public void setAutoma(Automa automa) {
        this.automa = automa;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
