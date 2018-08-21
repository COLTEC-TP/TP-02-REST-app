package restapi;


import restapi.artmusBusca.ArtMusResponse;
import restapi.musicadadosAttr.MusicaDados;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VagalumeService {
    @GET("search.php")
    Call <MusicaDados> getMusica(@Query("art") String artista,
                                   @Query("mus") String musica,
                                   @Query("apikey") String key
    );

    @GET("search.artmus")
    Call <ArtMusResponse> buscaArtmus(@Query("q") String query,
                                      @Query("limit") String limit,
                                      @Query("apikey") String key);

   /* @GET("{art}/discografia/index.js")
    Call <Discografia> getDiscografia(@Query("art") String query);
*/
}
