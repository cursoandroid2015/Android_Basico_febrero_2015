package cursoandroid.practicasandroid.fragments;


import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cursoandroid.practicasandroid.R;

public class SensoresFragment extends Fragment implements SensorEventListener {

    TextView txt_datossensor_estado;

    public SensoresFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_sensor, container, false);

        txt_datossensor_estado = (TextView) view.findViewById(R.id.txt_datossensor_estado);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        String estado = "";

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:

                // Aceleración Eje X
                float acel_x = event.values[0]; // m/s^2
                float acel_y = event.values[1]; // m/s^2
                float acel_z = event.values[2]; // m/s^2

                estado = String.format(
                        "Acelerómetro:%n[Ax: %s] [Ay: %s] [Az: %s]",
                        acel_x,
                        acel_y,
                        acel_z);

                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_TEMPERATURE:
                float gradosCelsius = event.values[0]; // ºC

                estado = String.format(
                        "Temperatura:%n [T: %s]",
                        gradosCelsius
                );

                break;
        }

        txt_datossensor_estado.setText(estado);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

