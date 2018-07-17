package coltectp.github.io.tp_02_rest_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Point {
    @SerializedName("x")
    @Expose
    private float x;

    @SerializedName("y")
    @Expose
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
