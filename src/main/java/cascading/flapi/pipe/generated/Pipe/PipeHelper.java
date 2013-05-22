
package cascading.flapi.pipe.generated.Pipe;

import javax.annotation.Generated;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
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
 * Generated on May 22, 2013 14:16:14 CEST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 22, 2013 14:16:14 CEST", comments = "generated using Flapi, the fluent API generator for Java")
public interface PipeHelper {


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
     * The name of the pipe
     * 
     */
    void withName(String name);

}
