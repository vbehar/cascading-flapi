package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.flapi.pipe.generated.Pipe.PipeHelper;
import cascading.flapi.pipe.generated.RenameField.RenameFieldHelper;
import cascading.operation.aggregator.Count;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Discard;
import cascading.pipe.assembly.Retain;
import cascading.pipe.assembly.Unique;
import cascading.tuple.Fields;

/**
 * The {@link PipeHelper} implementation for our PipeBuilder.
 */
class PipeBuilderHelper implements PipeHelper {

    /** The Pipe wrapper, so that other helpers can update its value */
    private final ObjectWrapper<Pipe> pipeWrapper = new ObjectWrapper<Pipe>(new Pipe("start"));

    @Override
    public Pipe pipe() {
        return pipeWrapper.get();
    }

    @Override
    public void from(Object initialPipe) {
        if (!Pipe.class.isInstance(initialPipe)) {
            throw new IllegalArgumentException(initialPipe.getClass().getName() + " is not a cascading Pipe !");
        }

        pipeWrapper.set((Pipe) initialPipe);
    }

    @Override
    public void withName(String pipeName) {
        pipeWrapper.set(new Pipe(pipeName, pipeWrapper.get()));
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

    @Override
    public void renameField(String field, ObjectWrapper<RenameFieldHelper> renameFieldHelperWrapper) {
        renameFieldHelperWrapper.set(new RenameFieldBuilderHelper(pipeWrapper, field));
    }

    @Override
    public void retain(@SuppressWarnings("rawtypes") Comparable... fieldsToKeep) {
        pipeWrapper.set(new Retain(pipeWrapper.get(), PipeBuilder.getSelector(fieldsToKeep)));
    }

    @Override
    public void discard(@SuppressWarnings("rawtypes") Comparable... fieldsToDiscard) {
        pipeWrapper.set(new Discard(pipeWrapper.get(), PipeBuilder.getSelector(fieldsToDiscard)));
    }

    @Override
    public void unique(@SuppressWarnings("rawtypes") Comparable... uniqueFields) {
        pipeWrapper.set(new Unique(pipeWrapper.get(), PipeBuilder.getSelector(uniqueFields)));
    }

    @Override
    public void count(String fieldDeclaration) {
        new EveryBuilderHelper(pipeWrapper).aggregate(new Count(new Fields(fieldDeclaration)));
    }

}
