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
import cascading.flapi.pipe.generated.ConfigProperty.ConfigPropertyHelper;
import cascading.pipe.Pipe;
import cascading.property.ConfigDef;
import cascading.property.ConfigDef.Mode;

/**
 * The {@link ConfigPropertyHelper} implementation for setting a {@link ConfigDef} property.
 */
class ConfigPropertyBuilderHelper implements ConfigPropertyHelper {

    static enum ConfigScope {
        GLOBAL, STEP
    }

    private ObjectWrapper<Pipe> pipeWrapper;

    private ObjectWrapper<PipeWrapperCallback> pipeCallback;

    private ConfigScope scope;

    private String key;

    private Mode mode;

    public ConfigPropertyBuilderHelper withPipeWrapper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
        return this;
    }

    public ConfigPropertyBuilderHelper withPipeCallback(ObjectWrapper<PipeWrapperCallback> pipeCallback) {
        this.pipeCallback = pipeCallback;
        return this;
    }

    public ConfigPropertyBuilderHelper withScope(ConfigScope scope) {
        this.scope = scope;
        return this;
    }

    public ConfigPropertyBuilderHelper withKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public void inDefaultMode() {
        inMode(Mode.DEFAULT);
    }

    @Override
    public void inReplaceMode() {
        inMode(Mode.REPLACE);
    }

    @Override
    public void inUpdateMode() {
        inMode(Mode.UPDATE);
    }

    @Override
    public void inMode(Object mode) {
        if (Mode.class.isInstance(mode)) {
            this.mode = (Mode) mode;
        } else if (String.class.isInstance(mode)) {
            try {
                this.mode = Mode.valueOf(((String) mode).toUpperCase());
            } catch (IllegalArgumentException e) {
            }
        }
    }

    @Override
    public void withValue(final String value) {
        if (pipeCallback != null) {
            pipeCallback.set(new PipeWrapperCallback() {

                @Override
                public void call(ObjectWrapper<Pipe> pipeWrapper) {
                    apply(value, pipeWrapper);
                }
            });
        } else {
            apply(value, pipeWrapper);
        }
    }

    private void apply(String value, ObjectWrapper<Pipe> pipeWrapper) {
        ConfigDef configDef = null;
        switch (scope) {
        case GLOBAL:
            configDef = pipeWrapper.get().getConfigDef();
            break;
        case STEP:
            configDef = pipeWrapper.get().getStepConfigDef();
            break;
        default:
            return;
        }

        if (mode != null) {
            configDef.setProperty(mode, key, value);
        } else {
            configDef.setProperty(key, value);
        }
    }

}
