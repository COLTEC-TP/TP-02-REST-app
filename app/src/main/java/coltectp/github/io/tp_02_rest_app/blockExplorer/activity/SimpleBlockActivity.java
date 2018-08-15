package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.HashMap;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlock;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlockList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleBlockActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private BlockchainAPI mService;
    private SimpleBlockRecyclerViewAdapter mAdapter;
    private SimpleBlockList mSimpleBlocks;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_block);

        mContext = getApplicationContext();
        mLayout = findViewById(R.id.simpleBlockActivityLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 4, true));

        mService = new RetrofitConfig(mContext).getInfoBlockchain(mContext);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_block_activity, menu);

        MenuItem item = menu.findItem(R.id.action_search_property);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                makeCall(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void makeCall (String searchedPool) {
        Map<String, String> data = new HashMap<>();
        data.put("format", "json");

        Log.d(SimpleBlockList.class.getSimpleName(), searchedPool);

        Call<SimpleBlockList> blocksBySpecificPoolCall =
                mService.getBlocksBySpecificPool(searchedPool);

        // fazendo a requisição de forma assíncrona
        blocksBySpecificPoolCall.enqueue(new Callback<SimpleBlockList>() {
            @Override
            public void onResponse(@NonNull Call<SimpleBlockList> call, @NonNull Response<SimpleBlockList> response) {
                Log.d(SimpleBlockList.class.getSimpleName(), "Making msg");
                mSimpleBlocks = response.body();

                if (mSimpleBlocks != null) {

                    mAdapter = new SimpleBlockRecyclerViewAdapter(mSimpleBlocks.getBlocks());
                    mAdapter.setOnItemClickListener(new SimpleBlockRecyclerViewAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Intent intent = new Intent(SimpleBlockActivity.this, SingleBlockActivity.class);
                            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
                            SimpleBlock item = mSimpleBlocks.getBlock(itemPosition);
                            String hash = item.getHash();

                            Bundle args = new Bundle();
                            args.putCharSequence("hash", hash);
                            intent.putExtras(args);

                            startActivity(intent);
                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);

                } else {

                    // Erro: Esse app parou de funcionar
                    Snackbar.make(mLayout, "Nada encontrado", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SimpleBlockList> call, @NonNull Throwable t) {
                Log.d(SimpleBlockList.class.getSimpleName(), "Failed", t);
            }
        });
    }
}
