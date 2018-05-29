package automa;

import automa.model.Image;
import automa.model.Website;
import automa.repository.ImageRepository;
import automa.repository.WebsiteRepository;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Path("/images")
@Stateless
public class ImageService {

    @Inject
    private ImageRepository imageRepo;
    @Inject
    private WebsiteRepository websiteRepo;

    @GET
    @Produces("application/json")
    public Response getImages() {
        List<Image> i = imageRepo.readAllImages();
        if (i == null) return Response.status(404).build();
        return Response.status(200).entity(i).build();
    }

    @GET
    @Path("{id}")
    @Produces("image/png")
    public Response getImage(@PathParam("id") int id) {
        Image i = imageRepo.readImage(id);
        if (i == null) return Response.status(404).build();
        return Response.status(200).entity(i.getSource()).build();
    }

    @POST
    @Consumes("application/json")
    public Response postImage(@NotNull @Valid Image image) {
            imageRepo.createImage(image.getName(),image.getSource());
            return Response.status(200).build();
    }

    @POST
    @Consumes
    public Response postImageSource(byte[] image) {
        imageRepo.createImage("new_image",image);
        return Response.status(200).build();
    }

    @POST
    @Path("/pushToDataBase")
    @Consumes("image/png")
    public Response pushImage() {
        try {
            byte[] image = Files.readAllBytes(Paths.get("./lama.jpg"));
           imageRepo.createImage("lama.png", image);
            return Response.status(200).build();
        } catch (IOException e) {
            return Response.status(500).build();
        }
    }


    @GET
    @Path("{id}/websites")
    @Produces("application/json")
    @RolesAllowed("user")
    public Response getImageWebsites(@PathParam("id") int id) {
        Image a = imageRepo.readImage(id);
        List<Website> automas = websiteRepo.getAllWebsite();
        if (a == null)  return Response.status(404).build();
        List<Website> result = automas.stream().filter(automa -> automa.getImages().stream().anyMatch(con -> con.getName().equals(a.getName()))).collect(Collectors.toList());
        return Response.status(200).entity(result).build();
    }
}
