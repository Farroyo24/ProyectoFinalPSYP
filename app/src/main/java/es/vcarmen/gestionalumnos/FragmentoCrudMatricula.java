package es.vcarmen.gestionalumnos;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentoCrudMatricula extends Fragment {
    private Button botonList;
    private Button botonCreate;
    private Button botonRead;
    private Button botonUpdate;
    private Button botonDelete;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_crud_matricula, container, false);

        botonList = (Button) view.findViewById(R.id.crudMatriculaList_boton);
        botonCreate = (Button) view.findViewById(R.id.crudMatriculaCreate_boton);
        botonRead = (Button) view.findViewById(R.id.crudMatriculaRead_boton);
        botonUpdate = (Button) view.findViewById(R.id.crudMatriculaUpdate_boton);
        botonDelete = (Button) view.findViewById(R.id.crudMatriculaDelete_boton);

        botonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoListadoMatriculas()).commit();

            }
        });

        botonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoMatriculaCreate()).commit();
            }
        });

        botonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoObtenerMatricula()).commit();
            }
        });

        botonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getFragmentManager().beginTransaction().replace(R.id.contenedor,new FragmentoMatriculaDelete()).commit();
            }
        });

        return  view;
    }
}
