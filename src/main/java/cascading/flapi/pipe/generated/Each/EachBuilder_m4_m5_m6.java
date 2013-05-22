
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
 * Generated on May 22, 2013 11:59:50 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 11:59:50 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface EachBuilder_m4_m5_m6 <_ReturnType >{


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

}
