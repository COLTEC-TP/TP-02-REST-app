package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Block;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlockList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleBlockActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private BlockchainAPI mService;
    private SingleBlockRecyclerViewAdapter mAdapter;
    private Block mBlock;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_block);

        Bundle activityBundle = this.getIntent().getExtras();
        assert activityBundle != null;
        String hash = activityBundle.getString("hash");
        mContext = getApplicationContext();
        mLayout = findViewById(R.id.singleBlockLayoutActivity);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 4, true));

        mService = new RetrofitConfig(mContext).getInfoBlockchain(mContext);

        makeCall(hash);
    }

    private void makeCall (String hash) {
        Map<String, String> data = new HashMap<>();
        data.put("format", "json");

        Log.d(SingleBlockActivity.class.getSimpleName(), hash);

        Call<Block> blocksBySpecificHashCall =
                mService.getSingleBlock(hash);

        // fazendo a requisição de forma assíncrona
        blocksBySpecificHashCall.enqueue(new Callback<Block>() {
            @Override
            public void onResponse(@NonNull Call<Block> call, @NonNull Response<Block> response) {
                Log.d(SimpleBlockList.class.getSimpleName(), "Making msg");
                mBlock = response.body();

                if (mBlock != null) {

                    mAdapter = new SingleBlockRecyclerViewAdapter(mBlock.getTransactions());
//                    mAdapter.setOnItemClickListener(new SimpleBlockRecyclerViewAdapter.ClickListener() {
//                        @Override
//                        public void onItemClick(int position, View v) {
//                            Intent intent = new Intent(SingleBlockActivity.this, TransactionActivity.class);
//                            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
//                            Transaction item = mBlock.getTransaction(itemPosition);
//                            String hash = item.getHash();
//
//                            Bundle args = new Bundle();
//                            args.putCharSequence("hash", hash);
//                            intent.putExtras(args);
//
//                            startActivity(intent);
//                        }
//                    });
                    mRecyclerView.setAdapter(mAdapter);

                } else {

                    // Erro: Esse app parou de funcionar
                    Snackbar.make(mLayout, "Nada encontrado", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Block> call, @NonNull Throwable t) {
                Log.d(SimpleBlockList.class.getSimpleName(), "Failed", t);
                Snackbar.make(mLayout, "Nada encontrado", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
