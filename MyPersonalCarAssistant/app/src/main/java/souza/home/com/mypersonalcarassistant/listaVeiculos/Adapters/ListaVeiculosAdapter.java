package souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import souza.home.com.mypersonalcarassistant.data.network.response.VeiculosResponse;
import souza.home.com.mypersonalcarassistant.R;

public class ListaVeiculosAdapter extends RecyclerView.Adapter<ListaVeiculosAdapter.ListaVeiculosViewHolder> {

    private List<VeiculosResponse> veiculos;
    private String codigo;

    public ListaVeiculosAdapter(List<VeiculosResponse> veiculos) {
        this.veiculos = veiculos;
    }


    @NonNull
    @Override
    public ListaVeiculosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veiculo, parent, false);

        return new ListaVeiculosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVeiculosViewHolder holder, int position) {
        holder.textNomeVeiculo.setText(veiculos.get(position).getNome());
        codigo = veiculos.get(position).getCodigo();
    }

    @Override
    public int getItemCount() {
        return (veiculos!= null && veiculos.size()>0) ? veiculos.size() : 0;
    }

    static class ListaVeiculosViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomeVeiculo;

        public ListaVeiculosViewHolder(View itemView){
            super(itemView);

            textNomeVeiculo = itemView.findViewById(R.id.text_veiculo);
        }

    }


}

