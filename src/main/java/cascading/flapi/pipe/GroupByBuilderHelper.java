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
import cascading.flapi.pipe.generated.GroupBy.GroupByHelper;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * The {@link GroupByHelper} implementation for the {@link GroupBy} of our PipeBuilder.
 */
class GroupByBuilderHelper implements GroupByHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private boolean reverseOrder = false;

    private Fields sortFields = null;

    public GroupByBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void reversed() {
        reverseOrder = true;
    }

    @Override
    public void withSortOnFields(@SuppressWarnings("rawtypes") Comparable... sortFields) {
        if (sortFields != null && sortFields.length > 0) {
            this.sortFields = PipeBuilder.getSelector(sortFields);
        }
    }

    @Override
    public void onFields(@SuppressWarnings("rawtypes") Comparable... fields) {
        pipeWrapper.set(new GroupBy(pipeWrapper.get(), PipeBuilder.getSelector(fields), sortFields, reverseOrder));
    }

}
