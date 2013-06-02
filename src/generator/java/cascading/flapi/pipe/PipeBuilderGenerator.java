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
                 
                .addMethod("from(Object initialPipe)")
                    .withDocumentation("Start from the given Pipe. " +
                    		"Note that any work done by this builder before the call to 'from' will be lost.")
                .atMost(1)
                
                .addMethod("withName(String pipeName)")
                    .withDocumentation("Set the name of the pipe. Can be called many times, to rename the pipe mid-way down.")
                .any()
                
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
                    
                    .addMethod("filterOut(Object filter)")
                        .withDocumentation("Filter out the tuples matching the given cascading Filter")
                    .last()
                    
                    .addMethod("filterIn(Object filter)")
                        .withDocumentation("Filter in (= keep) only the tuples matching the given cascading Filter")
                    .last()
                    
                    .startBlock("Filter", "filterOut()")
                        .withDocumentation("Start a new FilterOut Operation")
                    .last()
                        .addMethod("nullValues()")
                            .withDocumentation("Filter the Null values.")
                        .last()
                        .addMethod("valuesMatchingExpression(String expression, Class parameterType)")
                            .withDocumentation("Filter the values matching the given java expression.")
                        .last()
                    .endBlock()
                    
                    .addBlockReference("Filter", "filterIn()")
                        .withDocumentation("Start a new FilterIn (= keep) Operation")
                    .last()
                    
                    .addMethod("applyFunction(Object function)")
                        .withDocumentation("Apply the given cascading Function")
                    .last()
                    
                    .addMethod("insertField(String field)")
                        .withDocumentation("Shortcut to apply the Insert cascading Function : insert the given field in each tuple.")
                        .addBlockChain()
                        .startBlock("InsertField")
                            .addMethod("withValue(Object value)")
                                .withDocumentation("The value associated with the field to insert.")
                            .last()
                        .endBlock()
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
                 
                /*
                 * Rename
                 */
                  
                .startBlock("RenameField", "renameField(String field)")
                    .withDocumentation("Rename the given field")
                .any()

                    .addMethod("to(String newName)")
                        .withDocumentation("New name of the field")
                    .last()

                .endBlock()

                /*
                 * Retain / Discard
                 */

                .addMethod("retain(Comparable... fieldsToKeep)")
                    .withDocumentation("Retain only the given fields")
                .any()

                .addMethod("discard(Comparable... fieldsToDiscard)")
                    .withDocumentation("Discard the given fields")
                .any()

                /*
                 * Unique
                 */

                .addMethod("unique(Comparable... uniqueFields)")
                    .withDocumentation("Filter all duplicates out of a tuple stream.")
                .any()

                /*
                 * Count
                 */

                .addMethod("count(String fieldDeclaration)")
                    .withDocumentation("Count all the values and store the result as a long in the given fieldDeclaration.")
                .any()

                .build();

        descriptor.writeToFolder(args[0]);
    }

}
