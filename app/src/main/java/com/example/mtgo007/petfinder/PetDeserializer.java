package com.example.mtgo007.petfinder;

/**
 * Created by a2016952827 on 09/08/18.
 */

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class PetDeserializer implements JsonDeserializer<List> {

    @Override
    public List<Pet> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        List<Pet> pets = new ArrayList<>();

        JsonObject resp = jsonObject.get("petfinder").getAsJsonObject();
        resp = resp.get("pets").getAsJsonObject();
        JsonArray p = resp.get("pet").getAsJsonArray();

        for(int i =0; i<p.size();i++){
            JsonObject obj = p.get(i).getAsJsonObject();
            Pet pet = new Pet();

            JsonObject nome = obj.get("name").getAsJsonObject();
            pet.setNome(nome.get("$t").getAsString());

            JsonObject descricao = obj.get("description").getAsJsonObject();
            if (descricao.isJsonNull() != false) {
                pet.setDescricao(descricao.get("$t").getAsString());
            }

            JsonObject contato = obj.getAsJsonObject("contact");
//
            JsonObject telefone = contato.get("phone").getAsJsonObject();
            if(telefone.get("$t") != null){

                pet.setTelefone(telefone.get("$t").getAsString());
            } else{
                pet.setTelefone("");
            }
//
//
//
            if(contato.get("state") != null){
                JsonObject estado = contato.get("state").getAsJsonObject();
                pet.setEstado(estado.get("$t").getAsString());
            } else {
                pet.setEstado("");
            }

//
//
            if(contato.get("city") != null){
                JsonObject cidade = contato.get("city").getAsJsonObject();
                pet.setCidade(cidade.get("$t").getAsString());
            } else {
                pet.setCidade("");
            }
//
//
            JsonObject endereco = contato.get("address1").getAsJsonObject();
            if(endereco.get("$t") != null){

                pet.setEndereco(endereco.get("$t").getAsString());
            } else{
                pet.setEndereco("");
            }
//
//
//
            if(obj.get("age") != null){
                JsonObject idade = obj.get("age").getAsJsonObject();
                pet.setIdade(idade.get("$t").getAsString());
            } else {
                pet.setIdade("");
            }
//
//
//
            if(obj.get("sex") != null){
                JsonObject sexo = obj.get("sex").getAsJsonObject();
                pet.setSexo(sexo.get("$t").getAsString());
            } else {
                pet.setSexo("");
            }
//
//
            JsonObject raca = obj.get("breeds").getAsJsonObject();


            if(raca.isJsonNull() != false){

                raca = raca.get("breed").getAsJsonObject();
                pet.setRaça(raca.get("$t").getAsString());
            } else {
                pet.setRaça("");
            }
////
//
            JsonObject foto = obj.get("media").getAsJsonObject();
            if(foto.get("photos") != null){
                foto = foto.get("photos").getAsJsonObject();
                JsonArray fotos = foto.getAsJsonArray("photo");
                foto = fotos.get(2).getAsJsonObject();
                pet.setFoto(foto.get("$t").getAsString());
            } else{
                pet.setFoto(null);
            }
            pets.add(pet);
        }

        return (pets);
    }

}