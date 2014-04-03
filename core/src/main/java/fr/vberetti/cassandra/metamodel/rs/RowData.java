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
package fr.vberetti.cassandra.metamodel.rs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.core.Row;

import fr.vberetti.cassandra.metamodel.ColumnMetamodel;

public class RowData {

	public static String getString(Row row, ColumnMetamodel<String> column){
		return row.getString(column.getColumnName());
	}
	
	public static Date getDate(Row row, ColumnMetamodel<Date> column){
		return row.getDate(column.getColumnName());
	}
	
	public static Boolean getBool(Row row, ColumnMetamodel<Boolean> column){
		return row.getBool(column.getColumnName());
	}
	
	public static ByteBuffer getBytes(Row row, ColumnMetamodel<ByteBuffer> column){
		return row.getBytes(column.getColumnName());
	}
	
	public static ByteBuffer getBytesUnsafe(Row row, ColumnMetamodel<ByteBuffer> column){
		return row.getBytesUnsafe(column.getColumnName());
	}
	
	public static BigDecimal getDecimal(Row row, ColumnMetamodel<BigDecimal> column){
		return row.getDecimal(column.getColumnName());
	}
	
	public static Double getDouble(Row row, ColumnMetamodel<Double> column){
		return row.getDouble(column.getColumnName());
	}
	
	public static Float getFloat(Row row, ColumnMetamodel<Float> column){
		return row.getFloat(column.getColumnName());
	}
	
	public static InetAddress getInet(Row row, ColumnMetamodel<InetAddress> column){
		return row.getInet(column.getColumnName());
	}
	
	public static Integer getInt(Row row, ColumnMetamodel<Integer> column){
		return row.getInt(column.getColumnName());
	}
	
	public static Long getLong(Row row, ColumnMetamodel<Long> column){
		return row.getLong(column.getColumnName());
	}
	
	public static UUID getUUID(Row row, ColumnMetamodel<UUID> column){
		return row.getUUID(column.getColumnName());
	}
	
	public static BigInteger getVarint(Row row, ColumnMetamodel<BigInteger> column){
		return row.getVarint(column.getColumnName());
	}
}
