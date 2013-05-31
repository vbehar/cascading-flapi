
package cascading.flapi.pipe.generated.Filter;

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
public class ImplFilterBuilder_m21_m22
    implements FilterBuilder_m21_m22, BuilderImplementation
{

    private final FilterHelper _helper;
    private final Object _returnValue;

    public ImplFilterBuilder_m21_m22(FilterHelper helper, Object returnValue) {
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
     * Filter the Null values.
     * 
     */
    public Object nullValues() {
        _checkInvocations();
        _helper.nullValues();
         
        return _returnValue;
    }

    /**
     * Filter the values matching the given java expression.
     * 
     */
    public Object valuesMatchingExpression(String expression, Class parameterType) {
        _checkInvocations();
        _helper.valuesMatchingExpression(expression, parameterType);
         
        return _returnValue;
    }

}
