
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m4_m5_m6_m7_m8_m9_m10;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Each.ImplEachBuilder_m4_m5_m6_m7_m8_m9_m10;
import cascading.pipe.Pipe;
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
 * Generated on May 22, 2013 12:12:17 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 12:12:17 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplPipeBuilder_m1_m2
    implements PipeBuilder_m1_m2, BuilderImplementation
{

    private final PipeHelper _helper;
    private final Object _returnValue;

    public ImplPipeBuilder_m1_m2(PipeHelper helper, Object returnValue) {
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
     * Start a new Each operation
     * 
     */
    public EachBuilder_m4_m5_m6_m7_m8_m9_m10 each() {
        ObjectWrapper<EachHelper> helper1 = new ObjectWrapper<EachHelper>();
        _helper.each(helper1);
        ImplEachBuilder_m4_m5_m6_m7_m8_m9_m10 step1 = new ImplEachBuilder_m4_m5_m6_m7_m8_m9_m10(helper1 .get(), this);
         
        return step1;
    }

    /**
     * End the builder and return the cascading Pipe
     * 
     */
    public Pipe pipe() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Pipe intermediateResult = _helper.pipe();
         
        return intermediateResult;
    }

}
