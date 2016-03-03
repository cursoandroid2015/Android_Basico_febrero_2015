package cursoandroid.practicasandroid.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.servicios.FakeGPSService;

public class FakeGPSActivity extends AppCompatActivity {

    private Switch switch_fakegps;

    private boolean fakegpsActivo;
    private Intent servicioFakeGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_gps);

        switch_fakegps = (Switch) findViewById(R.id.switch_main_fakegps);
        switch_fakegps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    servicioFakeGPS = new Intent(FakeGPSActivity.this, FakeGPSService.class);
                    startService(servicioFakeGPS);
                    fakegpsActivo = true;
                } else {
                    stopService(servicioFakeGPS);
                    fakegpsActivo = false;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(fakegpsActivo) {
            stopService(servicioFakeGPS);
        }
    }
}
