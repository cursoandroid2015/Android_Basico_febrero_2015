package cursoandroid.practicasandroid.servicios;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;

import java.util.Date;

public class FakeGPSSservice extends Service implements Runnable {

    public static final String FAKE_GPS_PROVIDER = "FAKE_GPS";
    private LocationManager lm;

    private Thread hilo;
    private boolean enviarPosicion = true;

    @Override
    public void onCreate() {
        super.onCreate();

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.addTestProvider(
                FAKE_GPS_PROVIDER,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                1, // miliamperios hora
                Criteria.ACCURACY_FINE // precisión
        );
        lm.setTestProviderEnabled(FAKE_GPS_PROVIDER, true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
            while (enviarPosicion) {
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Location pos = new Location(FAKE_GPS_PROVIDER);
                pos.setAccuracy(4.0f); // precisión
                pos.setAltitude(1000.0);
                pos.setBearing();
                pos.setLatitude(45.0);
                pos.setLongitude(1.0);
                pos.setSpeed(10); // metros por segudnos
                pos.setElapsedRealtimeNanos(System.currentTimeMillis());
                pos.setTime(System.currentTimeMillis());
                lm.setTestProviderLocation(FAKE_GPS_PROVIDER, pos);
            }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        lm.removeTestProvider(FAKE_GPS_PROVIDER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        hilo = new Thread(this);
        hilo.start();

        return super.onStartCommand(intent, flags, startId);
    }
}
