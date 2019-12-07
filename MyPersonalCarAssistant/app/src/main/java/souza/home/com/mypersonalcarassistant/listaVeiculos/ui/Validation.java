package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.app.Activity;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;

public class Validation {
    private static AlertDialog alerta;
    private static String m_Text = "";
    private static BancoController crud;
    private static Cursor cursor, cursor2;
    private static TextView km_txtview;
    private static TextInputLayout input;
    private static String dataM;
    private static String strDateManutencao;
    private static Calendar cal, cal_bd, cal_bd_6_months, cal_today;
    private static int day, month, year;
    private static TextView dis1;
    private static String data_bd;
    private static String data_nova = "";
    private static DateFormat sdf;
    private static boolean dateSet;
    public static Fragment frag;



    public static void displayDialogFiltros(Activity atv) {

        dis1 = (TextView) atv.findViewById(R.id.tv_date);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal = Calendar.getInstance();
        cal_bd = Calendar.getInstance();
        cal_bd_6_months = Calendar.getInstance();
        cal_today = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);



        crud = new BancoController(atv.getBaseContext());
        cursor = crud.carregaData();



        final AlertDialog.Builder atualizaKm = new AlertDialog.Builder(new ContextThemeWrapper(atv, android.R.style.Theme_DeviceDefault_Light_Dialog));
        LayoutInflater factory = LayoutInflater.from(atv);
        final View f = factory.inflate(R.layout.custom_dialog_maintenance, null);

        dis1 = f.findViewById(R.id.tv_date);
        atualizaKm.setView(f);
        input = new TextInputLayout(atv);
        input = (f.findViewById(R.id.input_km_troca));
        dateSet = false;

        Button positivo = f.findViewById(R.id.d_button);
        Button negativo = f.findViewById(R.id.n_button);
        Button btn_data = f.findViewById(R.id.btn_date);

