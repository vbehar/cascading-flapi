package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.FilterBuilderHelper.FilterType;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Filter.FilterHelper;
import cascading.flapi.pipe.generated.InsertField.InsertFieldHelper;
import cascading.operation.Filter;
import cascading.operation.Function;
import cascading.operation.Operation;
import cascading.operation.filter.Not;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * The {@link EachHelper} implementation for the {@link Each} {@link Operation} of our PipeBuilder.
 */
class EachBuilderHelper extends OperationBuilderHelper implements EachHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    public EachBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void allowOverride() {
        outputSelector = Fields.REPLACE;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void filterOut(Object filter) {
        if (!Filter.class.isInstance(filter)) {
            throw new IllegalArgumentException(filter.getClass().getName() + " is not a cascading Filter !");
        }

        pipeWrapper.set(new Each(pipeWrapper.get(), argumentSelector, (Filter) filter));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void filterIn(Object filter) {
        if (!Filter.class.isInstance(filter)) {
            throw new IllegalArgumentException(filter.getClass().getName() + " is not a cascading Filter !");
        }

        filterOut(new Not((Filter) filter));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void applyFunction(Object function) {
        if (!Function.class.isInstance(function)) {
            throw new IllegalArgumentException(function.getClass().getName() + " is not a cascading Function !");
        }

        pipeWrapper.set(new Each(pipeWrapper.get(), argumentSelector, (Function) function, outputSelector));
    }

    @Override
    public void insertField(String field, ObjectWrapper<InsertFieldHelper> insertFieldHelperWrapper) {
        insertFieldHelperWrapper.set(new InsertFieldBuilderHelper(this, field));
    }

    @Override
    public void filterIn(ObjectWrapper<FilterHelper> filterHelperWrapper) {
        filterHelperWrapper.set(new FilterBuilderHelper(this, FilterType.IN));
    }

    @Override
    public void filterOut(ObjectWrapper<FilterHelper> filterHelperWrapper) {
        filterHelperWrapper.set(new FilterBuilderHelper(this, FilterType.OUT));
    }

}
