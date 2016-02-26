package cursoandroid.practicasandroid.servicios;

/**
 * Created by Arranque 1 on 26/02/2016.
 */
public interface MensajeriaServicioVinculadoRPCBinderInterface {
    void enviarMensaje(String idDestino, String mensaje);

    // Comunicación del servicio hacia la actividad que se registre
    // Sólo permito un cliente
    void registrarCliente(MensajeriaRPCCallback callback);

    void quitarCliente();

    void testEnviarMensaje(String mensaje);
}
