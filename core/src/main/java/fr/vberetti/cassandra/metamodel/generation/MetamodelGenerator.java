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
package fr.vberetti.cassandra.metamodel.generation;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Lists.newArrayList;
import static fr.vberetti.cassandra.metamodel.generation.TemplateUtil.normalizeUpper;
import static org.apache.commons.lang.StringUtils.lowerCase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.CQLDataSet;
import org.cassandraunit.dataset.cql.FileCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.google.common.base.Function;

public class MetamodelGenerator {

	private static final Logger logger = LoggerFactory
			.getLogger(MetamodelGenerator.class);

	private static final List<String> SYSTEM_TABLES = of(
			"batchlog", //
			"compaction_history",
			"compactions_in_progress", //
			"events", "hints", "IndexInfo",
			"local",
			"NodeIdInfo", //
			"paxos", "peer_events", "peers",
			"range_xfers",
			"schema_columnfamilies", //
			"schema_columns", "schema_keyspaces", "schema_triggers",
			"sessions", //
			"sstable_activity");

	private List<String> cqlFiles;

	private List<String> includeTables = new ArrayList<String>();

	private List<String> excludeTables = new ArrayList<String>(SYSTEM_TABLES);

	private String basePackage;

	private String outputDirectory;

	private Session session;
	private Cluster cluster;

	private VelocityEngine engine;

	private String keyspace;
	
	private Function<String, String> lowerCaseFcn = new Function<String,String>(){
		@Override
		public String apply(String input) {
			return lowerCase(input);
		}
	};

	public void generate() throws ConfigurationException, TTransportException,
			IOException, InterruptedException {
		try {
			// Start embedded Cassandra
			EmbeddedCassandraServerHelper.startEmbeddedCassandra();

			Thread.sleep(2000);
			// Clean all keyspace
			EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
			

			cluster = Cluster.builder().addContactPoint("localhost")
					.withPort(9142).build();
			session = cluster.connect();
			CQLDataLoader dataLoader = new CQLDataLoader(session);
			boolean firstDataSet = true;
			for (String cqlFile : cqlFiles) {
				CQLDataSet dataSet = new FileCQLDataSet(cqlFile, firstDataSet, false, keyspace);
				dataLoader.load(dataSet);
				firstDataSet = false;
			}
			// convert includes/excludes tables to uppercase
			includeTables = newArrayList(transform(includeTables, lowerCaseFcn));
			excludeTables = newArrayList(transform(excludeTables, lowerCaseFcn));

			configureTemplatingEngine();

			session = dataLoader.getSession();
			generateKeyspace(session.getCluster().getMetadata()
					.getKeyspace(keyspace));
		} finally {
			if (session != null) {
				session.close();
			}
			if (cluster != null) {
				cluster.close();
			}
			EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
		}
	}

	private void generateKeyspace(KeyspaceMetadata keyspace) {
		Collection<TableMetadata> tables = keyspace.getTables();
		for (TableMetadata table : tables) {
			if (includeTables.isEmpty()) {
				if (!excludeTables.contains(lowerCase(table.getName()))) {
					renderTableMetamodel(table);
				}
			} else {
				if (includeTables.contains(lowerCase(table.getName()))) {
					renderTableMetamodel(table);
				}
			}
		}
	}

	private void renderTableMetamodel(TableMetadata table) {
		File basePackageDirectory = new File(outputDirectory + File.separator
				+ basePackage.replaceAll("\\.", File.separator));
		basePackageDirectory.mkdirs();
		File outputFile = new File(basePackageDirectory,
				normalizeUpper(table.getName()) + "_.java");
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile);

			Template template = engine.getTemplate("Table_.java.vm");
			VelocityContext context = new VelocityContext();
			context.put("basePackage", basePackage);
			context.put("table", table);
			context.put("MetamodelGenerator", this);
			context.put("TemplateUtil", TemplateUtil.class);
			template.merge(context, writer);
		} catch (IOException e) {
			logger.error("Failed to generate "
					+ normalizeUpper(table.getName()), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private void configureTemplatingEngine() {
		engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		engine.init();
	}

	public List<String> getCqlFiles() {
		return cqlFiles;
	}

	public void setCqlFiles(List<String> cqlFiles) {
		this.cqlFiles = cqlFiles;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public String getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

	public List<String> getIncludeTables() {
		return includeTables;
	}
}
