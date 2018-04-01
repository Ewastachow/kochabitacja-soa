package automa;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

public class Automa {

    private String name;
    private List<State> states;

    public Automa() {
        this.name = "DefaultName";
        this.states = new ArrayList<>();
        states.add(new State());
        states.add(new State());
    }

    public Automa(String name) {
        this.name = name;
        this.states = new ArrayList<>();
        states.add(new State("DefState1"));
        states.add(new State("DefState2"));
    }

    public Automa(String name, List<State> states) {
        this.name = name;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "states")
    @XmlElement(name = "state")
    public List<State> getStates() {
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
