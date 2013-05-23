
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
 * Generated on May 23, 2013 14:21:13 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 23, 2013 14:21:13 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface GroupByBuilder_m21_m22_m23 <_ReturnType >{


    /**
     * Apply a GroupBy that will group on the given field names
     * 
     */
    _ReturnType onFields(Comparable... fields);

    /**
     * Reverse the GroupBy
     * 
     */
    GroupByBuilder_m21_m23 <_ReturnType> reversed();

    /**
     * Sorts the grouped values on the given fields names
     * 
     */
    GroupByBuilder_m21_m22 <_ReturnType> withSortOnFields(Comparable... sortFields);

}
