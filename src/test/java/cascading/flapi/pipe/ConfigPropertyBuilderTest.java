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
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

import cascading.flapi.pipe.TestHelper.NullGetter;
import cascading.pipe.Pipe;
import cascading.property.ConfigDef.Getter;

/**
 * Tests for all the ConfigProperty-related operations
 */
public class ConfigPropertyBuilderTest {

    @Test
    public void setConfigPropertyWithoutMode() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .setConfigProperty("mykey").withValue("myvalue")
                .pipe();
        
        assertThat(pipe.getConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getConfigDef().getAllKeys()).contains("mykey");
        
        String value = pipe.getConfigDef().apply("mykey", new Getter() {
            
            @Override
            public String update(String key, String value) {
                fail("should not be called");
                return null;
            }
            
            @Override
            public String get(String key) {
                return null;
            }
        });
        assertThat(value).isEqualTo("myvalue");
    }

    @Test
    public void setConfigPropertyInUpdateMode() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .setConfigProperty("mykey").inUpdateMode().withValue("myvalue2")
                .pipe();
        
        assertThat(pipe.getConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getConfigDef().getAllKeys()).contains("mykey");
        
        String value = pipe.getConfigDef().apply("mykey", new Getter() {
            
            @Override
            public String update(String key, String value) {
                assertThat(key).isEqualTo("mykey");
                assertThat(value).isEqualTo("myvalue2");
                return "myvalue1,myvalue2";
            }
            
            @Override
            public String get(String key) {
                return "myvalue1";
            }
        });
        assertThat(value).isEqualTo("myvalue1,myvalue2");
    }

    @Test
    public void setStepConfigPropertyWithoutMode() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .setStepConfigProperty("mykey").withValue("myvalue")
                .pipe();
        
        assertThat(pipe.getStepConfigDef().isEmpty()).isFalse();
        assertThat(pipe.getStepConfigDef().getAllKeys()).contains("mykey");
        
        String value = pipe.getStepConfigDef().apply("mykey", new NullGetter());
        assertThat(value).isEqualTo("myvalue");
    }

}
