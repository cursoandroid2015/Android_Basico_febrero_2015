package cursoandroid.practicasandroid.actividades;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import cursoandroid.practicasandroid.R;

public class PosicionamientoActivity extends AppCompatActivity implements LocationListener {

    public static final int DIALOG_PERMISSION_REQUEST_CODE = 100;
    private Button btn_loc_activar;
    private Button btn_loc_desactivar;
    private TextView txt_loc_detalle;
    private Spinner spin_loc_list;
    private LocationManager lm;

    private boolean permisoPosicionamiento = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicionamiento);

        btn_loc_activar = (Button) findViewById(R.id.btn_loc_activar);
        btn_loc_desactivar = (Button) findViewById(R.id.btn_loc_desactivar);
        txt_loc_detalle = (TextView) findViewById(R.id.txt_loc_detalle);
        spin_loc_list = (Spinner) findViewById(R.id.spin_loc_list);

        btn_loc_activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_loc_activar_action();
            }
        });

        btn_loc_desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_loc_desactivar_action();
            }
        });


        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Lista de Proveedores
        List<String> listaProveedores = lm.getAllProviders();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                listaProveedores
        );
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // Poblamos el spinner
        spin_loc_list.setAdapter(adaptador);

        Criteria criterios = new Criteria();
        criterios.setAccuracy(Criteria.ACCURACY_FINE);
        criterios.setSpeedRequired(true);
        criterios.setBearingRequired(true);

        String proveedor = lm.getBestProvider(criterios, true);
        txt_loc_detalle.setText("Proveedor encontrado: " + proveedor);
    }

    private void btn_loc_desactivar_action() {
        try {
            String nombreProveedor = (String) spin_loc_list.getSelectedItem();

            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission != PackageManager.PERMISSION_GRANTED) {

                permisoPosicionamiento = false;

                // Le pregunto al usuario que necesito el permiso poque está desactivado
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        DIALOG_PERMISSION_REQUEST_CODE
                );

            } else {
                lm.removeUpdates(this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btn_loc_activar_action() {

        try {
            String nombreProveedor = (String) spin_loc_list.getSelectedItem();

            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission != PackageManager.PERMISSION_GRANTED) {

                permisoPosicionamiento = false;

                // Le pregunto al usuario que necesito el permiso poque está desactivado
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        DIALOG_PERMISSION_REQUEST_CODE
                );

            } else {
                lm.requestLocationUpdates(
                        nombreProveedor,
                        2000, // cada cuanto tiempo milisegundos
                        10, // precisión mínima metros
                        this
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == DIALOG_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String nombreProveedor = (String) spin_loc_list.getSelectedItem();
                permisoPosicionamiento = true;
                lm.requestLocationUpdates(
                        nombreProveedor,
                        2000, // cada cuanto tiempo milisegundos
                        10, // precisión mínima metros
                        this
                );
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double altitude = location.getAltitude();
        // precisión en metros
        float accuracy = location.getAccuracy();
        // metros por segundo
        float speed = location.getSpeed();
        long time = location.getTime();
        // rumbo de 0 a 360
        float bearing = location.getBearing();

        String format = String.format(
                "Lon: %s\nLat: %s\nAlt: %s\nPre: %s\nVel: %s\nDate: %s\nRmb: %s",
                longitude, latitude, altitude, accuracy, speed, new Date(time), bearing
        );

        txt_loc_detalle.setText(format);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
