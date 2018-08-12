package restapi.artmusAttr;

public class ArtMusDocs {
    private String id;
    private Integer landID;
    private String url;
    private String title;
    private String band;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLandID() {
        return landID;
    }

    public void setLandID(Integer landID) {
        this.landID = landID;
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
