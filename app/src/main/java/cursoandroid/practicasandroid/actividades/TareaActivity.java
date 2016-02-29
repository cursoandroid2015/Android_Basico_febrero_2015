package cursoandroid.practicasandroid.actividades;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class TareaActivity extends ListActivity {

    public static final String ID_PROVIDER = "cursoandroid.practicasandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No se puede instanciar, hay que pasar por el método parse
        Uri UriConsultaDB = Uri.parse("content://" + ID_PROVIDER + "/tareas");

        // Accedemos al content provider, es decir a la BD en nuestro caso
        ContentResolver contenidoDB = getContentResolver();
        Cursor datos = contenidoDB.query(UriConsultaDB, null, null, null, null);
        //datos.getCount();

        setListAdapter(null);

    }
}
