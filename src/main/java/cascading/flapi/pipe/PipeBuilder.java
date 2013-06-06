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

import cascading.flapi.pipe.generated.CoGroup.CoGroupBuilder_m16_m17_m18_m19_m13;
import cascading.flapi.pipe.generated.Pipe.PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15;
import cascading.flapi.pipe.generated.Pipe.PipeBuilder_m1_m2_m3_m4_m5_m6_m8_m9_m10_m11_m12_m13_m14_m15;
import cascading.flapi.pipe.generated.Pipe.PipeGenerator;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

/**
 * The main entry point of the API.
 */
public class PipeBuilder {

    /**
     * Starts a new instance of PipeBuilder
     */
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15<Void> start() {
        return PipeGenerator.start(new PipeBuilderHelper());
    }

    /**
     * Starts a new instance of PipeBuilder, with the given name
     * 
     * @param pipeName
     *            of the initial Pipe
     */
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15<Void> start(String pipeName) {
        return PipeGenerator.start(new PipeBuilderHelper()).withName(pipeName);
    }

    /**
     * Starts a new instance of PipeBuilder, from the given Pipe
     * 
     * @param initialPipe
     *            to start from
     */
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m8_m9_m10_m11_m12_m13_m14_m15<Void> from(Pipe initialPipe) {
        return PipeGenerator.start(new PipeBuilderHelper()).from(initialPipe);
    }

    /**
     * Start a new instance of PipeBuilder, by joining the given pipes.
     * 
     * @param pipes
     *            to join
     */
    public static CoGroupBuilder_m16_m17_m18_m19_m13<PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15<Void>> coGroup(Pipe... pipes) {
        return PipeGenerator.start(new PipeBuilderHelper()).coGroup().from((Object[]) pipes);
    }

    static Fields getSelector(Comparable<?>... fields) {
        Fields selector = Fields.ALL;
        if (fields != null && fields.length > 0) {
            selector = new Fields(fields);
        }
        return selector;
    }

}
