package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class State {
    @JsonIgnore
    private int id;
    private String stateName;

    public State() {
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

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
