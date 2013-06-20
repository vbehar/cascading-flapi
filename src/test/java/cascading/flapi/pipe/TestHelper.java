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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.FlowProcess;
import cascading.flow.local.LocalFlowConnector;
import cascading.operation.BaseOperation;
import cascading.operation.Filter;
import cascading.operation.FilterCall;
import cascading.pipe.Pipe;
import cascading.property.ConfigDef.Getter;
import cascading.scheme.local.TextDelimited;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntryIterator;

/**
 * Simple test helper
 */
class TestHelper {

    static class NullGetter implements Getter {

        @Override
        public String update(String key, String value) {
            return null;
        }

        @Override
        public String get(String key) {
            return null;
        }
    }
    
    static class EqualsFilter extends BaseOperation<Void> implements Filter<Void> {
        private static final long serialVersionUID = 1L;

        private final Object value;

        private EqualsFilter(Object value) {
            super(1);
            this.value = value;
        }
        public static EqualsFilter value(Object value) {
            return new EqualsFilter(value);
        }

        @Override
        public boolean isRemove(@SuppressWarnings("rawtypes") FlowProcess flowProcess, FilterCall<Void> filterCall) {
            return value.equals(filterCall.getArguments().getTuple().getObject(0));
        }
    }
    
    static List<Tuple> launchFlow(Pipe sourcePipe, Pipe tailPipe, Fields inputFields, Tuple... inputTuples) throws IOException {
        File tmpDir = Files.createTempDir();
        try {
            File input = new File(tmpDir, "input.csv");
            File output = new File(tmpDir, "output.csv");
            FileWriter writer = new FileWriter(input);
            for(Tuple tuple : inputTuples) {
                if(inputFields.size() != tuple.size()) {
                    throw new IllegalArgumentException("Number of input fields is not the same of value of input tuple");
                }
                writer.write((tuple.getString(0) == null)? "" : tuple.getString(0));
                for(int i=1; i<tuple.size(); i++) {
                    writer.write("\t");
                    writer.write((tuple.getString(i) == null)? "" : tuple.getString(i));
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();
            FileTap inputTap = new FileTap(new TextDelimited(inputFields), input.getAbsolutePath());
            FileTap outputTap = new FileTap(new TextDelimited(true, "\t"), output.getAbsolutePath());
            FlowDef flowDef = FlowDef.flowDef().setName("testFlow")
                    .addSource(sourcePipe, inputTap)
                    .addTailSink(tailPipe, outputTap);
            Flow<?> flow = new LocalFlowConnector(new Properties()).connect(flowDef);
            flow.complete();
            
            List<Tuple> result = new ArrayList<Tuple>();
            TupleEntryIterator iterator = flow.openSink();
            while(iterator.hasNext()) {
                result.add(iterator.next().getTupleCopy());
            }
            iterator.close();
            return result;
        } finally {
            FileUtils.deleteDirectory(tmpDir);
        }
    }

}
