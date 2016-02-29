package cursoandroid.practicasandroid.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Para acceder a la BD es necesario extender esta clase de la SQLLITEOPENHELPER
// es neceario extender oncreate onupgrade
public class TareasDBHelper extends SQLiteOpenHelper {
    // el context es el id de la app donde estarán los datos (su zona privada)
    public TareasDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // al arrancar por primera vez comprueba si la bd con ese nombre y de esa versión existe llamando
        // al oncreate
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ejecuta lo que no sea una SELECT
        //db.execSQL();
        // lo misom que el anterior pero con parámetros en un array
        //db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
