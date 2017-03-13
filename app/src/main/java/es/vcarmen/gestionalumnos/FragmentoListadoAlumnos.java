package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentoListadoAlumnos extends Fragment {

    Retrofit retrofit;
    Api service;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_listado_alumnos, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(Api.class);

        listView = (ListView) view.findViewById(R.id.alumnos_listview);

        obtenerAlumnos();

        return view;
    }

    private void obtenerAlumnos() {
        Call<Alumno[]> call = service.getAlumnos();
        call.enqueue(new Callback<Alumno[]>() {
            @Override
            public void onResponse(Call<Alumno[]> call, Response<Alumno[]> response) {
                Alumno[] alumnos = response.body();
                String[] nombres = new String[alumnos.length];
                for (int i = 0; i < alumnos.length; i++) {
                    Log.v("HelloWorld", alumnos[i].toString());
                    nombres[i] = alumnos[i].getNombre();
                }
                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, nombres));
            }

            @Override
            public void onFailure(Call<Alumno[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });

    }
}
