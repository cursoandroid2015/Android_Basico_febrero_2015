package cursoandroid.practicasandroid.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import cursoandroid.practicasandroid.R;

public class TareaListAdapterActivity extends BaseAdapter {

    private Context ctx;
    private ArrayList<TareaDAO> listaTareas;
    private LayoutInflater inflater;

    public TareaListAdapterActivity(Context ctx, ArrayList<TareaDAO> listaTareas) {
        this.ctx = ctx;
        this.listaTareas = listaTareas;
        this.inflater = LayoutInflater.from(ctx);
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

        // Como este método reutilza las View para cada elemento i, hay que preguntar si ya existe
        // Además el elemento i-ésimo está en un recurso a través de un layout
        if (convertView == null) {
            // no hace falta poner el Viewgroup, ponemos null
            View layout = inflater.inflate(R.layout.tareas_item, null);
            TareaDAO tarea = listaTareas.get(position);

            PonerTexto(layout, R.id.edit_tarea_id, String.valueOf(tarea.getCodigo()));
            PonerTexto(layout, R.id.edit_tarea_titulo, String.valueOf(tarea.getTitulo()));
            PonerTexto(layout, R.id.edit_tarea_descripcion, String.valueOf(tarea.getDescripcion()));
            PonerTexto(layout, R.id.edit_tarea_fecha, tarea.getFecha().toString());

            return layout;
        } else {
            return convertView;
        }
    }

    private void PonerTexto(View layout, int codigo, String texto) {
        // obtengo el elmento i-ésimo
        EditText id = (EditText) layout.findViewById(codigo);
        id.setText(texto);
    }
}
