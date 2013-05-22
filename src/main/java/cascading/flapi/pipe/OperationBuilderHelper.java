package cascading.flapi.pipe;

import cascading.operation.Operation;
import cascading.tuple.Fields;

/**
 * Abstract Helper for the {@link Operation} builders.
 * 
 * Handles the fields-related actions.
 */
abstract class OperationBuilderHelper {

    protected Fields argumentSelector = Fields.ALL;

    protected Fields outputSelector = Fields.ALL;

    public void select(@SuppressWarnings("rawtypes") Comparable... arguments) {
        if (arguments != null && arguments.length > 0) {
            argumentSelector = PipeBuilder.getSelector(arguments);
        }
    }

    public void produce(@SuppressWarnings("rawtypes") Comparable... output) {
        if (output != null && output.length > 0) {
            outputSelector = PipeBuilder.getSelector(output);
        }
    }

}
