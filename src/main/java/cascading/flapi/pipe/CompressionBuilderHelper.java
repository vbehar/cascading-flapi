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
import cascading.flapi.pipe.generated.Compression.CompressionHelper;
import cascading.pipe.Pipe;
import cascading.property.ConfigDef;

/**
 * The {@link CompressionHelper} implementation for the enabling the compression.
 */
class CompressionBuilderHelper implements CompressionHelper {

    private final ObjectWrapper<Pipe> pipeWrapper;

    private boolean forThisStepOnly = false;

    private String compressionType;

    private String codecClassName;

    public CompressionBuilderHelper(ObjectWrapper<Pipe> pipeWrapper) {
        this.pipeWrapper = pipeWrapper;
    }

    @Override
    public void forThisStepOnly() {
        forThisStepOnly = true;
    }

    @Override
    public void withType(String compressionType) {
        this.compressionType = compressionType;
    }

    @Override
    public void withDefaultCodec() {
        withCodecClass("org.apache.hadoop.io.compress.DefaultCodec");
    }

    @Override
    public void withGzipCodec() {
        withCodecClass("org.apache.hadoop.io.compress.GzipCodec");
    }

    @Override
    public void withBZip2Codec() {
        withCodecClass("org.apache.hadoop.io.compress.BZip2Codec");
    }

    @Override
    public void withSnappyCodec() {
        withCodecClass("org.apache.hadoop.io.compress.SnappyCodec");
    }

    @Override
    public void withCodecClass(String fullCodecClassName) {
        this.codecClassName = fullCodecClassName;
    }

    @Override
    public void ofTheJob() {
        ConfigDef configDef = getConfigDef();
        configDef.setProperty("mapred.output.compress", "true");
        if (codecClassName != null) {
            configDef.setProperty("mapred.output.compression.codec", codecClassName);
        }
        if (compressionType != null) {
            configDef.setProperty("mapred.output.compression.type", compressionType);
        }
    }

    @Override
    public void ofTheMappers() {
        ConfigDef configDef = getConfigDef();
        configDef.setProperty("mapred.compress.map.output", "true");
        if (codecClassName != null) {
            configDef.setProperty("mapred.map.output.compression.codec", codecClassName);
        }
        if (compressionType != null) {
            configDef.setProperty("mapred.output.compression.type", compressionType);
        }
    }

    private ConfigDef getConfigDef() {
        return forThisStepOnly ? pipeWrapper.get().getStepConfigDef() : pipeWrapper.get().getConfigDef();
    }

}
