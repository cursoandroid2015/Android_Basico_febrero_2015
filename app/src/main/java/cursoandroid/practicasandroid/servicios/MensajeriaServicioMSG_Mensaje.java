package cursoandroid.practicasandroid.servicios;

import java.io.Serializable;

public class MensajeriaServicioMSG_Mensaje implements Serializable {

    private String idDestino;

    private String SMS;

    public MensajeriaServicioMSG_Mensaje(String idDestino, String mensaje) {
        this.idDestino = idDestino;
        this.SMS = mensaje;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String SMS) {
        this.SMS = SMS;
    }

}

