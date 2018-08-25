package br.ufmg.coltec.lolzinho;
/**
 * Created by a2016951782 on 02/08/18.
 */

import retrofit2.Call;
import retrofit2.http.GET;

public interface SummonerService {

    @GET("/lol/static-data/v3/champions?api_key=RGAPI-8fbdcb19-81a8-4e0c-ae53-45b5bd27d5c8")
    Call<Data> getChampionList();

}

