package souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.data.network.response.AnosResponse;

public class ListaAnosAdapter extends RecyclerView.Adapter<ListaAnosAdapter.ListaVeiculosViewHolder> {


    private Context context;
    private List<AnosResponse> anos;
    private String codigo;

    public ListaAnosAdapter(Context context, List<AnosResponse> anos) {
        this.context = context;
        this.anos = anos;
    }



    @NonNull
    @Override
    public ListaVeiculosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veiculo, parent, false);

        return new ListaVeiculosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVeiculosViewHolder holder, int position) {
        holder.textNomeVeiculo.setText(anos.get(position).getAno());
        codigo = anos.get(position).getAno();

    }

    @Override
    public int getItemCount() {
        return (anos!= null && anos.size()>0) ? anos.size() : 0;
    }

    static class ListaVeiculosViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomeVeiculo;

        public ListaVeiculosViewHolder(View itemView){
            super(itemView);


            textNomeVeiculo = itemView.findViewById(R.id.text_veiculo);

        }

    }


}
