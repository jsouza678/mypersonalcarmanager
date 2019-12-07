package souza.home.com.mypersonalcarassistant.data.network.response;

import com.squareup.moshi.Json;

public class MarcasResponse {

    @Json(name = "nome")
    private final String name;

    @Json(name = "codigo")
    private final String codigo;

    public MarcasResponse(String name, String codigo) {
        this.name = name;
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return name;
    }
}
