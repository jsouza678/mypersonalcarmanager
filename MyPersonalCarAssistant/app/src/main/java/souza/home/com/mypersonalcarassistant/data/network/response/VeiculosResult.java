package souza.home.com.mypersonalcarassistant.data.network.response;

import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

import java.util.Collection;
import java.util.List;

import souza.home.com.mypersonalcarassistant.data.model.Veiculo;

public class VeiculosResult {

    @Json(name="modelos")
    private final List<VeiculosResponse> modelos;

    public VeiculosResult(List<VeiculosResponse> modelos) {
        this.modelos = modelos;
    }


    public List<VeiculosResponse> getModelos() {
        return modelos;
    }



    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
