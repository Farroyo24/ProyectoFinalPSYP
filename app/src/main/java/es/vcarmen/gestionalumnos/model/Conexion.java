package es.vcarmen.gestionalumnos.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Conexion {
    private static Retrofit retrofit = null;
    private final static String BASE_URL = "http://192.168.0.117:3000/";
    private Conexion(){}

    public static Retrofit getConexion(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
