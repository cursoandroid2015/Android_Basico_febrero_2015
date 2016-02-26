package cursoandroid.practicasandroid.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

public class MensajeriaServicioMSG extends Service {

    public static final int ENVIAR_MENSAJE_ACTIVIDAD_TO_SERVICIO = 100;
    public static final int REGISTRAR_CLIENTE_MSG = 101;
    public static final int ENVIAR_MENSAJE_ACTIVIDAD_TO_CLIENT = 200;

    // Esto se construye en el oncreate y se pasa en el onbind
    // rpc
    private Messenger mensajeroDelServicio;

    // msg
    private Messenger mensajero_replyTo_Client;

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
                // rpc
                case ENVIAR_MENSAJE_ACTIVIDAD_TO_SERVICIO:
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
                // msg
                case REGISTRAR_CLIENTE_MSG:
                    // no es mensajero del servicio porque no sabemos quien se conecta a la cola
                    // de mensajes de la actividad
                    mensajero_replyTo_Client = msg.replyTo;

                    break;
                case ENVIAR_MENSAJE_ACTIVIDAD_TO_CLIENT:
                    String cadena = (String) msg.obj;

                    //ENVIAR_MENSAJE_SERVICIO_TO_ACTIVIDAD = 1000
                    Message msgDeVuelta = Message.obtain(null, 1000, cadena);

                    if (mensajero_replyTo_Client != null) {
                        // este mensaje se envia a través del mensajero que previamente ha pasado por el case 101 para registrarse
                        try {
                            mensajero_replyTo_Client.send(msgDeVuelta);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

            }


        }
    }
}
