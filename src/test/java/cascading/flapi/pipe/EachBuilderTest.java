package cascading.flapi.pipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.Test;

import cascading.operation.Filter;
import cascading.operation.Insert;
import cascading.operation.NoOp;
import cascading.operation.filter.FilterNull;
import cascading.operation.filter.Not;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * Tests for all the Each-related operations
 */
public class EachBuilderTest {

    @Test
    public void singleEachFunction() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().applyFunction(new NoOp())
                .pipe();
        
        assertThat(pipe).isInstanceOf(Each.class);
        
        Each each = (Each) pipe;
        assertThat(each.isFilter()).isFalse();
        assertThat(each.isFunction()).isTrue();
        assertThat(each.getFunction()).isInstanceOf(NoOp.class);
        assertThat(each.getArgumentSelector()).isEqualTo(Fields.ALL);
        assertThat(each.getOutputSelector()).isEqualTo(Fields.ALL);
    }

    @Test
    public void singleEachFunctionWithSingleSelect() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("url").applyFunction(new NoOp())
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url"));
        assertThat(each.getOutputSelector()).isEqualTo(Fields.ALL);
    }

    @Test
    public void singleEachFunctionWithMultipleSelect() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("url", "data").applyFunction(new NoOp())
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url", "data"));
        assertThat(each.getOutputSelector()).isEqualTo(Fields.ALL);
    }

    @Test
    public void singleEachFunctionWithProduce() throws Exception {
        Insert insertFunction = new Insert(new Fields("key"), "value");
        Pipe pipe = PipeBuilder.start()
                .each().produce("key").applyFunction(insertFunction)
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getArgumentSelector()).isEqualTo(Fields.ALL);
        assertThat(each.getOutputSelector()).isEqualTo(new Fields("key"));
        assertThat(each.getFieldDeclaration()).isEqualTo(new Fields("key"));
        assertThat(each.getFunction()).isEqualTo(insertFunction);
    }

    @Test
    public void singleEachFunctionWithOverride() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().allowOverride().applyFunction(new Insert(new Fields("key"), "value"))
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.getOutputSelector()).isEqualTo(Fields.REPLACE);
    }

    @Test
    public void singleEachFilterIn() throws Exception {
        FilterNull filterNull = new FilterNull();
        Pipe pipe = PipeBuilder.start()
                .each().filterIn(filterNull)
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.isFilter()).isTrue();
        assertThat(each.getFilter()).isInstanceOf(Not.class);
        
        Field filterField = Not.class.getDeclaredField("filter");
        filterField.setAccessible(true);
        Object filterObj = filterField.get(each.getFilter());
        assertThat(filterObj).isInstanceOf(Filter.class);
        Filter<?> filter = (Filter<?>) filterObj;
        assertThat(filter).isEqualTo(filterNull);
    }

    @Test
    public void multipleEach() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("data").filterOut(new FilterNull())
                .each().select("url").applyFunction(new NoOp())
                .pipe();

        assertThat(pipe).isInstanceOf(Each.class);
        Each eachFunction = (Each) pipe;
        assertThat(eachFunction.getArgumentSelector()).isEqualTo(new Fields("url"));
        assertThat(eachFunction.isFunction()).isTrue();
        
        assertThat(pipe.getPrevious()).hasSize(1);
        assertThat(pipe.getPrevious()[0]).isInstanceOf(Each.class);
        Each eachFilter = (Each) pipe.getPrevious()[0];
        assertThat(eachFilter.getArgumentSelector()).isEqualTo(new Fields("data"));
        assertThat(eachFilter.isFilter()).isTrue();
    }

}
