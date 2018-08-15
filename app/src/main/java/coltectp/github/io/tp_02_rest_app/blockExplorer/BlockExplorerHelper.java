package coltectp.github.io.tp_02_rest_app.blockExplorer;

import java.math.BigDecimal;
import java.util.List;

public class BlockExplorerHelper {
    public static BigDecimal sumOfValueOut (List<Output> outputs) {
        long sum = 0;

        for (Output output: outputs) {
            sum += output.getValue();
        }

        return new BigDecimal(sum).divide(BigDecimal.valueOf(100000000));
    }

    public static BigDecimal sumOfInputValue (List<Input> inputs) {
        long sum = 0;

        for (Input input : inputs) {
            if (input.getPreviousOutput() != null) {
                sum += input.getPreviousOutput().getValue();
            }
        }

        return new BigDecimal(sum).divide(BigDecimal.valueOf(100000000));
    }
}
