
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
 * Generated on May 22, 2013 12:20:53 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 12:20:53 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEveryBuilder_m12
    implements EveryBuilder_m12, BuilderImplementation
{

    private final EveryHelper _helper;
    private final Object _returnValue;

    public ImplEveryBuilder_m12(EveryHelper helper, Object returnValue) {
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

}
