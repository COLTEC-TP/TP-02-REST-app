package restapi.rankingModel;

public class Art {
    private String id;
    private String url;
    private String name;
    private String pic_medium;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_medium() {
        return pic_medium;
    }

    public void setPic_medium(String pic_medium) {
        this.pic_medium = pic_medium;
    }
}
