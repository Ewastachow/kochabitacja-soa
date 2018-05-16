package automa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
//@NamedQueries({@NamedQuery(name = State.STATE_BY_ID, query = "SELECT s from State s where s.id = :id")})
public class State {

//    public static final String STATE_BY_ID = "state_by_id";
//
//    @Id
//    @GeneratedValue
    private int id;

//    @NotNull
    private String stateName;

//    @NotNull
    private int automaId;

    public State() {
        stateName = "DefaultStateName";
    }

    public State(String stateName) {
        this.stateName = stateName;
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

    public int getAutomaId() {
        return automaId;
    }

    public void setAutomaId(int automaId) {
        this.automaId = automaId;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
