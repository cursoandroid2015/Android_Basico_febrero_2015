package cursoandroid.practicasandroid.actividades;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.List;

public class TelefoniaMonitor extends PhoneStateListener {

    private Context ctx;

    public TelefoniaMonitor(Context ctx) {

        this.ctx = ctx;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {

        if(state == TelephonyManager.CALL_STATE_RINGING) {
            Toast.makeText(ctx, "Llamada entrante: " + incomingNumber,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
    }

    @Override
    public void onDataConnectionStateChanged(int state) {
        super.onDataConnectionStateChanged(state);
    }
}


