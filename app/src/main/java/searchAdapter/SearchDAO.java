package searchAdapter;

import android.content.Context;

import java.util.ArrayList;

import restapi.artmusAttr.ArtMusDocs;

public class SearchDAO {
    private ArrayList<ArtMusDocs> resultados;
    private static SearchDAO instance;
    Context context;

    private SearchDAO(Context cont){
        context = cont;
        resultados = new ArrayList<>();
    }

    public static SearchDAO getInstance(Context cont) {
        if(instance == null)
            instance = new SearchDAO(cont);
        return instance;
    }

    private void carregaSearch() {

        /*Aqui as informações serão recuperadas do banco de dados*/
//        produtos.add(new Produto("Sabão", "Limpeza", 15.54));
//        produtos.add(new Produto("Escova de dentes", "Higiene", 3.7));
//        produtos.add(new Produto("Escova de dentes", "Higiene", 3.7));

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

