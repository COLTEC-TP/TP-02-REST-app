package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Block;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Output;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlockList;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Transaction;
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
    private AlertDialog.Builder alertBuilder;
    private ListView mListView;
    private AlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_block);

        Bundle activityBundle = this.getIntent().getExtras();
        assert activityBundle != null;
        String hash = activityBundle.getString("hash");
        mContext = getApplicationContext();
        mLayout = findViewById(R.id.singleBlockLayoutActivity);
        mRecyclerView = (RecyclerView) findViewById(R.id.single_block_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 4, true));

        alertBuilder = new AlertDialog.Builder(SingleBlockActivity.this);

        // Alert Dialog
        alertBuilder.setTitle("Transation");


        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.transaction_dialog_list, null);
        alertBuilder.setView(convertView);
        mListView = (ListView) convertView.findViewById(R.id.list_dialog);


        alertBuilder.setPositiveButton("Estou ciente!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Fique por sua conta e risco", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });


        alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Muito bem!!", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();

            }
        });

       mDialog = alertBuilder.create();

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
                    mAdapter.setOnItemClickListener(new SimpleBlockRecyclerViewAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
                            Transaction item = mBlock.getTransaction(itemPosition);
                            String hash = item.getHash();

                            SingleTransactionAdapter adapter = new SingleTransactionAdapter(SingleBlockActivity.this, item.getOutputs());
                            mListView.setAdapter(adapter);

                            mDialog.show();

                        }
                    });
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
