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
import cascading.pipe.Pipe;

/**
 * Define a callback to be executed on a Pipe instance.
 * 
 * Used to defer an operation until we can get a hold on a specific Pipe instance.
 */
interface PipeWrapperCallback {

    /**
     * Execute this callback on the given pipe instance
     * 
     * @param pipeWrapper
     *            holding a Pipe instance
     */
    void call(ObjectWrapper<Pipe> pipeWrapper);

}
