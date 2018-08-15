package coltectp.github.io.tp_02_rest_app.blockExplorer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimpleBlockList {
    @SerializedName("blocks")
    @Expose
    private List<SimpleBlock> blocks;

    public SimpleBlockList(List<SimpleBlock> blocks) {
        this.blocks = blocks;
    }

    public List<SimpleBlock> getBlocks() {
        return blocks;
    }

    public SimpleBlock getBlock(int i) {return blocks.get(i);}

    public void setBlocks(List<SimpleBlock> blocks) {
        this.blocks = blocks;
    }
}
