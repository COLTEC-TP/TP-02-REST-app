package coltectp.github.io.tp_02_rest_app.blockExplorer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Input {

    @SerializedName("prev_out")
    @Expose
    private Output previousOutput;

    @SerializedName("sequence")
    @Expose
    private long sequence;

    @SerializedName("witness")
    @Expose
    private String witness;

    @SerializedName("script")
    @Expose
    private String scriptSignature;

    public Input (Output previousOutput, long sequence, String scriptSignature) {
        this.previousOutput = previousOutput;
        this.sequence = sequence;
        this.scriptSignature = scriptSignature;
    }

    public Output getPreviousOutput() {
        return previousOutput;
    }

    public long getSequence() {
        return sequence;
    }

    public String getScriptSignature() {
        return scriptSignature;
    }

    public String getWitness() {
        return witness;
    }

    @Override
    public int hashCode () {
        int result = previousOutput == null ? 1 : previousOutput.hashCode();
        result = 31 * result + (int) (sequence ^ (sequence >>> 32));
        result = 31 * result + scriptSignature.hashCode();
        return result;
    }
}
