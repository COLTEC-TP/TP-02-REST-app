package coltectp.github.io.tp_02_rest_app.blockExplorer;

public class Input {
    private Output previousOutput;
    private long sequence;
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

    @Override
    public int hashCode () {
        int result = previousOutput == null ? 1 : previousOutput.hashCode();
        result = 31 * result + (int) (sequence ^ (sequence >>> 32));
        result = 31 * result + scriptSignature.hashCode();
        return result;
    }
}
