package cascading.flapi.pipe;

import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.InsertField.InsertFieldHelper;
import cascading.operation.Function;
import cascading.operation.Insert;
import cascading.tuple.Fields;

/**
 * The {@link InsertFieldHelper} implementation to insert a field.
 * 
 * Applies the cascading {@link Insert} {@link Function}.
 */
class InsertFieldBuilderHelper implements InsertFieldHelper {

    private final EachHelper eachHelper;

    private String field;

    public InsertFieldBuilderHelper(EachHelper eachHelper, String field) {
        this.eachHelper = eachHelper;
        this.field = field;
    }

    @Override
    public void withValue(Object value) {
        eachHelper.applyFunction(new Insert(new Fields(field), value));
    }

}
