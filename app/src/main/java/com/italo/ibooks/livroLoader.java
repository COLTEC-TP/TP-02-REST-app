package com.italo.ibooks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


public class livroLoader extends AsyncTaskLoader<List<Livro>> {
    String url;
    public static List<Livro> arrayList = null;

    public livroLoader(Context context, String url) {
        super(context);
        if(url == null){
            return;
        }
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Livro> loadInBackground() {
        if(url == null) {
            return null;
        }
        arrayList = QueryUtils.fetchBooksData(url);
        return arrayList;
    }
}
