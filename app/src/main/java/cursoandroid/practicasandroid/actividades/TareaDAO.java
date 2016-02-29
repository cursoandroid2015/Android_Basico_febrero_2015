package cursoandroid.practicasandroid.actividades;

import java.util.Date;

/**
 * Created by Arranque 1 on 29/02/2016.
 */
public class TareaDAO {

    private int codigo;
    private String titulo;
    private String descripcion;
    private Date fecha;

    public TareaDAO(int codigo, String titulo, String descripcion, Long fecha) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = new Date(fecha);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
