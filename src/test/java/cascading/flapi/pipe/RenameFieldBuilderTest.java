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

import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Rename;
import cascading.tuple.Fields;

/**
 * Tests for all the renameField-related operations
 */
public class RenameFieldBuilderTest {

    @Test
    public void renameField() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .renameField("one").to("two")
                .pipe();
        
        assertThat(pipe).isInstanceOf(Rename.class);
        
        Rename rename = (Rename)pipe;
        assertThat(rename.getTails()).hasSize(1);
        assertThat(rename.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)rename.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("one"));
    }

}
