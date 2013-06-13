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
import cascading.pipe.assembly.Rename;
import cascading.pipe.joiner.InnerJoin;
import cascading.tuple.Fields;

/**
 * The {@link CoGroupHelper} implementation for the {@link CoGroup} of our PipeBuilder.
 */
class CoGroupBuilderHelper implements CoGroupHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private final PipeWrapperCallbacksSupport callbacksSupport;

    private Pipe[] pipes;

    private String[] groupFields;

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
        this.groupFields = fields;
    }

    @Override
    public void onReducers(int numberOfReducers) {
        this.numberOfReducers = numberOfReducers;
    }

    @Override
    public void applyInnerJoin() {
        Fields[] groupFieldsPerPipe = new Fields[pipes.length];

        for (int pipeId = 0; pipeId < pipes.length; pipeId++) {
            if (pipeId == 0) {
                groupFieldsPerPipe[0] = new Fields(groupFields);
            } else {
                String[] modifiedGroupFields = new String[groupFields.length];
                for (int groupFieldId = 0; groupFieldId < groupFields.length; groupFieldId++) {
                    String groupField = groupFields[groupFieldId];
                    String modifiedGroupField = "__rhs" + String.valueOf(pipeId) + "__" + groupField;
                    pipes[pipeId] = new Rename(pipes[pipeId], new Fields(groupField), new Fields(modifiedGroupField));
                    modifiedGroupFields[groupFieldId] = modifiedGroupField;
                }
                groupFieldsPerPipe[pipeId] = new Fields(modifiedGroupFields);
            }
        }

        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new InnerJoin()));
        // TODO discard modified fields

        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
    }

}
