package restapi;


import restapi.artmusModel.MusResponse;
import restapi.musicadadosModel.MusicaDados;
import restapi.rankingModel.RankingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VagalumeService {
    @GET("search.php")
    Call <MusicaDados> getMusica(@Query("art") String artista,
                                   @Query("mus") String musica,
                                   @Query("apikey") String key
    );

    @GET("search.excerpt")
    Call <MusResponse> buscaArtmus(@Query("q") String query,
                                   @Query("limit") String limit,
                                   @Query("apikey") String key);
    @GET("rank.php")
    Call <RankingResponse> obterRanking(@Query("type") String type,
                                        @Query("period") String period,
                                        @Query("scope") String scope,
                                        @Query("limit") String limit,
                                        @Query("apikey") String key);



}
