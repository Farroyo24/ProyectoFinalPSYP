package es.vcarmen.gestionalumnos;

import java.util.Date;

import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Matricula;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface ApiMatricula {
    @GET("matriculas/{dni}")
    Call<Matricula> getCodMatricula(@Path("dni") String dni);

    @GET("matriculas")
    Call<Matricula[]> getMatriculas();

    @POST("matriculas")
    Call<Respuesta> postMatriculas(@Body Matricula matricula);

    @DELETE("matriculas/{dni}/{codigo_matricula}/{fecha}")
    Call<Respuesta> deleteMatriculas(@Path("dni")  String dni, @Path("codigo_matricula") String cod_matricula, @Path("fecha") String fecha);

    @PUT("matriculas/{codigo}")
    Call<Respuesta> putCodMatricula(@Body Matricula matricula, @Path("codigo") String cod_matricula);
}
