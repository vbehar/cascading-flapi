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

import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * Tests for all the GroupBy-related operations
 */
public class GroupByBuilderTest {

    @Test
    public void singleGroupBy() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .groupBy().onFields("url")
                .pipe();
        
        assertThat(pipe).isInstanceOf(GroupBy.class);
        
        GroupBy groupBy = (GroupBy) pipe;
        assertThat(groupBy.getKeySelectors())
            .hasSize(1)
            .containsKey(pipe.getName())
            .containsValue(new Fields("url"));
        assertThat(groupBy.isSortReversed()).isFalse();
        assertThat(groupBy.getSortingSelectors()).isEmpty();
    }

    @Test
    public void singleGroupByReversed() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .groupBy().reversed().onFields("url")
                .pipe();
        
        GroupBy groupBy = (GroupBy) pipe;
        assertThat(groupBy.isSortReversed()).isTrue();
    }

    @Test
    public void singleGroupByWithSort() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .groupBy().withSortOnFields("data").onFields("url")
                .pipe();
        
        GroupBy groupBy = (GroupBy) pipe;
        assertThat(groupBy.getSortingSelectors())
            .hasSize(1)
            .containsKey(pipe.getName())
            .containsValue(new Fields("data"));
    }

}
