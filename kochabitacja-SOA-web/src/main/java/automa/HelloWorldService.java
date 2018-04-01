package automa;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/HelloWorld")
public class HelloWorldService {

    @GET
    @Path("/sayHello")
    /**
     * http://localhost:8080/kochabitacja-SOA-web/HelloWorld/sayHello
     */
    public String sayHello() {
        return "<h1>Hello World</h1>";
    }
}
