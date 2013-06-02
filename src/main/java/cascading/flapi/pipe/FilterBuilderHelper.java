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

import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Filter.FilterHelper;
import cascading.operation.Filter;
import cascading.operation.Operation;
import cascading.operation.expression.ExpressionFilter;
import cascading.operation.filter.FilterNull;

/**
 * The {@link FilterHelper} implementation to apply a {@link Filter} {@link Operation}.
 */
class FilterBuilderHelper implements FilterHelper {

    static enum FilterType {
        IN, OUT
    }

    private EachHelper eachHelper;

    private FilterType filterType;

    public FilterBuilderHelper(EachHelper eachHelper, FilterType filterType) {
        this.eachHelper = eachHelper;
        this.filterType = filterType;
    }

    @Override
    public void nullValues() {
        applyFilter(new FilterNull());
    }

    @Override
    public void valuesMatchingExpression(String expression, @SuppressWarnings("rawtypes") Class parameterType) {
        applyFilter(new ExpressionFilter(expression, parameterType));
    }

    private void applyFilter(Filter<?> filter) {
        switch (filterType) {
        case IN:
            eachHelper.filterIn(filter);
            break;
        case OUT:
            eachHelper.filterOut(filter);
            break;
        }
    }

}
