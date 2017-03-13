package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentoObtenerAlumno extends Fragment {
    private TextView texto;
    private EditText dni;
    private Button boton;
    Retrofit retrofit;
    Api service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_obtener_alumno, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(Api.class);

        texto = (TextView) view.findViewById(R.id.obtenerAlumno);
        dni = (EditText) view.findViewById(R.id.obtenerAlumno_editText);
        boton = (Button) view.findViewById(R.id.obtenerAlumno_boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerAlumnoDni(dni.getText().toString());
            }
        });
        return view;
    }

    private void obtenerAlumnoDni(String dni) {

        Call<Alumno> call = service.getAlumnosDni(dni);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Alumno Alumno = response.body();
                texto.setText(Alumno.toString());
                Log.v("HelloWorld", Alumno.toString());
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
