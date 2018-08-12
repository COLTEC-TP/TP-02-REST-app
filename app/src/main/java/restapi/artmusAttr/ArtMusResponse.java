package restapi.artmusAttr;

import java.util.ArrayList;
public class ArtMusResponse {
    private Integer numFound;
    private Integer start;
    private ArrayList <ArtMusDocs> docs;

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

    public ArrayList<ArtMusDocs> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<ArtMusDocs> docs) {
        this.docs = docs;
    }
}
