package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * The {@link GroupByHelper} implementation for the {@link GroupBy} of our PipeBuilder.
 */
class GroupByBuilderHelper implements GroupByHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private boolean reverseOrder = false;

    private Fields sortFields = null;

    public GroupByBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void reversed() {
        reverseOrder = true;
    }

    @Override
    public void withSortOnFields(@SuppressWarnings("rawtypes") Comparable... sortFields) {
        if (sortFields != null && sortFields.length > 0) {
            this.sortFields = PipeBuilder.getSelector(sortFields);
        }
    }

    @Override
    public void onFields(@SuppressWarnings("rawtypes") Comparable... fields) {
        pipeWrapper.set(new GroupBy(pipeWrapper.get(), PipeBuilder.getSelector(fields), sortFields, reverseOrder));
    }

}
