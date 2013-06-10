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

import java.util.ArrayList;
import java.util.List;

import unquietcode.tools.flapi.support.ObjectWrapper;
import cascading.pipe.Pipe;

/**
 * Support class for working with {@link PipeWrapperCallback}s.
 * 
 * This class should be used in *Helper implementations to easily instantiate new callbacks and execute them.
 */
class PipeWrapperCallbacksSupport {

    private List<ObjectWrapper<PipeWrapperCallback>> callbackWrappers;

    /**
     * @return a new wrapper for a PipeWrapperCallback
     */
    ObjectWrapper<PipeWrapperCallback> newPipeWrapperCallbackWrapper() {
        if (callbackWrappers == null) {
            callbackWrappers = new ArrayList<ObjectWrapper<PipeWrapperCallback>>();
        }

        ObjectWrapper<PipeWrapperCallback> callback = new ObjectWrapper<PipeWrapperCallback>();
        callbackWrappers.add(callback);

        return callback;
    }

    /**
     * Apply the given pipe to all callbacks created through the {@link #newPipeWrapperCallbackWrapper()} method
     * 
     * @param pipeWrapper
     *            to pass to all callbacks
     * @see PipeWrapperCallback#call(ObjectWrapper)
     */
    void applyToAllCallbacks(ObjectWrapper<Pipe> pipeWrapper) {
        if (callbackWrappers != null) {
            for (ObjectWrapper<PipeWrapperCallback> callback : callbackWrappers) {
                if (callback.get() != null) {
                    callback.get().call(pipeWrapper);
                }
            }
        }
    }

}
