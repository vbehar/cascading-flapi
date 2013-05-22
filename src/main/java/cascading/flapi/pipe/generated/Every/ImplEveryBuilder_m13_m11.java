
package cascading.flapi.pipe.generated.Every;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on May 22, 2013 14:16:14 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 14:16:14 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEveryBuilder_m13_m11
    implements EveryBuilder_m13_m11, BuilderImplementation
{

    private final EveryHelper _helper;
    private final Object _returnValue;

    public ImplEveryBuilder_m13_m11(EveryHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    /**
     * Apply the given cascading Aggregator or Buffer
     * 
     */
    public Object aggregate(Object aggregator) {
        _checkInvocations();
        _helper.aggregate(aggregator);
         
        return _returnValue;
    }

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    public EveryBuilder_m13 produce(Comparable... output) {
        _helper.produce(output);
        ImplEveryBuilder_m13 step1 = new ImplEveryBuilder_m13(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
