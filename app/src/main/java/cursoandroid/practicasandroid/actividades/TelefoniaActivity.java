package cursoandroid.practicasandroid.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import java.util.List;

import cursoandroid.practicasandroid.R;

public class TelefoniaActivity extends AppCompatActivity  {

    private TelephonyManager tm;

    private TelefoniaMonitor monitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefonia);

        monitor = new TelefoniaMonitor(this);

        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        tm.listen(monitor,
                PhoneStateListener.LISTEN_CALL_STATE |
                        PhoneStateListener.LISTEN_DATA_ACTIVITY);

        // Recuperamos información del dispositivo

        String imei = tm.getDeviceId();
        String numeroTelf = tm.getLine1Number();
        // Sólo en Android 6.0+
        // int numeroTarjetasSIM = tm.getPhoneCount();
        String SIMNumber = tm.getSimSerialNumber();
        String SIMOperatorName = tm.getSimOperatorName();
        int SIMState = tm.getSimState(); //
        int callState = tm.getCallState();

        CellLocation pos = tm.getCellLocation();
        if(pos instanceof GsmCellLocation) {
            GsmCellLocation gsmPos = (GsmCellLocation) pos;
            int lac = gsmPos.getLac();
            int cid = gsmPos.getCid();
            int psc = gsmPos.getPsc();
        }
        else if(pos instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaPos = (CdmaCellLocation) pos;
            int stationId = cdmaPos.getBaseStationId();
            int latitude = cdmaPos.getBaseStationLatitude();
            int longitude = cdmaPos.getBaseStationLongitude();
        }

        List<CellInfo> listaAntenas = tm.getAllCellInfo();
        if(!listaAntenas.isEmpty()) {
            CellInfo cellInfo = listaAntenas.get(0);
            // Consultar todos los subtipos de CellInfo
            // CellInfoLte, CellInfoGsm, CellInfoCdma, CellInfoWcdma
        }

        ////////////////////////////////////////////////////////////////

        TextView txt_datos = (TextView) findViewById(R.id.txt_telefonia_datos);

        String mensaje = String.format(
                "IMEI: %s%nNúmero telf: %s%nSIM ID: %s%nSIM Operator: %s%n",
                imei, numeroTelf, SIMNumber, SIMOperatorName
        );

        txt_datos.setText(mensaje);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tm.listen(monitor, PhoneStateListener.LISTEN_NONE);
    }
}
