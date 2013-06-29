/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cascading.flapi.pipe;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.Flapi;
import cascading.pipe.Pipe;

/**
 * The PipeBuilder generator : use flapi to generate the java code.
 */
public class PipeBuilderGenerator {
    
    public static final transient int GROUP_CONFIG_PROPERTY_MODE = 1;
    
    public static final transient int GROUP_COMPRESSION_CODEC = 2;

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
                 
                .addMethod("from(cascading.pipe.Pipe initialPipe)")
                    .withDocumentation("Start from the given Pipe. " +
                    		"Note that any work done by this builder before the call to 'from' will be lost.")
                .atMost(1)
                
                .addMethod("withName(String pipeName)")
                    .withDocumentation("Set the name of the pipe. Can be called many times, to rename the pipe mid-way down.")
                .any()
                
                /*
                 * CoGroup
                 */
                 
                .startBlock("CoGroup", "coGroup()")
                    .withDocumentation("Start a new CoGroup")
                .any()
                
                    .addBlockReference("ConfigProperty", "setStepConfigProperty(String key)")
                        .withDocumentation("Set a Step ConfigDef property")
                    .any()
                    
                    .addMethod("onReducers(int numberOfReducers)")
                        .withDocumentation("Configure the number of reducer tasks used for this CoGroup")
                    .atMost(1)
                    
                    .addMethod("from(cascading.pipe.Pipe... pipes)")
                        .withDocumentation("Join the given pipes")
                    .exactly(1)
                    
                    .addMethod("onFields(String... fields)")
                        .withDocumentation("Join on the given fields")
                    .exactly(1)
                     
                    .addMethod("applyInnerJoin()")
                        .withDocumentation("Apply an inner join")
                    .last()
                    
                    .addMethod("applyLeftJoin()")
                        .withDocumentation("Apply an left join")
                    .last()
                    
                    .addMethod("applyOuterJoin()")
                        .withDocumentation("Apply an outer join")
                    .last()
                 
                .endBlock()
                
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
                    
                    .addMethod("filterOut(cascading.operation.Filter filter)")
                        .withDocumentation("Filter out the tuples matching the given cascading Filter")
                    .last()
                    
                    .addMethod("filterIn(cascading.operation.Filter filter)")
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
                    
                    .addMethod("applyFunction(cascading.operation.Function function)")
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
                    
                    .addMethod("aggregate(cascading.operation.Aggregator aggregator)")
                        .withDocumentation("Apply the given cascading Aggregator")
                    .last()
                    
                    .addMethod("aggregate(cascading.operation.Buffer buffer)")
                        .withDocumentation("Apply the given cascading Buffer")
                    .last()
                
                .endBlock()
                
                /*
                 * GroupBy
                 */
                 
                .startBlock("GroupBy", "groupBy()")
                    .withDocumentation("Start a new GroupBy")
                .any()
                
                    .addBlockReference("ConfigProperty", "setStepConfigProperty(String key)")
                        .withDocumentation("Set a Step ConfigDef property")
                    .any()
                    
                    .addMethod("onReducers(int numberOfReducers)")
                        .withDocumentation("Configure the number of reducer tasks used for this GroupBy")
                    .atMost(1)
                     
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
                 * Merge
                 */
                
                .addMethod("merge(cascading.pipe.Pipe... pipes)")
                    .withDocumentation("Merge the given pipes")
                .any()
                
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

                /*
                 * Config
                 */
                 
                .startBlock("ConfigProperty", "setConfigProperty(String key)")
                    .withDocumentation("Set a ConfigDef property")
                .any()

                    .addMethod("inMode(cascading.property.ConfigDef.Mode mode)")
                        .withDocumentation("Using the given ConfigDef.Mode value")
                    .atMost(1, GROUP_CONFIG_PROPERTY_MODE)

                    .addMethod("inDefaultMode()")
                        .withDocumentation("A DEFAULT property is only applied if there is no existing value in the property set.")
                    .atMost(1, GROUP_CONFIG_PROPERTY_MODE)

                    .addMethod("inReplaceMode()")
                        .withDocumentation("A REPLACE property is always applied overriding any previous values.")
                    .atMost(1, GROUP_CONFIG_PROPERTY_MODE)

                    .addMethod("inUpdateMode()")
                        .withDocumentation("An UPDATE property is always applied to an existing property. " +
                        		"Usually when the property key represent a list of values.")
                    .atMost(1, GROUP_CONFIG_PROPERTY_MODE)

                    .addMethod("withValue(String value)")
                        .withDocumentation("The value to associate with the property")
                    .last()

                .endBlock()
                
                .addBlockReference("ConfigProperty", "setStepConfigProperty(String key)")
                    .withDocumentation("Set a Step ConfigDef property")
                .any()

                /*
                 * Compression
                 */
                 
                .startBlock("Compression", "compressOutput()")
                    .withDocumentation("Compress the output")
                .any()

                    .addMethod("forThisStepOnly()")
                        .withDocumentation("Restrict the scope of the compression to the current step only")
                    .atMost(1)

                    .addMethod("withType(String compressionType)")
                        .withDocumentation("Use the given compression type (eg for SequenceFiles : NONE, RECORD, BLOCK)")
                    .atMost(1)

                    .addMethod("withCodecClass(String fullCodecClassName)")
                        .withDocumentation("Use the compression codec identified by the given class name")
                    .atMost(1, GROUP_COMPRESSION_CODEC)

                    .addMethod("withDefaultCodec()")
                        .withDocumentation("Use the default compression codec")
                    .atMost(1, GROUP_COMPRESSION_CODEC)

                    .addMethod("withGzipCodec()")
                        .withDocumentation("Use the Gzip compression codec")
                    .atMost(1, GROUP_COMPRESSION_CODEC)

                    .addMethod("withBZip2Codec()")
                        .withDocumentation("Use the BZip2 compression codec")
                    .atMost(1, GROUP_COMPRESSION_CODEC)

                    .addMethod("withSnappyCodec()")
                        .withDocumentation("Use the Snappy compression codec")
                    .atMost(1, GROUP_COMPRESSION_CODEC)

                    .addMethod("ofTheMappers()")
                        .withDocumentation("Compress the output of the Mappers (in SequenceFiles)")
                    .last()

                    .addMethod("ofTheJob()")
                        .withDocumentation("Compress the output of a MapReduce job")
                    .last()

                .endBlock()

                .build();

        descriptor.writeToFolder(args[0]);
    }

}
