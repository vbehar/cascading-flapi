
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
 * Generated on May 22, 2013 12:20:53 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 12:20:53 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface EachBuilder_m5_m6_m7_m8_m9 <_ReturnType >{


    /**
     * Allow the function applied to this operation to override some fields given as the input.
     * 
     */
    EachBuilder_m6_m7_m8_m9 <_ReturnType> allowOverride();

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
     * Shortcut to apply the Insert cascading Function : insert the given value for the given field.
     * 
     */
    _ReturnType insert(String field, Object value);

}
