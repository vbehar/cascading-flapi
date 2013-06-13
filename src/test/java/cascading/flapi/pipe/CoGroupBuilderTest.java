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

import cascading.pipe.CoGroup;
import cascading.pipe.Pipe;
import cascading.pipe.joiner.InnerJoin;

/**
 * Tests for all the CoGroup-related operations
 */
public class CoGroupBuilderTest {

    @Test
    public void simpleCoGroup() throws Exception {
        Pipe pipe = PipeBuilder
                .coGroup(new Pipe("one"), new Pipe("two"))
                    .onFields("url")
                    .applyInnerJoin()
                .pipe();
        
        assertThat(pipe).isInstanceOf(CoGroup.class);
        
        CoGroup coGroup = (CoGroup) pipe;
        assertThat(coGroup.getJoiner()).isInstanceOf(InnerJoin.class);
        assertThat(coGroup.getHeads()).hasSize(2);
        assertThat(coGroup.getHeads()).extracting("name").contains("one", "two");
        assertThat(coGroup.getDeclaredFields()).isNull();
    }

    @Test
    public void complexCoGroup() throws Exception {
        Pipe one = PipeBuilder.start("one")
                .each().insertField("root").withValue("root value")
                .each().insertField("pattern").withValue("pattern value")
                .each().insertField("field1").withValue("value")
                .pipe();

        Pipe two = PipeBuilder.start("two")
                .each().insertField("root").withValue("root value")
                .each().insertField("pattern").withValue("pattern value")
                .each().insertField("field2").withValue("value")
                .pipe();

        Pipe three = PipeBuilder.start("three")
                .each().insertField("root").withValue("root value")
                .each().insertField("pattern").withValue("pattern value")
                .each().insertField("field3").withValue("value")
                .pipe();
        
        Pipe merged = PipeBuilder.coGroup(one, two, three)
                .onFields("root", "pattern").applyInnerJoin()
                .pipe();
        
        assertThat(merged).isInstanceOf(CoGroup.class);
        
        CoGroup coGroup = (CoGroup) merged;
        assertThat(coGroup.getJoiner()).isInstanceOf(InnerJoin.class);
        assertThat(coGroup.getHeads()).hasSize(3);
        assertThat(coGroup.getDeclaredFields()).isNull();
    }

}
