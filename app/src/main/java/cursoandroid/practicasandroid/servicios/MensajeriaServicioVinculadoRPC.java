package cursoandroid.practicasandroid.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// Esta clase delega toda la funcionalidad a la clase MensajeriaServicioRPCBinder.java

public class MensajeriaServicioVinculadoRPC extends Service {


    private MensajeriaServicioVinculadoRPCBinder mensajeriaRPCBinder;

    public MensajeriaServicioVinculadoRPC() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mensajeriaRPCBinder = new MensajeriaServicioVinculadoRPCBinder(this);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mensajeriaRPCBinder;
    }
}
