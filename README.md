Cassandra Metamodel
===================

Cassandra Metamodel is a thin and non-intrusive layer on top of Datastax Java Driver to provides typed queries and result sets.

Benefits
--------
* Discovers problems at compilation time instead of runtime
* Improves refactoring on columns name/type changes

Requirements
------------
* Java 1.7+
* Cassandra 2.0+
* Datastax Java Driver 2.0.1+

Quick Start
-----------
### Generate metamodel ###
#### Cassandra metamodel maven plugin ####
##### Goals #####
* _generate_ : generates the metamodel for the provided cql files. Bound on generate-sources.
##### Plugin definition example #####
        <plugin>
				<groupId>fr.vberetti.cassandra.metamodel</groupId>
				<artifactId>cassandra-metamodel-maven-plugin</artifactId>
				<version>XXXXX</version>
				<configuration>
					<cqlFiles>
						<cqlFile>src/main/cql/file.cql</cqlFile>
					</cqlFiles>
					<keyspace>DEFAULT_KSP</keyspace>
					<basePackage>fr.vberetti.cassandra.sample</basePackage>
				</configuration>
				<executions>
					<execution>
						<id>generate</id>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
#### Manually ####
Cassandra metamodel maven plugin is optional. Be free to define the metamodel manually.

### How to use ###
#### Query Creation ####
`com.datastax.driver.core.querybuilder.TypedQueryBuilder`.
##### Before #####
        QueryBuilder.select("name")
				.from("sample")
				.where(QueryBuilder.eq("id", "2"))
##### Now #####
        TypedQueryBuilder.select(Sample_.name)
				.from(Sample_.TABLE_NAME)
				.where(TypedQueryBuilder.eq(Sample_.id, "2"))
#### ResultSet ####
`fr.vberetti.cassandra.metamodel.rs.RowData`
##### Before #####
        ResultSet rs = session.execute(...);
        Row one = rs.one();
        String name = one.getString("name");
##### Now #####
        ResultSet rs = session.execute(...);
        Row one = rs.one();
        String name = RowData.getString(row, Sample_.name);

How does it work ?
------------------
### Project configuration ###
3 modules :

* maven-plugin : provides the ability to generate metamodel from maven.
* core : contains the API to be used in queries and resultSet as well as the generator used by maven plugin. 
* integration-tests : validates core and maven plugin. Can be used as a sample project.

### Generator ###

#### Steps ####
* starts an embedded Cassandra instance
* loads CQL files into embedded instance
* browses the keyspace metadata and generate one class per table

#### Fields naming ####
Every table/column names are converted into camel case format for classes and fields names.

### Maven Plugin ###
The Maven plugin simply wraps the generator into a maven plugin structure.



