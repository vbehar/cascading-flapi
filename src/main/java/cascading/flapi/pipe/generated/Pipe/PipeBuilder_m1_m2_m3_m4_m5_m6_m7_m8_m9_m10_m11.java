
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20;
import cascading.flapi.pipe.generated.Every.EveryBuilder_m24_m19_m20;
import cascading.flapi.pipe.generated.GroupBy.GroupByBuilder_m25_m26_m27;
import cascading.flapi.pipe.generated.RenameField.RenameFieldBuilder_m28;
import cascading.pipe.Pipe;


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
public interface PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType >{


    /**
     * Count all the values and store the result as a long in the given fieldDeclaration.
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType> count(String fieldDeclaration);

    /**
     * Discard the given fields
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType> discard(Comparable... fieldsToDiscard);

    /**
     * Start a new Each operation
     * 
     */
    EachBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20 <PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType>> each();

    /**
     * Start a new Every operation
     * 
     */
    EveryBuilder_m24_m19_m20 <PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType>> every();

    /**
     * Start from the given Pipe. Note that any work done by this builder before the call to 'from' will be lost.
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11 <_ReturnType> from(Object initialPipe);

    /**
     * Start a new GroupBy
     * 
     */
    GroupByBuilder_m25_m26_m27 <PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType>> groupBy();

    /**
     * End the builder and return the cascading Pipe
     * 
     */
    Pipe pipe();

    /**
     * Rename the given field
     * 
     */
    RenameFieldBuilder_m28 <PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType>> renameField(String field);

    /**
     * Retain only the given fields
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType> retain(Comparable... fieldsToKeep);

    /**
     * Filter all duplicates out of a tuple stream.
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType> unique(Comparable... uniqueFields);

    /**
     * Set the name of the pipe. Can be called many times, to rename the pipe mid-way down.
     * 
     */
    PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11 <_ReturnType> withName(String pipeName);

}
