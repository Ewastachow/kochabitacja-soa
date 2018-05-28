package automa.consumer;

import javax.ws.rs.client.ClientBuilder;

public class HelloConsumer {

    public static String getHello(){
        return ClientBuilder
                .newClient()
                .target("http://localhost:8080/HelloWorld")
                .path("hello")
                .request()
                .get()
                .readEntity(String.class);
    }
}
