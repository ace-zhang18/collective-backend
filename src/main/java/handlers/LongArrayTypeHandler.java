package handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

public class LongArrayTypeHandler extends BaseTypeHandler<long[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, long[] parameter, JdbcType jdbcType) throws SQLException {
		ps.setObject(i, parameter);
	}

    @Override
    public long[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
    	String data = rs.getString(columnName);
    	try {
    		String[] parts = data.split("\\{");
    		data = parts[1].substring(0, parts[1].length() - 1);
    		String[] tokens = data.split(",");
    		long[] ar = new long[tokens.length];
    		for(int i = 0; i < tokens.length; i++) {
    			ar[i] = Long.parseLong(tokens[i]);
    		}
    		return ar;
    	} catch (NullPointerException e) {
    		return null;
    	}
    }

    @Override
    public long[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    	String data = rs.getString(columnIndex);
    	try {
    		String[] parts = data.split("\\{");
    		data = parts[1].substring(0, parts[1].length() - 1);
    		String[] tokens = data.split(",");
    		long[] ar = new long[tokens.length];
    		for(int i = 0; i < tokens.length; i++) {
    			ar[i] = Long.parseLong(tokens[i]);
    		}
    		return ar;
    	} catch (NullPointerException e) {
    		return null;
    	}
    }

    @Override
    public long[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    	String data = cs.getString(columnIndex);
    	try {
    		System.out.println(data);
    		String[] parts = data.split("\\{");
    		data = parts[1].substring(0, parts[1].length() - 1);
    		String[] tokens = data.split(",");
    		long[] ar = new long[tokens.length];
    		for(int i = 0; i < tokens.length; i++) {
    			ar[i] = Long.parseLong(tokens[i]);
    		}
    		return ar;
    	} catch (NullPointerException e) {
    		return null;
    	}
    }

}