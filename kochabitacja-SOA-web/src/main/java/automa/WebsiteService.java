package automa;

import automa.model.Website;
import automa.repository.ImageRepository;
import automa.repository.WebsiteRepository;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/websites")
@Stateless
public class WebsiteService {

    @Inject
    private ImageRepository imageRepo;
    @Inject
    private WebsiteRepository websiteRepo;


    @GET
    @Produces("application/json")
    @RolesAllowed("user")
    public Response getWebsites() {
        List<Website> a = websiteRepo.getAllWebsite();
        if (a == null)  return Response.status(404).build();
        return Response.status(200).entity(a).build();
    }

    @POST
    @Consumes("application/json")
    @RolesAllowed("user")
    public Response postContribiutor(@NotNull @Valid Website contribiutor) {
        try {
            websiteRepo.createWebsite(contribiutor);
            return Response.status(201).build();
        } catch (EJBException e) {
            if (e.getCause() instanceof ConstraintViolationException)
                return Response.status(400).build();
            throw e;
        }
    }


    @GET
    @Path("{id}")
    @Produces("application/json")
    @RolesAllowed("user")
    public Response getContribiutor(@PathParam("id") int id) {
        Website a = websiteRepo.getWebsite(id);
        if (a == null)  return Response.status(404).build();
        return Response.status(200).entity(a).build();
    }

    @GET
    @Path("{id}/images")
    @Produces("application/json")
    @RolesAllowed("user")
    public Response getContribiutorAutomas(@PathParam("id") int id) {
        Website a = websiteRepo.getWebsite(id);
        if (a == null)  return Response.status(404).build();
        return Response.status(200).entity(a.getImages()).build();
    }



}
