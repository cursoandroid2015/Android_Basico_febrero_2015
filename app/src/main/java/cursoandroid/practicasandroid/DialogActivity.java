package cursoandroid.practicasandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progress = null;
    private Button btn_toast;
    private Button btn_alert;
    private Button btn_progress;
    private Button btn_date;
    private Button btn_time;


    // como hacer cada botón es un coñazo, voy a implementar un interface onClickListener para diseñar
    // el evento onClick de todos los botones
    // Antes
    //      public class DialogActivity extends AppCompatActivity {
    // Después
    //      public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    // Con ALT+ENTER implementa automaticamente el método:
        /*    @Override
              public void onClick(View v) {
              }
        */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btn_toast = (Button) findViewById(R.id.btn_dialog_Toast);
        btn_alert = (Button) findViewById(R.id.btn_dialog_alertdialog);
        btn_progress = (Button) findViewById(R.id.btn_dialog_progressdialog);
        btn_date = (Button) findViewById(R.id.btn_dialog_datepickerdialog);
        btn_time = (Button) findViewById(R.id.btn_dialog_TimePickerDialog);

        // Llama al interaface implements View.OnClickListener, se le pasa un View, porque
        // todos los objetos View implementa el evento OnClick
        btn_toast.setOnClickListener(this);
        btn_alert.setOnClickListener(this);
        btn_progress.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Seleccionamos el objteo que ha llamado
        switch (v.getId()) {
            case R.id.btn_dialog_Toast:
                Toast t = Toast.makeText(this, "", Toast.LENGTH_SHORT);

                // La imagen se puede cargar con New -> Image Asset o New -> Vector Asset
                ImageView image = new ImageView(this);
                image.setImageResource(R.mipmap.ic_dialog);

                // Le cambio la vista con un componente de tipo View, pero como todos los objetos
                // son View, le paso un ImageView que tb es un View
                t.setView(image);
                t.show();
                break;
            case R.id.btn_dialog_alertdialog:

                AlertDialog alert = new AlertDialog.Builder(this).create();

                alert.setMessage("Problamakklasd flñkasd flaksdj fñlaksdjf alkdf a asddf asdfa\nProblema asldfajñsld kfjasñldfk jañlskdfj añlskdfj a...");

                alert.setButton(
                        AlertDialog.BUTTON_POSITIVE,
                        "Reintentar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "BUTTON_POSITIVE", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                alert.setButton(
                        AlertDialog.BUTTON_NEGATIVE,
                        "Negativo",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "BUTTON_NEGATIVE", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                alert.setButton(
                        AlertDialog.BUTTON_NEUTRAL,
                        "Neutro",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "BUTTON_NEUTRAL", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                alert.show();


                break;
            case R.id.btn_dialog_progressdialog:

                progress = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);

                progress.setMessage("hola \nasdfasdfasl dfajñs ldfkasñjldf ajñsldkf añlskd faslkdf");
                progress.setTitle("Adios");

                // Bloquea el UI del usuario
                progress.show();

                // Creo un nuevo hilo que será el encargado de cerrar el dialogo
                final Handler cerrarDlg = new Handler();

                // Como el show bloquea el UI, hago en otro hilo nuevo un sleep y un hide del progress
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Al pasar los 2 segundos ejecuto el hilo creado antes ya que no puedo hacer un
                        // progress.hide directamente, daría un error: de que no puedo cerrar algo que no se ha creado en el mismo hilo
                        cerrarDlg.post(new Runnable() {
                            @Override
                            public void run() {
                                progress.hide();
                            }
                        });

                    }
                });

                hilo.start();

                break;
            case R.id.btn_dialog_datepickerdialog:

                DatePickerDialog dateDlg = new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Toast.makeText(DialogActivity.this,
                                        String.format("%s/%s/%s", year, monthOfYear, dayOfMonth),
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        // el mes empieza en 0 por eso 1 es febrero
                        2016, 1, 24
                );

                dateDlg.show();

                break;
            case R.id.btn_dialog_TimePickerDialog:
                TimePickerDialog timeDlg = new TimePickerDialog(
                        this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(DialogActivity.this,
                                        String.format("%s:%s", hourOfDay, minute),
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        10, 30, true
                );

                timeDlg.show();
                break;

        }
    }
}
