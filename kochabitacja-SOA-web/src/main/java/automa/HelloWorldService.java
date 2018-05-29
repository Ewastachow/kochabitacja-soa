package automa;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/HelloWorld")
public class HelloWorldService {

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_XHTML_XML)
    /**
     * http://localhost:8080/kochabitacja-SOA-web/HelloWorld/hello
     */
    public String sayHello(@Context SecurityContext context) {
        return "<h1>Hello World_lama</h1>"+context.isUserInRole("user");
    }
}
