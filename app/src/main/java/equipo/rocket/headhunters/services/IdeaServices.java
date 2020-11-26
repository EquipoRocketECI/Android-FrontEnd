package equipo.rocket.headhunters.services;


import java.util.List;

import equipo.rocket.headhunters.model.Idea;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IdeaServices {

    public final static String IDEA_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/";

    @GET("/ideas")
    Call<List<Idea>> getAllIdeas();


    @GET("ideas/getDestacadas")
    Call<List<Idea>> getIdeasDestacadas();

    @GET("ideas/{id}")
    Call<Idea> getIdeabyId(@Path("id") int ideaID);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("ideas")
    Call <Idea>  postIdea(@Body Idea idea);



}
