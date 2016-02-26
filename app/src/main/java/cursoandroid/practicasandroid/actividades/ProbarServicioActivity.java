package cursoandroid.practicasandroid.actividades;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.servicios.MonitorDeviceService;

public class ProbarServicioActivity extends AppCompatActivity implements Runnable {

    Intent mensajeLanzaServicio;
    private Button btn_arrancar;
    private Button btn_parar;
    private Boolean continuarLeyendo = true;
    private Thread hiloLectura;
    private Handler handler = new Handler();
    private TextView txt_datagrama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar_servicio);

        btn_arrancar = (Button) findViewById(R.id.btn_arrancar_servicio);
        btn_parar = (Button) findViewById(R.id.btn_parar_servicio);
        txt_datagrama = (TextView) findViewById(R.id.txt_datagrama);

        btn_arrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_arrancar_Servicio(v);
            }
        });

        btn_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_parar_Servicio(v);
            }
        });
    }

    private void btn_arrancar_Servicio(View v) {

        mensajeLanzaServicio = new Intent(this, MonitorDeviceService.class);
        mensajeLanzaServicio.putExtra("timeout", 5000);

        continuarLeyendo = true;
        hiloLectura = new Thread(this);
        hiloLectura.start();

        startService(mensajeLanzaServicio);

        btn_arrancar.setEnabled(false);
        btn_parar.setEnabled(true);
    }

    private void btn_parar_Servicio(View v) {

        stopService(mensajeLanzaServicio);
        continuarLeyendo = false;

        btn_arrancar.setEnabled(true);
        btn_parar.setEnabled(false);
    }

    // se implementa automaticamente al cargar el imterface Runnable
    @Override
    public void run() {

        try {
            MulticastSocket mcs = new MulticastSocket(6100);
            InetAddress mcsAddress = Inet4Address.getByName("229.2.10.10");
            mcs.joinGroup(mcsAddress);

            byte[] buffer = new byte[1024];
            DatagramPacket data = new DatagramPacket(buffer, buffer.length);
            while (continuarLeyendo) {
                mcs.receive(data);

                final String dataStr = new String(data.getData());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt_datagrama.setText(dataStr);
                    }
                });


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
