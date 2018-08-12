package restapi.musicadadosAttr;

import java.util.ArrayList;
import restapi.musicadadosAttr.Artista;
import restapi.musicadadosAttr.Musica;

public class MusicaDados {
    private Artista art;
    private String badwords;
    private ArrayList<Musica> mus;
    private String type;

    public Artista getArtista() {
        return art;
    }

    public void setArtista(Artista art) {
        this.art = art;
    }

    public String getBadwords() {
        return badwords;
    }

    public void setBadwords(String badwords) {
        this.badwords = badwords;
    }

    public ArrayList<Musica> getMusica() {
        return mus;
    }

    public void setMusica(ArrayList<Musica> mus) {
        this.mus = mus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
