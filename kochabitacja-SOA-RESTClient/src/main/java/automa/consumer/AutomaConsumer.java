package automa.consumer;

import automa.model.Automa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AutomaConsumer {

//    private static final String target = "http://localhost:8080/kochabitacja-SOA-web/automats";
    private static final String target = "http://localhost:8080/automats";

    public static List<Automa> getAutomats() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(target)
                .request()
//                .header("Authorization", getAuthorizationHeader("lama", "alpaka"))
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
                .header("Authorization", getAuthorizationHeader("lama", "alpaka"))
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

    private static String getAuthorizationHeader(String username, String password) {
        String usernamePassword = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes());
    }

}