package cursoandroid.practicasandroid.actividades;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.servicios.MensajeriaRPCCallback;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioVinculadoRPC;
import cursoandroid.practicasandroid.servicios.MensajeriaServicioVinculadoRPCBinderInterface;

// Para probar tanto el servicio de mensajería por RPC o sin el
public class ServicioMensajeriaVinculadoActivity extends AppCompatActivity implements View.OnClickListener, MensajeriaRPCCallback {

    private Button btn_serv_bind_rpc_link;
    private Button btn_serv_bind_rpc_unlink;
    private Button btn_serv_bind_rpc_send;
    private Button btn_serv_bind_rpc_receive_callback;

    private MensajeriaServicioVinculadoRPCBinderInterface ServiceMensajeria;
    private boolean ServiceMensajeria_OK = true;

    // Interface de callback para que al intentar conectarnos nos devuelva el tipo ibinder
    private ServiceConnection ServiceConnectionCallBack = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServiceMensajeria = (MensajeriaServicioVinculadoRPCBinderInterface) service;
            ServiceMensajeria.registrarCliente(ServicioMensajeriaVinculadoActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Solo llamado si ocurre algún problema con el servicio,
            // no como confirmación de unbindservice
            ServiceMensajeria_OK = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_mensajeria_vinculado);

        btn_serv_bind_rpc_link = (Button) findViewById(R.id.btn_serv_bind_rpc_link);
        btn_serv_bind_rpc_unlink = (Button) findViewById(R.id.btn_serv_bind_rpc_unlink);
        btn_serv_bind_rpc_send = (Button) findViewById(R.id.btn_serv_bind_rpc_send);
        btn_serv_bind_rpc_receive_callback = (Button) findViewById(R.id.btn_serv_bind_rpc_receive_callback);

        btn_serv_bind_rpc_link.setOnClickListener(this);
        btn_serv_bind_rpc_unlink.setOnClickListener(this);
        btn_serv_bind_rpc_send.setOnClickListener(this);
        btn_serv_bind_rpc_receive_callback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_serv_bind_rpc_link:
                // El cliente se conecta al servicio "vinculado"

                // Intent vinculado al servicio que hemos creado como clase SERVICE
                Intent lanzarServicio = new Intent(this, MensajeriaServicioVinculadoRPC.class);
                bindService(
                        lanzarServicio, // Servicio al que conectarnos
                        ServiceConnectionCallBack, // Metodo de callback
                        BIND_AUTO_CREATE); // Si no está creado ya lo crea
                break;
            case R.id.btn_serv_bind_rpc_unlink:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    unbindService(ServiceConnectionCallBack);
                    ServiceMensajeria = null;
                }
                break;
            case R.id.btn_serv_bind_rpc_send:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    ServiceMensajeria.enviarMensaje("678xxx000", "Enviando mensaje");
                }

                break;
            case R.id.btn_serv_bind_rpc_receive_callback:
                if (ServiceMensajeria_OK && ServiceMensajeria != null) {
                    ServiceMensajeria.testEnviarMensaje("prueba de callback");
                }
                break;
        }
    }

    @Override
    public void mensajeNuevo(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

    }
}
