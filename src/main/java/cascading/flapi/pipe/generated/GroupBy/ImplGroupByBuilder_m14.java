
package cascading.flapi.pipe.generated.GroupBy;

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
public class ImplGroupByBuilder_m14
    implements GroupByBuilder_m14, BuilderImplementation
{

    private final GroupByHelper _helper;
    private final Object _returnValue;

    public ImplGroupByBuilder_m14(GroupByHelper helper, Object returnValue) {
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
     * Apply a GroupBy that will group on the given field names
     * 
     */
    public Object onFields(Comparable... fields) {
        _checkInvocations();
        _helper.onFields(fields);
         
        return _returnValue;
    }

}
