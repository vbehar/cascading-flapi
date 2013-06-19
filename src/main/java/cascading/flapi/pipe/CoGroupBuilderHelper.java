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
import cascading.flapi.pipe.ConfigPropertyBuilderHelper.ConfigScope;
import cascading.flapi.pipe.generated.CoGroup.CoGroupHelper;
import cascading.flapi.pipe.generated.ConfigProperty.ConfigPropertyHelper;
import cascading.pipe.CoGroup;
import cascading.pipe.Pipe;
import cascading.pipe.joiner.InnerJoin;
import cascading.pipe.joiner.LeftJoin;
import cascading.tuple.Fields;

/**
 * The {@link CoGroupHelper} implementation for the {@link CoGroup} of our PipeBuilder.
 */
class CoGroupBuilderHelper implements CoGroupHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private final PipeWrapperCallbacksSupport callbacksSupport;

    private Pipe[] pipes;

    private Fields[] groupFieldsPerPipe;
    private String[] modifiedGroupFieldsSet;
    
    private int numberOfReducers = -1;

    public CoGroupBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
        callbacksSupport = new PipeWrapperCallbacksSupport();
    }

    @Override
    public void setStepConfigProperty(String key, ObjectWrapper<ConfigPropertyHelper> configPropertyHelperWrapper) {
        configPropertyHelperWrapper.set(new ConfigPropertyBuilderHelper()
            .withPipeCallback(callbacksSupport.newPipeWrapperCallbackWrapper())
            .withScope(ConfigScope.STEP)
            .withKey(key));
    }

    @Override
    public void from(Object... pipes) {
        if (Pipe[].class.isInstance(pipes)) {
            this.pipes = (Pipe[]) pipes;
        }
    }

    @Override
    public void onFields(String... fields) {
        groupFieldsPerPipe = new Fields[pipes.length];
        groupFieldsPerPipe[0] = new Fields(fields);
        modifiedGroupFieldsSet = new String[(pipes.length-1) * fields.length];
        int modifiedGroupFieldsId = 0;
        for (int pipeId = 1; pipeId < pipes.length; pipeId++) {
            String[] modifiedGroupFields = new String[fields.length];
            for (int groupFieldId = 0; groupFieldId < fields.length; groupFieldId++) {
                String groupField = fields[groupFieldId];
                String modifiedGroupField = "__rhs" + String.valueOf(pipeId) + "__" + groupField;
                pipes[pipeId] = PipeBuilder.from(pipes[pipeId]).renameField(groupField).to(modifiedGroupField).pipe();
                modifiedGroupFields[groupFieldId] = modifiedGroupField;
                modifiedGroupFieldsSet[modifiedGroupFieldsId++] = modifiedGroupField;
            }
            groupFieldsPerPipe[pipeId] = new Fields(modifiedGroupFields);
        }
    }

    @Override
    public void onReducers(int numberOfReducers) {
        this.numberOfReducers = numberOfReducers;
    }

    @Override
    public void applyInnerJoin() {
        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new InnerJoin()));
        
        // Discard modified group fields (__rhs1__..., __rhs2__)
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get()).discard(modifiedGroupFieldsSet).pipe());
        
        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
    }

    @Override
    public void applyLeftJoin() {
        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new LeftJoin()));
        
        // Discard modified group fields (__rhs1__..., __rhs2__)
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get()).discard(modifiedGroupFieldsSet).pipe());
        
        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
    }
}
