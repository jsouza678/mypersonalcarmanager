package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.onboarding.MainOnboarding;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;

public class MaintenanceActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button btnConfirma;
    int km_oleo_var;
    int km_filtros_var;
    private TextInputLayout km_filtros;
    private TextInputLayout km_oleo;
    private RadioGroup rd_oleo, rd_filtros;
    private RadioButton rd1_oleo, rd2_oleo, rd1_filtros, rd2_filtros;
    private int day, month, year;
    private Calendar cal_oleo, cal_filtros;
    private TextView dis1, dis2;
    private Cursor cursor;
    private BancoController crud;
    private boolean ok1 = false;
    private boolean ok2 = false;
    int kmAtual;
    DateFormat sdf;
    //bd
    String strDateOleo;
    String strDateFiltros;
    String date_oleo_bd, date_filtros_bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        dis1 = (TextView) findViewById(R.id.dis1);
        dis2 = (TextView) findViewById(R.id.dis2);


        Calendar now = Calendar.getInstance();
        day = now.get(Calendar.DAY_OF_MONTH);
        month = now.get(Calendar.MONTH);
        year = now.get(Calendar.YEAR);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal_oleo = Calendar.getInstance();
        cal_filtros = Calendar.getInstance();

        km_oleo = findViewById(R.id.edit_km_oleo);
        rd_oleo = findViewById(R.id.radio_oleo);
        rd1_oleo = findViewById(R.id.radio_yes_oleo);
        rd2_oleo = findViewById(R.id.radio_no_oleo);

        km_filtros = findViewById(R.id.edit_km_filtros);
        rd_filtros = findViewById(R.id.radio_filtros);
        rd1_filtros = findViewById(R.id.radio_yes_filtros);
        rd2_filtros = findViewById(R.id.radio_no_filtros);

        crud = new BancoController(getBaseContext());
        cursor = crud.carregaData(); // index 4 a km.

        kmAtual = Integer.parseInt(cursor.getString(4));


        rd_oleo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(RadioGroup group, int checkedId) {

                                                   if (rd1_oleo.isChecked()) {
                                                       Toast.makeText(MaintenanceActivity.this, "Sim", Toast.LENGTH_SHORT).show();

                                                       DatePickerDialog datepickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                           @Override
                                                           public void onDateSet(DatePickerDialog view, int yearInside, int monthOfYear, int dayOfMonth) {
                                                               strDateOleo = dayOfMonth + "/" + monthOfYear + "/" + yearInside;
                                                               //Toast.makeText(MaintenanceActivity.this, strDateOleo, Toast.LENGTH_SHORT).show();

                                                               cal_oleo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                                               cal_oleo.set(Calendar.MONTH, monthOfYear);
                                                               cal_oleo.set(Calendar.YEAR, yearInside);

                                                               date_oleo_bd = sdf.format(cal_oleo.getTime());

                                                               dis1.setText(strDateOleo);
                                                           }
                                                       }, year, month, day);

                                                       datepickerDialog.setOkText("Ok");
                                                       datepickerDialog.setCancelText("Cancelar");
                                                       datepickerDialog.setTitle("Calendário");
                                                       datepickerDialog.show(getFragmentManager(), "DatePicker");
                                                       ok1 = true;
                                                   } else if(rd2_oleo.isChecked()){
                                                       Toast.makeText(MaintenanceActivity.this, "Se não souber, digite os dados atuais", Toast.LENGTH_SHORT).show();
                                                   }
                                               }



                                           }
        );


        rd_filtros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (rd1_filtros.isChecked()) {
                    Toast.makeText(MaintenanceActivity.this, "Sim", Toast.LENGTH_SHORT).show();

                    DatePickerDialog datepickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int yearInside, int monthOfYear, int dayOfMonth) {
                            strDateFiltros = dayOfMonth + "/" + monthOfYear + "/" + yearInside;
                            //Toast.makeText(MaintenanceActivity.this, strDateFiltros, Toast.LENGTH_SHORT).show();
                            cal_filtros.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            cal_filtros.set(Calendar.MONTH, monthOfYear);
                            cal_filtros.set(Calendar.YEAR, yearInside);

                            date_filtros_bd = sdf.format(cal_filtros.getTime());

                            dis2.setText(strDateFiltros);
                        }
                    }, year, month, day);

                    datepickerDialog.setOkText("Ok");
                    datepickerDialog.setCancelText("Cancelar");
                    datepickerDialog.setTitle("Calendário");
                    datepickerDialog.show(getFragmentManager(), "DatePicker");
                    ok2 = true;


                } else if(rd2_filtros.isChecked()){
                    Toast.makeText(MaintenanceActivity.this, "Se não souber, digite os dados atuais", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );


        btnConfirma = (Button) findViewById(R.id.btn_confirmar);

        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((km_oleo.getEditText().getText().toString()).trim().equals("")) {

                    km_oleo.setError("Preencha corretamente!");

                } else if ((km_filtros.getEditText().getText().toString()).trim().equals("")) {

                    km_filtros.setError("Preencha corretamente!");

                } else if ((Integer.parseInt(km_oleo.getEditText().getText().toString())) > kmAtual) {

                    km_oleo.setError("Km maior do que a atual!");

                } else if (((Integer.parseInt(km_filtros.getEditText().getText().toString())) > kmAtual)) {

                    km_filtros.setError("Km maior do que a atual!");

                } else {

                    km_oleo_var = Integer.parseInt(km_oleo.getEditText().getText().toString());
                    km_filtros_var = Integer.parseInt(km_filtros.getEditText().getText().toString());
                    if(ok1 && ok2 == true) {
                        cadastro();
                        openNewActivity();
                    }else{
                        Toast.makeText(MaintenanceActivity.this, "Se não souber, digite os dados atuais", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void openNewActivity() {
        Intent it = new Intent(this, MainOnboarding.class);
        startActivity(it);
    }

    public void cadastro() {
        BancoController crud = new BancoController(getBaseContext());

        //EditEmail = findViewById(R.id.email_txt);
        //EditKm = findViewById(R.id.km_txt);


        //String email = EditEmail.getText().toString();
        //int kmAtual = Integer.parseInt(EditKm.getText().toString());

        String resultado;

        resultado = crud.insereDadosManut(km_oleo_var, km_filtros_var, date_oleo_bd, date_filtros_bd);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(this, String.format("You selected: %d/%d/%d", dayOfMonth, monthOfYear, year), Toast.LENGTH_SHORT).show();
    }
}
