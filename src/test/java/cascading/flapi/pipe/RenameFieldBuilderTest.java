package cascading.flapi.pipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Rename;
import cascading.tuple.Fields;

/**
 * Tests for all the renameField-related operations
 */
public class RenameFieldBuilderTest {

    @Test
    public void renameField() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .renameField("one").to("two")
                .pipe();
        
        assertThat(pipe).isInstanceOf(Rename.class);
        
        Rename rename = (Rename)pipe;
        assertThat(rename.getTails()).hasSize(1);
        assertThat(rename.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)rename.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("one"));
    }

}
