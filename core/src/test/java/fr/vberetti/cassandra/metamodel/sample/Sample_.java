
package fr.vberetti.cassandra.metamodel.sample;
import fr.vberetti.cassandra.metamodel.impl.ColumnMetamodelImpl;
import fr.vberetti.cassandra.metamodel.ColumnMetamodel;
import fr.vberetti.cassandra.metamodel.impl.IdxColumnMetamodelImpl;
import fr.vberetti.cassandra.metamodel.IdxColumnMetamodel;

/**
 * Generated with Cassandra Metamodel<br/>
 * https://github.com/vberetti/cassandra-metamodel
 */
public class Sample_ {
    public static final String TABLE_NAME="sample";
	
    public static final IdxColumnMetamodel<java.lang.String> first = 
        new IdxColumnMetamodelImpl<java.lang.String>("first");
    public static final IdxColumnMetamodel<java.lang.Integer> second = 
        new IdxColumnMetamodelImpl<java.lang.Integer>("second");
    public static final IdxColumnMetamodel<java.util.Date> third = 
        new IdxColumnMetamodelImpl<java.util.Date>("third");
    public static final ColumnMetamodel<java.lang.String> camelcase = 
        new ColumnMetamodelImpl<java.lang.String>("camelcase");
    public static final ColumnMetamodel<java.lang.String> caseSensitive = 
        new ColumnMetamodelImpl<java.lang.String>("caseSensitive");
    public static final IdxColumnMetamodel<java.lang.String> secondaryIndexColumn = 
        new IdxColumnMetamodelImpl<java.lang.String>("secondary_index_column");
    public static final ColumnMetamodel<java.lang.String> typea = 
        new ColumnMetamodelImpl<java.lang.String>("typea");
    public static final ColumnMetamodel<java.lang.Long> typeb = 
        new ColumnMetamodelImpl<java.lang.Long>("typeb");
    public static final ColumnMetamodel<java.nio.ByteBuffer> typec = 
        new ColumnMetamodelImpl<java.nio.ByteBuffer>("typec");
    public static final ColumnMetamodel<java.lang.Boolean> typed = 
        new ColumnMetamodelImpl<java.lang.Boolean>("typed");
    public static final ColumnMetamodel<java.math.BigDecimal> typef = 
        new ColumnMetamodelImpl<java.math.BigDecimal>("typef");
    public static final ColumnMetamodel<java.lang.Double> typeg = 
        new ColumnMetamodelImpl<java.lang.Double>("typeg");
    public static final ColumnMetamodel<java.lang.Float> typeh = 
        new ColumnMetamodelImpl<java.lang.Float>("typeh");
    public static final ColumnMetamodel<java.lang.Integer> typei = 
        new ColumnMetamodelImpl<java.lang.Integer>("typei");
    public static final ColumnMetamodel<java.lang.String> typej = 
        new ColumnMetamodelImpl<java.lang.String>("typej");
    public static final ColumnMetamodel<java.util.Date> typek = 
        new ColumnMetamodelImpl<java.util.Date>("typek");
    public static final ColumnMetamodel<java.util.UUID> typel = 
        new ColumnMetamodelImpl<java.util.UUID>("typel");
    public static final ColumnMetamodel<java.lang.String> typem = 
        new ColumnMetamodelImpl<java.lang.String>("typem");
    public static final ColumnMetamodel<java.math.BigInteger> typen = 
        new ColumnMetamodelImpl<java.math.BigInteger>("typen");
    public static final ColumnMetamodel<java.util.UUID> typeo = 
        new ColumnMetamodelImpl<java.util.UUID>("typeo");
    public static final ColumnMetamodel<java.lang.String> underscoreCase = 
        new ColumnMetamodelImpl<java.lang.String>("underscore_case");
}