package cursoandroid.practicasandroid.actividades;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import cursoandroid.practicasandroid.R;

public class PreferenciasActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }
}
