
package cascading.flapi.pipe.generated.Each;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Filter.FilterBuilder_m21_m22;
import cascading.flapi.pipe.generated.InsertField.InsertFieldBuilder_m23;


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
public interface EachBuilder_m13_m14_m15_m16_m17_m18_m19_m20 <_ReturnType >{


    /**
     * Apply the given cascading Function
     * 
     */
    _ReturnType applyFunction(Object function);

    /**
     * Start a new FilterIn (= keep) Operation
     * 
     */
    FilterBuilder_m21_m22 <_ReturnType> filterIn();

    /**
     * Filter in (= keep) only the tuples matching the given cascading Filter
     * 
     */
    _ReturnType filterIn(Object filter);

    /**
     * Start a new FilterOut Operation
     * 
     */
    FilterBuilder_m21_m22 <_ReturnType> filterOut();

    /**
     * Filter out the tuples matching the given cascading Filter
     * 
     */
    _ReturnType filterOut(Object filter);

    /**
     * Shortcut to apply the Insert cascading Function : insert the given field in each tuple.
     * 
     */
    InsertFieldBuilder_m23 <_ReturnType> insertField(String field);

    /**
     * Restrict the fields to be used as the 'output selection' for this operation
     * 
     */
    EachBuilder_m13_m14_m15_m16_m17_m18_m20 <_ReturnType> produce(Comparable... output);

    /**
     * Restrict the fields to be used as the 'argument selector' for this operation
     * 
     */
    EachBuilder_m13_m14_m15_m16_m17_m18_m19 <_ReturnType> select(Comparable... arguments);

}
