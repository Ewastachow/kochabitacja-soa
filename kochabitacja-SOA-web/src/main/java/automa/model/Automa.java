package automa.model;

import automa.model.State;

import java.util.ArrayList;
import java.util.List;

public class Automa {

    private String name;
    private List<automa.model.State> states;

    public Automa() {
        this.name = "DefaultName";
        this.states = new ArrayList<>();
        states.add(new automa.model.State());
        states.add(new automa.model.State());
    }

    public Automa(String name) {
        this.name = name;
        this.states = new ArrayList<>();
        states.add(new automa.model.State("DefState1"));
        states.add(new automa.model.State("DefState2"));
    }

    public Automa(String name, List<automa.model.State> states) {
        this.name = name;
        this.states = states;
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
                ", states=" + states +
                '}';
    }
}
