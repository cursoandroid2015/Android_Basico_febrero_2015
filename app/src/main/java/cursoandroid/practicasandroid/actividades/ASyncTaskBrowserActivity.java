package cursoandroid.practicasandroid.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cursoandroid.practicasandroid.ASyncTask.ASyncTaskCallBackInterface;
import cursoandroid.practicasandroid.ASyncTask.ASyncTaskCallWS;
import cursoandroid.practicasandroid.R;

public class ASyncTaskBrowserActivity extends AppCompatActivity implements ASyncTaskCallBackInterface {

    private TextView txt_async_url;
    private Button btn_async_buscar;
    private TextView txt_async_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_browser);

        txt_async_url = (TextView) findViewById(R.id.txt_async_url);
        btn_async_buscar = (Button) findViewById(R.id.btn_async_buscar);
        txt_async_result = (TextView) findViewById(R.id.txt_async_result);

        btn_async_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_async_buscar_lanzar(v);
            }
        });
    }

    private void btn_async_buscar_lanzar(View v) {
        // Le paso al constructor la referencia para el interface
        ASyncTaskCallWS TareaAsincrona = new ASyncTaskCallWS(this);
        TareaAsincrona.execute(txt_async_url.getText().toString());

        // Saber el estado de la tarea
        Toast.makeText(this, TareaAsincrona.getStatus().toString(), Toast.LENGTH_LONG).show();


        // Me bloqueo esperando por el estado.
        /*
        try {
            String resultadoget = TareaAsincrona.get().toString();
        } catch (Exception e) {
            // Excepción dentro del doBackground
            e.printStackTrace();
        }
        */
        // Me bloqueo esperando por el estado durante 4 segundos
        /*
        try {
            String resultadogetLong = TareaAsincrona.get(4, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // Excepción dentro del doBackground
            // Si me da un error por pasar de los 4 segundos, cancelo la operación
            TareaAsincrona.cancel(true);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }

    // Tiene que ser un rocedimiento publico
    // Crear: Refract -> extract -> Interface
    // Al crearlo se implemantado en la actividad
    //          public class ASyncTaskBrowserActivity extends AppCompatActivity implements ASyncTaskCallBackInterface {
    // El interface creado es este:
        /*
            public interface ASyncTaskCallBackInterface {
                // Procedimiento publico -> Refracto -> extract -> Interface
                void servicioCallInterface(String resultado);
            }
        */

    @Override
    public void servicioCallInterface(String resultado) {
        txt_async_result.setText(resultado);
    }

}
