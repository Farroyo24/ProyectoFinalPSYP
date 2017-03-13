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
import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Matricula;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoObtenerMatricula extends Fragment {
    private TextView texto;
    private EditText codigo;
    private Button boton;
    Retrofit retrofit;
    ApiMatricula service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_obtener_matricula, container, false);
        retrofit = Conexion.getConexion();
        service = retrofit.create(ApiMatricula.class);

        texto = (TextView) view.findViewById(R.id.obtenerMatricula);
        codigo = (EditText) view.findViewById(R.id.obtenerMatricula_editText);
        boton = (Button) view.findViewById(R.id.obtenerMatricula_boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerMatriculaCodigo(codigo.getText().toString());
                Log.v("prueba",codigo.getText().toString());
            }
        });
        return view;
    }

    private void obtenerMatriculaCodigo(String codigo) {
        Call<Matricula> call = service.getCodMatricula(codigo);
        call.enqueue(new Callback<Matricula>() {
            @Override
            public void onResponse(Call<Matricula> call, Response<Matricula> response) {
                Matricula matricula = response.body();
                texto.setText(matricula.toString());
                Log.v("HelloWorld", response.body().toString());
            }

            @Override
            public void onFailure(Call<Matricula> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

}
