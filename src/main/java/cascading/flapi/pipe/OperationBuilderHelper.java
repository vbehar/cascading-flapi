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

import cascading.operation.Operation;
import cascading.tuple.Fields;

/**
 * Abstract Helper for the {@link Operation} builders.
 * 
 * Handles the fields-related actions.
 */
abstract class OperationBuilderHelper {

    protected Fields argumentSelector = Fields.ALL;

    protected Fields outputSelector = Fields.ALL;

    public void select(@SuppressWarnings("rawtypes") Comparable... arguments) {
        if (arguments != null && arguments.length > 0) {
            argumentSelector = PipeBuilder.getSelector(arguments);
        }
    }

    public void produce(@SuppressWarnings("rawtypes") Comparable... output) {
        if (output != null && output.length > 0) {
            outputSelector = PipeBuilder.getSelector(output);
        }
    }

}
