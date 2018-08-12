package restapi.artmusAttr;

public class ArtMusDocs {
    private String id;
    private Integer langID;
    private String url;
    private String title;
    private String band;

    public ArtMusDocs(String id, Integer langID, String url, String title, String band){
        this.id = id;
        this.langID = langID;
        this.url = url;
        this.title = title;
        this.band = band;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLangID() {
        return langID;
    }

    public void setLangID(Integer langID) {
        this.langID = langID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }
}
