package cursoandroid.practicasandroid.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

// Servicio tipo mensajería que hace cosas en background
public class MonitorDeviceService extends Service implements Runnable {

    private boolean ejecutarServicio = true;
    private Thread hiloServicio;
    private int timeout;


    @Override
    public void onCreate() {
        super.onCreate();

        hiloServicio = new Thread(this);

        // Para depuración
        hiloServicio.setName("hilo_MonitorDeviceService");
        hiloServicio.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timeout = intent.getIntExtra("timeout", 2000);

        // Arranco el hilo
        hiloServicio.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ejecutarServicio = false;
    }

    // Este método hay que crearlo siempre, se puede hacer con
    // ALT+ENTER -> crear métodos
    // No se utliza, al ser Service una clase Abastracta, el diseñador ha obligado
    // a implementar este método
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Este método se añade al implemtar la interface Runnable
    @Override
    public void run() {
        // Al arrancar el hilo con el start, se ejecuta este Run

        try {

            // Puerto que designo para hacer multicast,
            // Todas la redes que tengan activado multicasting se pude hacer
            // a las redes .224 a .239
            MulticastSocket mcs = new MulticastSocket(6100);
            InetAddress mcsAddress = Inet4Address.getByName("229.2.10.10");
            mcs.joinGroup(mcsAddress);
            mcs.setTimeToLive(100);

            String mensaje;


            while (ejecutarServicio) {
                Thread.sleep(timeout);

                mensaje = ":)" + new Date();

                DatagramPacket data = new DatagramPacket(
                        mensaje.getBytes(),
                        mensaje.length(),
                        mcsAddress,
                        6100
                );

                // El metodo recibe que utilizaremos para recibir el datagrama es bloqueante
                // asi que haremos un thread con el recibe
                mcs.send(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
