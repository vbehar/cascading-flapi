
package cascading.flapi.pipe.generated.Each;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.InsertField.ImplInsertFieldBuilder_m19;
import cascading.flapi.pipe.generated.InsertField.InsertFieldBuilder_m19;
import cascading.flapi.pipe.generated.InsertField.InsertFieldHelper;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public class ImplEachBuilder_m12_m13_m14_m15_m16_m17
    implements EachBuilder_m12_m13_m14_m15_m16_m17, BuilderImplementation
{

    private final EachHelper _helper;
    private final Object _returnValue;

    public ImplEachBuilder_m12_m13_m14_m15_m16_m17(EachHelper helper, Object returnValue) {
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
     * Allow the function applied to this operation to override some fields given as the input.
     * 
     */
    public EachBuilder_m13_m14_m15_m16_m17 allowOverride() {
        _helper.allowOverride();
        ImplEachBuilder_m13_m14_m15_m16_m17 step1 = new ImplEachBuilder_m13_m14_m15_m16_m17(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
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
     * Shortcut to apply the Insert cascading Function : insert the given field in each tuple.
     * 
     */
    public InsertFieldBuilder_m19 insertField(String field) {
        _checkInvocations();
        ObjectWrapper<InsertFieldHelper> helper1 = new ObjectWrapper<InsertFieldHelper>();
        _helper.insertField(field, helper1);
        ImplInsertFieldBuilder_m19 step1 = new ImplInsertFieldBuilder_m19(helper1 .get(), _returnValue);
         
        return step1;
    }

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    public EachBuilder_m12_m13_m14_m15_m16 produce(Comparable... output) {
        _helper.produce(output);
        ImplEachBuilder_m12_m13_m14_m15_m16 step1 = new ImplEachBuilder_m12_m13_m14_m15_m16(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
