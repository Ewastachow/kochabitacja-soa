package automa;

import automa.model.Automa;
import automa.model.State;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/automats")
public class AutomatsService {

    /**
     * http://localhost:8080/kochabitacja-SOA-web/automats
     */
    @GET
    @Produces("application/json")
//    @PermitAll
    //TODO jak ustawiac kody bledu???
    public List<Automa> get(){
        List<Automa> automats = new ArrayList<>();
        automats.add(new Automa("Lama", Arrays.asList(new State("alpaka"), new State("lama"))));
        automats.add(new Automa("Wierza", Arrays.asList(new State("pilka"))));
        automats.add(new Automa("Combo", Arrays.asList(new State("alpaka"), new State("mewa"), new State("piorun"))));
        return automats;
    }

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")  //TODO Co to? I jakie importy do tego ->
//          import javax.annotation.security.PermitAll;
//          import javax.annotation.security.RolesAllowed;
    @Path("{id}")
    //TODO Psudo pobiranie id - wymyslec cos sensownego
    public Response getById(@PathParam("id") int id) {
        Automa automa = new Automa("Lama"+id, Arrays.asList(new State("alpaka"), new State("lama")));
        if (id>200)
            return Response.status(404).build();
        return Response.status(200).entity(automa).build();
    }

    @POST
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response post(@NotNull Automa automa) {
//        try {
            System.out.println("Adding automa: " + automa);
            Automa newAutoma = automa;
//            studentRepo.addStudent(student.getFirstName(), student.getLastName());
//            System.out.println("Student list: " + studentRepo.getStudents());

            return Response.status(201).build();
//        } catch (EJBException e) {
//            if (e.getCause() instanceof ConstraintViolationException)
//                return Response.status(400).build();
//            throw e;
//        }
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response put(@PathParam("id") int id, Automa automa) {
//        boolean found = studentRepo.updateStudent(id, student);
//        return Response.status(found ? 204 : 404).build();
        return Response.status(201).build();
    }

    @DELETE
    @Path("{id}")
//    @RolesAllowed("user")
    public Response delete(@PathParam("id") int id) {
//        return Response.status(studentRepo.deleteStudent(id) ? 204 : 404).build();
        return Response.status(204).build();
    }

    @GET
    @Path("{id}/avatar")
    @Produces("image/png")
//    @PermitAll
    public Response avatar() {
        try {
            //TODO podmienic link o zdjecia
            byte[] image = Files.readAllBytes(Paths.get("/home/mmos/src/soa/avatar.png"));
            return Response.status(200).entity(image).build();
        } catch (IOException e) {
            return Response.status(500).build();
        }
    }

}
