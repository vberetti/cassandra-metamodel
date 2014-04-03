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
package com.datastax.driver.core.querybuilder;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Iterables.toArray;
import static java.util.Arrays.asList;

import java.util.Set;

import com.google.common.base.Function;

import fr.vberetti.cassandra.metamodel.ColumnMetamodel;
import fr.vberetti.cassandra.metamodel.IdxColumnMetamodel;
import fr.vberetti.cassandra.metamodel.IncrColumnMetamodel;

/**
 * Strongly typed query builder.
 */
public class TypedQueryBuilder {

    @SuppressWarnings("rawtypes")
    private static Function<ColumnMetamodel, String> metamodelColumnNamesFunction = new Function<ColumnMetamodel, String>() {
        @Override
        public String apply(ColumnMetamodel metamodel) {
            return metamodel.getColumnName();
        }
    };

    @SuppressWarnings("rawtypes")
    public static Select.Builder select(ColumnMetamodel... columnMetas) {
        return QueryBuilder.select(toArray(transform(asList(columnMetas), metamodelColumnNamesFunction), String.class));
    }

    @SuppressWarnings("rawtypes")
    public static Delete.Builder delete(ColumnMetamodel... columnMetas) {
        return QueryBuilder.delete(toArray(transform(asList(columnMetas), metamodelColumnNamesFunction), String.class));
    }

    public static <T> Clause eq(IdxColumnMetamodel<T> column, T value) {
        return QueryBuilder.eq(column.getColumnName(), value);
    }

    @SafeVarargs
    public static <T> Clause in(IdxColumnMetamodel<T> column, T... values) {
        return QueryBuilder.in(column.getColumnName(), values);
    }

    public static <T> Clause lt(IdxColumnMetamodel<T> column, T value) {
        return QueryBuilder.lt(column.getColumnName(), value);
    }

    public static <T> Clause lte(IdxColumnMetamodel<T> column, T value) {
        return QueryBuilder.lte(column.getColumnName(), value);
    }

    public static <T> Clause gt(IdxColumnMetamodel<T> column, T value) {
        return QueryBuilder.gt(column.getColumnName(), value);
    }

    public static <T> Clause gte(IdxColumnMetamodel<T> column, T value) {
        return QueryBuilder.gte(column.getColumnName(), value);
    }

    @SuppressWarnings("rawtypes")
    public static Ordering asc(IdxColumnMetamodel column) {
        return QueryBuilder.asc(column.getColumnName());
    }

    @SuppressWarnings("rawtypes")
    public static Ordering desc(IdxColumnMetamodel column) {
        return QueryBuilder.desc(column.getColumnName());
    }

    public static <T> Assignment set(ColumnMetamodel<T> column, T value) {
        return QueryBuilder.set(column.getColumnName(), value);
    }

    @SuppressWarnings("rawtypes")
    public static Assignment incr(IncrColumnMetamodel column) {
        return QueryBuilder.incr(column.getColumnName(), 1L);
    }

    @SuppressWarnings("rawtypes")
    public static Assignment incr(IncrColumnMetamodel column, long value) {
        return QueryBuilder.incr(column.getColumnName(), value);
    }

    @SuppressWarnings("rawtypes")
    public static Assignment decr(IncrColumnMetamodel column) {
        return QueryBuilder.decr(column.getColumnName(), 1L);
    }

    @SuppressWarnings("rawtypes")
    public static Assignment decr(IncrColumnMetamodel column, long value) {
        return QueryBuilder.decr(column.getColumnName(), value);
    }

    public static <T> Assignment add(ColumnMetamodel<T> column, T value) {
        return QueryBuilder.add(column.getColumnName(), value);
    }

    public static <T> Assignment addAll(ColumnMetamodel<T> column, Set<T> set) {
        return QueryBuilder.addAll(column.getColumnName(), set);
    }

    public static <T> Assignment remove(ColumnMetamodel<T> column, T value) {
        return QueryBuilder.remove(column.getColumnName(), value);
    }

    public static <T> Assignment removeAll(ColumnMetamodel<T> column, Set<T> set) {
        return QueryBuilder.removeAll(column.getColumnName(), set);
    }

}
