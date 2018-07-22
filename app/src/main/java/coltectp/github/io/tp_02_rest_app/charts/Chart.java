package coltectp.github.io.tp_02_rest_app.charts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chart {
    @SerializedName("status")
    private String status;

    @SerializedName("name")
    private String name;

    @SerializedName("unit")
    private String unit;

    @SerializedName("period")
    private String period;

    @SerializedName("description")
    private String description;

    @SerializedName("values")
    private List<Point> points;

    public Chart(String status, String name, String unit, String period, String description, List<Point> points) {
        this.status = status;
        this.name = name;
        this.unit = unit;
        this.period = period;
        this.description = description;
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
