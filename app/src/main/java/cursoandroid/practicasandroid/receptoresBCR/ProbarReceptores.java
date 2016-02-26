package cursoandroid.practicasandroid.receptoresBCR;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Arranque 1 on 25/02/2016.
 */
public class ProbarReceptores extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            // Capturamos el evento de Modo Avioon

            boolean state = intent.getBooleanExtra("state", false);
            Toast.makeText(context, "SYSTEM: Modo Avión cambiado: " + state, Toast.LENGTH_LONG);

        } else if (action.equals("cursoandroid.SINCRONIZACION_TERMINADA")) {
            // Capturamos un evento que generamos nosotros
            String state = intent.getStringExtra("state");
            Toast.makeText(context, "PROPIETARIO: Modo Avión cambiado: " + state, Toast.LENGTH_LONG);

        }
    }
}
