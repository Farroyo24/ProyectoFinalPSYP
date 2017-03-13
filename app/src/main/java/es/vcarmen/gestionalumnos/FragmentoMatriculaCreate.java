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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Matricula;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoMatriculaCreate extends Fragment {
    private EditText nota, cod_asignatura, dni, fecha;
    Button boton;
    Retrofit retrofit;
    ApiMatricula service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_matricula_create,container, false);
        nota= (EditText) view.findViewById(R.id.notaCreateMatricula);
        cod_asignatura= (EditText) view.findViewById(R.id.codigoCreateMatricula);
        dni= (EditText) view.findViewById(R.id.dniCreateMatricula);
        fecha= (EditText) view.findViewById(R.id.fechaCreateMatricula);
        boton= (Button) view.findViewById(R.id.crear_boton_matricula);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiMatricula.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("prueba",cod_asignatura.getText().toString());
                crearMatricula(new Matricula(dni.getText().toString(),cod_asignatura.getText().toString(),Integer.parseInt(nota.getText().toString()),fecha.getText().toString()+"01-01"));
            }
        });

        return view;
    }

    private void crearMatricula(Matricula matricula) {
        Log.v("prueba",matricula.toString());
        Call<Respuesta> call = service.postMatriculas(matricula);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta != null) {
                    Snackbar.make(getView(),respuesta.getRespuesta(), Snackbar.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                } else {
//texto.setText("se ha producido un error al insertar el Alumno en la base de datos");
                    Log.v("HelloWorld", "se ha producido un error al insertar la Matricula en la base de datos");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
