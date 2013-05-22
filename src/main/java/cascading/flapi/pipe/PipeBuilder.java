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
     * @param name
     *            of the initial Pipe
     */
    public static PipeBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10_m11<Void> start(String name) {
        return PipeGenerator.start(new PipeBuilderHelper()).withName(name);
    }

    /**
     * Starts a new instance of PipeBuilder, from the given Pipe
     * 
     * @param pipe
     *            to start from
     */
    public static PipeBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10_m11<Void> from(Pipe pipe) {
        return PipeGenerator.start(new PipeBuilderHelper()).from(pipe);
    }

    static Fields getSelector(Comparable<?>... fields) {
        Fields selector = Fields.ALL;
        if (fields != null && fields.length > 0) {
            selector = new Fields(fields);
        }
        return selector;
    }

}
