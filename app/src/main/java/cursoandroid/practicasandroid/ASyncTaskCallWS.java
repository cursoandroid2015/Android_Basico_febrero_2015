package cursoandroid.practicasandroid;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Arranque 1 on 23/02/2016.
 */
public class ASyncTaskCallWS extends AsyncTask<String, Integer, String> {

    // Guardamos la variable callback para poder recupeararla
    private ASyncTaskCallBackInterface callback;

    public ASyncTaskCallWS(ASyncTaskCallBackInterface callback) {
        this.callback = callback;
    }


    // params -> tipo de dato para comunicarse con la cadena asíncrona
    // progress -> evolución de la clase
    // Result -> tipo de dato como resultado

    // Primer parametro de Async es el primer parametro del metodo doInBackground
    // tercer parametro de Async es el retorno del metodo doInBackground

    // se ejecuta en el hilo original
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // Se ejecuta en el nuevo hilo
    // aquí no puedo tocar entorno grafico, en el resto si.
    @Override
    protected String doInBackground(String... params) {

        String url = params[0];
        StringBuilder contenido = new StringBuilder();
        HttpURLConnection HttpConnection_UrlConnection = null;

        try {
            URL urlServicio = new URL(url);

            HttpConnection_UrlConnection = (HttpURLConnection) urlServicio.openConnection();

            HttpConnection_UrlConnection.setConnectTimeout(5000);
            HttpConnection_UrlConnection.setDoInput(true);
            HttpConnection_UrlConnection.setRequestMethod("GET");

            HttpConnection_UrlConnection.connect();

            if (HttpConnection_UrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Scanner leerRespuesta = new Scanner(HttpConnection_UrlConnection.getInputStream(), "UTF-8");
                while (leerRespuesta.hasNextLine()) {
                    contenido.append(leerRespuesta.nextLine());

                }
                return contenido.toString();
            }
        } catch (Exception e) {
            Log.e("APP_X", e.toString());
        } finally {
            if (HttpConnection_UrlConnection != null) {
                HttpConnection_UrlConnection.disconnect();
            }
        }

        return null;
    }

    // se ejecuta en el hilo original
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // Esta clase no sabe quien es callback, pero quien haya implementado el interface
        // recibirá y ejecutará el procedimiento "servicioCallInteraface" de quien la haya implementado
        if (this.callback != null) {
            callback.servicioCallInterface(s);
        }
    }


    // se ejecuta en el hilo original
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    // se ejecuta en el hilo original
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    // se ejecuta en el hilo original
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
