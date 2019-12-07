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
import souza.home.com.mypersonalcarassistant.data.network.response.MarcasResponse;

public class ListaMarcasAdapter extends RecyclerView.Adapter<ListaMarcasAdapter.ListaVeiculosViewHolder> {


    private Context context;
    private List<MarcasResponse> marcas;
    private String codigo;

    public ListaMarcasAdapter(Context context, List<MarcasResponse> marcas) {
        this.context = context;
        this.marcas = marcas;
    }



    @NonNull
    @Override
    public ListaVeiculosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veiculo, parent, false);

        return new ListaVeiculosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVeiculosViewHolder holder, int position) {
        holder.textNomeVeiculo.setText(marcas.get(position).getName());
        codigo = marcas.get(position).getCodigo();

    }

    @Override
    public int getItemCount() {
        return (marcas!= null && marcas.size()>0) ? marcas.size() : 0;
    }

    static class ListaVeiculosViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomeVeiculo;

        public ListaVeiculosViewHolder(View itemView){
            super(itemView);


            textNomeVeiculo = itemView.findViewById(R.id.text_veiculo);

        }

    }


}
