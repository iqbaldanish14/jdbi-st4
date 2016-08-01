/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.st4;

import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ST4Test {

    @Test
    public void testFoo() throws Exception {
        final URL stg = ST4Test.class.getResource("st4behavior.stg");
        final STGroup group = new STGroupFile(stg, "UTF-8", '<', '>');
        final ST st = group.getInstanceOf("foo");
        st.add("name", "Brian");
        final String out = st.render();
        assertThat(out).isEqualTo("hello (: Brian :)");
    }
}
