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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.UriInfo;

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
    public Response getAutomats(@Context UriInfo info) {

        final String nameFilter = info.getQueryParameters().getFirst("name");
        String minStatesFilter = info.getQueryParameters().getFirst("minStatesAmong");
        String maxStatesFilter = info.getQueryParameters().getFirst("maxStatesAmong");

        List<Automa> a = studentRepo.getAllAuutomatas(nameFilter, minStatesFilter, maxStatesFilter);

        a = nameFilter==null ? a :
                a.stream()
                        .filter(automa -> automa.getName().contains(nameFilter))
                        .collect(Collectors.toList());
        a = minStatesFilter==null ? a :
                a.stream()
                        .filter(automa -> automa.getStates().size()>=Integer.parseInt(minStatesFilter))
                        .collect(Collectors.toList());
        a = maxStatesFilter==null ? a :
                a.stream()
                        .filter(automa -> automa.getStates().size()<=Integer.parseInt(maxStatesFilter))
                        .collect(Collectors.toList());

        if (a == null)  return Response.status(404).build();
        return Response.status(200).entity(a).build();
    }

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")
    @Path("{id}")
    public Response getAutomaById(@PathParam("id") int id) {
        Automa s = studentRepo.getAutoma(id);
        if (s == null) return Response.status(404).build();
        return Response.status(200).entity(s).build();
    }

    @GET
    @Produces("application/json")
//    @RolesAllowed("user")
    @Path("{id}/states")
    public Response getAutomaStatesByAutomaId(@PathParam("id") int id) {
        Automa s = studentRepo.getAutoma(id);
        if (s == null) return Response.status(404).build();
        return Response.status(200).entity(s.getStates()).build();
    }

    @POST
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response postAutoma(@NotNull @Valid Automa student) {
        try {
            studentRepo.createAutoma(student);
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
            stateRepo.createState(state,studentRepo.getAutoma(id));
            return Response.status(201).build();
        } catch (EJBException e) {
            if (e.getCause() instanceof ConstraintViolationException)
                return Response.status(400).build();
            throw e;
        }
    }
//
    @PUT
    @Path("{id}")
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response putAutoma(@PathParam("id") int id, @NotNull @Valid Automa student) {
        boolean found = studentRepo.updateAutoma(id, student);
        return found ? Response.status(201).build() : Response.status(404).build();
    }
    //

    @PUT
    @Path("{idAutoma}/states/{idState}")
    @Consumes("application/json")
//    @RolesAllowed("user")
    public Response putState(@PathParam("idState") int id, @NotNull @Valid State state) {
        boolean found = stateRepo.updateState(id, state);
        return found ? Response.status(201).build() : Response.status(404).build();
    }
//
    @DELETE
    @Path("{id}")
//    @RolesAllowed("user")
    public Response deleteAutoma(@PathParam("id") int id) {
        return Response.status(studentRepo.deleteAutoma(id) ? 204 : 404).build();
    }

    @DELETE
    @Path("{idAutoma}/states/{idState}")
//    @RolesAllowed("user")
    public Response deleteState(@PathParam("idState") int idState,@PathParam("idAutoma") int idAutoma) {
        return Response.status(stateRepo.deleteState(idState, studentRepo.getAutoma(idAutoma)) ? 204 : 404).build();
    }
}
