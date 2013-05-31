
package cascading.flapi.pipe.generated.Filter;

import javax.annotation.Generated;


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
public interface FilterBuilder_m21_m22 <_ReturnType >{


    /**
     * Filter the Null values.
     * 
     */
    _ReturnType nullValues();

    /**
     * Filter the values matching the given java expression.
     * 
     */
    _ReturnType valuesMatchingExpression(String expression, Class parameterType);

}
