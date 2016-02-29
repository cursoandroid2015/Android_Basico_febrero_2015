package cursoandroid.practicasandroid.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.providers.TareasDB;

public class AltaTareaActivity extends AppCompatActivity {

    private EditText alta_tarea_titulo;
    private EditText alta_tarea_descripcion;
    private Button alta_tarea_btn_enviar;

    private TareasDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_tarea);

        // abrimos db
        db = new TareasDB(this );

        alta_tarea_titulo = (EditText) findViewById(R.id.alta_tarea_titulo);
        alta_tarea_descripcion = (EditText) findViewById(R.id.alta_tarea_descripcion);
        alta_tarea_btn_enviar = (Button) findViewById(R.id.alta_tarea_btn_enviar);

        alta_tarea_btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alta_tarea_btn_enviar_action();
            }
        });
    }

    private void alta_tarea_btn_enviar_action() {
        db.AbrirDB();

        db.altaTarea(
                alta_tarea_titulo.getText().toString(),
                alta_tarea_descripcion.getText().toString()
        );

        db.CerrarDB();
    }
}
