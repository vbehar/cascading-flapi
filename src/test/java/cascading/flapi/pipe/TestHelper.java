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

import cascading.property.ConfigDef.Getter;

/**
 * Simple test helper
 */
public class TestHelper {

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

}
