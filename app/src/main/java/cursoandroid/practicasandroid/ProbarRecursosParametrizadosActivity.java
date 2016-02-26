package cursoandroid.practicasandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class ProbarRecursosParametrizadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar_recursos_parametrizados);

        TextView txt_mensaje = (TextView) findViewById(R.id.txt_mensaje_parametrizable);
        int[] conversiones = getResources().getIntArray(R.array.conversiones);
        txt_mensaje.setText(Arrays.toString(conversiones));

    }
}
