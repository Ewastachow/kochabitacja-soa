package automa;

import automa.model.Automa;
import automa.model.State;
import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.ws.WebServiceException;
import java.io.*;
import java.util.List;
import javax.annotation.security.PermitAll;

@Stateless
@SecurityDomain("cxfservice-security-domain")
@DeclareRoles({"user"})
@WebContext(contextRoot="/kochabitacja-soap", urlPattern="/*", authMethod="BASIC", transportGuarantee="NONE", secureWSDLAccess = false)
@SOAPBinding(style=SOAPBinding.Style.RPC)
@WebService(serviceName = "projekt", name = "automata")
public class ServerSOAP {

    @WebMethod
    @PermitAll
    @WebResult(name = "hello_message")
    public String hello(@WebParam(name = "toHello") String s){
        return "Hello "+s;
    }

    @WebMethod
    @DenyAll
    @WebResult(name = "hello_message")
    public String hello2(@WebParam(name = "toHello") String s){
        return "Hello "+s;
    }

    @WebMethod
    @RolesAllowed("user")
    @WebResult(name = "automata_from_param")
    public Automa takeItParams(@WebParam(name = "name")String name, @WebParam(name = "states_list") List<State> states){
        return new Automa(name, states);
    }

    @WebMethod
    @RolesAllowed("user")
    @WebResult(name = "automata_default")
    public Automa takeItDefault(){
        return new Automa();
    }

    @WebMethod
    @RolesAllowed("user")
    @XmlElementWrapper(name = "states")
    @XmlElement(name = "state")
    @WebResult(name = "states_list")
    public List<State> takeStatesList(){
        return new Automa().getStates();
    }

    @WebMethod
    @RolesAllowed("user")
    @WebResult(name = "string_automata")
    public String takeAutomata(@WebParam(name = "automa") Automa automa){
        return automa.toString();
    }

    @WebMethod
    @WebResult(name = "download_file")
    public byte[] download(@WebParam(name = "file_name")String filename) {
        String filePath = "automa/"+filename;
        System.out.println("Sending file: " + filePath);
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();
            return fileBytes;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

    @WebMethod
    @WebResult(name = "upload_file")
    public void upload(@WebParam(name = "file_name")String fileName, @WebParam(name = "bites")byte[] imageBytes) {
        String filePath = "automa/" + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();
            System.out.println("Received file: " + filePath);
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

}
