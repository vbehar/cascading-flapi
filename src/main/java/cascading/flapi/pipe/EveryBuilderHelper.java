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

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.generated.Every.EveryHelper;
import cascading.operation.Aggregator;
import cascading.operation.Buffer;
import cascading.operation.Operation;
import cascading.pipe.Every;
import cascading.pipe.Pipe;

/**
 * The {@link EveryHelper} implementation for the {@link Every} {@link Operation} of our PipeBuilder.
 */
class EveryBuilderHelper extends OperationBuilderHelper implements EveryHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    public EveryBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void aggregate(Object aggregator) {
        if (Aggregator.class.isInstance(aggregator)) {
            pipeWrapper.set(new Every(pipeWrapper.get(), argumentSelector, (Aggregator<?>) aggregator, outputSelector));
        } else if (Buffer.class.isInstance(aggregator)) {
            pipeWrapper.set(new Every(pipeWrapper.get(), argumentSelector, (Buffer<?>) aggregator, outputSelector));
        } else {
            throw new IllegalArgumentException(aggregator.getClass().getName() + " is not a cascading Aggregator or Buffer !");
        }
    }

}
