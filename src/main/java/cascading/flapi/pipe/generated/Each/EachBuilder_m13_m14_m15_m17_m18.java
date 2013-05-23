
package cascading.flapi.pipe.generated.Each;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on May 23, 2013 14:19:50 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 23, 2013 14:19:50 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface EachBuilder_m13_m14_m15_m17_m18 <_ReturnType >{


    /**
     * Apply the given cascading Function
     * 
     */
    _ReturnType applyFunction(Object function);

    /**
     * Filter in (= keep) only the tuples matching the given cascading Filter
     * 
     */
    _ReturnType filterIn(Object filter);

    /**
     * Filter out the tuples matching the given cascading Filter
     * 
     */
    _ReturnType filterOut(Object filter);

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    EachBuilder_m13_m14_m15_m18 <_ReturnType> produce(Comparable... output);

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    EachBuilder_m13_m14_m15_m17 <_ReturnType> select(Comparable... arguments);

}
