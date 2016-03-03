package cursoandroid.practicasandroid.actividades;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cursoandroid.practicasandroid.R;

public class ConectividadActivity extends AppCompatActivity {

    private ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectividad);

        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        // Android 5.0+
        Network[] redesDisponibles = cm.getAllNetworks();

        for(Network red : redesDisponibles) {

            NetworkInfo info = cm.getNetworkInfo(red);
            int type = info.getType();
            String typeName = info.getTypeName();
            NetworkInfo.State state = info.getState();

        }

        // Android 6.0+
        Network redActiva = cm.getActiveNetwork();

        cm.addDefaultNetworkActiveListener(new ConnectivityManager.OnNetworkActiveListener() {
            @Override
            public void onNetworkActive() {

                ConectividadActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ConectividadActivity.this,
                                "Conectividad reestablecida",
                                Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
    }
}

