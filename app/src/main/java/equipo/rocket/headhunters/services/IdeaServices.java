package equipo.rocket.headhunters.services;






import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import equipo.rocket.headhunters.model.Idea;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IdeaServices {

    public final static String IDEA_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/";

    @GET("/ideas")
    Call<List<Idea>> getAllIdeas();

    @POST("/ideas/filtered")
    Call<List<Idea>> filter(@Body JsonObject selectedFilters);
}
