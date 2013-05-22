
package cascading.flapi.pipe.generated.Each;

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
 * Generated on May 22, 2013 11:59:50 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 11:59:50 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEachBuilder_m4_m5_m6
    implements EachBuilder_m4_m5_m6, BuilderImplementation
{

    private final EachHelper _helper;
    private final Object _returnValue;

    public ImplEachBuilder_m4_m5_m6(EachHelper helper, Object returnValue) {
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
     * Apply the given cascading Function
     * 
     */
    public Object applyFunction(Object function) {
        _checkInvocations();
        _helper.applyFunction(function);
         
        return _returnValue;
    }

    /**
     * Filter in (= keep) only the tuples matching the given cascading Filter
     * 
     */
    public Object filterIn(Object filter) {
        _checkInvocations();
        _helper.filterIn(filter);
         
        return _returnValue;
    }

    /**
     * Filter out the tuples matching the given cascading Filter
     * 
     */
    public Object filterOut(Object filter) {
        _checkInvocations();
        _helper.filterOut(filter);
         
        return _returnValue;
    }

}
