package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentoCrudAlumno extends Fragment {

    private Button botonList;
    private Button botonCreate;
    private Button botonRead;
    private Button botonUpdate;
    private Button botonDelete;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_crud_alumno, container, false);

        botonList = (Button) view.findViewById(R.id.crudAlumnoList_boton);
        botonCreate = (Button) view.findViewById(R.id.crudAlumnoCreate_boton);
        botonRead = (Button) view.findViewById(R.id.crudAlumnoRead_boton);
        botonUpdate = (Button) view.findViewById(R.id.crudAlumnoUpdate_boton);
        botonDelete = (Button) view.findViewById(R.id.crudAlumnoDelete_boton);

        botonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoListadoAlumnos()).commit();

            }
        });

        botonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoAlumnoCreate()).commit();
            }
        });

        botonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoObtenerAlumno()).commit();
            }
        });

        botonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoPutAlumno()).commit();
            }
        });

        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoAlumnoDelete()).commit();
            }
        });

        return  view;
    }
}
