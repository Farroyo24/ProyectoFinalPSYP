package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Pedro on 17/02/2017.
 */

public class FragmentoCrudAsignatura extends Fragment{
    private Button botonCreate;
    private Button botonRead;
    private Button botonUpdate;
    private Button botonDelete;
    private Button botonLista;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_crud_asignatura, container, false);

        botonCreate = (Button) view.findViewById(R.id.crudAsignaturaCreate_boton);
        botonRead = (Button) view.findViewById(R.id.crudAsignaturaRead_boton);
        botonLista = (Button) view.findViewById(R.id.crudAsignaturaList_boton);
        botonUpdate = (Button) view.findViewById(R.id.crudAsignaturaUpdate_boton);
        botonDelete = (Button) view.findViewById(R.id.crudAsignaturaDelete_boton);

        botonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoAsignaturaCreate()).commit();
            }
        });

        botonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoObtenerAsignatura()).commit();
            }
        });

        botonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoPutAsignatura()).commit();
            }
        });

        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoDeleteAsignatura()).commit();
            }
        });
        botonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoListadoAsignaturas()).commit();
            }
        });
        return  view;
    }
}
