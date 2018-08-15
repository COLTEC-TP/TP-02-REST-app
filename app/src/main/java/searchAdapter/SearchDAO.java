package searchAdapter;

import android.content.Context;

import java.util.ArrayList;

import restapi.artmusAttr.ArtMusDocs;

public class SearchDAO {
    private ArrayList<ArtMusDocs> resultados;
    private static SearchDAO instance;
    private Context context;

    private SearchDAO(Context cont){
        context = cont;
        resultados = new ArrayList<>();
        carregaSearch();
    }

    public static SearchDAO getInstance(Context cont) {
        if(instance == null)
            instance = new SearchDAO(cont);
        return instance;
    }

    private void carregaSearch() {
        /*resultados.add(new ArtMusDocs("456789", 2, "oi.com", "2u", "u2"));
        resultados.add(new ArtMusDocs("456789", 2, "oi.com", "2u", "u2"));
        resultados.add(new ArtMusDocs("456789", 2, "oi.com", "2u", "u2"));
        */
    }
    public void adiconaItem (ArtMusDocs novoItem){
        instance.resultados.add(novoItem);
    }
    public void removeItem (int position){
        instance.resultados.remove(position);
    }

    public ArrayList<ArtMusDocs> getSearch() {
        return instance.resultados;
    }


}

