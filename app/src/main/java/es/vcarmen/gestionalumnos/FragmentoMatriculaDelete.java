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

import java.util.Date;

import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentoMatriculaDelete extends Fragment {
    private EditText codigo, dni, fecha;
    private Button borrar;
    Retrofit retrofit;
    ApiMatricula service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_matricula_delete,container,false);
        codigo= (EditText) view.findViewById(R.id.codigo_delete_matriculas);
        dni= (EditText) view.findViewById(R.id.dni_delete_matriculas);
        fecha= (EditText) view.findViewById(R.id.fecha_delete_matriculas);
        borrar= (Button) view.findViewById(R.id.boton_delete_matriculas);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiMatricula.class);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarMatriculaCodigo(codigo.getText().toString(), dni.getText().toString(),fecha.getText().toString()+"-00-00");//(fecha.getText().toString()));
            }
        });

        return view;
    }

    private void borrarMatriculaCodigo(String codigo, String dni, String fecha) {
        Call<Respuesta> call = service.deleteMatriculas(dni, codigo, fecha);
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
