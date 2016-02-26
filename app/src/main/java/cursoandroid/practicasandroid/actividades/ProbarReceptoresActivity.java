package cursoandroid.practicasandroid.actividades;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.receptoresBCR.ProbarReceptores;

// Implementamos el médoto onclick del view
public class ProbarReceptoresActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_activar;
    private Button btn_desactivar;
    private Button btn_emitir;

    private ProbarReceptores receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar_receptores);

        btn_activar = (Button) findViewById(R.id.btn_probar_receptores_activar);
        btn_desactivar = (Button) findViewById(R.id.btn_probar_receptores_desactivar);
        btn_emitir = (Button) findViewById(R.id.btn_probar_receptores_emitir);

        btn_activar.setOnClickListener(this);
        btn_desactivar.setOnClickListener(this);
        btn_emitir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_probar_receptores_activar:
                // Registro el broadcast MODO AVION
                IntentFilter filtro = new IntentFilter(
                        Intent.ACTION_AIRPLANE_MODE_CHANGED
                );
                filtro.addAction("cursoandroid.SINCRONIZACION_TERMINADA");

                receptor = new ProbarReceptores();
                registerReceiver(receptor, filtro);
                break;
            case R.id.btn_probar_receptores_desactivar:
                if (receptor != null) {
                    unregisterReceiver(receptor);
                }
                break;
            case R.id.btn_probar_receptores_emitir:
                Intent emision = new Intent("cursoandroid.SINCRONIZACION_TERMINADA");
                emision.putExtra("state", "TERMINADO");
                sendBroadcast(emision);
                break;
        }
    }
}
