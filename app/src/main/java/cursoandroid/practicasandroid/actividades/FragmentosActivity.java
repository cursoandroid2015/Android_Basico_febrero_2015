package cursoandroid.practicasandroid.actividades;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import cursoandroid.practicasandroid.R;
import cursoandroid.practicasandroid.fragments.ListaSensoresFragment;
import cursoandroid.practicasandroid.fragments.SensoresFragment;

public class FragmentosActivity extends AppCompatActivity {

    private FragmentManager fm;
    private ListaSensoresFragment listaFlag;
    private SensoresFragment datosFlag;

    private boolean modoStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentos);

        fm = getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragmentos_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {

            case R.id.mnu_fragmentos_modo_stack:
                modoStack = !modoStack;
                break;

            case R.id.mnu_fragmentos_lista_mostrar:
                listaFlag = new ListaSensoresFragment() ;
                ft.add(R.id.frame_fragmentos_lista, listaFlag);
                break;

            case R.id.mnu_fragmentos_datos_mostrar:
                datosFlag = new SensoresFragment();
                ft.add(R.id.frame_fragmentos_datos, datosFlag);
                break;

            case R.id.mnu_fragmentos_lista_quitar:
                ft.remove(listaFlag);
                listaFlag = null;
                break;

            case R.id.mnu_fragmentos_datos_quitar:
                ft.remove(datosFlag);
                datosFlag = null;
                break;

        }

        if(modoStack) {
            ft.addToBackStack(null);
        }

        ft.commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        MenuItem mnu_lista_mostrar = menu.findItem(R.id.mnu_fragmentos_lista_mostrar);
        MenuItem mnu_lista_quitar = menu.findItem(R.id.mnu_fragmentos_lista_quitar);
        MenuItem mnu_datos_mostrar = menu.findItem(R.id.mnu_fragmentos_datos_mostrar);
        MenuItem mnu_datos_quitar = menu.findItem(R.id.mnu_fragmentos_datos_quitar);
        MenuItem mnu_modo_stack = menu.findItem(R.id.mnu_fragmentos_modo_stack);

        mnu_modo_stack.setChecked(modoStack);

        mnu_lista_mostrar.setEnabled(listaFlag == null);
        mnu_lista_quitar.setEnabled(listaFlag != null);
        mnu_datos_mostrar.setEnabled(datosFlag == null);
        mnu_datos_quitar.setEnabled(datosFlag != null);

        return super.onPrepareOptionsMenu(menu);
    }}
