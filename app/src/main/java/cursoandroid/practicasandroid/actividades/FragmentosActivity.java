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

        // Caso 1
        setContentView(R.layout.activity_fragmentos);

        // Caso 2
        // Para probar el Layout con fragmentos estáticos
        //setContentView(R.layout.activity_fragmentos_estaticos);
        // Si activamos esto no estaría enlazado el la lista de sensores con los datos de sensores, para tenerlos
        // enlazados hay que hacerlo como está ahora, que es dinámico

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

            case R.id.mnu_fragmentos_lista_mostrar:
                listaFlag = new ListaSensoresFragment() ;
                ft.add(R.id.frame_fragmentos_lista, listaFlag);
                break;

            case R.id.mnu_fragmentos_datos_mostrar:
                // Creo el fragmento
                datosFlag = new SensoresFragment();
                // Lo incorporo a la vista
                ft.add(R.id.frame_fragmentos_datos, datosFlag);

                // Nuestro interfaz de callback
                if (listaFlag != null) {
                    listaFlag.setReceptor(datosFlag);
                }

                break;

            case R.id.mnu_fragmentos_lista_quitar:
                ft.remove(listaFlag);
                listaFlag = null;
                break;

            case R.id.mnu_fragmentos_datos_quitar:
                if (listaFlag != null) {
                    listaFlag.setReceptor(null);
                }
                ft.remove(datosFlag);
                datosFlag = null;
                break;
            case R.id.mnu_fragmentos_modo_stack:
                modoStack = !modoStack;
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
