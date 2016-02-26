package cursoandroid.practicasandroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Arranque 1 on 24/02/2016.
 */
public class PreferenciasActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }
}
