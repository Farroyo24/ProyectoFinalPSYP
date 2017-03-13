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



public class FragmentoAlumnoCreate extends Fragment {
    private EditText nombre, apellidos, dni;
    Button boton;
    Retrofit retrofit;
    Api service;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_alumno_create,container, false);
        nombre= (EditText) view.findViewById(R.id.nombreCreateAlumno);
        apellidos= (EditText) view.findViewById(R.id.apellidosCreateAlumno);
        dni = (EditText) view.findViewById(R.id.dniCreateAlumno);
        boton= (Button) view.findViewById(R.id.crear_boton_alumno);
        retrofit = Conexion.getConexion();
        service = retrofit.create(Api.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearAlumno(new Alumno(dni.getText().toString(), nombre.getText().toString(), apellidos.getText().toString()));
            }
        });

        return view;
    }

    private void crearAlumno(Alumno alumno) {
        Call<Respuesta> call = service.postAlumnos(alumno);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta != null) {
                    Snackbar.make(getView(),respuesta.getRespuesta(), Snackbar.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                } else {
                    //texto.setText("se ha producido un error al insertar el Alumno en la base de datos");
                    Log.v("HelloWorld", "se ha producido un error al insertar el Alumno en la base de datos");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
