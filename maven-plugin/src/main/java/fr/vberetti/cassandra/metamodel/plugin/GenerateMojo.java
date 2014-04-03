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
package fr.vberetti.cassandra.metamodel.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.thrift.transport.TTransportException;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import fr.vberetti.cassandra.metamodel.generation.MetamodelGenerator;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresProject=true)
public class GenerateMojo extends AbstractMojo {

	@Parameter
	private List<File> cqlFiles = new ArrayList<>();

	@Parameter
	private List<String> includeTables = new ArrayList<String>();

	@Parameter(property = "cassandra.metamodel.basePackage", required = true)
	private String basePackage;

	@Parameter(property = "cassandra.metamodel.outputDirectory", defaultValue = "${project.build.directory}/generated-metamodel")
	private String outputDirectory;

	@Parameter(property = "cassandra.metamodel.keyspace", required = true)
	private String keyspace;

	@Component
	private MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		MetamodelGenerator generator = new MetamodelGenerator();
		generator.setBasePackage(basePackage);
		List<String> cqlFilePaths = Lists.newArrayList(Collections2.transform(
				cqlFiles, new Function<File, String>() {
					@Override
					public String apply(File input) {
						return input.getAbsolutePath();
					}
				}));
		generator.setCqlFiles(cqlFilePaths);
		generator.getIncludeTables().addAll(includeTables);
		generator.setKeyspace(keyspace);
		generator.setOutputDirectory(outputDirectory);

		try {
			generator.generate();
		} catch (ConfigurationException | TTransportException | IOException
				| InterruptedException e) {
			throw new MojoFailureException("Metamodel generation failed "
					+ e.getMessage());
		}

		getLog().info("Adding generation output directory as source ...");
		project.addCompileSourceRoot(outputDirectory);
	}

}
