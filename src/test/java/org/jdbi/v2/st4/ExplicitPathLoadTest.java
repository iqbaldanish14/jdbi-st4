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
package org.jdbi.v2.st4;

import org.junit.Rule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExplicitPathLoadTest {
    @Rule
    public final H2Rule h2 = new H2Rule();

    @Test
    public void testFoo() throws Exception {
        DBI dbi = new DBI(h2);
        dbi.setStatementLocator(ST4StatementLocator.fromClasspath("/explicit/sql.stg"));

        Dao dao = dbi.onDemand(Dao.class);

        dao.create();
        dao.insert(1, "Brian");
        String brian = dao.findNameById(1);

        assertThat(brian).isEqualTo("Brian");
    }

    public interface Dao {

        @SqlUpdate
        void create();

        @SqlUpdate
        void insert(int id, String name);

        @SqlQuery
        String findNameById(int id);
    }
}
