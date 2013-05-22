
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m6_m7_m8_m9_m10_m11_m12;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Each.ImplEachBuilder_m6_m7_m8_m9_m10_m11_m12;
import cascading.flapi.pipe.generated.Every.EveryBuilder_m13_m11_m12;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.Every.ImplEveryBuilder_m13_m11_m12;
import cascading.flapi.pipe.generated.GroupBy.GroupByBuilder_m14_m15_m16;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.flapi.pipe.generated.GroupBy.ImplGroupByBuilder_m14_m15_m16;
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
 * Generated on May 22, 2013 14:16:14 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 14:16:14 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplPipeBuilder_m1_m2_m3_m4
    implements PipeBuilder_m1_m2_m3_m4, BuilderImplementation
{

    private final PipeHelper _helper;
    private final Object _returnValue;

    public ImplPipeBuilder_m1_m2_m3_m4(PipeHelper helper, Object returnValue) {
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
    public EachBuilder_m6_m7_m8_m9_m10_m11_m12 each() {
        ObjectWrapper<EachHelper> helper1 = new ObjectWrapper<EachHelper>();
        _helper.each(helper1);
        ImplEachBuilder_m6_m7_m8_m9_m10_m11_m12 step1 = new ImplEachBuilder_m6_m7_m8_m9_m10_m11_m12(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Start a new Every operation
     * 
     */
    public EveryBuilder_m13_m11_m12 every() {
        ObjectWrapper<EveryHelper> helper1 = new ObjectWrapper<EveryHelper>();
        _helper.every(helper1);
        ImplEveryBuilder_m13_m11_m12 step1 = new ImplEveryBuilder_m13_m11_m12(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Start a new GroupBy
     * 
     */
    public GroupByBuilder_m14_m15_m16 groupBy() {
        ObjectWrapper<GroupByHelper> helper1 = new ObjectWrapper<GroupByHelper>();
        _helper.groupBy(helper1);
        ImplGroupByBuilder_m14_m15_m16 step1 = new ImplGroupByBuilder_m14_m15_m16(helper1 .get(), this);
         
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
