/**
 * Copyright (C) 2014 the original author or authors (none)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.vberetti.cassandra.metamodel.sample;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.TypedQueryBuilder;

public class SampleQueriesTest {

	@Test
	public void select() throws Exception {
		String typedQuery = TypedQueryBuilder
				.select(Sample_.first, Sample_.typea, Sample_.typeb)
				.from(Sample_.TABLE_NAME).getQueryString();

		String query = QueryBuilder.select("first", "typea", "typeb")
				.from("sample").getQueryString();

		Assertions.assertThat(typedQuery).isEqualTo(query);
	}

	@Test
	public void delete() throws Exception {
		String typedQuery = TypedQueryBuilder
				.delete(Sample_.first, Sample_.typea, Sample_.typeb)
				.from(Sample_.TABLE_NAME).getQueryString();

		String query = QueryBuilder.delete("first", "typea", "typeb")
				.from("sample").getQueryString();

		Assertions.assertThat(typedQuery).isEqualTo(query);
	}

	@Test
	public void eq() throws Exception {
		String typedQuery = QueryBuilder.select().from(Sample_.TABLE_NAME)
				.where(TypedQueryBuilder.eq(Sample_.first, "value"))
				.getQueryString();

		String query = QueryBuilder.select()
				.from("sample").where(QueryBuilder.eq("first", "value")).getQueryString();

		Assertions.assertThat(typedQuery).isEqualTo(query);
	}
}
