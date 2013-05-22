
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
 * Generated on May 22, 2013 14:16:14 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 14:16:14 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface EachHelper {


    /**
     * Allow the function applied to this operation to override some fields given as the input.
     * 
     */
    void allowOverride();

    /**
     * Apply the given cascading Function
     * 
     */
    void applyFunction(Object function);

    /**
     * Filter in (= keep) only the tuples matching the given cascading Filter
     * 
     */
    void filterIn(Object filter);

    /**
     * Filter out the tuples matching the given cascading Filter
     * 
     */
    void filterOut(Object filter);

    /**
     * Shortcut to apply the Insert cascading Function : insert the given value for the given field.
     * 
     */
    void insert(String field, Object value);

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
