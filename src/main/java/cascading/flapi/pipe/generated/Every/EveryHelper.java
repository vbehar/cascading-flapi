
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
 * Generated on May 22, 2013 14:16:14 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 14:16:14 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface EveryHelper {


    /**
     * Apply the given cascading Aggregator or Buffer
     * 
     */
    void aggregate(Object aggregator);

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    void produce(Comparable... output);

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    void select(Comparable... arguments);

}
