package cursoandroid.practicasandroid.actividades;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;

public class TareaDAOBinding extends BaseObservable {

    private int codigo;
    private String titulo;
    private String descripcion;
    private Date fecha;

    public TareaDAOBinding(int codigo, String titulo, String descripcion, Long fecha) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = new Date(fecha);
    }

    @Bindable
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
        notifyPropertyChanged(cursoandroid.practicasandroid.actividades.BR.codigo);
    }

    @Bindable
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        notifyPropertyChanged(cursoandroid.practicasandroid.actividades.BR.titulo);
    }

    @Bindable
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        notifyPropertyChanged(cursoandroid.practicasandroid.actividades.BR.descripcion);
    }

    @Bindable
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        notifyPropertyChanged(cursoandroid.practicasandroid.actividades.BR.fecha);
    }
}

