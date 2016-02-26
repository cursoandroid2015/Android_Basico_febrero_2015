package cursoandroid.practicasandroid.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MensajeriaServicioMSG extends Service {

    public static final int ENVIAR_MENSAJE = 100;

    // Esto se construye en el oncreate y se pasa en el onbind
    private Messenger mensajeroDelServicio;

    public MensajeriaServicioMSG() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        procesarMensajesDelServicio procesar = new procesarMensajesDelServicio();
        mensajeroDelServicio = new Messenger(procesar);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mensajeroDelServicio.getBinder();
    }

    private class procesarMensajesDelServicio extends Handler {

        // Sobreescrito de la clase handler
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // identificador del mensaje, CODIGO DEL MENSAJE
            switch (msg.what) {
                case ENVIAR_MENSAJE:
                    // con obj se puede transportar el mensaje
                    // Creamos una clase auxiliar para transportar los datos
                    MensajeriaServicioMSG_Mensaje mensaje = (MensajeriaServicioMSG_Mensaje) msg.obj;
                    Toast.makeText(
                            MensajeriaServicioMSG.this,
                            String.format(
                                    "MensajeriaServicioMSG: D: %s - M: %s",
                                    mensaje.getIdDestino(),
                                    mensaje.getSMS()
                            ),
                            Toast.LENGTH_LONG
                    ).show();
                    break;
                case 200:
                    break;
            }


        }
    }
}
