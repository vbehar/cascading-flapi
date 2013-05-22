
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m5_m6_m7_m8_m9_m10_m11;
import cascading.flapi.pipe.generated.Every.EveryBuilder_m12_m10_m11;
import cascading.pipe.Pipe;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on May 22, 2013 12:20:53 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 12:20:53 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface PipeBuilder_m1_m2_m3 <_ReturnType >{


    /**
     * Start a new Each operation
     * 
     */
    EachBuilder_m5_m6_m7_m8_m9_m10_m11 <PipeBuilder_m1_m2_m3 <_ReturnType>> each();

    /**
     * Start a new Every operation
     * 
     */
    EveryBuilder_m12_m10_m11 <PipeBuilder_m1_m2_m3 <_ReturnType>> every();

    /**
     * End the builder and return the cascading Pipe
     * 
     */
    Pipe pipe();

}
