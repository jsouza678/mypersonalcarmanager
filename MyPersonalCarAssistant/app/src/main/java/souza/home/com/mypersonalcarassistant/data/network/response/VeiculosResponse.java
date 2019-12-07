package souza.home.com.mypersonalcarassistant.data.network.response;

import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

public class VeiculosResponse {

    @Json(name="nome")
    private final String nome;

    private final String img;

    @Json(name="codigo")
    private final String codigo;

    public VeiculosResponse(String nome, String codigo, String img) {
        this.nome = nome;
        this.img = img;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getImg() {
        return img;
    }


    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
