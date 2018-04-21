package rest_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest_client.model.Automa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AutomaConsumer {

    private Client client;
    private WebTarget target;

    public AutomaConsumer() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/kochabitacja-SOA-web/automats");
    }

    public AutomaConsumer(Client client) {
        this.client = client;
        target = client.target("http://localhost:8080/kochabitacja-SOA-web/automats");
    }

    public List<Automa> getAutomats(){
        String resultJson = target.request().get().readEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Automa[] automas;
        try {
            automas = objectMapper.readValue(resultJson, Automa[].class);
        } catch (IOException e) {
            automas = new Automa[0];
            e.printStackTrace();
        }
        return Arrays.asList(automas);
    }

    public Automa getAutomataById(int id){
        String resultJson =  target.path(String.valueOf(id)).request().get().readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Automa automa = null;
        try {
            automa = mapper.readValue(resultJson, Automa.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automa;
    }

    public int postAutomata(Automa automa){
        String automaJson = null;
        try {
            automaJson = new ObjectMapper().writeValueAsString(automa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(automaJson,MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public int putAutomata(Automa automa, int id){
        String automaJson = null;
        try {
            automaJson = new ObjectMapper().writeValueAsString(automa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Response response = target
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(automaJson,MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public int deleteAutomata(int id){

        String ids = String.valueOf(id);
        Response response = target
                .path(ids)
                .request()
                .delete();
        return response.getStatus();
    }

}