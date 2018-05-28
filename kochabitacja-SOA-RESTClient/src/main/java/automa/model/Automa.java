package automa.model;

import java.util.List;

public class Automa {
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
