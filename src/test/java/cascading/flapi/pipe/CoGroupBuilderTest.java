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

import java.util.List;

import org.junit.Test;

import cascading.flapi.pipe.TestHelper.EqualsFilter;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;


/**
 * Tests for all the CoGroup-related operations
 */
public class CoGroupBuilderTest {

    @Test
    public void innerJoin() throws Exception {
        Pipe startPipe = PipeBuilder.start("start")
                .retain("root", "pattern")
                .pipe();
        Pipe one = PipeBuilder.from(startPipe).withName("one")
                .each().insertField("field1").withValue("fieldValue1")
                .pipe();

        Pipe two = PipeBuilder.from(startPipe).withName("two")
                .each().insertField("field2").withValue("fieldValue2")
                .pipe();

        Pipe three = PipeBuilder.from(startPipe).withName("three")
                .each().insertField("field3").withValue("fieldValue3")
                .each().select("pattern").filterOut(EqualsFilter.value("patternValue2"))
                .pipe();
        
        Pipe merged = PipeBuilder.coGroup(one, two, three)
                .onFields("root", "pattern").applyInnerJoin()
                .pipe();
        
        List<Tuple> tuples = TestHelper.launchFlow(startPipe, merged,
                                                   new Fields("root", "pattern"),
                                                   new Tuple("rootValue", "patternValue"),
                                                   new Tuple("rootValue", "patternValue2"));
        assertThat(tuples.size()).isEqualTo(1);
        Tuple tuple = tuples.get(0);
        assertThat(tuple.getString(0)).isEqualTo("rootValue");
        assertThat(tuple.getString(1)).isEqualTo("patternValue");
        assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
        assertThat(tuple.getString(3)).isEqualTo("fieldValue2");
        assertThat(tuple.getString(4)).isEqualTo("fieldValue3");
    }
    
    @Test
    public void leftJoin() throws Exception {
        Pipe startPipe = PipeBuilder.start("start")
                .retain("root", "pattern")
                .pipe();
        Pipe one = PipeBuilder.from(startPipe).withName("one")
                .each().insertField("field1").withValue("fieldValue1")
                .pipe();

        Pipe two = PipeBuilder.from(startPipe).withName("two")
                .each().insertField("field2").withValue("fieldValue2")
                .pipe();

        Pipe three = PipeBuilder.from(startPipe).withName("three")
                .each().insertField("field3").withValue("fieldValue3")
                .each().select("pattern").filterOut(EqualsFilter.value("patternValue2"))
                .pipe();
        
        // merge one, two and three
        {
            Pipe merged = PipeBuilder.coGroup(one, two, three)
                    .onFields("root", "pattern")
                    .applyLeftJoin()
                    .pipe();
        
            List<Tuple> tuples = TestHelper.launchFlow(startPipe, merged,
                                                       new Fields("root", "pattern"),
                                                       new Tuple("rootValue", "patternValue"),
                                                       new Tuple("rootValue", "patternValue2"));
            assertThat(tuples.size()).isEqualTo(2);
            {
                Tuple tuple = tuples.get(0);
                assertThat(tuple.getString(0)).isEqualTo("rootValue");
                assertThat(tuple.getString(1)).isEqualTo("patternValue");
                assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
                assertThat(tuple.getString(3)).isEqualTo("fieldValue2");
                assertThat(tuple.getString(4)).isEqualTo("fieldValue3");
            }
            {
                Tuple tuple = tuples.get(1);
                assertThat(tuple.getString(0)).isEqualTo("rootValue");
                assertThat(tuple.getString(1)).isEqualTo("patternValue2");
                assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
                assertThat(tuple.getString(3)).isEqualTo("fieldValue2");
                assertThat(tuple.getString(4)).isNull();
            }
        }
        // merge one, three and two
        {
            Pipe merged = PipeBuilder.coGroup(one, three, two)
                    .onFields("root", "pattern")
                    .applyLeftJoin()
                    .pipe();
        
            List<Tuple> tuples = TestHelper.launchFlow(startPipe, merged,
                                                       new Fields("root", "pattern"),
                                                       new Tuple("rootValue", "patternValue"),
                                                       new Tuple("rootValue", "patternValue2"));
            assertThat(tuples.size()).isEqualTo(1);
            Tuple tuple = tuples.get(0);
            assertThat(tuple.getString(0)).isEqualTo("rootValue");
            assertThat(tuple.getString(1)).isEqualTo("patternValue");
            assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(3)).isEqualTo("fieldValue3");
            assertThat(tuple.getString(4)).isEqualTo("fieldValue2");
        }
    }
}
