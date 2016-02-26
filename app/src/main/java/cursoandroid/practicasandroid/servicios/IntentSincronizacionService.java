package cursoandroid.practicasandroid.servicios;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.actividades.ProbarServicioActivity;

public class IntentSincronizacionService extends IntentService {

    private static int LAST_ID = 1000;

    public IntentSincronizacionService() {
        super("IntentSincronizacionService");
    }


    // Se invoca en un nuevo hilo que crea la clase base
    // y al terminar se para el servicio y el hilo
    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            int timeout = intent.getIntExtra("timeout", 1000);

            Thread.sleep(timeout);

            NotificationManager servicioNotificaciones =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);

            Notification.Builder builder = new Notification.Builder(this);
            builder.setTicker("Sincronización terminada");
            builder.setSmallIcon(R.mipmap.ic_rayo);
            builder.setWhen(System.currentTimeMillis());

            builder.setAutoCancel(true);

            Intent lanzarActividad = new Intent(this, ProbarServicioActivity.class);
            PendingIntent actividadPendiente = PendingIntent.getActivity(
                    this,
                    0,
                    lanzarActividad,
                    PendingIntent.FLAG_ONE_SHOT
            );


            builder.setContentIntent(actividadPendiente);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setContentText("Se ha terminado correctamente la sincronización de datos");
            builder.setContentTitle("Sincronización terminada");

            Notification notificacion = builder.build();

            servicioNotificaciones.notify(++LAST_ID, notificacion);

            ///////////////////////////////////////////////////////////////

/*
            Notification notificacion = new Notification(
                    R.drawable.ic_notificacion_sincronizacion,
                    "Sincronización terminada",
                    System.currentTimeMillis()
            );

            Intent lanzarActividad = new Intent(this, MonitorDeviceService.class);

            PendingIntent actividadPendiente = PendingIntent.getActivity(
                    this,
                    0,
                    lanzarActividad,
                    Intent.FLAG_ACTIVITY_NEW_TASK
            );

            notificacion.defaults |= Notification.DEFAULT_ALL;
            notificacion.flags |= Notification.FLAG_AUTO_CANCEL;


            ELIMINADO EN ANDROID 6.0

            notificacion.setLatestEventInfo(
                    this,
                    "Sincronización terminada",
                    "Se ha terminado correctamente la sincronización de datos",
                    actividadPendiente
            );


            servicioNotificaciones.notify(1000, notificacion);
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
