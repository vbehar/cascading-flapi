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
