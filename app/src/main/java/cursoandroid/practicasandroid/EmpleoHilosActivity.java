package cursoandroid.practicasandroid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import android.os.Handler;

import java.util.logging.LogRecord;

public class EmpleoHilosActivity extends AppCompatActivity {

    private TextView txt_segundos;
    private SeekBar seekbar_hilos_segundos;
    private Button btn_ope_hilo_ui;

    private Button btn_arrancar;
    private Button btn_parar;
    private TextView txt_emplea_hilos;

    private Boolean CodigoAsincronoOK = true;
    private Boolean flagColor = false;

    private Handler handler;
    private Runnable CodigoRepintado = new Runnable() {
        @Override
        public void run() {
            Actualizar();
        }
    };

    private Runnable CodigoAsíncrono = new Runnable() {
        @Override
        public void run() {
            while (CodigoAsincronoOK) {

                //Actualizar();
                handler.post(CodigoRepintado);

                try {
                    Thread.sleep((seekbar_hilos_segundos.getProgress()) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Thread hilo;

    protected void Actualizar() {
        if (flagColor) {
            txt_emplea_hilos.setBackgroundColor(Color.BLUE);
        } else {
            txt_emplea_hilos.setBackgroundColor(Color.RED);
        }
        flagColor = !flagColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleo_hilos);

        txt_segundos = (TextView) findViewById(R.id.txt_segundos);
        seekbar_hilos_segundos = (SeekBar) findViewById(R.id.seekbar_hilos_segundos);
        btn_ope_hilo_ui = (Button) findViewById(R.id.btn_ope_hilo_ui);

        txt_segundos.setText("Segundos: " + seekbar_hilos_segundos.getProgress());

        seekbar_hilos_segundos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_segundos.setText("Segundos: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_ope_hilo_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_ope_hilo_ui();
            }
        });

        btn_arrancar = (Button) findViewById(R.id.btn_arrancar_hilo);
        btn_parar = (Button) findViewById(R.id.btn_parar_hilo);
        txt_emplea_hilos = (TextView) findViewById(R.id.txt_emplea_hilos);

        btn_arrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_arrancar_operacion(v);
            }
        });

        btn_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_parar_operacion(v);
            }
        });

        // Esta llamada por defecto almacena el hilo actual
        // import android.os.Handler;
        handler = new Handler();

    }

    private void btn_arrancar_operacion(View v) {
        CodigoAsincronoOK = true;
        hilo = new Thread(CodigoAsíncrono);
        hilo.start();

        btn_arrancar.setEnabled(false);
    }

    private void btn_parar_operacion(View v) {
        CodigoAsincronoOK = false;

        btn_arrancar.setEnabled(true);

    }

    @Override
    protected void onStop() {
        super.onStop();

        CodigoAsincronoOK = false;
    }

    private void btn_ope_hilo_ui() {
        try {
            Thread.sleep(seekbar_hilos_segundos.getProgress() * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
