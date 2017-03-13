package es.vcarmen.gestionalumnos;


import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface Api {
    //obtener un alumno
    @GET("alumnos/{dni}")
    Call<Alumno> getAlumnosDni(@Path("dni") String dni);

    @GET("alumnos")
    Call<Alumno[]> getAlumnos();

    @POST("alumnos")
    Call<Respuesta> postAlumnos(@Body Alumno alumno);

    @DELETE("alumnos/{dni}")
    Call<Respuesta> deleteAlumnos(@Path("dni") String dni);

    @PUT("alumnos/{dni}")
    Call<Respuesta> putAlumnoDni(@Body Alumno alumno, @Path("dni") String dni);


}
