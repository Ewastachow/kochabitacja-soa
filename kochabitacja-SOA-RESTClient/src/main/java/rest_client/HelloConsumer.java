package rest_client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class HelloConsumer {

    private Client client;
    private WebTarget target;

    public HelloConsumer() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/kochabitacja-SOA-web/HelloWorld");
    }

    public HelloConsumer(Client client) {
        this.client = client;
        target = client.target("http://localhost:8080/kochabitacja-SOA-web/HelloWorld");
    }

    public String getHello(){
        return target.path("hello").request().get().readEntity(String.class);
    }
}
