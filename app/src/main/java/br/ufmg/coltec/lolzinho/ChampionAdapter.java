package br.ufmg.coltec.lolzinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951782 on 23/08/18.
 */

public class ChampionAdapter extends BaseAdapter {

    // lista que conterá a linguagens a serem exibidas
    private List<Champion> champions;
    private Context context;

    public ChampionAdapter(Context context, ArrayList<Champion> champs) {
        this.context = context;
        this.champions = champs;
        //... carrega dados da lista
    }

    @Override
    public int getCount() {
        return this.champions.size();
    }

    @Override
    public Object getItem(int i) {
        return this.champions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Champion champ = this.champions.get(i);

        View newView = LayoutInflater.from(this.context).inflate(R.layout.adapter_champion, viewGroup, false);

        TextView lblName = newView.findViewById(R.id.nameChampion);
        TextView lblAuthor = newView.findViewById(R.id.titleChampion);

        lblName.setText(champ.nome);
        lblAuthor.setText(champ.titulo);
        new DownloadImageTask((ImageView) newView.findViewById(R.id.champImage))
                .execute("http://ddragon.leagueoflegends.com/cdn/8.16.1/img/champion/"+ champ.nome  +".png");

        return newView;
    }
}
