
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m12_m13_m14_m15_m16_m17_m18;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Each.ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18;
import cascading.flapi.pipe.generated.Every.EveryBuilder_m20_m17_m18;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.Every.ImplEveryBuilder_m20_m17_m18;
import cascading.flapi.pipe.generated.GroupBy.GroupByBuilder_m21_m22_m23;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.flapi.pipe.generated.GroupBy.ImplGroupByBuilder_m21_m22_m23;
import cascading.flapi.pipe.generated.RenameField.ImplRenameFieldBuilder_m24;
import cascading.flapi.pipe.generated.RenameField.RenameFieldBuilder_m24;
import cascading.flapi.pipe.generated.RenameField.RenameFieldHelper;
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
 * Generated on May 23, 2013 14:21:13 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 23, 2013 14:21:13 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplPipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11
    implements PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11, BuilderImplementation
{

    private final PipeHelper _helper;
    private final Object _returnValue;

    public ImplPipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11(PipeHelper helper, Object returnValue) {
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
     * Count all the values and store the result as a long in the given fieldDeclaration.
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 count(String fieldDeclaration) {
        _helper.count(fieldDeclaration);
         
        return this;
    }

    /**
     * Discard the given fields
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 discard(Comparable... fieldsToDiscard) {
        _helper.discard(fieldsToDiscard);
         
        return this;
    }

    /**
     * Start a new Each operation
     * 
     */
    public EachBuilder_m12_m13_m14_m15_m16_m17_m18 each() {
        ObjectWrapper<EachHelper> helper1 = new ObjectWrapper<EachHelper>();
        _helper.each(helper1);
        ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18 step1 = new ImplEachBuilder_m12_m13_m14_m15_m16_m17_m18(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Start a new Every operation
     * 
     */
    public EveryBuilder_m20_m17_m18 every() {
        ObjectWrapper<EveryHelper> helper1 = new ObjectWrapper<EveryHelper>();
        _helper.every(helper1);
        ImplEveryBuilder_m20_m17_m18 step1 = new ImplEveryBuilder_m20_m17_m18(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Start from the given Pipe. Note that any work done by this builder before the call to 'from' will be lost.
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11 from(Object pipe) {
        _helper.from(pipe);
        ImplPipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11 step1 = new ImplPipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * Start a new GroupBy
     * 
     */
    public GroupByBuilder_m21_m22_m23 groupBy() {
        ObjectWrapper<GroupByHelper> helper1 = new ObjectWrapper<GroupByHelper>();
        _helper.groupBy(helper1);
        ImplGroupByBuilder_m21_m22_m23 step1 = new ImplGroupByBuilder_m21_m22_m23(helper1 .get(), this);
         
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

    /**
     * Rename the given field
     * 
     */
    public RenameFieldBuilder_m24 renameField(String field) {
        ObjectWrapper<RenameFieldHelper> helper1 = new ObjectWrapper<RenameFieldHelper>();
        _helper.renameField(field, helper1);
        ImplRenameFieldBuilder_m24 step1 = new ImplRenameFieldBuilder_m24(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Retain only the given fields
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 retain(Comparable... fieldsToKeep) {
        _helper.retain(fieldsToKeep);
         
        return this;
    }

    /**
     * Filter all duplicates out of a tuple stream.
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 unique(Comparable... uniqueFields) {
        _helper.unique(uniqueFields);
         
        return this;
    }

    /**
     * Set the name of the pipe. Can be called many times, to rename the pipe mid-way down.
     * 
     */
    public PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 withName(String name) {
        _helper.withName(name);
         
        return this;
    }

}
