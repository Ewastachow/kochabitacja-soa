package rest_client.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest_client.model.Automa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AutomaConsumer {

    private static final String target = "http://localhost:8080/kochabitacja-SOA-web/automats";

    public static List<Automa> getAutomats() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .request()
                .get();
        return Arrays.asList(
                new ObjectMapper()
                        .readValue(response.readEntity(String.class), Automa[].class));
    }

    public static Automa getAutomataById(int id) throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path(String.valueOf(id))
                .request()
                .get();
        return new ObjectMapper()
                .readValue(response.readEntity(String.class), Automa.class);
    }

    public static int postAutomata(Automa automa) throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new ObjectMapper().writeValueAsString(automa),MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public static int putAutomata(Automa automa, int id) throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(new ObjectMapper().writeValueAsString(automa),MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public static int deleteAutomata(int id){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path(String.valueOf(id))
                .request()
                .delete();
        return response.getStatus();
    }

    public static int saveAvatar() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .path("3/avatar")
                .request().get();
        byte[] imageBytes = response.readEntity(byte[].class);
        Files.write(Paths.get("C:\\Users\\yevvy\\Documents\\Projects\\SOA\\KochabitacjaSOA\\kochabitacja-SOA-RESTClient\\src\\main\\resources\\ap.jpg"),imageBytes);
        return response.getStatus();
    }

}