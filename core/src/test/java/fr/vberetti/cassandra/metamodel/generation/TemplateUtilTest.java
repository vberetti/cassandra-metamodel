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

import static fr.vberetti.cassandra.metamodel.generation.TemplateUtil.normalizeLower;
import static fr.vberetti.cassandra.metamodel.generation.TemplateUtil.normalizeUpper;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class TemplateUtilTest {

	@Test
	public void testNormalizeUpper() throws Exception{
		assertThat(normalizeUpper(null)).isEqualTo(null);
		assertThat(normalizeUpper("")).isEqualTo("");
		assertThat(normalizeUpper("   ")).isEqualTo("   ");
		assertThat(normalizeUpper("camelCase")).isEqualTo("CamelCase");
		assertThat(normalizeUpper("CamelCase")).isEqualTo("CamelCase");
		assertThat(normalizeUpper(" camelCase ")).isEqualTo("CamelCase");
		assertThat(normalizeUpper("not_camel_case")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("Not_cAmel_caSe")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("not-camel-case")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("nOt-cAmel-Case")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("not camel case")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("not_camel case")).isEqualTo("NotCamelCase");
		assertThat(normalizeUpper("not_cAmel caSe-at-aLl")).isEqualTo("NotCamelCaseAtAll");
	}
	@Test
	public void testNormalizeLower() throws Exception{
		assertThat(normalizeLower(null)).isEqualTo(null);
		assertThat(normalizeLower("")).isEqualTo("");
		assertThat(normalizeLower("   ")).isEqualTo("   ");
		assertThat(normalizeLower("camelCase")).isEqualTo("camelCase");
		assertThat(normalizeLower("CamelCase")).isEqualTo("camelCase");
		assertThat(normalizeLower(" camelCase ")).isEqualTo("camelCase");
		assertThat(normalizeLower("not_camel_case")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("Not_cAmel_caSe")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("not-camel-case")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("nOt-cAmel-Case")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("not camel case")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("not_camel case")).isEqualTo("notCamelCase");
		assertThat(normalizeLower("not_cAmel caSe-at-aLl")).isEqualTo("notCamelCaseAtAll");
	}
}
