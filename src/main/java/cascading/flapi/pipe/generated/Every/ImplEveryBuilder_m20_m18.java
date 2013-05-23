
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
 * Generated on May 23, 2013 14:21:13 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 23, 2013 14:21:13 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEveryBuilder_m20_m18
    implements EveryBuilder_m20_m18, BuilderImplementation
{

    private final EveryHelper _helper;
    private final Object _returnValue;

    public ImplEveryBuilder_m20_m18(EveryHelper helper, Object returnValue) {
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
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    public EveryBuilder_m20 select(Comparable... arguments) {
        _helper.select(arguments);
        ImplEveryBuilder_m20 step1 = new ImplEveryBuilder_m20(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
