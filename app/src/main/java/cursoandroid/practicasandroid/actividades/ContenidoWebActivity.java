package cursoandroid.practicasandroid.actividades;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import cursoandroid.practicasandroid.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ContenidoWebActivity extends Activity {

    private WebView webPanel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contenido_web);

        webPanel = (WebView) findViewById(R.id.webView_webPanel);
        webPanel.loadUrl("http://android.awslabstest.net/web.html");

        WebSettings webPanelSettings = webPanel.getSettings();
        webPanelSettings.setJavaScriptEnabled(true);
        webPanelSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webPanelSettings.setDisplayZoomControls(false);

        // Ejemplo sin utilizar una clase anónimca new Object
        // http://stackoverflow.com/questions/21749425/android-webview-addjavascriptinterface-does-not-work-if-the-webview-is-created-i
        // aunque sale error, no le hacemos caso, es del editor
        webPanel.addJavascriptInterface(
                new Object() {

                    @JavascriptInterface
                    public void mostrarMensaje(String mensaje) {
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                    }
                },
                "codigoAndroid"
        );

    }

}
