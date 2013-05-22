package cascading.flapi.pipe;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.Flapi;
import cascading.pipe.Pipe;

/**
 * The PipeBuilder generator : use flapi to generate the java code.
 */
public class PipeBuilderGenerator {

    public static void main(String[] args) {
        Descriptor descriptor = Flapi.builder()
                
                /*
                 * Global configuration
                 */
                
                .setPackage("cascading.flapi.pipe.generated")
                .enableCondensedClassNames()
                .setDescriptorName("Pipe")
                .setStartingMethodName("start")
                
                .addMethod("pipe()")
                    .withDocumentation("End the builder and return the cascading Pipe")
                .last(Pipe.class)
                
                /*
                 * top-level helper methods
                 */
                
                .addMethod("withName(String name)")
                    .withDocumentation("The name of the pipe")
                .atMost(1)
                
                /*
                 * Each
                 */
                
                .startBlock("Each", "each()")
                    .withDocumentation("Start a new Each operation")
                .any()
                    
                    .addMethod("select(Comparable... arguments)")
                        .withDocumentation("Restrict the fields to be used as the 'argument selector' for this operation")
                    .atMost(1)
                    
                    .addMethod("produce(Comparable... output)")
                        .withDocumentation("Restrict the fields to be used as the 'output selection' for this operation")
                    .atMost(1)
                    
                    .addMethod("allowOverride()")
                        .withDocumentation("Allow the function applied to this operation to override some fields given as the input.")
                    .atMost(1)
                    
                    .addMethod("insert(String field, Object value)")
                        .withDocumentation("Shortcut to apply the Insert cascading Function : insert the given value for the given field.")
                    .last()
                    
                    .addMethod("applyFunction(Object function)")
                        .withDocumentation("Apply the given cascading Function")
                    .last()
                    
                    .addMethod("filterOut(Object filter)")
                        .withDocumentation("Filter out the tuples matching the given cascading Filter")
                    .last()
                    
                    .addMethod("filterIn(Object filter)")
                        .withDocumentation("Filter in (= keep) only the tuples matching the given cascading Filter")
                    .last()
                
                .endBlock()
                
                /*
                 * Every
                 */
                
                .startBlock("Every", "every()")
                    .withDocumentation("Start a new Every operation")
                .any()
                    
                    .addMethod("select(Comparable... arguments)")
                        .withDocumentation("Restrict the fields to be used as the 'argument selector' for this operation")
                    .atMost(1)
                    
                    .addMethod("produce(Comparable... output)")
                        .withDocumentation("Restrict the fields to be used as the 'output selection' for this operation")
                    .atMost(1)
                    
                    .addMethod("aggregate(Object aggregator)")
                        .withDocumentation("Apply the given cascading Aggregator or Buffer")
                    .last()
                
                .endBlock()
                
                /*
                 * GroupBy
                 */
                 
                 .startBlock("GroupBy", "groupBy()")
                     .withDocumentation("Start a new GroupBy")
                 .any()
                     
                     .addMethod("reversed()")
                         .withDocumentation("Reverse the GroupBy")
                     .atMost(1)
                     
                     .addMethod("withSortOnFields(Comparable... sortFields)")
                         .withDocumentation("Sorts the grouped values on the given fields names")
                     .atMost(1)
                     
                     .addMethod("onFields(Comparable... fields)")
                         .withDocumentation("Apply a GroupBy that will group on the given field names")
                     .last()
                 
                 .endBlock()
        
                .build();
        
        // descriptor.writeToFolder("/path/to/cascading-flapi/src/main/java");
        descriptor.writeToFolder(args[0]);
    }

}
