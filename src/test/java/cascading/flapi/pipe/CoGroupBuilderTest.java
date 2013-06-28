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

import cascading.flapi.pipe.TestHelper.FlowHelper.Source;
import cascading.pipe.Pipe;
import cascading.tuple.Tuple;

/**
 * Tests for all the CoGroup-related operations
 */
public class CoGroupBuilderTest {

    @Test
    public void innerJoin() throws Exception {
        Pipe one = PipeBuilder.start("one").pipe();
        Pipe two = PipeBuilder.start("two").pipe();
        Pipe three = PipeBuilder.start("three").pipe();
        
        Pipe merged = PipeBuilder.coGroup(one, two, three)
                .onFields("root", "pattern").applyInnerJoin()
                .pipe();
        
        
        List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("root", "pattern", "field1")
                                                                        .withTuples(new Tuple("rootValue", "patternValue", "fieldValue1"),
                                                                                    new Tuple("rootValue", "patternValue2", "fieldValue1")),
                                                        Source.from(two).withFields("root", "pattern", "field2")
                                                                        .withTuples(new Tuple("rootValue", "patternValue", "fieldValue2"),
                                                                                    new Tuple("rootValue", "patternValue2", "fieldValue2")),
                                                        Source.from(three).withFields("root", "pattern", "field3")
                                                                          .withTuples(new Tuple("rootValue", "patternValue", "fieldValue3")))
                                                   .withOutput(merged)
                                                   .launchFlow();
        assertThat(tuples.size()).isEqualTo(1);
        Tuple tuple = tuples.get(0);
        System.out.println(tuple);
        assertThat(tuple.getString(0)).isEqualTo("rootValue");
        assertThat(tuple.getString(1)).isEqualTo("patternValue");
        assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
        assertThat(tuple.getString(3)).isEqualTo("fieldValue2");
        assertThat(tuple.getString(4)).isEqualTo("fieldValue3");
    }
    
    @Test
    public void leftJoin() throws Exception {
        Pipe one = PipeBuilder.start("one").pipe();
        Pipe two = PipeBuilder.start("two").pipe();
        Pipe three = PipeBuilder.start("three").pipe();
        
        // merge one, two and three
        {
            Pipe merged = PipeBuilder.coGroup(one, two, three)
                    .onFields("root", "pattern").applyLeftJoin()
                    .pipe();
        
            List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("root", "pattern", "field1")
                                                                            .withTuples(new Tuple("rootValue", "patternValue", "fieldValue1"),
                                                                                        new Tuple("rootValue", "patternValue2", "fieldValue1")),
                                                            Source.from(two).withFields("root", "pattern", "field2")
                                                                            .withTuples(new Tuple("rootValue", "patternValue", "fieldValue2"),
                                                                                        new Tuple("rootValue", "patternValue2", "fieldValue2")),
                                                            Source.from(three).withFields("root", "pattern", "field3")
                                                                              .withTuples(new Tuple("rootValue", "patternValue", "fieldValue3")))
                                                      .withOutput(merged)
                                                      .launchFlow();
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
        
            List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("root", "pattern", "field1")
                                                                            .withTuples(new Tuple("rootValue", "patternValue", "fieldValue1"),
                                                                                        new Tuple("rootValue", "patternValue2", "fieldValue1")),
                                                            Source.from(two).withFields("root", "pattern", "field2")
                                                                            .withTuples(new Tuple("rootValue", "patternValue", "fieldValue2"),
                                                                                        new Tuple("rootValue", "patternValue2", "fieldValue2")),
                                                            Source.from(three).withFields("root", "pattern", "field3")
                                                                              .withTuples(new Tuple("rootValue", "patternValue", "fieldValue3")))
                                                      .withOutput(merged)
                                                      .launchFlow();
            assertThat(tuples.size()).isEqualTo(1);
            Tuple tuple = tuples.get(0);
            assertThat(tuple.getString(0)).isEqualTo("rootValue");
            assertThat(tuple.getString(1)).isEqualTo("patternValue");
            assertThat(tuple.getString(2)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(3)).isEqualTo("fieldValue3");
            assertThat(tuple.getString(4)).isEqualTo("fieldValue2");
        }
    }
    
    @Test
    public void simpleOuterJoin() throws Exception {
        Pipe one = PipeBuilder.start("one").pipe();
        Pipe two = PipeBuilder.start("two").pipe();
        
        Pipe merged = PipeBuilder.coGroup(one, two)
                .onFields("groupField").applyOuterJoin()
                .pipe();
        
        List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("groupField", "field1")
                                                                        .withTuples(new Tuple("groupValue", "fieldValue1"),
                                                                                    new Tuple("groupValue2", "fieldValue1"),
                                                                                    new Tuple(null, "fieldValue1")),
                                                        Source.from(two).withFields("groupField", "field2")
                                                                        .withTuples(new Tuple("groupValue", "fieldValue2"),
                                                                                    new Tuple("groupValue3", "fieldValue2")))
                                                  .withOutput(merged)
                                                  .launchFlow();
        
        assertThat(tuples.size()).isEqualTo(4);
        {
            Tuple tuple = tuples.get(0);
            assertThat(tuple.size()).isEqualTo(3);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isNull();
            assertThat(tuple.getString(2)).isNull();
        }
        {
            Tuple tuple = tuples.get(1);
            assertThat(tuple.size()).isEqualTo(3);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isEqualTo("fieldValue2");
            assertThat(tuple.getString(2)).isEqualTo("groupValue");
        }
        {
            Tuple tuple = tuples.get(2);
            assertThat(tuple.size()).isEqualTo(3);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isNull();
            assertThat(tuple.getString(2)).isEqualTo("groupValue2");
        }
        {
            Tuple tuple = tuples.get(3);
            assertThat(tuple.size()).isEqualTo(3);
            assertThat(tuple.getString(0)).isNull();
            assertThat(tuple.getString(1)).isEqualTo("fieldValue2");
            assertThat(tuple.getString(2)).isEqualTo("groupValue3");
        }
    }
    
    @Test
    public void outerJoinWith3Pipes() throws Exception {
        Pipe one = PipeBuilder.start("one").pipe();
        Pipe two = PipeBuilder.start("two").pipe();
        Pipe three = PipeBuilder.start("three").pipe();
        
        Pipe merged = PipeBuilder.coGroup(one, two, three)
                .onFields("groupField").applyOuterJoin()
                .pipe();
        
        List<Tuple> tuples = TestHelper.FlowHelper.from(Source.from(one).withFields("groupField", "field1")
                                                                        .withTuples(new Tuple("groupValue1", "fieldValue1"),
                                                                                    new Tuple("groupValue2", "fieldValue1"),
                                                                                    new Tuple(null, "fieldValue1")),
                                                        Source.from(two).withFields("groupField", "field2")
                                                                        .withTuples(new Tuple("groupValue1", "fieldValue2"),
                                                                                    new Tuple("groupValue3", "fieldValue2")),
                                                        Source.from(three).withFields("groupField", "field3")
                                                                          .withTuples(new Tuple("groupValue3", "fieldValue3"),
                                                                                      new Tuple("groupValue4", "fieldValue3"),
                                                                                      new Tuple(null, "fieldValue3")))
                                                  .withOutput(merged)
                                                  .launchFlow();
        
        assertThat(tuples.size()).isEqualTo(5);
        {
            Tuple tuple = tuples.get(0);
            assertThat(tuple.size()).isEqualTo(4);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isNull();
            assertThat(tuple.getString(2)).isEqualTo("fieldValue3");
            assertThat(tuple.getString(3)).isNull();
        }
        {
            Tuple tuple = tuples.get(1);
            assertThat(tuple.size()).isEqualTo(4);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isEqualTo("fieldValue2");
            assertThat(tuple.getString(2)).isNull();
            assertThat(tuple.getString(3)).isEqualTo("groupValue1");
        }
        {
            Tuple tuple = tuples.get(2);
            assertThat(tuple.size()).isEqualTo(4);
            assertThat(tuple.getString(0)).isEqualTo("fieldValue1");
            assertThat(tuple.getString(1)).isNull();
            assertThat(tuple.getString(2)).isNull();
            assertThat(tuple.getString(3)).isEqualTo("groupValue2");
        }
        {
            Tuple tuple = tuples.get(3);
            assertThat(tuple.size()).isEqualTo(4);
            assertThat(tuple.getString(0)).isNull();
            assertThat(tuple.getString(1)).isEqualTo("fieldValue2");
            assertThat(tuple.getString(2)).isEqualTo("fieldValue3");
            assertThat(tuple.getString(3)).isEqualTo("groupValue3");
        }
        {
            Tuple tuple = tuples.get(4);
            assertThat(tuple.size()).isEqualTo(4);
            assertThat(tuple.getString(0)).isNull();
            assertThat(tuple.getString(1)).isNull();
            assertThat(tuple.getString(2)).isEqualTo("fieldValue3");
            assertThat(tuple.getString(3)).isEqualTo("groupValue4");
        }
    }
    
}
