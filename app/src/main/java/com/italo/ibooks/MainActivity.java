package com.italo.ibooks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Livro>> {

    private static final String bookFetchUrl = "https://www.googleapis.com/books/v1/volumes";
    private RecyclerView recyclerView;
    public LivroAdapter adapter;

    //  ArrayList estático pra ligar a instância de classe e não tenhamos que inicializar novamente em onCreate

    public static ArrayList<Livro> livroList = null;
    private static final int BOOKS_LOADER_ID = 1;
    private EditText searchBox;
    private ProgressBar books_progressBar;
    private TextView empty_state;

    public static final String TAG = "MyActivity";

    @Override
    public Loader<List<Livro>> onCreateLoader(int id, Bundle args) {
        searchBox = (EditText) findViewById(R.id.searchBox);

        // Convertendo os espaços

        String query = searchBox.getText().toString();
        if(query.isEmpty() || query.length() == 0){
            searchBox.setError("Digite algo para ser pesquisado");
            return new livroLoader(this, null);
        }

        // URI

        Uri baseUri = Uri.parse(bookFetchUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", query);

        // click searchButton teclado sobe

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        searchBox.setText("");

        //Returning a new Loader Object retornando o objeto
        return new livroLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Livro>> loader, List<Livro> list) {
        books_progressBar.setVisibility(View.GONE);
        if(list !=null && !list.isEmpty()){
            prepareBooks(list);
            Log.i(TAG, "onLoadFinished: ");
        }
        else{
            Toast InfoToast;
            InfoToast = Toast.makeText(MainActivity.this,"Sem Informações",Toast.LENGTH_SHORT);
            InfoToast.show();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Livro>> loader) {
        Log.i(QueryUtils.TAG, "onLoaderReset: ");
        if(adapter == null){
            return;
        }
        livroList.clear();
        adapter.notifyDataSetChanged();
        Log.i(TAG, "onLoaderReset: " + livroList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        books_progressBar = (ProgressBar) findViewById(R.id.books_progressBar);
        books_progressBar.setIndeterminate(true);
        books_progressBar.setVisibility(View.GONE);

        empty_state = (TextView) findViewById(R.id.empty_state);

        //Checando se tem internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            empty_state.setText("Sem Internet");
            empty_state.setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.searchButton)).setEnabled(false);
        }

        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if(savedInstanceState == null || !savedInstanceState.containsKey("booksList")){
            livroList = new ArrayList<>();
            adapter = new LivroAdapter(this, livroList);

            //log Statement
            Log.i(TAG, "onCreate: " + livroList);
        }else {
            livroList.addAll(savedInstanceState.<Livro>getParcelableArrayList("booksList"));

            //log statement
            Log.i(TAG, "onCreate: under else" + livroList);
            adapter = new LivroAdapter(this, livroList);
            // recarega o adapter
            adapter.notifyDataSetChanged();

        }


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
            Glide.with(this).load(R.drawable.biblioteca).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("booksList", livroList);
        super.onSaveInstanceState(outState);
    }

    public void searchButton(View view){
        books_progressBar.setVisibility(View.VISIBLE);
        livroList.clear();
        adapter.notifyDataSetChanged();
        getLoaderManager().restartLoader(BOOKS_LOADER_ID, null, this);
        getLoaderManager().initLoader(BOOKS_LOADER_ID, null, this);
        Log.i(TAG, "searchButton: "  + livroList);
    }


      //collapsing toolbar mostrando toolbar no scroll

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // ERROR:  This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and


        //  Mostrando o titulo quando o toolbar expande e colapsa
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareBooks(List<Livro> booksList) {

        livroList.addAll(booksList);
        Log.i(TAG, "prepareBooks: " + livroList);

        //notifiying the recycleradapter that data has been changed
        adapter.notifyDataSetChanged();
    }
      // grid
      // RecyclerView decoration,margin envolta do grid

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }

            else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


     // converte dp para pixel para não ficar bugado a thumbnail

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}