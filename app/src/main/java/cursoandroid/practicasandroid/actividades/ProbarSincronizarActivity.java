package cursoandroid.practicasandroid.actividades;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.servicios.IntentSincronizacionService;

public class ProbarSincronizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar_sincronizar);

        final Button btn_sincronizar =
                (Button) findViewById(R.id.btn_sincro_service);
        btn_sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sincronizar_onClick(v);
            }
        });

        final Button btn_quitar =
                (Button) findViewById(R.id.btn_sincro_quitarnotificaciones_service);
        btn_quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_quitar_onClick(v);
            }
        });

    }

    public void btn_quitar_onClick(View v) {
        NotificationManager servicioNotificaciones =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        servicioNotificaciones.cancelAll();
    }

    public void btn_sincronizar_onClick(View v) {
        startService(new Intent(this, IntentSincronizacionService.class));
    }
}
