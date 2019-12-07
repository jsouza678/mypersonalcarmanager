package souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.data.model.notification;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;

public class ListaNotificationsAdapter extends RecyclerView.Adapter<ListaNotificationsAdapter.ListaNotificationsViewHolder> {

    private List<notification> listN;

    public ListaNotificationsAdapter(List<notification> listN) {
        this.listN = listN;
    }

    @NonNull
    @Override
    public ListaNotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);

        return new ListaNotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNotificationsViewHolder holder, int position) {
        //holder.textNomeVeiculo.setText(veiculos.get(position).getNome());
        holder.textNomeVeiculo.setText(listN.get(position).getNome());
        holder.text_km_filtros.setText(listN.get(position).getKm_prox());
        holder.text_km_oleo.setText(listN.get(position).getKm_oleo());
        holder.img_maintenance.setImageResource(listN.get(position).getImage());
        //txt_km.setText(texto_km);
    }

    @Override
    public int getItemCount() {
        //return (veiculos!= null && veiculos.size()>0) ? veiculos.size() : 0;
        return listN.size();
    }

    static class ListaNotificationsViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomeVeiculo;
        private TextView text_km_oleo;
        private TextView text_km_filtros;
        private ImageView img_maintenance;


        public ListaNotificationsViewHolder(View itemView){
            super(itemView);

            textNomeVeiculo = itemView.findViewById(R.id.txt_teste2);
            text_km_filtros = itemView.findViewById(R.id.txt_prox);
            text_km_oleo = itemView.findViewById(R.id.txt_km);
            img_maintenance = itemView.findViewById(R.id.img_maint);
        }

    }

}
