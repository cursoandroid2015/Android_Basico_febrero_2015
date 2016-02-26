package cursoandroid.practicasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CicloDeVidaActivity extends AppCompatActivity {

    private String estadoInterno = "Cadena por defecto";
    private TextView txt_mensaje;
    private Button btn_cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_de_vida);

        Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();

        txt_mensaje = (TextView) findViewById(R.id.txt_ciclodevida_mensaje);
        btn_cambiar = (Button) findViewById(R.id.btn_ciclodevida_cambiar);

        btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cambiar_onClick(v);
            }
        });
    }


    public void btn_cambiar_onClick(View v) {
        estadoInterno = "Estado cambiado";
        txt_mensaje.setText(estadoInterno);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_mensaje.setText(estadoInterno);
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
        outState.putString("estado", estadoInterno);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show();
        estadoInterno = savedInstanceState.getString("estado");
    }
}
