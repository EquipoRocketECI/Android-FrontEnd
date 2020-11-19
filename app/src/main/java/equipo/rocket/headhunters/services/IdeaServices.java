package equipo.rocket.headhunters.services;


import java.util.List;

import equipo.rocket.headhunters.model.Idea;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IdeaServices {

    public final static String IDEA_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/";

    @GET("/ideas")
    Call<List<Idea>> getAllIdeas();

    @GET("ideas/getDestacadas")
    Call<List<Idea>> getIdeasDestacadas();
}
