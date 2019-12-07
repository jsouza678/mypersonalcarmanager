package souza.home.com.mypersonalcarassistant.data.network.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiService {


    private static VeiculosService INSTANCE;

    public static VeiculosService getInstance() {
        if(INSTANCE == null){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1/carros/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

            INSTANCE = retrofit.create(VeiculosService.class);
         }
        return INSTANCE;
    }

}
