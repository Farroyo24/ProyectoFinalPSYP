package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class FragmentoDeleteAsignatura extends Fragment {
    private EditText codigo;
    private Button borrar;
    Retrofit retrofit;
    ApiAsignatura service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_asignatura_delete,container,false);
        codigo= (EditText) view.findViewById(R.id.codigo_delete_asignaturas);
        borrar= (Button) view.findViewById(R.id.boton_delete_asignatura);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiAsignatura.class);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarAsignaturaCodigo(codigo.getText().toString());
            }
        });

        return view;
    }

    private void borrarAsignaturaCodigo(String codigo) {
        Call<Respuesta> call = service.deleteAsignaturas(codigo);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                Snackbar.make(getView(),respuesta.getRespuesta(),Snackbar.LENGTH_SHORT).show();
                Log.v("HelloWorld", respuesta.toString());
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
