package cursoandroid.practicasandroid.providers;

import android.content.Context;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import cursoandroid.practicasandroid.R;

// Para acceder a la BD es necesario extender esta clase de la SQLLITEOPENHELPER
// es neceario extender oncreate onupgrade
public class TareasDBHelper extends SQLiteOpenHelper {

    private Context context;

    public TareasDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // al arrancar por primera vez comprueba si la bd con ese nombre y de esa versión existe llamando
        // al oncreate

        // si se cambia de versión de una X a Y se ejecuta el método onupgrade

        // el context es el id de la app donde estarán los datos (su zona privada)
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ejecuta lo que no sea una SELECT
        //db.execSQL();
        // lo misom que el anterior pero con parámetros en un array
        //db.execSQL();

        // necesitamos cargar el recurso db_tareas.sqll a través del context
        InputStream FicheroSQL =  null;
        StringBuilder TextoFicheroSQL = new StringBuilder();

        try {
            FicheroSQL = context.getResources().openRawResource(R.raw.db_tareas);
            Scanner LeerFicheroSQL = new Scanner(FicheroSQL, "UTF-8");

            while (LeerFicheroSQL.hasNextLine()) {
                TextoFicheroSQL.append(LeerFicheroSQL.nextLine());
            }
            db.execSQL(TextoFicheroSQL.toString());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (FicheroSQL != null) {
                    FicheroSQL.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // si se cambia de versión de una X a Y se ejecuta el método onupgrade

    }
}
