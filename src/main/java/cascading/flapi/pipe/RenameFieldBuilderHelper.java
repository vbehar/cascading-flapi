package cascading.flapi.pipe;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.RenameField.RenameFieldHelper;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Rename;
import cascading.tuple.Fields;

/**
 * The {@link RenameFieldHelper} implementation to rename a field.
 */
class RenameFieldBuilderHelper implements RenameFieldHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private String field;

    public RenameFieldBuilderHelper(ObjectWrapper<Pipe> pipeWrapper, String field) {
        this.pipeWrapper = pipeWrapper;
        this.field = field;
    }

    @Override
    public void to(String newName) {
        pipeWrapper.set(new Rename(pipeWrapper.get(), new Fields(field), new Fields(newName)));
    }

}
