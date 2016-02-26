package cursoandroid.practicasandroid.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cursoandroid.practicasandroid.R;

// Para probar tanto el servicio de mensajería por RPC o sin el
public class ServicioMensajeriaVinculadoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_serv_bind_rpc_link;
    private Button btn_serv_bind_rpc_unlink;
    private Button btn_serv_bind_rpc_send;
    private Button btn_serv_bind_rpc_receive_callback;

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
                break;
            case R.id.btn_serv_bind_rpc_unlink:
                break;
            case R.id.btn_serv_bind_rpc_send:
                break;
            case R.id.btn_serv_bind_rpc_receive_callback:
                break;
        }
    }
}
