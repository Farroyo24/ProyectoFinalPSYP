package es.vcarmen.gestionalumnos;

import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Pedro on 21/02/2017.
 */

public interface ApiAsignatura {
    //obtener una Asignatura
    @GET("asignaturas/{codigo}")
    Call<Asignatura> getCodAsignatura(@Path("codigo") String codigo);

    @GET("asignaturas")
    Call<Asignatura[]> getAsignaturas();

    @POST("asignaturas")
    Call<Respuesta> postAsignaturas(@Body Asignatura asignatura);

    @DELETE("asignaturas/{codigo}")
    Call<Respuesta> deleteAsignaturas(@Path("codigo") String cod_asignatura);

    @PUT("asignaturas/{codigo}")
    Call<Respuesta> putCodAsignatura(@Body Asignatura asignatura, @Path("codigo") String cod_asignatura);

}