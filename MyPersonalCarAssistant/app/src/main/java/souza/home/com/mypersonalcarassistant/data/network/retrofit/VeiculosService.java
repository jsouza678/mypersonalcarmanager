package souza.home.com.mypersonalcarassistant.data.network.retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import souza.home.com.mypersonalcarassistant.data.model.Marcas;
import souza.home.com.mypersonalcarassistant.data.network.response.AnosResponse;
import souza.home.com.mypersonalcarassistant.data.network.response.MarcasResponse;
import souza.home.com.mypersonalcarassistant.data.network.response.VeiculosResult;

public interface VeiculosService {


        @GET("marcas")
        Call<List<MarcasResponse>> getMarcas();

        @GET("marcas/{id}/modelos")
        Call<VeiculosResult> getModels(@Path("id") String id);

        @GET("marcas/{idM}/modelos/{model}/anos")
        Call<List<AnosResponse>>  getAnos(@Path("idM") String idM, @Path("model") String model);

}
