package cursoandroid.practicasandroid.actividades;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.servicios.MensajeriaRPCCallback;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioMSG;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioMSG_Mensaje;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioVinculadoRPC;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioVinculadoRPCBinderInterface;

// Para probar tanto el servicio de mensajería por RPC o sin el
public class ServicioMensajeriaVinculadoActivity extends AppCompatActivity implements View.OnClickListener, MensajeriaRPCCallback {

    public static final int ENVIAR_MENSAJE_SERVICIO_TO_ACTIVIDAD = 1000;
    ////////////////////////////////////////////////////////////////////////////
    // msg -> mensajero de la actividad para que el servicio se conecte con la actividad para enviar mensajes a la servicio->actividad
    private Messenger mensajeroDeLaActividad;
    // rpc
    private Button btn_serv_bind_rpc_link;

    ////////////////////////////////////////////////////////////////////////////
    private Button btn_serv_bind_rpc_unlink;
    private Button btn_serv_bind_rpc_send;
    private Button btn_serv_bind_rpc_receive_callback;
    // msg
    private Button btn_serv_bind_msg_link;
    private Button btn_serv_bind_msg_unlink;
    private Button btn_serv_bind_msg_send;
    private Button btn_serv_bind_msg_receive_callback;
    // rpc
    private MensajeriaServicioVinculadoRPCBinderInterface ServiceMensajeria;
    private boolean ServiceMensajeria_OK = true;
    // msg -> mensajero del servicio para que la actividad se conecte con el servicio para enviar mensajes a la actividad->servicio
    private Messenger mensajeroDelServicio;
    // rpc y msg
    // Interface de callback para que al intentar conectarnos nos devuelva el tipo ibinder
    private ServiceConnection ServiceConnectionCallBack = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // preguntamos quien hace la llamada
            if (name.getClassName().equals(MensajeriaServicioVinculadoRPC.class.getName())) {
                // rpc
                ServiceMensajeria = (MensajeriaServicioVinculadoRPCBinderInterface) service;
                ServiceMensajeria.registrarCliente(ServicioMensajeriaVinculadoActivity.this);
            } else if (name.getClassName().equals(MensajeriaServicioMSG.class.getName())) {
                // msg
                mensajeroDelServicio = new Messenger(service);

                // REGISTRAR_CLIENTE_MSG = 101
                Message msgRegistro = Message.obtain(null, 101);
                msgRegistro.replyTo = mensajeroDeLaActividad;
                try {
                    mensajeroDeLaActividad.send(msgRegistro);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // preguntamos quien hace la llamada
            if (name.getClassName().equals(MensajeriaServicioVinculadoRPC.class.getName())) {
                // rpc
                // Solo llamado si ocurre algún problema con el servicio,
                // no como confirmación de unbindservice
                ServiceMensajeria_OK = false;
            } else if (name.getClassName().equals(MensajeriaServicioMSG.class.getName())) {
                // msg
                mensajeroDelServicio = null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_mensajeria_vinculado);

        // MSG
        // en el onServiceConnected me registraré
        mensajeroDeLaActividad = new Messenger(new ProcesarMensajesDeLaActividad());

        // rpc
        btn_serv_bind_rpc_link = (Button) findViewById(R.id.btn_serv_bind_rpc_link);
        btn_serv_bind_rpc_unlink = (Button) findViewById(R.id.btn_serv_bind_rpc_unlink);
        btn_serv_bind_rpc_send = (Button) findViewById(R.id.btn_serv_bind_rpc_send);
        btn_serv_bind_rpc_receive_callback = (Button) findViewById(R.id.btn_serv_bind_rpc_receive_callback);

        // rpc
        btn_serv_bind_rpc_link.setOnClickListener(this);
        btn_serv_bind_rpc_unlink.setOnClickListener(this);
        btn_serv_bind_rpc_send.setOnClickListener(this);
        btn_serv_bind_rpc_receive_callback.setOnClickListener(this);

        // msg
        btn_serv_bind_msg_link = (Button) findViewById(R.id.btn_serv_bind_msg_link);
        btn_serv_bind_msg_unlink = (Button) findViewById(R.id.btn_serv_bind_msg_unlink);
        btn_serv_bind_msg_send = (Button) findViewById(R.id.btn_serv_bind_msg_send);
        btn_serv_bind_msg_receive_callback = (Button) findViewById(R.id.btn_serv_bind_msg_receive_callback);

        // msg
        btn_serv_bind_msg_link.setOnClickListener(this);
        btn_serv_bind_msg_unlink.setOnClickListener(this);
        btn_serv_bind_msg_send.setOnClickListener(this);
        btn_serv_bind_msg_receive_callback.setOnClickListener(this);

    }

    // rpc
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // rpc /////////////////////////////////////////////////////////////////////////////////
            case R.id.btn_serv_bind_rpc_link:
                if (ServiceMensajeria == null) {
                    // El cliente se conecta al servicio "vinculado"

                    // Intent vinculado al servicio que hemos creado como clase SERVICE
                    Intent lanzarServicio = new Intent(this, MensajeriaServicioVinculadoRPC.class);
                    bindService(
                            lanzarServicio, // Servicio al que conectarnos
                            ServiceConnectionCallBack, // Metodo de callback
                            BIND_AUTO_CREATE); // Si no está creado ya lo crea
                }
                break;
            case R.id.btn_serv_bind_rpc_unlink:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    unbindService(ServiceConnectionCallBack);
                    ServiceMensajeria = null;
                }
                break;
            case R.id.btn_serv_bind_rpc_send:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    ServiceMensajeria.enviarMensaje("678xxx000_RPC", "Enviando mensaje");
                }

                break;
            case R.id.btn_serv_bind_rpc_receive_callback:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    ServiceMensajeria.testEnviarMensaje("RPC: prueba de callback ");
                }
                break;
            // msg /////////////////////////////////////////////////////////////////////////////////
            case R.id.btn_serv_bind_msg_link:
                if (mensajeroDelServicio == null) {
                    Intent lanzarServicioMSG = new Intent(this, MensajeriaServicioMSG.class);
                    bindService(lanzarServicioMSG, ServiceConnectionCallBack, BIND_AUTO_CREATE);
                }
                break;
            case R.id.btn_serv_bind_msg_unlink:
                if (mensajeroDelServicio != null) {
                    unbindService(ServiceConnectionCallBack);
                    mensajeroDelServicio = null;
                }
                break;
            case R.id.btn_serv_bind_msg_send:
                if (mensajeroDelServicio != null) {
                    // esta clase no se puede instanciar por definición
                    Message Mensaje = Message.obtain(
                            null,
                            MensajeriaServicioMSG.ENVIAR_MENSAJE_ACTIVIDAD_TO_SERVICIO,
                            new MensajeriaServicioMSG_Mensaje("678xxx000_MSG", "Enviando mensaje")
                    );

                    // msg (messenger )es asíncronoa
                    // rpc si es bloqueante
                    try {
                        mensajeroDelServicio.send(Mensaje);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_serv_bind_msg_receive_callback:
                if (mensajeroDelServicio != null) {
                    // esta clase no se puede instanciar por definición
                    // ENVIAR_MENSAJE_ACTIVIDAD_TO_CLIENT = 200;
                    Message Mensaje = Message.obtain(
                            null,
                            200,
                            "Enviando mensaje MSG prueba"
                    );
                    Toast.makeText(this, "aqui llega", Toast.LENGTH_LONG).show();
                    // msg (messenger )es asíncronoa
                    // rpc si es bloqueante
                    try {
                        Toast.makeText(this, "aqui llega y aqui", Toast.LENGTH_LONG).show();
                        mensajeroDelServicio.send(Mensaje);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }

    // rpc
    @Override
    public void mensajeNuevo(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public class ProcesarMensajesDeLaActividad extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case ENVIAR_MENSAJE_SERVICIO_TO_ACTIVIDAD:
                    String textoMensaje = (String) msg.obj;
                    Toast.makeText(
                            ServicioMensajeriaVinculadoActivity.this,
                            "Mensaje Nuevo MSG: " + textoMensaje,
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

            }
        }
    }
}
