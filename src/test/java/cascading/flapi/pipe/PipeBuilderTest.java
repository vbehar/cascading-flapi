package cascading.flapi.pipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import cascading.operation.aggregator.Count;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Discard;
import cascading.pipe.assembly.Retain;
import cascading.pipe.assembly.Unique;
import cascading.tuple.Fields;

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
        Pipe pipe = PipeBuilder.start()
                .pipe();
        
        assertThat(pipe).isNotNull();
        assertThat(pipe.getName()).isEqualTo("start");
    }

    @Test
    public void emptyWithName() throws Exception {
        Pipe pipe = PipeBuilder.start("toto")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("toto");
    }

    @Test
    public void withName() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .withName("toto")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("toto");
    }

    @Test
    public void from() throws Exception {
        Pipe initialPipe = new Pipe("initial");
        
        Pipe pipe = PipeBuilder.from(initialPipe)
                .pipe();
        
        assertThat(pipe).isEqualTo(initialPipe);
    }

    @Test
    public void fromWithName() throws Exception {
        Pipe initialPipe = new Pipe("initial");
        
        Pipe pipe = PipeBuilder.from(initialPipe)
                .withName("new pipe")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("new pipe");
        assertThat(pipe.getPrevious()).hasSize(1);
        assertThat(pipe.getPrevious()).containsExactly(initialPipe);
    }

    @Test
    public void retain() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .retain("url")
                .pipe();

        assertThat(pipe).isInstanceOf(Retain.class);
        
        Retain retain = (Retain) pipe;
        assertThat(retain.getTails()).hasSize(1);
        assertThat(retain.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)retain.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url"));
    }

    @Test
    public void discard() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .discard("url")
                .pipe();

        assertThat(pipe).isInstanceOf(Discard.class);
        
        Discard discard = (Discard) pipe;
        assertThat(discard.getTails()).hasSize(1);
        assertThat(discard.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)discard.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url"));
    }

    @Test
    public void unique() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .unique()
                .pipe();

        assertThat(pipe).isInstanceOf(Unique.class);
    }

    @Test
    public void count() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .count("myCount")
                .pipe();

        assertThat(pipe).isInstanceOf(Every.class);

        Every every = (Every) pipe;
        assertThat(every.getAggregator()).isInstanceOf(Count.class);
        assertThat(every.getAggregator().getFieldDeclaration()).isEqualTo(new Fields("myCount"));
    }

}
