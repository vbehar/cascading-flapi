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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import cascading.flapi.pipe.TestHelper.NullGetter;
import cascading.pipe.Pipe;

/**
 * Tests for all the Compression-related operations
 */
public class CompressionBuilderTest {

    @Test
    public void compressJobOutput() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .compressOutput().ofTheJob()
                .pipe();
        
        assertThat(pipe.getConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getConfigDef().getAllKeys()).contains("mapred.output.compress");
        
        assertThat(pipe.getConfigDef().apply("mapred.output.compress", new NullGetter())).isEqualTo("true");
    }

    @Test
    public void compressJobOutputWithCodec() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .compressOutput().withSnappyCodec().ofTheJob()
                .pipe();
        
        assertThat(pipe.getConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getConfigDef().getAllKeys()).contains("mapred.output.compress", "mapred.output.compression.codec");
        
        assertThat(pipe.getConfigDef().apply("mapred.output.compress", new NullGetter())).isEqualTo("true");
        assertThat(pipe.getConfigDef().apply("mapred.output.compression.codec", new NullGetter())).contains("SnappyCodec");
    }

    @Test
    public void compressCurrentJobOutput() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .compressOutput().forThisStepOnly().ofTheJob()
                .pipe();
        
        assertThat(pipe.getStepConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getStepConfigDef().getAllKeys()).contains("mapred.output.compress");
        
        assertThat(pipe.getStepConfigDef().apply("mapred.output.compress", new NullGetter())).isEqualTo("true");
    }

    @Test
    public void compressMapOutput() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .compressOutput().ofTheMappers()
                .pipe();
        
        assertThat(pipe.getConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getConfigDef().getAllKeys()).contains("mapred.compress.map.output");
        
        assertThat(pipe.getConfigDef().apply("mapred.compress.map.output", new NullGetter())).isEqualTo("true");
    }

}
