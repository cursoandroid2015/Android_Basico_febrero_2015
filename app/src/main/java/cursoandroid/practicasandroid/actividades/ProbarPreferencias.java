package cursoandroid.practicasandroid.actividades;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import cursoandroid.practicasandroid.R;

public class ProbarPreferencias extends AppCompatActivity {

    private Button btn_preferencias_reset;
    private Button btn_preferencias_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar_preferencias);

        btn_preferencias_reset = (Button) findViewById(R.id.btn_preferencias_reset);
        btn_preferencias_read = (Button) findViewById(R.id.btn_preferencias_read);

        btn_preferencias_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_preferencias_reset_action(v);
            }
        });

        btn_preferencias_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_preferencias_read_action(v);
            }
        });
    }

    private void btn_preferencias_read_action(View v) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String nombre = pref.getString("nombre", "defaultnombre");
        String password = pref.getString("password", "defaultpassword");

        boolean depuracion = pref.getBoolean("depuracion", false);
        boolean auditoria = pref.getBoolean("auditoria", false);

        String tipoConexion = pref.getString("tipoConexion", "tipoConexionNone");

        Set<String> conexionesActivas = pref.getStringSet("ConexionesActivas", new HashSet<String>() {
        });

        Toast.makeText(this, String.format("Nombre: %s\nPassword: %s\nDepuracion: %s\nAuditoria: %s\nTipoConexion: %s\nConexionesActivas: %s",
                nombre,
                password,
                depuracion,
                auditoria,
                tipoConexion,
                conexionesActivas.toString()
        ), Toast.LENGTH_LONG).show();
    }

    private void btn_preferencias_reset_action(View v) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("nombre", "1111");
        editor.putBoolean("depuracion", true);

        HashSet<String> valores = new HashSet<>();
        valores.add("x1");
        valores.add("x2");
        editor.putStringSet("ConexionesActivas", valores);

        editor.commit();
    }
}
