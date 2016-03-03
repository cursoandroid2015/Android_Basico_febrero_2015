package cursoandroid.practicasandroid.fragments;

        import android.app.Activity;
        import android.app.Fragment;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorManager;
        import android.net.Uri;
        import android.os.Bundle;

        import android.text.Html;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import cursoandroid.practicasandroid.R;

public class ListaSensoresFragment extends Fragment {

    private SensorManager sm;
    private Context context;

    private ArrayList<String> listaCadenas = new ArrayList<String>();

    public ListaSensoresFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor acelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(acelerometro != null) {
            Toast.makeText(context, "Hay acelerómetro", Toast.LENGTH_LONG).show();
        }

        List<Sensor> listaSensores = sm.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s : listaSensores) {

            String tipo = s.getStringType();
            String nombre = s.getName();
            String fabricante = s.getVendor();
            float consumo = s.getPower();

            String texto =
                    Html.fromHtml(
                            String.format(
                                    "<h1>%s</h1><h3>%s</h3><p>Fabricante: %s</p><p>Consumo: %smAh</p>",
                                    tipo, nombre, fabricante, consumo
                            )
                    ).toString();

            listaCadenas.add(texto);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(
                R.layout.fragment_lista_sensores,
                container, false);

        ListView lista = (ListView) layout.findViewById(
                R.id.list_listasensores_lista);

        lista.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, listaCadenas));

        return layout;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
