package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Block;
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
    private AlertDialog.Builder alertBuilder;
    private ListView mListOuputViewDialog;
    private ListView mListInputViewDialog;
    private AlertDialog mDialog;
    private RelativeLayout mLayout;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_block);

        Bundle activityBundle = this.getIntent().getExtras();
        assert activityBundle != null;
        String hash = activityBundle.getString("hash");
        mContext = getApplicationContext();
        mProgressBar = findViewById(R.id.progressBar);
        mLayout = (RelativeLayout) findViewById(R.id.singleBlockLayoutActivity);
        mRecyclerView = (RecyclerView) findViewById(R.id.single_block_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 4, true));

        alertBuilder = new AlertDialog.Builder(SingleBlockActivity.this);

        // Alert Dialog
        alertBuilder.setTitle("Transaction");


        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.transaction_dialog_list, null);
        alertBuilder.setView(convertView);

        mListInputViewDialog = (ListView) convertView.findViewById(R.id.list_input_dialog);
        mListOuputViewDialog = (ListView) convertView.findViewById(R.id.list_output_dialog);

        alertBuilder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mDialog = alertBuilder.create();

        mService = new RetrofitConfig(mContext).getInfoBlockchain(mContext);

        makeCall(hash);
        showProgress(true);
    }

    private void showProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showRecyclerView(boolean show) {
        mRecyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
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

                            SingleTransactionOutputAdapter outputAdapter =
                                    new SingleTransactionOutputAdapter(SingleBlockActivity.this,
                                                                                item.getOutputs());
                            mListOuputViewDialog.setAdapter(outputAdapter);

                            SingleTransactionInputAdapter inputAdapter =
                                    new SingleTransactionInputAdapter(SingleBlockActivity.this,
                                                                                item.getInputs());

                            mListInputViewDialog.setAdapter(inputAdapter);
                            mDialog.show();

                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);
                    showProgress(false);
                    showRecyclerView(true);
                } else {
                    showProgress(false);
                    showRecyclerView(false);
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
