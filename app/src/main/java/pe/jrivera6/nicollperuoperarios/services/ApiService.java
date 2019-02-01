package pe.jrivera6.nicollperuoperarios.services;

import pe.jrivera6.nicollperuoperarios.models.Formulario;
import pe.jrivera6.nicollperuoperarios.models.ResponseMessage;
import pe.jrivera6.nicollperuoperarios.models.Tubo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

//    String API_BASE_URL = "http://10.139.24.189:8089";
//    String API_BASE_URL = "http://10.139.24.99:8089";
    String API_BASE_URL = "http://10.0.2.2:8089";

    //10.0.2.2


    @GET("/api/v1/tubo/{id}")
    Call<Tubo> getTubo(@Path("id") long idTubo);


    @GET("/api/v1/indicadores/{id}")
    Call<Formulario> getForm(@Path("id") long idForm);


    @POST("/api/v1/formulario")
    Call<Formulario> createForm(@Body Formulario formulario);

//    @GET("/api/estandar")
//
//
//    @FormUrlEncoded
//    @POST("/api/usuarios")
//    Call<ResponseMessage> createUser(
//            @Field("nombres")String nombre,
//            @Field("apellidos")String apellidos,
//            @Field("username")String nom_usuario,
//            @Field("password")String password,
//            @Field("email")String email
//    );
//
//
//
}
