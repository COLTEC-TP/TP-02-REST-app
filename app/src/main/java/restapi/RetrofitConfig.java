package restapi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    // Objeto que fará o acesso a API do serviço
    private final Retrofit retrofit;

    public RetrofitConfig() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vagalume.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public VagalumeService getVagalumeService() {
        return this.retrofit.create(VagalumeService.class);
    }

}