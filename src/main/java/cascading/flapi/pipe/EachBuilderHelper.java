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

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.flapi.pipe.FilterBuilderHelper.FilterType;
import cascading.flapi.pipe.generated.Each.EachHelper;
import cascading.flapi.pipe.generated.Filter.FilterHelper;
import cascading.flapi.pipe.generated.InsertField.InsertFieldHelper;
import cascading.operation.Filter;
import cascading.operation.Function;
import cascading.operation.Operation;
import cascading.operation.filter.Not;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * The {@link EachHelper} implementation for the {@link Each} {@link Operation} of our PipeBuilder.
 */
class EachBuilderHelper extends OperationBuilderHelper implements EachHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    public EachBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void allowOverride() {
        outputSelector = Fields.REPLACE;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void filterOut(Filter filter) {
        pipeWrapper.set(new Each(pipeWrapper.get(), argumentSelector, filter));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void filterIn(Filter filter) {
        filterOut(new Not(filter));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void applyFunction(Function function) {
        pipeWrapper.set(new Each(pipeWrapper.get(), argumentSelector, function, outputSelector));
    }

    @Override
    public void insertField(String field, ObjectWrapper<InsertFieldHelper> insertFieldHelperWrapper) {
        insertFieldHelperWrapper.set(new InsertFieldBuilderHelper(this, field));
    }

    @Override
    public void filterIn(ObjectWrapper<FilterHelper> filterHelperWrapper) {
        filterHelperWrapper.set(new FilterBuilderHelper(this, FilterType.IN));
    }

    @Override
    public void filterOut(ObjectWrapper<FilterHelper> filterHelperWrapper) {
        filterHelperWrapper.set(new FilterBuilderHelper(this, FilterType.OUT));
    }

}
