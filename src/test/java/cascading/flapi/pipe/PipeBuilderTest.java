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

import cascading.operation.Identity;
import cascading.operation.Insert;
import cascading.operation.NoOp;
import cascading.operation.aggregator.Count;
import cascading.operation.expression.ExpressionFunction;
import cascading.operation.filter.FilterNull;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Discard;
import cascading.pipe.assembly.Rename;
import cascading.pipe.assembly.Retain;
import cascading.pipe.assembly.Unique;
import cascading.tuple.Fields;

/**
 * Tests for all the global operations on {@link PipeBuilder}.
 * 
 * @see EachBuilderTest
 * @see EveryBuilderTest
 * @see GroupByBuilderTest
 */
public class PipeBuilderTest {

    @Test
    public void empty() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .pipe();
        
        assertThat(pipe).isNotNull();
        assertThat(pipe.getName()).isEqualTo("start");
    }

    @Test
    public void emptyWithName() throws Exception {
        Pipe pipe = PipeBuilder.start("toto")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("toto");
    }

    @Test
    public void withName() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .withName("toto")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("toto");
    }

    @Test
    public void from() throws Exception {
        Pipe initialPipe = new Pipe("initial");
        
        Pipe pipe = PipeBuilder.from(initialPipe)
                .pipe();
        
        assertThat(pipe).isEqualTo(initialPipe);
    }

    @Test
    public void fromWithName() throws Exception {
        Pipe initialPipe = new Pipe("initial");
        
        Pipe pipe = PipeBuilder.from(initialPipe)
                .withName("new pipe")
                .pipe();
        
        assertThat(pipe.getName()).isEqualTo("new pipe");
        assertThat(pipe.getPrevious()).hasSize(1);
        assertThat(pipe.getPrevious()).containsExactly(initialPipe);
    }

    @Test
    public void retain() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .retain("url")
                .pipe();

        assertThat(pipe).isInstanceOf(Retain.class);
        
        Retain retain = (Retain) pipe;
        assertThat(retain.getTails()).hasSize(1);
        assertThat(retain.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)retain.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url"));
    }

    @Test
    public void discard() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .discard("url")
                .pipe();

        assertThat(pipe).isInstanceOf(Discard.class);
        
        Discard discard = (Discard) pipe;
        assertThat(discard.getTails()).hasSize(1);
        assertThat(discard.getTails()[0]).isInstanceOf(Each.class);
        
        Each each = (Each)discard.getTails()[0];
        assertThat(each.getArgumentSelector()).isEqualTo(new Fields("url"));
    }

    @Test
    public void unique() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .unique()
                .pipe();

        assertThat(pipe).isInstanceOf(Unique.class);
    }

    @Test
    public void count() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .count("myCount")
                .pipe();

        assertThat(pipe).isInstanceOf(Every.class);

        Every every = (Every) pipe;
        assertThat(every.getAggregator()).isInstanceOf(Count.class);
        assertThat(every.getAggregator().getFieldDeclaration()).isEqualTo(new Fields("myCount"));
    }

    @Test
    public void complexPipe() throws Exception {
        Pipe pipe = PipeBuilder.start()
                .each().select("data").filterOut().nullValues()
                .each().insertField("processingDate").withValue(System.currentTimeMillis())
                .renameField("domains").to("oldDomains")
                .each().select("url").applyFunction(new ExpressionFunction(new Fields("isRelativeUrl"), "url.startsWith(\"http\")", String.class))
                .compressOutput().forThisStepOnly().withGzipCodec().withType("BLOCK").ofTheMappers()
                .discard("data")
                .groupBy().withSortOnFields("url").onFields("domain")
                .count("domains")
                .pipe();
        
        // count
        assertThat(pipe).isInstanceOf(Every.class);
        assertThat(((Every)pipe).getOperation()).isInstanceOf(Count.class);
        
        // groupBy
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(GroupBy.class);
        
        // discard
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Discard.class);
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Each.class);
        assertThat(((Each)pipe).getOperation()).isInstanceOf(NoOp.class);
        
        // expression
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Each.class);
        assertThat(((Each)pipe).getOperation()).isInstanceOf(ExpressionFunction.class);
        
        // rename
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Rename.class);
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Each.class);
        assertThat(((Each)pipe).getOperation()).isInstanceOf(Identity.class);
        
        // insert
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Each.class);
        assertThat(((Each)pipe).getOperation()).isInstanceOf(Insert.class);
        
        // filter null
        pipe = pipe.getPrevious()[0];
        assertThat(pipe).isInstanceOf(Each.class);
        assertThat(((Each)pipe).getOperation()).isInstanceOf(FilterNull.class);
        
        // initial pipe
        pipe = pipe.getPrevious()[0];
        assertThat(pipe.getClass()).isEqualTo(Pipe.class);
    }

}
