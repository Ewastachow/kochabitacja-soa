package automa;

import automa.model.Automa;
import automa.model.Automata;
import automa.model.Projekt;
import automa.model.StateArray;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        Automata hello = new Projekt().getAutomataPort();
        setCredentials((BindingProvider) hello);

        String res = hello.hello("lama");
        System.out.println(res);

        Automa res2 = hello.takeItDefault();
        System.out.println(res2);

        StateArray res3 = hello.takeStatesList();
        System.out.println(res3.getItem().get(0).getStateName());

//        upload("lama1.jpg", hello.download("lama.jpg"));

    }

    private static void upload(String fileName, byte[] imageBytes) {
        String filePath = "/home/yevv/Dropbox/Semestr-6/SOA/Labolatoria/Lab1_project/lab1_project_linux/hellosoaclient/src/main/resources/"+fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();
            System.out.println("Received file: " + filePath);
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

    private static void setCredentials(BindingProvider port) {
        port.getRequestContext().put("javax.xml.ws.security.auth.username", "lama");
        port.getRequestContext().put("javax.xml.ws.security.auth.password", "lama");
    }
}
