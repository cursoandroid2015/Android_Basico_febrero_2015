package cursoandroid.practicasandroid.actividades;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cursoandroid.practicasandroid.R;

public class ServicioAlarmasActivity extends AppCompatActivity {

    private AlarmManager am;

    private Button btn_alarma_activar;
    private Button btn_alarma_desactivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_alarmas);

        btn_alarma_activar = (Button) findViewById(R.id.btn_alarma_activar);
        btn_alarma_desactivar = (Button) findViewById(R.id.btn_alarma_activar);

        btn_alarma_activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_alarma_activar_click();
            }
        });

        btn_alarma_desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_alarma_desactivar_click();
        }
        });

        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void btn_alarma_desactivar_click() {

    }

    private void btn_alarma_activar_click() {

    }
}
