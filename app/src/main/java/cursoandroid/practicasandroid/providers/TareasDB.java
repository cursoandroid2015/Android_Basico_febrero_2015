package cursoandroid.practicasandroid.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TareasDB {
    // tipo de dato para acceder a la DB
    private SQLiteDatabase db;

    // nuestra clase
    private TareasDBHelper dbHelper;

    public TareasDB(Context ctx) {
        // Contexto de la aplicación donde se almacenará la DB
        // ctx

        // Genera un fichero tareas.db
        dbHelper = new TareasDBHelper(ctx, "tareas", null, 1);
    }

    public void AbrirDB () {
        // Si no existe llamará a nuestro dbHelper.onCreate
        this.db = dbHelper.getWritableDatabase();
    }

    public void CerrarDB() {
        if (db != null) {
            dbHelper.close();
        }
    }

    public void altaTarea( String titulo, String descripcion) {
        ContentValues nuevaTarea = new ContentValues();
        nuevaTarea.put("TITULO",titulo);
        nuevaTarea.put("DESCRIPCION",descripcion);
        nuevaTarea.put("FECHA",System.currentTimeMillis());

        db.insert("TAREAS", null, nuevaTarea);
    }

    public Cursor LeerTareas() {
        // Como es una SELECT no se puede utilizar execsql

        // si son todas la columnas, ponemos null
        return db.query("TAREAS", null, null, null, null, null, null, null);
    }
}
