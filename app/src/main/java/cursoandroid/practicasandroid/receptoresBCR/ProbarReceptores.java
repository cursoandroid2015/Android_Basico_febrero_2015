package cursoandroid.practicasandroid.receptoresBCR;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Arranque 1 on 25/02/2016.
 */

// Esta clase indica que hacemos cuando se generan estos eventos
// modo avion
// personal cursoandroid.SINCRONIZACION_TERMINADA

// No es un contexto de aplicación, por eso me pasan un context
public class ProbarReceptores extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        // Escucho por el mensaje de broadcast MODO AVION
        if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            // Capturamos el evento de Modo Avioon

            boolean state = intent.getBooleanExtra("state", false);
            Toast.makeText(context, "SYSTEM: Modo Avión cambiado: " + state, Toast.LENGTH_LONG).show();

        } else if (action.equals("cursoandroid.SINCRONIZACION_TERMINADA")) {
            // Capturamos un evento que generamos nosotros
            // Escucho por el mensaje de broadcast cursoandroid.SINCRONIZACION_TERMINADA
            String state = intent.getStringExtra("state");
            Toast.makeText(context, "PROPIETARIO:cursoandroid.SINCRONIZACION_TERMINADA: " + state, Toast.LENGTH_LONG).show();


        }
    }
}
