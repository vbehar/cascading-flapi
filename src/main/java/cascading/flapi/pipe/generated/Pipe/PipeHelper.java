
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.flapi.pipe.generated.RenameField.RenameFieldHelper;
import cascading.pipe.Pipe;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public interface PipeHelper {


    /**
     * Count all the values and store the result as a long in the given fieldDeclaration.
     * 
     */
    void count(String fieldDeclaration);

    /**
     * Discard the given fields
     * 
     */
    void discard(Comparable... fieldsToDiscard);

    /**
     * Start a new Each operation
     * 
     */
    void each(ObjectWrapper<EachHelper> _helper1);

    /**
     * Start a new Every operation
     * 
     */
    void every(ObjectWrapper<EveryHelper> _helper1);

    /**
     * Start from the given Pipe. Note that any work done by this builder before the call to 'from' will be lost.
     * 
     */
    void from(Object pipe);

    /**
     * Start a new GroupBy
     * 
     */
    void groupBy(ObjectWrapper<GroupByHelper> _helper1);

    /**
     * End the builder and return the cascading Pipe
     * 
     */
    Pipe pipe();

    /**
     * Rename the given field
     * 
     */
    void renameField(String field, ObjectWrapper<RenameFieldHelper> _helper1);

    /**
     * Retain only the given fields
     * 
     */
    void retain(Comparable... fieldsToKeep);

    /**
     * Filter all duplicates out of a tuple stream.
     * 
     */
    void unique(Comparable... uniqueFields);

    /**
     * Set the name of the pipe. Can be called many times, to rename the pipe mid-way down.
     * 
     */
    void withName(String name);

}
