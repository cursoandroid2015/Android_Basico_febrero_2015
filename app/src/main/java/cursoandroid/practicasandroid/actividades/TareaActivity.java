package cursoandroid.practicasandroid.actividades;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class TareaActivity extends ListActivity {

    public static final String ID_PROVIDER = "cursoandroid.practicasandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No se puede instanciar, hay que pasar por el método parse
        Uri UriConsultaDB = Uri.parse("content://" + ID_PROVIDER + "/tareas");

        // Accedemos al content provider, es decir a la BD en nuestro caso
        ContentResolver contenidoDB = getContentResolver();

        // En el cursor tengo el cursor con la consulta
        Cursor datos = contenidoDB.query(UriConsultaDB, null, null, null, null);

        // Array de Tareas tipo TareaDAO, es decir cada elemento de la consulta se guardará en un
        // tipo de datos JAVA. Este tipo de datos se podrá serializar
        ArrayList<TareaDAO> listaTareas = new ArrayList<TareaDAO>();

        // empezamos a menejar el cursor
        startManagingCursor(datos);

        // Rellenamos el arrayList con los datos del cursor
        if (datos.moveToFirst()) {
            do {
                TareaDAO t = new TareaDAO(
                        datos.getInt(datos.getColumnIndex   ("ID_TAREA")),
                        datos.getString(datos.getColumnIndex("TITULO")),
                        datos.getString(datos.getColumnIndex("DESCRIPCION")),
                        datos.getLong(datos.getColumnIndex  ("FECHA"))
                        );
                listaTareas.add(t);
            } while (datos.moveToNext());
        }

        // dejamos de menejar el cursor
        stopManagingCursor(datos);

        // Creamos el adaptador con el contenido del cursor
        TareaListAdapterActivity adaptador = new TareaListAdapterActivity(getApplicationContext(), listaTareas);

        // Le pasamos el arrayList para que "poble" la ListActivity
        setListAdapter(adaptador);

    }
}
