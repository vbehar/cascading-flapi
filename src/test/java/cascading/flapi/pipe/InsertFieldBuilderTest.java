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

import java.lang.reflect.Field;

import org.junit.Test;

import cascading.operation.Insert;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;

/**
 * Tests for all the insertField-related operations
 */
public class InsertFieldBuilderTest {

    @Test
    public void insertField() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().insertField("key").withValue("value")
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getFunction()).isInstanceOf(Insert.class);
        assertThat(each.getFunction().getFieldDeclaration()).isEqualTo(new Fields("key"));
        
        Field valuesField = Insert.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        Object valuesObj = valuesField.get(each.getFunction());
        assertThat(valuesObj).isInstanceOf(Tuple.class);
        Tuple values = (Tuple) valuesObj;
        assertThat(values).isEqualTo(new Tuple("value"));
    }

}
