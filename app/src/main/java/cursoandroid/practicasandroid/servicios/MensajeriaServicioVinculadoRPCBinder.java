package cursoandroid.practicasandroid.servicios;

import android.content.Context;
import android.os.Binder;
import android.widget.Toast;

/**
 * Created by Arranque 1 on 26/02/2016.
 */
// No implementamos iBinder, sino extendemos de Binder
// Creamos una interface para esta clase y por si algun días cambiamos esta clase el interface no cambia
// Refractor -> Extract -> Interface -> cambiar nombre, aceptar
public class MensajeriaServicioVinculadoRPCBinder extends Binder implements MensajeriaServicioVinculadoRPCBinderInterface {

    private Context ctx;
    private MensajeriaRPCCallback callback;

    // Creamos el constructor
    public MensajeriaServicioVinculadoRPCBinder(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void enviarMensaje(String idDestino, String mensaje) {

        // No hay contexto
        Toast.makeText(
                this.ctx,
                String.format("MensajeriaServicioVinculadoRPCBinder - SEND: D:%s - M: %s", idDestino, mensaje),
                Toast.LENGTH_LONG
        ).show();
    }

    // Comunicación del servicio hacia la actividad que se registre
    // Sólo permito un cliente
    @Override
    public void registrarCliente(MensajeriaRPCCallback callback) {
        this.callback = callback;
    }

    @Override
    public void quitarCliente() {
        this.callback = null;
    }

    @Override
    public void testEnviarMensaje(String mensaje) {

        // Prueba de envio de un mensaje
        if (this.callback != null) {
            this.callback.mensajeNuevo(mensaje);
            // Para hacer la prueba el mensaje que envío se lo devuelvo,
            // con eso veo que funciona
        }
    }
}
