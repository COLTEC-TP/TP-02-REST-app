package searchAdapter;

import android.content.Context;

import java.util.ArrayList;

import restapi.artmusBusca.ArtMusDocs;

public class BuscaDAO {
    private ArrayList<ArtMusDocs> resultados;
    private static BuscaDAO instance;
    private Context context;

    private BuscaDAO(Context cont){
        context = cont;
        resultados = new ArrayList<>();
        carregaBusca();
    }

    public static BuscaDAO getInstance(Context cont) {
        if(instance == null)
            instance = new BuscaDAO(cont);
        return instance;
    }

    private void carregaBusca() {
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

    public ArrayList<ArtMusDocs> getBusca() {
        return instance.resultados;
    }


}

