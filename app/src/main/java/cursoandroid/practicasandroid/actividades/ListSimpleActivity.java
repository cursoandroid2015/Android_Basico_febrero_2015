package cursoandroid.practicasandroid.actividades;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSimpleActivity extends ListActivity {
    private static final String[] LISTA_OPCIONES = {
            "Abrir sitio web",
            "Abrir contactos",
            "Hacer llamada",
            "Busqueda google",
            "Comando de voz",
            "Reconocimiento de voz"
    };
    private Integer ACTIVIDAD_RECONOCIMIENTO = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No definimos el layout, sino que la actividad cargará una list por defecto, en vez de hacer un layout con un listview
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LISTA_OPCIONES));

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // Abrir sitio web.
                        // Ponemos un ACTION genérico y si hay más de una actividad con el mismo protocolo asociado (https) lanzará un menú para seleccionar
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es")));
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people")));
                        break;
                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:600600600")));
                        break;
                    case 3:
                        Intent LanzarWEB = new Intent(Intent.ACTION_WEB_SEARCH);
                        LanzarWEB.putExtra(SearchManager.QUERY, "ssl");
                        startActivity(LanzarWEB);
                        break;
                    case 4:
                        startActivity(new Intent(Intent.ACTION_VOICE_COMMAND));
                        break;
                    case 5:
                        Intent LanzarReco = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        LanzarReco.putExtra(RecognizerIntent.EXTRA_PROMPT, "diga el mensaje");
                        LanzarReco.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                        startActivityForResult(LanzarReco, ACTIVIDAD_RECONOCIMIENTO);
                        break;
                    default:
                        Toast.makeText(view.getContext(), "Defult", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // este es un punto central de retorno de todas las actividades que se han llamado
        // requestCode -> actividad que ha terminado en nuestro caso tenemos identificado la 1000
        if (requestCode == ACTIVIDAD_RECONOCIMIENTO) {
            if (resultCode == RESULT_OK) {
                // Devuelve el resultado a través de un array de cadenas
                ArrayList<String> ArrayListReco = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Toast.makeText(this, ArrayListReco.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Salida: RESULT_CANCEL", Toast.LENGTH_SHORT).show();
            }
        } else {
            // si se ha llamado con startActivityForResult
            Toast.makeText(this, "finish: otra actividad", Toast.LENGTH_SHORT).show();
        }
    }
}
