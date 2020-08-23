package handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.json.JSONArray;
import org.postgresql.util.PGobject;

@MappedTypes(JSONArray.class)
public class JSONArrayTypeHandler extends BaseTypeHandler<JSONArray> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType) throws SQLException {
	    PGobject[] pga = new PGobject[parameter.length()];
	    for(int a = 0; a < pga.length; a++) {
	    	pga[a] = new PGobject();
	    	pga[a].setType("json");
	    	pga[a].setValue(parameter.get(a).toString());
	    }
	    ps.setObject(i, pga);
	}

    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        if(jsonSource == null) return null;
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        JSONArray ar = new JSONArray();
        for(int i = 0; i < tokens.length; i++) {
        	ar.put(tokens[i]);
        }
        return ar;
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        if(jsonSource == null) return null;
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        JSONArray ar = new JSONArray();
        for(int i = 0; i < tokens.length; i++) {
        	ar.put(tokens[i]);
        }
        return ar;
    }

    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        if(jsonSource == null) return null;
        jsonSource = jsonSource.substring(2, jsonSource.length() - 2);
        jsonSource = jsonSource.replaceAll("\\\\","");
        jsonSource = jsonSource.replace("\"}\",", "\"}\"=+=");
        String[] tokens = jsonSource.split("\"=\\+=\"");
        JSONArray ar = new JSONArray();
        for(int i = 0; i < tokens.length; i++) {
        	ar.put(tokens[i]);
        }
        return ar;
    }
}