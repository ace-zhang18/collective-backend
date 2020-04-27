package handlers;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

@MappedTypes(PGobject[].class)
public class PGObjectArrayTypeHandler extends BaseTypeHandler<PGobject[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PGobject[] parameter, JdbcType jdbcType) throws SQLException {
	    Connection conn = ps.getConnection();
	    Array array = conn.createArrayOf("json", parameter);
	    ps.setArray(i, array);
	}

    @Override
    public PGobject[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        PGobject[] ar = new PGobject[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
        	ar[i] = new PGobject();
        	ar[i].setType("json");
        	ar[i].setValue(tokens[i]);
        }
        return ar;
    }

    @Override
    public PGobject[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        PGobject[] ar = new PGobject[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
        	ar[i] = new PGobject();
        	ar[i].setType("json");
        	ar[i].setValue(tokens[i]);
        }
        return ar;
    }

    @Override
    public PGobject[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        PGobject[] ar = new PGobject[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
        	ar[i] = new PGobject();
        	ar[i].setType("json");
        	ar[i].setValue(tokens[i]);
        }
        return ar;
    }
}