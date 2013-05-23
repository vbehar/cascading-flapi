
package cascading.flapi.pipe.generated.RenameField;

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
public class ImplRenameFieldBuilder_m24
    implements RenameFieldBuilder_m24, BuilderImplementation
{

    private final RenameFieldHelper _helper;
    private final Object _returnValue;

    public ImplRenameFieldBuilder_m24(RenameFieldHelper helper, Object returnValue) {
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
     * New name of the field
     * 
     */
    public Object to(String newName) {
        _checkInvocations();
        _helper.to(newName);
         
        return _returnValue;
    }

}
