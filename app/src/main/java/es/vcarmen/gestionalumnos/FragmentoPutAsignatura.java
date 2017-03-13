package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoPutAsignatura extends Fragment {
    EditText codigoBusqueda, nombre, codigo;
    Button botonBuscar, botonActualizar;
    Retrofit retrofit;
    ApiAsignatura service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_put_asignatura,container,false);
        codigoBusqueda = (EditText) view.findViewById(R.id.buscarPutAsignatura);
        nombre = (EditText) view.findViewById(R.id.nombrePutAsignatura);
        codigo = (EditText) view.findViewById(R.id.codigoAsignatura);
        botonBuscar= (Button) view.findViewById(R.id.botonBuscarAsignatura);
        botonActualizar= (Button) view.findViewById(R.id.botonActualizarAsignatura);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiAsignatura.class);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerAsignaturaCodigo(codigoBusqueda.getText().toString());
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAsignatura(new Asignatura(nombre.getText().toString(),codigo.getText().toString()), codigoBusqueda.getText().toString());
            }
        });
        return view;
    }

    private void obtenerAsignaturaCodigo(String codigoAsignatura) {

        Call<Asignatura> call = service.getCodAsignatura(codigoAsignatura);
        call.enqueue(new Callback<Asignatura>() {
            @Override
            public void onResponse(Call<Asignatura> call, Response<Asignatura> response) {
                Asignatura asignatura = response.body();
                nombre.setText(asignatura.getNombre());
                codigo.setText(asignatura.getCodigo());

                Log.v("HelloWorld", asignatura.toString());
            }

            @Override
            public void onFailure(Call<Asignatura> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
    private void actualizarAsignatura(Asignatura asignatura, String codigo) {
        Call<Respuesta> call = service.putCodAsignatura(asignatura, codigo);
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
