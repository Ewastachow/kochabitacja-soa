//package automa;
//
//import automa.model.Automa;
//import automa.model.Contribiutor;
//import automa.repository.AutomaRepository;
//import automa.repository.ContribiutorRepository;
//import automa.repository.StateRepository;
//
//import javax.ejb.EJBException;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Path("/contrs")
//@Stateless
//public class ContribiutorServices {
//
//    @Inject
//    private AutomaRepository automaRepo;
//    @Inject
//    private ContribiutorRepository contribiutorRepo;
//
//
//    @GET
//    @Produces("application/json")
////    @RolesAllowed("user")
//    public Response getContribiutors() {
//        List<Contribiutor> a = contribiutorRepo.getAllContribiutors();
//        if (a == null)  return Response.status(404).build();
//        return Response.status(200).entity(a).build();
//    }
//
//    @POST
//    @Consumes("application/json")
////    @RolesAllowed("user")
//    public Response postContribiutor(@NotNull @Valid Contribiutor contribiutor) {
//        try {
//            contribiutorRepo.createContribiutor(contribiutor);
//            return Response.status(201).build();
//        } catch (EJBException e) {
//            if (e.getCause() instanceof ConstraintViolationException)
//                return Response.status(400).build();
//            throw e;
//        }
//    }
//
//
//    @GET
//    @Path("{id}")
//    @Produces("application/json")
////    @RolesAllowed("user")
//    public Response getContribiutor(@PathParam("id") int id) {
//        Contribiutor a = contribiutorRepo.getContribiutor(id);
//        if (a == null)  return Response.status(404).build();
//        return Response.status(200).entity(a).build();
//    }
//
//    @GET
//    @Path("{id}/automas")
//    @Produces("application/json")
////    @RolesAllowed("user")
//    public Response getContribiutorAutomas(@PathParam("id") int id) {
//        Contribiutor a = contribiutorRepo.getContribiutor(id);
//        List<Automa> automas = automaRepo.getAllAuutomatas(null, null, null);
//        if (a == null)  return Response.status(404).build();
//        List<Automa> result = automas.stream().filter(automa -> automa.getContribiutors().stream().anyMatch(con -> con.getName().equals(a.getName()))).collect(Collectors.toList());
//        return Response.status(200).entity(result).build();
//    }
//
//
//
//}
