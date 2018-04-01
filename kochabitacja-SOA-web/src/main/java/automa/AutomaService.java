package automa;

import automa.model.Automa;
import automa.model.State;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/automa")
public class AutomaService {

    @GET
    @Produces("application/json")
    public List<Automa> get(){
        List<Automa> automas = new ArrayList<>();
        automas.add(new Automa("Lama", Arrays.asList(new State("alpaka"), new State("lama"))));
        automas.add(new Automa("Wierza", Arrays.asList(new State("pilka"))));
        automas.add(new Automa("Combo", Arrays.asList(new State("alpaka"), new State("mewa"), new State("piorun"))));
        return automas;
    }

}
