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
import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.operation.OperationCall;
import cascading.pipe.CoGroup;
import cascading.pipe.Pipe;
import cascading.pipe.joiner.InnerJoin;
import cascading.pipe.joiner.LeftJoin;
import cascading.pipe.joiner.OuterJoin;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

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
        renamePipesExceptFirst();
        
        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new InnerJoin()));
        
        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
        
        // Discard temporary group fields (__rhs1__..., __rhs2__)
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get()).discard(allTemporaryFields).pipe());
    }

    @Override
    public void applyLeftJoin() {
        renamePipesExceptFirst();
        
        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new LeftJoin()));
        
        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
        
        // Discard temporary group fields (__rhs1__..., __rhs2__)
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get()).discard(allTemporaryFields).pipe());
    }
    
    @Override
    public void applyOuterJoin() {
        renameAllPipes();
        
        pipeWrapper.set(new CoGroup(pipes, groupFieldsPerPipe, null, new OuterJoin()));
        
        // Optionally set the number of reducers
        if (numberOfReducers > 0) {
            pipeWrapper.get().getStepConfigDef().setProperty("mapred.reduce.tasks", String.valueOf(numberOfReducers));
        }
        
        // Optionally execute the callbacks AFTER the groupBy
        callbacksSupport.applyToAllCallbacks(pipeWrapper);
        
        // Set final group fields
        String[] finalGroupFields = new String[groupFields.length];
        for(int i=0; i<groupFields.length; i++) {
            finalGroupFields[i] = "__tmp_finalGroupField__" + groupFields[i];
        }
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get())
                        .each().select().applyFunction(new OuterJoinFinalGroupFieldSetterFunction(groupFieldsPerPipe,
                                                                                                  finalGroupFields))
                        .pipe());
        
        // Rename final group fields to original group fields
        for(int i=0; i<finalGroupFields.length; i++) {
            pipeWrapper.set(PipeBuilder.from(pipeWrapper.get())
                            .renameField(finalGroupFields[i])
                            .to(groupFields[i]).pipe());
        }
        
        //Discard temporary group fields (__rhs1__..., __rhs2__)
        pipeWrapper.set(PipeBuilder.from(pipeWrapper.get()).discard(allTemporaryFields).pipe());
    }
    
    private Fields[] groupFieldsPerPipe;
    private String[] allTemporaryFields;
    private void renameAllPipes() {
        renamePipes(true);
    }
    private void renamePipesExceptFirst() {
        renamePipes(false);
    }
    private void renamePipes(boolean renameFirstPipe) {
        groupFieldsPerPipe = new Fields[pipes.length];
        if(!renameFirstPipe) {
            groupFieldsPerPipe[0] = new Fields(groupFields);
        }
        int start = (renameFirstPipe)? 0 : 1;
        allTemporaryFields = new String[(pipes.length-start) * groupFields.length];
        int allTemporaryFieldsId = 0;
        for (int pipeId = start; pipeId < pipes.length; pipeId++) {
            String[] temporaryGroupFields = new String[groupFields.length];
            for (int groupFieldId = 0; groupFieldId < groupFields.length; groupFieldId++) {
                String groupField = groupFields[groupFieldId];
                String temporaryGroupField = "__rhs" + pipeId + "__" + groupField;
                pipes[pipeId] = PipeBuilder.from(pipes[pipeId]).renameField(groupField).to(temporaryGroupField).pipe();
                temporaryGroupFields[groupFieldId] = temporaryGroupField;
                allTemporaryFields[allTemporaryFieldsId++] = temporaryGroupField;
            }
            groupFieldsPerPipe[pipeId] = new Fields(temporaryGroupFields);
        }
    }
    
    private static class OuterJoinFinalGroupFieldSetterFunction extends BaseOperation<Tuple> implements Function<Tuple> {
        private static final long serialVersionUID = 1L;
        private final Fields[] groupFieldsPerPipe;
        private final int numOfGroupFields;
        
        private OuterJoinFinalGroupFieldSetterFunction(Fields[] groupFieldsPerPipe, String[] finalGroupFields) {
            super(new Fields(finalGroupFields));
            this.numOfGroupFields = finalGroupFields.length;
            this.groupFieldsPerPipe = groupFieldsPerPipe;
        }
        
        @Override
        public void prepare(@SuppressWarnings("rawtypes") FlowProcess flowProcess, OperationCall<Tuple> operationCall) {
            operationCall.setContext(Tuple.size(numOfGroupFields));
        }

        @Override
        public void operate(@SuppressWarnings("rawtypes") FlowProcess flowProcess, FunctionCall<Tuple> functionCall) {
            TupleEntry tupleEntry = functionCall.getArguments();
            Tuple outputTuple = functionCall.getContext();
            boolean allGroupFieldsValuesAreNull = true;
            for(Fields fields : groupFieldsPerPipe) {
                if(! groupFieldsValuesAreNull(tupleEntry, fields)) {
                    for(int i=0; i<fields.size(); i++) {
                        outputTuple.set(i, tupleEntry.getObject(fields.get(i)));
                    }
                    allGroupFieldsValuesAreNull = false;
                    break;
                }
            }
            if(allGroupFieldsValuesAreNull) {
                outputTuple = Tuple.size(numOfGroupFields);
            }
            functionCall.getOutputCollector().add(outputTuple);
        }
        
        private boolean groupFieldsValuesAreNull(TupleEntry tupleEntry, Fields fields) {
            for(int i=0; i<fields.size(); i++) {
                if(tupleEntry.getObject(fields.get(i)) != null) {
                    return false;
                }
            }
            return true;
        }
    }
}
