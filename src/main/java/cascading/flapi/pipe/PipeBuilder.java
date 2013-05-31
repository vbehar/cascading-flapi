package cascading.flapi.pipe;

import cascading.flapi.pipe.generated.Pipe.PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11;
import cascading.flapi.pipe.generated.Pipe.PipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11;
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
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11<Void> start() {
        return PipeGenerator.start(new PipeBuilderHelper());
    }

    /**
     * Starts a new instance of PipeBuilder, with the given name
     * 
     * @param pipeName
     *            of the initial Pipe
     */
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11<Void> start(String pipeName) {
        return PipeGenerator.start(new PipeBuilderHelper()).withName(pipeName);
    }

    /**
     * Starts a new instance of PipeBuilder, from the given Pipe
     * 
     * @param initialPipe
     *            to start from
     */
    public static PipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11<Void> from(Pipe initialPipe) {
        return PipeGenerator.start(new PipeBuilderHelper()).from(initialPipe);
    }

    static Fields getSelector(Comparable<?>... fields) {
        Fields selector = Fields.ALL;
        if (fields != null && fields.length > 0) {
            selector = new Fields(fields);
        }
        return selector;
    }

}