        System.out.println(cal_bd.toString());

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datepickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int yearInside, int monthOfYear, int dayOfMonth) {
                        strDateManutencao = dayOfMonth + "/" + monthOfYear + "/" + yearInside;
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.YEAR, yearInside);

                        crud = new BancoController(atv.getBaseContext());
                        cursor2 = crud.carregaDataManut();
                        //cursor2 = crud.carregaData();
                        dataM = cursor2.getString(3);
                        //hj
                        try {
                            cal_bd.setTime(sdf.parse(dataM));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                        if ((cal.after(cal_today))) { // data depois de hoje, nao altera // erro
                            Toast.makeText(atv, "Insira apenas quando tiver feito a manutenção!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if(cal.before(cal_today)){ // data antes do BD, ou antes do BD // erro
                            Toast.makeText(atv, "Data inválida!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if (cal.equals(cal_bd)) { // data de hoje. não altera. manutenção já realizada!
                            Toast.makeText(atv, "Manutenção já realizada!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if(cal.after(cal_bd)){
                            data_nova = sdf.format(cal.getTime()).toString();
                            dis1.setText(data_nova);
                            dateSet = true;
                        }



                        //data_bd = cal_bd.toString();


                    }
                }, year, month, day);


                datepickerDialog.setOkText("Ok");
                datepickerDialog.setCancelText("Cancelar");
                datepickerDialog.setTitle("Calendário");
                datepickerDialog.show(atv.getFragmentManager(), "DatePicker");
            }
        });

        positivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dateSet) {
                    Toast.makeText(atv, "Por favor, digite a data", Toast.LENGTH_SHORT).show();
                } else {
                    crud = new BancoController(atv.getBaseContext());

                    m_Text = input.getEditText().getText().toString();

                    if (m_Text.equals("")) {
                        Toast.makeText(atv, "Por favor, digite novamente", Toast.LENGTH_SHORT).show();
                        input.setError("Digite a Km atual");
                    } else {
                        cursor = crud.carregaData();
                        int passa = Integer.parseInt(m_Text);
                        if (passa >= cursor.getInt(4)) {
                            km_txtview = atv.findViewById(R.id.txt_km_atual);
                            crud.updateKm(passa);
                            cursor = crud.carregaData();
                            km_txtview.setText(cursor.getInt(4) + " Km");
                            crud.updateManutencaoFiltro(passa, data_nova);
                            crud.updateKm(passa);
                            alerta.dismiss();
                        } else if (passa < cursor.getInt(4)) {
                            input.setError("Km menor do que a atual");
                            Toast.makeText(atv, "Digite a Km corretamente", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });

        negativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });





        alerta = atualizaKm.create();
        alerta.show();
    }


    public static void displayDialogOleo(Activity atv) {

        dis1 = (TextView) atv.findViewById(R.id.tv_date);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal = Calendar.getInstance();
        cal_bd = Calendar.getInstance();
        cal_bd_6_months = Calendar.getInstance();
        cal_today = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);





        crud = new BancoController(atv.getBaseContext());
        cursor = crud.carregaData();



        final AlertDialog.Builder atualizaKm = new AlertDialog.Builder(new ContextThemeWrapper(atv, android.R.style.Theme_DeviceDefault_Light_Dialog));
        LayoutInflater factory = LayoutInflater.from(atv);
        final View f = factory.inflate(R.layout.custom_dialog_maintenance, null);

        dis1 = f.findViewById(R.id.tv_date);
        atualizaKm.setView(f);
        input = new TextInputLayout(atv);
        input = (f.findViewById(R.id.input_km_troca));
        dateSet = false;

        Button positivo = f.findViewById(R.id.d_button);
        Button negativo = f.findViewById(R.id.n_button);
        Button btn_data = f.findViewById(R.id.btn_date);

        System.out.println(cal_bd.toString());

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datepickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int yearInside, int monthOfYear, int dayOfMonth) {
                        strDateManutencao = dayOfMonth + "/" + monthOfYear + "/" + yearInside;
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.YEAR, yearInside);

                        crud = new BancoController(atv.getBaseContext());
                        cursor2 = crud.carregaDataManut();
                        //cursor2 = crud.carregaData();
                        dataM = cursor2.getString(2);
                        //hj
                        try {
                            cal_bd.setTime(sdf.parse(dataM));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                        if ((cal.after(cal_today))) { // data depois de hoje, nao altera // erro
                            Toast.makeText(atv, "Insira apenas quando tiver feito a manutenção!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if(cal.before(cal_today)){ // data antes do BD, ou antes do BD // erro
                            Toast.makeText(atv, "Data inválida!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if (cal.equals(cal_bd)) { // data de hoje. não altera. manutenção já realizada!
                            Toast.makeText(atv, "Manutenção já realizada!", Toast.LENGTH_SHORT).show();
                            alerta.cancel();
                        }else if(cal.after(cal_bd)){
                            data_nova = sdf.format(cal.getTime()).toString();
                            dis1.setText(data_nova);
                            dateSet = true;
                        }



                        //data_bd = cal_bd.toString();


                    }
                }, year, month, day);


                datepickerDialog.setOkText("Ok");
                datepickerDialog.setCancelText("Cancelar");
                datepickerDialog.setTitle("Calendário");
                datepickerDialog.show(atv.getFragmentManager(), "DatePicker");
            }
        });

        positivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dateSet) {
                    Toast.makeText(atv, "Por favor, digite a data", Toast.LENGTH_SHORT).show();
                } else {
                    crud = new BancoController(atv.getBaseContext());

                    m_Text = input.getEditText().getText().toString();

                    if (m_Text.equals("")) {
                        Toast.makeText(atv, "Por favor, digite novamente", Toast.LENGTH_SHORT).show();
                        input.setError("Digite a Km atual");
                    } else {
                        cursor = crud.carregaData();
                        int passa = Integer.parseInt(m_Text);
                        if (passa >= cursor.getInt(4)) {
                            km_txtview = atv.findViewById(R.id.txt_km_atual);
                            crud.updateKm(passa);
                            cursor = crud.carregaData();
                            km_txtview.setText(cursor.getInt(4) + " Km");
                            crud.updateManutencaoOleo(passa, data_nova);
                            crud.updateKm(passa);
                            Toast.makeText(atv, "Manutenção Adicionada!", Toast.LENGTH_SHORT).show();
                            alerta.dismiss();
                        } else if (passa < cursor.getInt(4)) {
                            input.setError("Km menor do que a atual");
                            Toast.makeText(atv, "Digite a Km corretamente", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });

        negativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });





        alerta = atualizaKm.create();
        alerta.show();
    }


}


