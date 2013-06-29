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

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cascading.flapi.pipe.TestHelper.FlowHelper.Source;
import cascading.pipe.Merge;
import cascading.pipe.Pipe;
import cascading.tuple.Tuple;

public class MergeTest {
    @Test
    public void merge() throws Exception {
        Pipe one = PipeBuilder.start("one").pipe();
        Pipe two = PipeBuilder.start("two").pipe();
        Pipe three = PipeBuilder.start("three").pipe();
        
        Pipe merged = PipeBuilder.merge(one, two, three).pipe();
        
        List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("field1", "field2")
                                                                        .withTuples(new Tuple("value11", "value12"),
                                                                                    new Tuple("value21", "value22"),
                                                                                    new Tuple(null, "value32")),
                                                        Source.from(two).withFields("field1", "field2")
                                                                        .withTuples(new Tuple("value41", "value42"),
                                                                                    new Tuple("value51", "value52")),
                                                        Source.from(three).withFields("field1", "field2")
                                                                          .withTuples(new Tuple("value61", "value62")))
                                                  .withOutput(merged)
                                                  .launchFlow();
        
        assertThat(merged).isInstanceOf(Merge.class);
        assertThat(tuples.size()).isEqualTo(6);
        Collections.sort(tuples);
        {
            Tuple tuple = tuples.get(0);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isNull();
            assertThat(tuple.getString(1)).isEqualTo("value32");
        }
        {
            Tuple tuple = tuples.get(1);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isEqualTo("value11");
            assertThat(tuple.getString(1)).isEqualTo("value12");
        }
        {
            Tuple tuple = tuples.get(2);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isEqualTo("value21");
            assertThat(tuple.getString(1)).isEqualTo("value22");
        }
        {
            Tuple tuple = tuples.get(3);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isEqualTo("value41");
            assertThat(tuple.getString(1)).isEqualTo("value42");
        }
        {
            Tuple tuple = tuples.get(4);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isEqualTo("value51");
            assertThat(tuple.getString(1)).isEqualTo("value52");
        }
        {
            Tuple tuple = tuples.get(5);
            assertThat(tuple.size()).isEqualTo(2);
            assertThat(tuple.getString(0)).isEqualTo("value61");
            assertThat(tuple.getString(1)).isEqualTo("value62");
        }
    }
}
