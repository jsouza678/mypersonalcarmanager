package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.data.model.notification;
import souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters.ListaNotificationsAdapter;

public class NotificationsFragment extends Fragment {

    TextView txt_km, txt_prox;
    String ultima_km_oleo, proxima_km_oleo;
    String ultima_km_filtros, proxima_km_filtros;
    int km_oleo, km_filtros;
    String data_oleo;
    int km_atual;
    String data_filtros;
    Notificador notificador;

    Calendar cal_today, cal_oleo, cal_futuro_oleo, cal_filtros, cal_futuro_filtros;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView;
        textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, textView::setText);

        txt_km = root.findViewById(R.id.txt_km);
        txt_prox = root.findViewById(R.id.txt_prox);
        //Button botao = root.findViewById(R.id.button123);
        notificador = new Notificador();




        BancoController crud = new BancoController( Objects.requireNonNull(getActivity()).getBaseContext() );
        Cursor cursor = crud.carregaDataManut();
        Cursor cursor2 = crud.carregaData();

        ultima_km_oleo = cursor.getString(2) + " ou " + cursor.getString(0) + " Km";
        ultima_km_filtros = cursor.getString(3) + " ou " + cursor.getString(1) + " Km";
        data_oleo = cursor.getString(2);
        data_filtros = cursor.getString(3);
        km_atual = Integer.parseInt(cursor2.getString(4));

        km_oleo = Integer.parseInt(cursor.getString(0) );
        km_filtros = Integer.parseInt(cursor.getString(1) );

        @SuppressLint("SimpleDateFormat") DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        cal_today = Calendar.getInstance();

        cal_oleo = Calendar.getInstance();
        cal_futuro_oleo = Calendar.getInstance();
        try {
            cal_oleo.setTime(sdf.parse(data_oleo));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal_filtros = Calendar.getInstance();
        cal_futuro_filtros = Calendar.getInstance();
        try {
            cal_filtros.setTime(sdf.parse(data_filtros));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        cal_futuro_oleo = cal_oleo;
        cal_futuro_oleo.add(Calendar.YEAR, 1);

        cal_futuro_filtros = cal_filtros;
        cal_futuro_filtros.add(Calendar.YEAR, 1);

        /*
        botao.setOnClickListener(v -> {
            cal_today.add(Calendar.MONTH, 1);

            @SuppressLint("SimpleDateFormat") DateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(sdf1.format(cal_today.getTime()));

            notificador.notificar(getActivity(), km_oleo, km_filtros, km_atual, cal_futuro_oleo, cal_futuro_oleo, cal_futuro_filtros);

           // if(cal_today.equals(cal_futuro_oleo) || (cal_today.after(cal_futuro_filtros)) || km_oleo < (km_atual + 100000) || km_filtros < (km_atual + 100000)) {
             //   Toast.makeText(getContext(), "NOTIFICAR PORRAAAA TESTANDO BIRL", Toast.LENGTH_SHORT).show();
            //}
        });
        */

        notificador.notificar(getActivity(), km_oleo, km_filtros, km_atual, cal_today, cal_futuro_oleo, cal_futuro_filtros);


        proxima_km_filtros = sdf.format(cal_futuro_filtros.getTime()) + " ou " + (Integer.parseInt(cursor.getString(1)) + 10000) + " Km";
        proxima_km_oleo = sdf.format(cal_futuro_oleo.getTime()) + " ou " + (Integer.parseInt(cursor.getString(0)) + 10000) + " Km";



        RecyclerView recyclerView = root.findViewById(R.id.my_recycler_view1);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ListaNotificationsAdapter(criaNotifications()));



        return root;


    }

    private List<notification> criaNotifications(){
        return Arrays.asList(
                new notification("Troca de óleo e filtro", ultima_km_oleo, proxima_km_oleo, R.drawable.oil),
                new notification("Filtro de ar", ultima_km_filtros, proxima_km_filtros, R.drawable.air),
                new notification("Filtro de combustivel", ultima_km_filtros, proxima_km_filtros, R.drawable.fuel),
                new notification("Filtro de ar condicionado", ultima_km_filtros, proxima_km_filtros, R.drawable.cond)

        );

    }


}