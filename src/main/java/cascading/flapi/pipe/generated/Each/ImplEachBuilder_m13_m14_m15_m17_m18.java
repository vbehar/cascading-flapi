
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
 * Generated on May 23, 2013 14:19:50 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 23, 2013 14:19:50 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEachBuilder_m13_m14_m15_m17_m18
    implements EachBuilder_m13_m14_m15_m17_m18, BuilderImplementation
{

    private final EachHelper _helper;
    private final Object _returnValue;

    public ImplEachBuilder_m13_m14_m15_m17_m18(EachHelper helper, Object returnValue) {
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

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    public EachBuilder_m13_m14_m15_m18 produce(Comparable... output) {
        _helper.produce(output);
        ImplEachBuilder_m13_m14_m15_m18 step1 = new ImplEachBuilder_m13_m14_m15_m18(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    public EachBuilder_m13_m14_m15_m17 select(Comparable... arguments) {
        _helper.select(arguments);
        ImplEachBuilder_m13_m14_m15_m17 step1 = new ImplEachBuilder_m13_m14_m15_m17(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
