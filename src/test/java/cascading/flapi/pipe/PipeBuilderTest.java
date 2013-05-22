package cascading.flapi.pipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import cascading.pipe.Pipe;

/**
 * Tests for all the global operations on {@link PipeBuilder}.
 * 
 * @see EachBuilderTest
 * @see EveryBuilderTest
 * @see GroupByBuilderTest
 */
public class PipeBuilderTest {

    @Test
    public void empty() throws Exception {
        Pipe pipe = PipeBuilder.start().pipe();
        
        assertThat(pipe).isNotNull();
        assertThat(pipe.getName()).isEqualTo("start");
    }

    @Test
    public void withName() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .withName("toto")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("toto");
    }

}
