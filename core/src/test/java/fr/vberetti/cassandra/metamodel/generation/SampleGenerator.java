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

import static com.google.common.collect.Lists.newArrayList;
import fr.vberetti.cassandra.metamodel.generation.MetamodelGenerator;

public class SampleGenerator {

	public static void main(String[] args) {

		try {
			MetamodelGenerator generator = new MetamodelGenerator();
			generator.setBasePackage("fr.vberetti.cassandra.metamodel.sample");
			generator.setKeyspace("DEFAULT");
			generator.setOutputDirectory("src/test/java/");
			generator.setCqlFiles(newArrayList("src/test/resources/sample.cql"));
			generator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
