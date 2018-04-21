package rest_client;

import rest_client.model.Automa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class KochabitacjaRestClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        HelloConsumer helloConsumer = new HelloConsumer(client);
        System.out.println(helloConsumer.getHello());

        AutomaConsumer automaConsumer = new AutomaConsumer(client);
        System.out.println("GET all:  " + automaConsumer.getAutomats());
        System.out.println("GET    :  " + automaConsumer.getAutomataById(3));
        System.out.println("POST   :  " + automaConsumer.postAutomata(new Automa()));
        System.out.println("DELETE :  " + automaConsumer.deleteAutomata(4));
        System.out.println("PUT    :  " + automaConsumer.putAutomata(new Automa(),3));


    }
}
