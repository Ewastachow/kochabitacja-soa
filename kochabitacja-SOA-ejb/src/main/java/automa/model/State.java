package automa.model;

import javax.xml.bind.annotation.XmlElement;

public class State {

    private String stateName;

    public State() {
        stateName = "DefaultStateName";
    }

    public State(String stateName) {
        this.stateName = stateName;
    }

    @XmlElement(name = "stateName")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                '}';
    }

}
