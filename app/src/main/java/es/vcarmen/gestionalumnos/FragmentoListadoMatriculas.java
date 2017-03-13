package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.vcarmen.gestionalumnos.model.Matricula;
import es.vcarmen.gestionalumnos.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentoListadoMatriculas extends Fragment {

    Retrofit retrofit;
    ApiMatricula service;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_listado_matriculas, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiMatricula.class);

        listView = (ListView) view.findViewById(R.id.matriculas_listview);

        obtenerMatriculas();

        return view;
    }

    private void obtenerMatriculas() {
        Call<Matricula[]> call = service.getMatriculas();
        call.enqueue(new Callback<Matricula[]>() {
            @Override
            public void onResponse(Call<Matricula[]> call, Response<Matricula[]> response) {
                Matricula[] matriculas = response.body();
                String[] nombres = new String[matriculas.length];
                for (int i = 0; i < matriculas.length; i++) {
                    Log.v("HelloWorld", matriculas[i].toString());
                    nombres[i] = matriculas[i].getDni_alumno();
                }
                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, nombres));
            }

            @Override
            public void onFailure(Call<Matricula[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
        }

}
