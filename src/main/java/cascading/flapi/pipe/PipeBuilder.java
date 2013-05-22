package cascading.flapi.pipe;

import cascading.flapi.pipe.generated.Pipe.PipeBuilder_m1_m2_m3_m4_m5;
import cascading.flapi.pipe.generated.Pipe.PipeGenerator;
import cascading.tuple.Fields;

/**
 * The main entry point of the API.
 */
public class PipeBuilder {

    /**
     * Starts a new instance of PipeBuilder
     */
    public static PipeBuilder_m1_m2_m3_m4_m5<Void> start() {
        return PipeGenerator.start(new PipeBuilderHelper());
    }

    static Fields getSelector(Comparable<?>... fields) {
        Fields selector = Fields.ALL;
        if (fields != null && fields.length > 0) {
            selector = new Fields(fields);
        }
        return selector;
    }

}
