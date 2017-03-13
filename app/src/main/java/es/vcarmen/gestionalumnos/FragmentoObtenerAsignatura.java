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

import es.vcarmen.gestionalumnos.model.Asignatura;
import es.vcarmen.gestionalumnos.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Pedro on 21/02/2017.
 */

public class FragmentoObtenerAsignatura extends Fragment{
    private TextView texto;
    private EditText codigo;
    private Button boton;
    Retrofit retrofit;
    ApiAsignatura service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_obtener_asignatura, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiAsignatura.class);

        texto = (TextView) view.findViewById(R.id.obtenerAsignatura);
        codigo = (EditText) view.findViewById(R.id.obtenerAsignatura_editText);
        boton = (Button) view.findViewById(R.id.obtenerAsignatura_boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerCodigo(codigo.getText().toString());
                Log.v("prueba",codigo.getText().toString());
            }
        });
        return view;
    }

    private void obtenerCodigo(String codigo) {

        Call<Asignatura> call = service.getCodAsignatura(codigo);
        call.enqueue(new Callback<Asignatura>() {
            @Override
            public void onResponse(Call<Asignatura> call, Response<Asignatura> response) {
                Asignatura asignatura = response.body();
                texto.setText(asignatura.toString());
                Log.v("HelloWorld", response.body().toString());
            }

            @Override
            public void onFailure(Call<Asignatura> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
