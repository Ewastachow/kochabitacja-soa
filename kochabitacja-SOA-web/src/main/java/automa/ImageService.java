package automa;

import automa.model.Automa;
import automa.model.Image;
import automa.repository.ImageRepository;

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

@Path("/images")
@Stateless
public class ImageService {

    @Inject
    private ImageRepository imageRepo;

    @GET
    @Produces("application/json")
    public Response getImages() {
        List<Image> i = imageRepo.readAllImages();
        if (i == null) return Response.status(404).build();
        return Response.status(200).entity(i).build();
//        try {
//            //TODO podmienic link o zdjecia
//            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
//            return Response.status(200).entity(image).build();
//        } catch (IOException e) {
//            return Response.status(500).build();
//        }
    }

    @GET
    @Path("{id}")
    @Produces("image/png")
    public Response getImage(@PathParam("id") int id) {
        Image i = imageRepo.readImage(id);
        if (i == null) return Response.status(404).build();
        return Response.status(200).entity(i.getSource()).build();
//        try {
//            //TODO podmienic link o zdjecia
//            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
//            return Response.status(200).entity(image).build();
//        } catch (IOException e) {
//            return Response.status(500).build();
//        }
    }

    @POST
    @Consumes("application/json")
//    @Consumes("image/png")
    public Response postImage(@NotNull @Valid Image image) {
//        try {
            imageRepo.createImage(image.getName(),image.getSource());
//            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
            return Response.status(200).build();
//        } catch (IOException e) {
//            return Response.status(500).build();
//        }
    }

    @POST
    @Path("/pushToDataBase")
    @Consumes("image/png")
    public Response pushImage() {
        try {
            byte[] image = Files.readAllBytes(Paths.get("C:\\Users\\yevvy\\IdeaProjects\\kochabitacja-soa\\kochabitacja-SOA-web\\src\\main\\resources\\automa\\lama.jpg"));
           imageRepo.createImage("lama.png", image);
            return Response.status(200).build();
        } catch (IOException e) {
            return Response.status(500).build();
        }
    }
}