package rankingAdapter;

import android.content.Context;

import java.util.ArrayList;

import restapi.rankingModel.Art;
import restapi.rankingModel.MusicaRank;


public class RankingDAO {
    private ArrayList<MusicaRank> ranking;
    private static RankingDAO instance;
    private Context context;

    private RankingDAO(Context cont){
        context = cont;
        ranking = new ArrayList<>();
        carregaRanking();
    }

    public static RankingDAO getInstance(Context cont) {
        if(instance == null)
            instance = new RankingDAO(cont);
        return instance;
    }

    private void carregaRanking() {
    }
    public void adiconaItem (MusicaRank novoItem){
        instance.ranking.add(novoItem);
    }
    public void removeItem (int position){
        instance.ranking.remove(position);
    }

    public ArrayList<MusicaRank> getRanking() {
        return instance.ranking;
    }


}

