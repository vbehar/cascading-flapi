
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
 * Generated on May 30, 2013 18:39:44 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 30, 2013 18:39:44 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplGroupByBuilder_m25_m26
    implements GroupByBuilder_m25_m26, BuilderImplementation
{

    private final GroupByHelper _helper;
    private final Object _returnValue;

    public ImplGroupByBuilder_m25_m26(GroupByHelper helper, Object returnValue) {
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

    /**
     * Reverse the GroupBy
     * 
     */
    public GroupByBuilder_m25 reversed() {
        _helper.reversed();
        ImplGroupByBuilder_m25 step1 = new ImplGroupByBuilder_m25(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
