package equipo.rocket.headhunters.services;

import java.util.List;

import equipo.rocket.headhunters.model.Interaccion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InteraccionesServices {

    public final static String INTERACCION_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/interaccion/";

    @GET("byIdeaAndTipo/{idIdea}/{tipo}")
    Call<List<Interaccion>> byIdeaAndTipo(@Path("idIdea") String idea, @Path("tipo") String tipo);
}
