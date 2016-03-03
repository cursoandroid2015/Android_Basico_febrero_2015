package cursoandroid.practicasandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import cursoandroid.practicasandroid.actividades.ASyncTaskBrowserActivity;
import cursoandroid.practicasandroid.actividades.ConectividadActivity;
import cursoandroid.practicasandroid.actividades.ContenidoWebActivity;
import cursoandroid.practicasandroid.actividades.FakeGPSActivity;
import cursoandroid.practicasandroid.actividades.FragmentosActivity;
import cursoandroid.practicasandroid.actividades.PosicionamientoActivity;
import cursoandroid.practicasandroid.actividades.ProbarPreferencias;
import cursoandroid.practicasandroid.actividades.TareaAltaActivity;
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
import cursoandroid.practicasandroid.actividades.TareaConsultaActivity;
import cursoandroid.practicasandroid.actividades.TelefoniaActivity;

public class MainActivity extends AppCompatActivity {

    private Integer ACTIVIDAD_PASO_DE_PARAMETROS = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
            case R.id.main_menu_LanzaModifPreferencias:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ProbarPreferencias.class);
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
            case R.id.main_menu_LanzaAltaTareasContentProvider:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, TareaAltaActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaConsultarTareasaContentProvider:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, TareaConsultaActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaPosicionamiento:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, PosicionamientoActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaFakeGPS:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, FakeGPSActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaTelefonia:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, TelefoniaActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaConectividad:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ConectividadActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaFragmentos:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, FragmentosActivity.class);
                startActivity(MessageLaunchActivity);
                break;
            case R.id.main_menu_LanzaFullScreenWebView:
                // Llamada través de la clase
                MessageLaunchActivity = new Intent(this, ContenidoWebActivity.class);
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
