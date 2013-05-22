
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachBuilder_m4_m5_m6_m7_m8_m9_m10;
import cascading.pipe.Pipe;


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
public interface PipeBuilder_m1_m2 <_ReturnType >{


    /**
     * Start a new Each operation
     * 
     */
    EachBuilder_m4_m5_m6_m7_m8_m9_m10 <PipeBuilder_m1_m2 <_ReturnType>> each();

    /**
     * End the builder and return the cascading Pipe
     * 
     */
    Pipe pipe();

}
