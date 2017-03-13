package es.vcarmen.gestionalumnos;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.vcarmen.gestionalumnos.ApiAsignatura;
import es.vcarmen.gestionalumnos.R;
import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Pedro on 21/02/2017.
 */

public class FragmentoListadoAsignaturas extends Fragment{
    Retrofit retrofit;
    ApiAsignatura service;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_listado_asignatura, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiAsignatura.class);

        listView = (ListView) view.findViewById(R.id.asignaturas_listview);

        obtenerAsignaturas();

        return view;
    }

    private void obtenerAsignaturas() {
        Call<Asignatura[]> call = service.getAsignaturas();
        call.enqueue(new Callback<Asignatura[]>() {
            @Override
            public void onResponse(Call<Asignatura[]> call, Response<Asignatura[]> response) {
                Asignatura[] asignaturas = response.body();
                String[] nombres = new String[asignaturas.length];
                for (int i = 0; i < asignaturas.length; i++) {
                    Log.v("HelloWorld", asignaturas[i].toString());
                    nombres[i] = asignaturas[i].getNombre();
                }
                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, nombres));
            }

            @Override
            public void onFailure(Call<Asignatura[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });

    }
}

