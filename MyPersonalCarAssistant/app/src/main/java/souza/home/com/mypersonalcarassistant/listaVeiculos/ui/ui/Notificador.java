package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import souza.home.com.mypersonalcarassistant.R;

class Notificador {
    private static AlertDialog alerta;

    void notificar(Activity atv, int km_oleo, int km_filtros, int km_atual, Calendar cal_today, Calendar cal_futuro_oleo, Calendar cal_futuro_filtros) {

        if ((cal_today.equals(cal_futuro_oleo)) || (cal_today.after(cal_futuro_filtros)) || (km_atual >= (km_oleo + 10000)) || (km_atual >= (km_filtros + 10000))) {
            //Toast.makeText(atv.getApplicationContext(), "Chegou a hora da manutenção!", Toast.LENGTH_SHORT).show();
            alertaD(atv);
        }

    }

    void alertaD(Activity atv){
        final AlertDialog.Builder notifica_ad = new AlertDialog.Builder(new ContextThemeWrapper(atv, android.R.style.Theme_DeviceDefault_Light_Dialog));
        LayoutInflater factory = LayoutInflater.from(atv);
        final View f = factory.inflate(R.layout.custom_dialog_notificador, null);

        Button positivo = f.findViewById(R.id.d_button);

        notifica_ad.setView(f);
        positivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });
        alerta = notifica_ad.create();
        alerta.show();
    }


}