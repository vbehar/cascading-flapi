package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.flapi.pipe.generated.Pipe.PipeHelper;
import cascading.pipe.Pipe;

/**
 * The {@link PipeHelper} implementation for our PipeBuilder.
 */
class PipeBuilderHelper implements PipeHelper {

    /** The Pipe wrapper, so that other helpers can update its value */
    private final ObjectWrapper<Pipe> pipeWrapper = new ObjectWrapper<Pipe>(new Pipe("start"));

    @Override
    public void withName(String name) {
        pipeWrapper.set(new Pipe(name, pipeWrapper.get()));
    }

    @Override
    public Pipe pipe() {
        return pipeWrapper.get();
    }

    @Override
    public void each(ObjectWrapper<EachHelper> eachHelperWrapper) {
        eachHelperWrapper.set(new EachBuilderHelper(pipeWrapper));
    }

    @Override
    public void every(ObjectWrapper<EveryHelper> everyHelperWrapper) {
        everyHelperWrapper.set(new EveryBuilderHelper(pipeWrapper));
    }

    @Override
    public void groupBy(ObjectWrapper<GroupByHelper> groupByHelperWrapper) {
        groupByHelperWrapper.set(new GroupByBuilderHelper(pipeWrapper));
    }

}
