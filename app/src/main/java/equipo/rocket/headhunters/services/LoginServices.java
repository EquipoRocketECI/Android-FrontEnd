package equipo.rocket.headhunters.services;


import java.util.List;

import equipo.rocket.headhunters.model.Login;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginServices {

    public final static String LOGIN_BASE_URL = "https://mysterious-refuge-36454.herokuapp.com/";

    @GET("usuario")
    Call<List<Login>> getAllUser();


    @GET("usuario/{correo}")
    Call<Login> getUsuario(@Path("correo") String correo);

    @GET("usuario/getNombreCompleto/{correo}")
    Call<Login> getNombreCompleto(@Path("correo") String correo);

    @POST("usuario/")
    Call<Login> addUser();

}
