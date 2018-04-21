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
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Path("/automats")
public class AutomatsService {

    /**
     * http://localhost:8080/kochabitacja-SOA-web
     */
    @GET
    @Produces("application/json")
    public List<Automa> get(){
        List<Automa> automats = new ArrayList<>();
        automats.add(new Automa("Lama", Arrays.asList(new State("alpaka"), new State("lama"))));
        automats.add(new Automa("Wierza", Arrays.asList(new State("pilka"))));
        automats.add(new Automa("Combo", Arrays.asList(new State("alpaka"), new State("mewa"), new State("piorun"))));
        return automats;
    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        Automa automa = new Automa("Lama"+id, Arrays.asList(new State("alpaka"), new State("lama")));
        if (id>200)
            return Response.status(404).build();
        return Response.status(200).entity(automa).build();
    }

    @POST
    @Consumes("application/json")
    public Response post(@NotNull Automa automa) {
        System.out.println("Adding automa: " + automa);
        Automa newAutoma = automa;
        return Response.status(201).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response put(@NotNull Automa automa) {
        return Response.status(201).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete() {
        return Response.status(204).build();
    }

    @GET
    @Path("{id}/avatar")
    @Produces("image/png")
    public Response avatar() {
        try {
            //TODO podmienic link o zdjecia
            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\Documents\\Projects\\SOA\\KochabitacjaSOA\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
            return Response.status(200).entity(image).build();
        } catch (IOException e) {
            return Response.status(500).build();
        }
    }

}
