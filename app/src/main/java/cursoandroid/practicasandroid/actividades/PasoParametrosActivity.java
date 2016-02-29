package cursoandroid.practicasandroid.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cursoandroid.practicasandroid.R;

public class PasoParametrosActivity extends Activity {

    private EditText editNombre;
    private EditText editClave;

    private Button botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pasodeparametros);

        editNombre = (EditText) findViewById(R.id.edit_nombre);
        editClave = (EditText) findViewById(R.id.edit_clave);

        botonAceptar = (Button) findViewById(R.id.btn_aceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_aceptar_onClick(v);
            }
        });

        Intent mensaje = getIntent();
        String nombre = mensaje.getStringExtra("nombre");
        String clave = mensaje.getStringExtra("clave");

        editNombre.setText(nombre);
        editClave.setText(clave);
    }

    private void btn_aceptar_onClick(View v) {

        Intent mensaje = getIntent();
        mensaje.putExtra("nombre", editNombre.getText().toString());
        mensaje.putExtra("clave", editClave.getText().toString());

        setResult(RESULT_OK, mensaje);

        finish();
    }
}
