
package cascading.flapi.pipe.generated.Each;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Filter.FilterBuilder_m21_m22;
import cascading.flapi.pipe.generated.Filter.FilterHelper;
import cascading.flapi.pipe.generated.Filter.ImplFilterBuilder_m21_m22;
import cascading.flapi.pipe.generated.InsertField.ImplInsertFieldBuilder_m23;
import cascading.flapi.pipe.generated.InsertField.InsertFieldBuilder_m23;
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
 * Generated on May 30, 2013 18:39:44 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 30, 2013 18:39:44 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20
    implements EachBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20, BuilderImplementation
{

    private final EachHelper _helper;
    private final Object _returnValue;

    public ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20(EachHelper helper, Object returnValue) {
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
    public EachBuilder_m13_m14_m15_m16_m17_m18_m19_m20 allowOverride() {
        _helper.allowOverride();
        ImplEachBuilder_m13_m14_m15_m16_m17_m18_m19_m20 step1 = new ImplEachBuilder_m13_m14_m15_m16_m17_m18_m19_m20(_helper, _returnValue);
         
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
     * Start a new FilterIn (= keep) Operation
     * 
     */
    public FilterBuilder_m21_m22 filterIn() {
        _checkInvocations();
        ObjectWrapper<FilterHelper> helper1 = new ObjectWrapper<FilterHelper>();
        _helper.filterIn(helper1);
        ImplFilterBuilder_m21_m22 step1 = new ImplFilterBuilder_m21_m22(helper1 .get(), _returnValue);
         
        return step1;
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
     * Start a new FilterOut Operation
     * 
     */
    public FilterBuilder_m21_m22 filterOut() {
        _checkInvocations();
        ObjectWrapper<FilterHelper> helper1 = new ObjectWrapper<FilterHelper>();
        _helper.filterOut(helper1);
        ImplFilterBuilder_m21_m22 step1 = new ImplFilterBuilder_m21_m22(helper1 .get(), _returnValue);
         
        return step1;
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
    public InsertFieldBuilder_m23 insertField(String field) {
        _checkInvocations();
        ObjectWrapper<InsertFieldHelper> helper1 = new ObjectWrapper<InsertFieldHelper>();
        _helper.insertField(field, helper1);
        ImplInsertFieldBuilder_m23 step1 = new ImplInsertFieldBuilder_m23(helper1 .get(), _returnValue);
         
        return step1;
    }

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    public EachBuilder_m12_m13_m14_m15_m16_m17_m18_m20 produce(Comparable... output) {
        _helper.produce(output);
        ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m20 step1 = new ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m20(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    public EachBuilder_m12_m13_m14_m15_m16_m17_m18_m19 select(Comparable... arguments) {
        _helper.select(arguments);
        ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m19 step1 = new ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18_m19(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
