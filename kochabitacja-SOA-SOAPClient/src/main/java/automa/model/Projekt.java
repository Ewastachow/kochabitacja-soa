
package automa.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "projekt", targetNamespace = "http://automa/", wsdlLocation = "http://localhost:8080/kochabitacja-soap?wsdl")
public class Projekt
    extends Service
{

    private final static URL PROJEKT_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(automa.model.Projekt.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = automa.model.Projekt.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/kochabitacja-soap?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/kochabitacja-soap?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        PROJEKT_WSDL_LOCATION = url;
    }

    public Projekt(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Projekt() {
        super(PROJEKT_WSDL_LOCATION, new QName("http://automa/", "projekt"));
    }

    /**
     * 
     * @return
     *     returns Automata
     */
    @WebEndpoint(name = "automataPort")
    public Automata getAutomataPort() {
        return super.getPort(new QName("http://automa/", "automataPort"), Automata.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Automata
     */
    @WebEndpoint(name = "automataPort")
    public Automata getAutomataPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://automa/", "automataPort"), Automata.class, features);
    }

}