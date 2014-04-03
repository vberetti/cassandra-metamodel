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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.dataset.cql.FileCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CustomerRepositoryIT {

	private static final String KEYSPACE = "METAMODEL_KSP";
	private static Cluster cluster;
	private CustomerRepository customerRepository;

	@BeforeClass
	public static void beforeClass() throws Exception {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		Thread.sleep(10000);
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
		cluster = Cluster.builder().addContactPoint("localhost").withPort(9142)
				.build();
		Session session = cluster.connect();
		CQLDataLoader dataLoader = new CQLDataLoader(session);
		dataLoader.load(new FileCQLDataSet("src/main/cql/A.cql", KEYSPACE));
		dataLoader.load(new ClassPathCQLDataSet("data.cql", KEYSPACE));
	}

	@Before
	public void setUp() throws Exception {
		customerRepository = new CustomerRepository(cluster.connect(KEYSPACE));
	}

	@Test
	public void testFindOrdersIdByCustomerId() {
		List<Integer> orders = customerRepository.findOrdersIdByCustomerId(1);
		assertThat(orders).hasSize(3).contains(1, 2, 3);
	}

	@Test
	public void testFindOrdersIdByCustomerIdEmpty() {
		List<Integer> orders = customerRepository.findOrdersIdByCustomerId(999);
		assertThat(orders).isEmpty();
	}

}
