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
import cascading.flapi.pipe.generated.RenameField.RenameFieldHelper;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Rename;
import cascading.tuple.Fields;

/**
 * The {@link RenameFieldHelper} implementation to rename a field.
 */
class RenameFieldBuilderHelper implements RenameFieldHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private String field;

    public RenameFieldBuilderHelper(ObjectWrapper<Pipe> pipeWrapper, String field) {
        this.pipeWrapper = pipeWrapper;
        this.field = field;
    }

    @Override
    public void to(String newName) {
        pipeWrapper.set(new Rename(pipeWrapper.get(), new Fields(field), new Fields(newName)));
    }

}
