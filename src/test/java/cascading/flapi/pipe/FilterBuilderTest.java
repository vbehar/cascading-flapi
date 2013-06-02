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

import java.lang.reflect.Field;

import org.junit.Test;

import cascading.operation.expression.ExpressionFilter;
import cascading.operation.expression.ExpressionOperation;
import cascading.operation.filter.FilterNull;
import cascading.operation.filter.Not;
import cascading.pipe.Each;
import cascading.pipe.Pipe;

/**
 * Tests for all the Filter-related operations
 */
public class FilterBuilderTest {

    @Test
    public void filterOutNullValues() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("data").filterOut().nullValues()
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.isFilter()).isTrue();
        assertThat(each.getFilter()).isInstanceOf(FilterNull.class);
    }

    @Test
    public void filterOutValuesMatchingExpression() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("url").filterOut().valuesMatchingExpression("url.startsWith(\"https\")", String.class)
                .pipe();

        Each each = (Each) pipe;
        assertThat(each.isFilter()).isTrue();
        assertThat(each.getFilter()).isInstanceOf(ExpressionFilter.class);
        
        Field expressionField = ExpressionOperation.class.getDeclaredField("expression");
        expressionField.setAccessible(true);
        Object expressionObj = expressionField.get(each.getFilter());
        assertThat(expressionObj).isInstanceOf(String.class);
        assertThat(expressionObj).isEqualTo("url.startsWith(\"https\")");

        Field parameterTypesField = ExpressionOperation.class.getDeclaredField("parameterTypes");
        parameterTypesField.setAccessible(true);
        Object parameterTypesObj = parameterTypesField.get(each.getFilter());
        assertThat(parameterTypesObj).isInstanceOf(Class[].class);
        assertThat(parameterTypesObj).isEqualTo(new Class[] { String.class });

        Field parameterNamesField = ExpressionOperation.class.getDeclaredField("parameterNames");
        parameterNamesField.setAccessible(true);
        Object parameterNamesObj = parameterNamesField.get(each.getFilter());
        assertThat(parameterNamesObj).isNull();
    }

    @Test
    public void filterInNullValues() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("data").filterIn().nullValues()
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.isFilter()).isTrue();
        assertThat(each.getFilter()).isInstanceOf(Not.class);
        
        Field filterField = Not.class.getDeclaredField("filter");
        filterField.setAccessible(true);
        Object filterObj = filterField.get(each.getFilter());
        assertThat(filterObj).isInstanceOf(FilterNull.class);
    }

    @Test
    public void filterInValuesMatchingExpression() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("url").filterIn().valuesMatchingExpression("url.startsWith(\"https\")", String.class)
                .pipe();
        
        Each each = (Each) pipe;
        assertThat(each.isFilter()).isTrue();
        assertThat(each.getFilter()).isInstanceOf(Not.class);
        
        Field filterField = Not.class.getDeclaredField("filter");
        filterField.setAccessible(true);
        Object filterObj = filterField.get(each.getFilter());
        assertThat(filterObj).isInstanceOf(ExpressionFilter.class);
    }

}
