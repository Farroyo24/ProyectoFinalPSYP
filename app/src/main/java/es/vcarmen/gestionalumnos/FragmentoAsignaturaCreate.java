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

import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentoAsignaturaCreate extends Fragment{
    private EditText nombre, cod_asignatura;
    Button boton;
    Retrofit retrofit;
    ApiAsignatura service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_asignatura_create,container, false);
        nombre= (EditText) view.findViewById(R.id.nombreCreateAsignatura);
        cod_asignatura= (EditText) view.findViewById(R.id.codigoCreateAsignatura);
        boton= (Button) view.findViewById(R.id.crear_boton_asignatura);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiAsignatura.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearAsignatura(new Asignatura(nombre.getText().toString(), cod_asignatura.getText().toString()));
            }
        });

        return view;
    }

    private void crearAsignatura(Asignatura asignatura) {
        Call<Respuesta> call = service.postAsignaturas(asignatura);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta != null) {
                    Snackbar.make(getView(),respuesta.getRespuesta(), Snackbar.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                } else {
//texto.setText("se ha producido un error al insertar el Alumno en la base de datos");
                    Log.v("HelloWorld", "se ha producido un error al insertar la Asginatura en la base de datos");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
