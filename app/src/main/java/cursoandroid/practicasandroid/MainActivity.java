package cursoandroid.practicasandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import cursoandroid.practicasandroid.actividades.ASyncTaskBrowserActivity;
import cursoandroid.practicasandroid.actividades.CicloDeVidaActivity;
import cursoandroid.practicasandroid.actividades.DialogActivity;
import cursoandroid.practicasandroid.actividades.EmpleoHilosActivity;
import cursoandroid.practicasandroid.actividades.ListSimpleActivity;
import cursoandroid.practicasandroid.actividades.PreferenciasActivity;
import cursoandroid.practicasandroid.actividades.ProbarReceptoresActivity;
import cursoandroid.practicasandroid.actividades.ProbarRecursosParametrizadosActivity;
import cursoandroid.practicasandroid.actividades.ProbarServicioActivity;
import cursoandroid.practicasandroid.actividades.ProbarSincronizarActivity;
import cursoandroid.practicasandroid.actividades.ServicioMensajeriaVinculadoActivity;

public class MainActivity extends AppCompatActivity {

    private Integer ACTIVIDAD_PASO_DE_PARAMETROS = 1000;
    private Button btn_preferencias_reset;
    private Button btn_preferencias_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_preferencias_reset = (Button) findViewById(R.id.btn_preferencias_reset);
        btn_preferencias_read = (Button) findViewById(R.id.btn_preferencias_read);

        btn_preferencias_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_preferencias_reset_action(v);
            }
        });

        btn_preferencias_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_preferencias_read_action(v);
            }
        });
    }

    private void btn_preferencias_read_action(View v) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String nombre = pref.getString("nombre", "defaultnombre");
        String password = pref.getString("password", "defaultpassword");

        boolean depuracion = pref.getBoolean("depuracion", false);
        boolean auditoria = pref.getBoolean("auditoria", false);

        String tipoConexion = pref.getString("tipoConexion", "tipoConexionNone");

        Set<String> conexionesActivas = pref.getStringSet("ConexionesActivas", new HashSet<String>() {
        });

        Toast.makeText(this, String.format("Nombre: %s\nPassword: %s\nDepuracion: %s\nAuditoria: %s\nTipoConexion: %s\nConexionesActivas: %s",
                nombre,
                password,
                depuracion,
                auditoria,
                tipoConexion,
                conexionesActivas.toString()
        ), Toast.LENGTH_LONG).show();
    }

    private void btn_preferencias_reset_action(View v) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("nombre", "1111");
        editor.putBoolean("depuracion", true);

        HashSet<String> valores = new HashSet<>();
        valores.add("x1");
        valores.add("x2");
        editor.putStringSet("ConexionesActivas", valores);

        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater MenuInicio = getMenuInflater();
        MenuInicio.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemID = item.getItemId();
        Intent MessageLaunchActivity;

        switch (itemID) {
            case R.id.main_menu_LaunchCiclo:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, CicloDeVidaActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LaunchPaso:
                // Llamada a través del nombre y la categoría
                // y paso de parámetros
                MessageLaunchActivity = new Intent("cursoandroid.practicasandroid.PASODEPARAMETROS");
                MessageLaunchActivity.addCategory(Intent.CATEGORY_DEFAULT);
                MessageLaunchActivity.putExtra("nombre", "hola");
                MessageLaunchActivity.putExtra("clave", "adios");
                // 1000 identifica la llamada a la nueva actividad, cuando regrese, el 1000 nos servirá para
                // saber quien ha hecho la llamada
                startActivityForResult(MessageLaunchActivity, ACTIVIDAD_PASO_DE_PARAMETROS);
                break;
            case R.id.main_menu_LaunchReco:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ListSimpleActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaThreadNoHilo:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, EmpleoHilosActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaHHTP:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ASyncTaskBrowserActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaAlert:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, DialogActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaPreferencias:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, PreferenciasActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaParametrizable:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ProbarRecursosParametrizadosActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaServicio:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ProbarServicioActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaIntentSincroServicio:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ProbarSincronizarActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaIntentProbarReceptores:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ProbarReceptoresActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaIntentProbarMensajeria:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ServicioMensajeriaVinculadoActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // este es un punto central de retorno de todas las actividades que se han llamado
        // requestCode -> actividad que ha terminado en nuestro caso tenemos identificado la 1000
        if (requestCode == ACTIVIDAD_PASO_DE_PARAMETROS) {
            if (resultCode == RESULT_OK) {
                String nombre = data.getStringExtra("nombre");
                String clave = data.getStringExtra("clave");
                String msg = String.format("Nombre: %s - Clave: %s", nombre, clave);
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Salida: RESULT_CANCEL", Toast.LENGTH_SHORT).show();
            }
        } else {
            // si se ha llamado con startActivityForResult
            Toast.makeText(this, "finish: otra actividad", Toast.LENGTH_SHORT).show();
        }
    }
}
