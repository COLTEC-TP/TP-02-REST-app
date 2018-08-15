package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import coltectp.github.io.tp_02_rest_app.R;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        TextView textView = findViewById(R.id.hash_tv);
        Bundle activityBundle = this.getIntent().getExtras();
        assert activityBundle != null;
        String hash = activityBundle.getString("hash");

        textView.setText(hash);
    }
}
