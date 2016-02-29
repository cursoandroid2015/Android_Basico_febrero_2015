package cursoandroid.practicasandroid.actividades;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class TareaListAdapterActivity extends BaseAdapter {

    private ArrayList<TareaDAO> listaTareas;

    public TareaListAdapterActivity(ArrayList<TareaDAO> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @Override
    public int getCount() {
        return listaTareas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
