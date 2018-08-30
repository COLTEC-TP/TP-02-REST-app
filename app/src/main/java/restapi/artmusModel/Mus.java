package restapi.artmusModel;

import java.util.ArrayList;
public class Mus {
    private Integer numFound;
    private Integer start;
    private ArrayList <MusDocs> docs;

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public ArrayList<MusDocs> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<MusDocs> docs) {
        this.docs = docs;
    }
}
