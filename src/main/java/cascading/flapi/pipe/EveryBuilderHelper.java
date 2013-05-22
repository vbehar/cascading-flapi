package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.operation.Aggregator;
import cascading.operation.Buffer;
import cascading.operation.Operation;
import cascading.pipe.Every;
import cascading.pipe.Pipe;

/**
 * The {@link EveryHelper} implementation for the {@link Every} {@link Operation} of our PipeBuilder.
 */
class EveryBuilderHelper extends OperationBuilderHelper implements EveryHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    public EveryBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void aggregate(Object aggregator) {
        if (Aggregator.class.isInstance(aggregator)) {
            pipeWrapper.set(new Every(pipeWrapper.get(), argumentSelector, (Aggregator<?>) aggregator, outputSelector));
        } else if (Buffer.class.isInstance(aggregator)) {
            pipeWrapper.set(new Every(pipeWrapper.get(), argumentSelector, (Buffer<?>) aggregator, outputSelector));
        } else {
            throw new IllegalArgumentException(aggregator.getClass().getName() + " is not a cascading Aggregator or Buffer !");
        }
    }

}
