
package cascading.flapi.pipe.generated.Every;

import javax.annotation.Generated;


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
public interface EveryBuilder_m12_m10_m11 <_ReturnType >{


    /**
     * Apply the given cascading Aggregator or Buffer
     * 
     */
    _ReturnType aggregate(Object aggregator);

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    EveryBuilder_m12_m11 <_ReturnType> produce(Comparable... output);

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    EveryBuilder_m12_m10 <_ReturnType> select(Comparable... arguments);

}
