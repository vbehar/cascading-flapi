
package cascading.flapi.pipe.generated.GroupBy;

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
public interface GroupByBuilder_m25_m26 <_ReturnType >{


    /**
     * Apply a GroupBy that will group on the given field names
     * 
     */
    _ReturnType onFields(Comparable... fields);

    /**
     * Reverse the GroupBy
     * 
     */
    GroupByBuilder_m25 <_ReturnType> reversed();

}
