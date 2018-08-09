package com.example.mtgo007.petfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by a2016952827 on 09/08/18.
 */

public class PetAdapter extends BaseAdapter {

    private List<Pet> pets;
    private Context context;

    public PetAdapter(Context c, List<Pet> p){
        this.context = c;
        this.pets = p;
    }

    @Override
    public int getCount() {
        return this.pets.size();
    }

    @Override
    public Object getItem(int i) {
        return this.pets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Pet pet = this.pets.get(i);

        // cria o componente que será carregado na lista
        View newView = LayoutInflater.from(this.context).inflate(R.layout.pet_layout, viewGroup, false);

        ImageView foto = newView.findViewById(R.id.foto);
        if(pet.getFoto() != null){
            new DownloadImageTask(foto)
                    .execute(pet.getFoto());
        }


        TextView nome = newView.findViewById(R.id.nome);
        nome.setText(pet.getNome());

        TextView telefone = newView.findViewById(R.id.telefone);
        telefone.setText(pet.getTelefone());

        TextView estado = newView.findViewById(R.id.estado);
        estado.setText(pet.getEstado());

        TextView cidade = newView.findViewById(R.id.cidade);
        cidade.setText(pet.getCidade());

        TextView endereco = newView.findViewById(R.id.endereco);
        endereco.setText(pet.getEndereco());

        TextView idade = newView.findViewById(R.id.idade);
        idade.setText(pet.getIdade());

        TextView sexo = newView.findViewById(R.id.sexo);
        sexo.setText(pet.getSexo());

        TextView raca = newView.findViewById(R.id.raca);
        raca.setText(pet.getRaça());


        return newView;
    }
}
