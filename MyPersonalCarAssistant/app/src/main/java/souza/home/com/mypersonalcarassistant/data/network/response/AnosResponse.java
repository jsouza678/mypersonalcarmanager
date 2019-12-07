package souza.home.com.mypersonalcarassistant.data.network.response;

import com.squareup.moshi.Json;

public class AnosResponse {

    @Json(name = "nome")
    private String ano;


    public String getAno() {
        return ano;
    }

    public AnosResponse(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return ano;
    }
}
