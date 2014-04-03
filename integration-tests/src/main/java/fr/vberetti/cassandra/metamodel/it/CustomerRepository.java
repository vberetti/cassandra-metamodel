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
package fr.vberetti.cassandra.metamodel.it;

import static com.google.common.collect.Collections2.transform;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Select.Where;
import com.datastax.driver.core.querybuilder.TypedQueryBuilder;
import com.google.common.base.Function;

import fr.vberetti.cassandra.metamodel.rs.RowDataGetter;

public class CustomerRepository {

	private Session session;

	public CustomerRepository(Session session) {
		this.session = session;
	}

	public List<Integer> findOrdersIdByCustomerId(int customerId) {
		Where query = TypedQueryBuilder
				.select(CustomerOrders_.orderId)
				.from(CustomerOrders_.TABLE_NAME)
				.where(TypedQueryBuilder.eq(CustomerOrders_.customerId,
						customerId));
		ResultSet rs = session.execute(query.getQueryString());

		List<Integer> orderIds = new ArrayList<>(transform(rs.all(),
				new Function<Row, Integer>() {
					@Override
					public Integer apply(Row row) {
						return RowDataGetter.getInt(row,
								CustomerOrders_.orderId);
					}
				}));
		return orderIds;
	}
}
