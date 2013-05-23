package cascading.flapi.pipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.Test;

import cascading.operation.Insert;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;

/**
 * Tests for all the insertField-related operations
 */
public class InsertFieldBuilderTest {

    @Test
    public void insertField() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().insertField("key").withValue("value")
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getFunction()).isInstanceOf(Insert.class);
        assertThat(each.getFunction().getFieldDeclaration()).isEqualTo(new Fields("key"));
        
        Field valuesField = Insert.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        Object valuesObj = valuesField.get(each.getFunction());
        assertThat(valuesObj).isInstanceOf(Tuple.class);
        Tuple values = (Tuple) valuesObj;
        assertThat(values).isEqualTo(new Tuple("value"));
    }

}
