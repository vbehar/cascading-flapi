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
