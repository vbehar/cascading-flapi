
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
public interface GroupByHelper {


    /**
     * Apply a GroupBy that will group on the given field names
     * 
     */
    void onFields(Comparable... fields);

    /**
     * Reverse the GroupBy
     * 
     */
    void reversed();

    /**
     * Sorts the grouped values on the given fields names
     * 
     */
    void withSortOnFields(Comparable... sortFields);

}
