
package automa.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the automa.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: automa.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link Automa }
     * 
     */
    public Automa createAutoma() {
        return new Automa();
    }

    /**
     * Create an instance of {@link Automa.States }
     * 
     */
    public Automa.States createAutomaStates() {
        return new Automa.States();
    }

    /**
     * Create an instance of {@link StateArray }
     * 
     */
    public StateArray createStateArray() {
        return new StateArray();
    }

}
