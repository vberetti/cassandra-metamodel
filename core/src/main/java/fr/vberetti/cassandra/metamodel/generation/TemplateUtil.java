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

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;
import static org.apache.commons.lang.StringUtils.isBlank;

import com.google.common.base.CaseFormat;

public class TemplateUtil {

    public static String normalizeUpper(String raw) {
        return normalize(raw, UPPER_CAMEL, false);
    }

    public static String normalizeLower(String raw) {
        return normalize(raw, LOWER_CAMEL, true);
    }

    private static String normalize(String raw, CaseFormat destination, boolean lowerCase) {
    	if (isBlank(raw)){
    		return raw;
    	}
    	
    	String toNormalize = raw.trim();
    	if (toNormalize.contains(" ")) {
    		toNormalize = toNormalize.replaceAll(" ", "_");
    	}
    	if (toNormalize.contains("_") && toNormalize.contains("-")) {
    		toNormalize = toNormalize.replaceAll("-", "_");
    	}
    	
    	CaseFormat original = lowerCase ? UPPER_CAMEL : LOWER_CAMEL;
        if (toNormalize.contains("_")) {
        	original =  lowerCase ? UPPER_UNDERSCORE : LOWER_UNDERSCORE;
        } else if(toNormalize.contains("-")){
        	original =  LOWER_HYPHEN;
        }
        
        return original.to(destination, toNormalize);
    }
}
