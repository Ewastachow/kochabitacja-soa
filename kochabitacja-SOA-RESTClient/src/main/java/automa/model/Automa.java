package automa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Automa {
    @JsonIgnore
    private int id;
    private String name;
    private List<State> states;

    public Automa() {
    }

    public Automa(String name) {
        this.name = name;
    }

    public Automa(String name, List<automa.model.State> states) {
        this.name = name;
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
//                ", states=" + states +
                '}';
    }
}
