package cursoandroid.practicasandroid.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

// proveedor de contenido para que la gente externa pueda acceder a nuetro TareasDB -> TareasDBHelper -> Base de Datos sqlite

// uri:
// content://id/tareas/....
// funciona como un proceso restful


public class TareasContentProvider extends ContentProvider {

    // URI = content://cursoandroid.practicasandro/tareas --> CODIGO RESULTANTE EL 100
    public static final int EXTRAE_TAREA_ALL = 100;
    public static final int EXTRAE_TAREA_X = 101;
    public static final int EXTRAE_TAREA_TITULO_X = 102;
    public static final int EXTRAE_TAREA_ENTRE_FECHAS = 103;
    private TareasDB db;

    // este es mi ID de PROVEEDOR que utilizará quien se quiera conectar
    public static final String ID_PROVIDER = "cursoandroid.practicasandroid";

    // para hacer la comparación de URIs
    private UriMatcher UriComparador;

    @Override
    public boolean onCreate() {

        // Si puedo acceder al contexto desde donde se ejecuta el Content Provider
        db = new TareasDB(this.getContext().getApplicationContext());

        // Montamos el comparador de URIs
        // Si no coincide con ninguna URI el comparador devuelve lo que se le pasa por parámetro
        // al constructor NO_MATCH
        UriComparador = new UriMatcher(UriMatcher.NO_MATCH);

        db.AbrirDB();

        // Sabremos lo que quiere hacer al llamar al QUERY INSERT DELETE UPDATE de esta clase
        // URI = content://cursoandroid.practicasandro/tareas --> CODIGO RESULTANTE EL 100
        UriComparador.addURI(ID_PROVIDER, "tareas", EXTRAE_TAREA_ALL);

        // El caracter # indica "dame la tarea número X"
        UriComparador.addURI(ID_PROVIDER, "tareas/#", EXTRAE_TAREA_X);

        // Dame todos los titulos que coincidan con la cadena X
        UriComparador.addURI(ID_PROVIDER, "tareas/titulo/*", EXTRAE_TAREA_TITULO_X);

        // Dame la tarea entre la fecha X y Y"
        UriComparador.addURI(ID_PROVIDER, "tareas/fecha/#/#", EXTRAE_TAREA_ENTRE_FECHAS);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // al leer un cursor la db debe estar abierta
        switch (UriComparador.match(uri)) {
            case EXTRAE_TAREA_ALL:
                return db.LeerTareas();
            case EXTRAE_TAREA_X:
                break;
            case EXTRAE_TAREA_TITULO_X:
                break;
            case EXTRAE_TAREA_ENTRE_FECHAS:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Uri no reconocida");
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
