package automa;

import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/HelloWorld")
@SecurityDomain("cxfservice-security-domain")
@WebContext(contextRoot="/kochabitacja-rest", urlPattern="/*", authMethod="BASIC", transportGuarantee="NONE", secureWSDLAccess = false)
@DeclareRoles({"user"})
public class HelloWorldService {

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_XHTML_XML)
    /**
     * http://localhost:8080/kochabitacja-SOA-web/HelloWorld/hello
     */
    public String sayHello() {
        return "<h1>Hello World</h1>";
    }
}
