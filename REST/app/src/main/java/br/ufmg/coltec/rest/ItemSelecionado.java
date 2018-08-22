package br.ufmg.coltec.rest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ItemSelecionado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selecionado);

        Bundle data = this.getIntent().getExtras();
        String nome = data.getString("nome");
        final String valor = data.getString("valor");

        final float valorF = Float.parseFloat(valor);
        final float conversaoF = 1 / valorF;

        String conversaoS = String.valueOf(conversaoF);
        EditText lblvaueInput1 = findViewById(R.id.textView19);
        EditText lblvaueInput2 = findViewById(R.id.textView24);
        TextView lblNome1 = findViewById(R.id.textView18);
        TextView lblNome2 = findViewById(R.id.textView22);
        TextView lblNome3 = findViewById(R.id.textView1);
        final TextView lblValor1 = findViewById(R.id.textView20);
        final TextView lblValor2 = findViewById(R.id.textView25);

        lblvaueInput1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    lblValor1.setText("");
                } else {
                    lblValor1.setText(String.valueOf(Float.parseFloat(editable.toString()) * valorF));
                }
            }
        });

        lblvaueInput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    lblValor2.setText("");
                } else {
                    lblValor2.setText(String.valueOf(Float.parseFloat(editable.toString()) * conversaoF));
                }
            }
        });

        lblNome1.setText(nome);
        lblNome2.setText(nome);
        lblNome3.setText(nome);
        lblValor1.setText(valor);
        lblValor2.setText(conversaoS);
    }
}
