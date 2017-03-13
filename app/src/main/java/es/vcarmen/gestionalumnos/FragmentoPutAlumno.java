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

import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class FragmentoPutAlumno extends Fragment {
    EditText dniBusqueda, nombre, apellidos, dni;
    Button botonBuscar, botonActualizar;
    Retrofit retrofit;
    Api service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_put_alumno,container,false);
        dniBusqueda= (EditText) view.findViewById(R.id.buscarPutAlumno);
        dni= (EditText) view.findViewById(R.id.dniPutAlumno);
        nombre= (EditText) view.findViewById(R.id.nombrePutAlumno);
        apellidos= (EditText) view.findViewById(R.id.apellidosPutAlumno);
        botonBuscar= (Button) view.findViewById(R.id.botonBuscarAlumno);
        botonActualizar= (Button) view.findViewById(R.id.actualizar_boton_alumno);
        retrofit = Conexion.getConexion();
        service = retrofit.create(Api.class);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerAlumnoDni(dniBusqueda.getText().toString());
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAlumno(new Alumno(dni.getText().toString(),nombre.getText().toString(),apellidos.getText().toString()),dniBusqueda.getText().toString());
            }
        });
        return view;
    }

    private void obtenerAlumnoDni(String dni_alumno) {

        Call<Alumno> call = service.getAlumnosDni(dni_alumno);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Alumno alumno = response.body();
                dni.setText(alumno.getDni());
                nombre.setText(alumno.getNombre());
                apellidos.setText(alumno.getApellidos());

                Log.v("HelloWorld", alumno.toString());
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
    private void actualizarAlumno(Alumno alumno, String dni) {
        Call<Respuesta> call = service.putAlumnoDni(alumno, dni);
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
