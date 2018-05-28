package rest_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import automa.consumer.AutomaConsumer;
//import rest_client.consumer.HelloConsumer;
//import rest_client.model.Automa;
import automa.model.Automa;

import java.io.IOException;

public class KochabitacjaRestClient {

        public static void main(String[] args) {
        try {
//            System.out.println("GET XML    :  " + HelloConsumer.getHello());
            System.out.println("GET all    :  " + AutomaConsumer.getAutomats());
//            System.out.println("GET        :  " + AutomaConsumer.getAutomataById(3));
            System.out.println("POST       :  " + AutomaConsumer.postAutomata(new Automa("Test22")));
            System.out.println("PUT        :  " + AutomaConsumer.putAutomata(new Automa("NewTest22"),1));
//            System.out.println("DELETE     :  " + AutomaConsumer.deleteAutomata(4));
//            System.out.println("GET FILE   :  " + AutomaConsumer.saveAvatar());
            System.out.println("GET all    :  " + AutomaConsumer.getAutomats());
            System.out.println("PUT        :  " + AutomaConsumer.putAutomata(new Automa("OldTest"),1));
        } catch (JsonProcessingException e) {
            System.out.println("There was a problem with mapping to Json");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was a problem with mapping from Json");
            e.printStackTrace();
        }
    }
}
