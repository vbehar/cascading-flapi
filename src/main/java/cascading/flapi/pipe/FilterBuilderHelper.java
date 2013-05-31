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
