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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import cascading.operation.aggregator.Count;
import cascading.pipe.Every;
import cascading.pipe.Pipe;

/**
 * Tests for all the Every-related operations
 */
public class EveryBuilderTest {

    @Test
    public void singleEveryAggregator() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .every().aggregate(new Count())
                .pipe();
        
        assertThat(pipe).isInstanceOf(Every.class);
        
        Every every = (Every) pipe;
        assertThat(every.getAggregator()).isInstanceOf(Count.class);
    }

}
