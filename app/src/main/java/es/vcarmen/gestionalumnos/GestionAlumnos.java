package es.vcarmen.gestionalumnos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import es.vcarmen.gestionalumnos.model.Alumno;
import es.vcarmen.gestionalumnos.model.Conexion;
import es.vcarmen.gestionalumnos.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestionAlumnos extends AppCompatActivity {
    private TextView texto;
    private EditText dni;
    NavigationView navigation;
    DrawerLayout drawer;


    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_alumnos);
        fm = getSupportFragmentManager();
        navigation= (NavigationView) findViewById(R.id.navview);
        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmento = new Fragment();
                //creamos un fragmento en funcion del item que pulsemos
                switch (item.getItemId()) {
                    case R.id.menu_seccion_1:
                        fragmento = new FragmentoCrudAlumno();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.contenedor,fragmento);
                        ft.commit();
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.menu_seccion_2:
                        fragmento = new FragmentoCrudAsignatura();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.contenedor,fragmento);
                        ft.commit();
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.menu_seccion_3:
                        fragmento = new FragmentoCrudMatricula();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.contenedor,fragmento);
                        ft.commit();
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                }
                return true;
            }
        });


    }
}


    //metodo para obtener un Alumno
   /* private void obtenerAlumnoDni() {

        Call<Alumno> call = service.getAlumnosDni(dni.getText().toString());
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
    }*/


    /*private void obtenerAlumnos() {
        Call<Alumno[]> call = service.getAlumnos();
        call.enqueue(new Callback<Alumno[]>() {
            @Override
            public void onResponse(Call<Alumno[]> call, Response<Alumno[]> response) {
                Alumno[] Alumnos = response.body();
                texto.setText("");
                for (Alumno Alumno : Alumnos) {
                    texto.append(Alumno + "\n\n");
                    Log.v("HelloWorld", Alumno.toString());
                }

            }

            @Override
            public void onFailure(Call<Alumno[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }


    private void crearAlumno() {
        Call<Respuesta> call = service.postAlumnos(new Alumno("26053371Y", "David", "Martinez Tuñon"));
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta != null) {
                    texto.setText(respuesta.toString());
                    Log.v("HelloWorld", respuesta.toString());
                } else {
                    texto.setText("se ha producido un error al insertar el Alumno en la base de datos");
                    Log.v("HelloWorld", "se ha producido un error al insertar el Alumno en la base de datos");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

    private void borrarAlumnoDni() {
        Call<Respuesta> call = service.deleteAlumnos("26053371Y");
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                texto.setText(respuesta.toString());
                Log.v("HelloWorld", respuesta.toString());
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

    private void actualizarAlumno() {
        Call<Respuesta> call = service.putAlumnoDni(new Alumno("26053371Y", "Pepe", "Martinez Tuñon"),"26053371Y");
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                texto.setText(respuesta.toString());
                Log.v("HelloWorld", respuesta.toString());

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

    public void obtenerAlumnos(View view) {
        obtenerAlumnos();
        //startActivity(intent);
    }

    public void obtenerAlumno(View view) {

        obtenerAlumnoDni();
    }

    public void crearAlumno(View view) {
        crearAlumno();
    }

    public void borrarAlumno(View view) {
        borrarAlumnoDni();
    }

    public void actualizarAlumno(View view) {
        actualizarAlumno();
    }
*/
