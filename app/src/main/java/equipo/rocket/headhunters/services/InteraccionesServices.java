package equipo.rocket.headhunters.services;

import java.util.List;

import equipo.rocket.headhunters.model.Interaccion;
import equipo.rocket.headhunters.model.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InteraccionesServices {

    public final static String INTERACCION_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/interaccion/";

    @GET("byIdeaAndTipo/{idIdea}/{tipo}")
    Call<List<Interaccion>> byIdeaAndTipo(@Path("idIdea") String idea, @Path("tipo") String tipo);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST(".")
    Call<Interaccion> addInteraccion(@Body Interaccion interaccion);
}
