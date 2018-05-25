package automa;

import automa.model.Automa;
import automa.model.State;
import automa.repository.AutomaRepository;
import automa.repository.StateRepository;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Path("/automats")
@Stateless
public class AutomatsService {

    @Inject
    private AutomaRepository studentRepo;
    @Inject
    private StateRepository stateRepo;

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")
    public Response getAutomats() {
        List<Automa> a = studentRepo.getAllAutomatasList();
        if (a == null)  return Response.status(404).build();
        return Response.status(200).entity(a).build();
    }

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")
    @Path("{id}")
    public Response getAutomaById(@PathParam("id") int id) {
        Automa s = studentRepo.getOneStudent(id);
        if (s == null) return Response.status(404).build();
        return Response.status(200).entity(s).build();
    }

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")
    @Path("{id}/states")
    public Response getAutomaStatesByAutomaId(@PathParam("id") int id) {
        Automa s = studentRepo.getOneStudent(id);
        if (s == null) return Response.status(404).build();
        return Response.status(200).entity(s.getStates()).build();
    }

    @POST
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response postAutoma(@NotNull @Valid Automa student) {
        System.out.println(student);
        try {
            System.out.println("Adding student: " + student);
            studentRepo.addStudent(student);
            System.out.println("Student list: " + studentRepo.getAllAutomatasList());
            return Response.status(201).build();
        } catch (EJBException e) {
            if (e.getCause() instanceof ConstraintViolationException)
                return Response.status(400).build();
            throw e;
        }
    }

    @POST
    @Path("{id}/states")
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response postStateToAutoma(@PathParam("id") int id, @NotNull @Valid State state) {
        try {
            stateRepo.createState(state.getStateName(),studentRepo.getOneStudent(id));
            return Response.status(204).build();
        } catch (EJBException e) {
            if (e.getCause() instanceof ConstraintViolationException)
                return Response.status(400).build();
            throw e;
        }
    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes("application/json")
//    @RolesAllowed("user")
//    public Response put(@PathParam("id") int id, Student student) {
//        boolean found = studentRepo.updateStudent(id, student);
//        return found ? Response.status(204).build() : Response.status(404).build();
//    }
//
//    @DELETE
//    @Path("{id}")
//    @RolesAllowed("user")
//    public Response delete(@PathParam("id") int id) {
//        return Response.status(studentRepo.deleteStudent(id) ? 204 : 404).build();
//    }
//
//    @GET
//    @Path("{id}/book")
//    @Produces("application/pdf")
//    @PermitAll
//    public Response avatar() {
//        try {
//            byte[] image = Files.readAllBytes(Paths.get("/home/marcin/IdeaProjects/soa123/whyfp90.pdf"));
//            return Response.status(200).entity(image).build();
//        } catch (IOException e) {
//            return Response.status(500).build();
//        }
//    }

//    /**
//     * http://localhost:8080/kochabitacja-SOA-web
//     */
//    @GET
//    @Produces("application/json")
//    public List<Automa> get(@QueryParam("name") String name){
//        String newName = name==null || name=="" ? "Lamaaa" : name;
//        List<Automa> automats = new ArrayList<>();
//        automats.add(new Automa("Lama", Arrays.asList(new State("alpaka"), new State("lama"))));
//        automats.add(new Automa("Wierza", Arrays.asList(new State("pilka"))));
//        automats.add(new Automa(newName, Arrays.asList(new State("alpaka"), new State("mewa"), new State("piorun"))));
//        return automats;
//    }

//    @GET
//    @RolesAllowed("TestRole")
//    @Produces("application/json")
//    @Path("{id}")
//    public Response getById(@PathParam("id") int id) {
//        Automa automa = new Automa("Lama"+id, Arrays.asList(new State("alpaka"), new State("lama")));
//        if (id>200)
//            return Response.status(404).build();
//        return Response.status(200).entity(automa).build();
//    }

//    @POST
//    @Consumes("application/json")
//    public Response post(@NotNull @Valid Automa automa) {
////        System.out.println("Adding automa: " + automa);
////        Automa newAutoma = automa;
//        return Response.status(201).build();
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes("application/json")
//    public Response put(@NotNull Automa automa) {
//        return Response.status(201).build();
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response delete() {
//        return Response.status(204).build();
//    }
//
//    @GET
//    @Path("{id}/avatar")
//    @Produces("image/png")
//    public Response avatar() {
//        try {
//            //TODO podmienic link o zdjecia
//            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
//            return Response.status(200).entity(image).build();
//        } catch (IOException e) {
//            return Response.status(500).build();
//        }
//    }

}
