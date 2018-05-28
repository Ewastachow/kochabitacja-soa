package automa.consumer;

import automa.model.Image;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageConsumer {

    private static final String target = "http://localhost:8080/images";

    public static int saveAvatar() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path("3/avatar")
                .request().get();
        byte[] imageBytes = response.readEntity(byte[].class);
        Files.write(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-RESTClient\\src\\main\\resources\\rest_client\\lama.jpg"),imageBytes);
        return response.getStatus();
    }

    public static int sandAvatar(Image image) throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path("3/avatar")
                .request().get();
        byte[] imageBytes = response.readEntity(byte[].class);
        Files.write(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-RESTClient\\src\\main\\resources\\rest_client\\lama.jpg"),imageBytes);
        return response.getStatus();
    }
}
