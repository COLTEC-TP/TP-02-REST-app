package restapi;


import restapi.artmusAttr.ArtMus;
import restapi.musicadadosAttr.MusicaDados;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VagalumeService {
    @GET("search.php")
    Call <MusicaDados> searchLetra(@Query("art") String artista,
                                   @Query("mus") String musica);

    @GET("search.artmus")
    Call <ArtMus> searchArtmus(@Query("q") String query,
                               @Query("limit") String limit);
}
