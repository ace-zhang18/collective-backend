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

@MappedTypes(JSONObject.class)
public class JSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
        PGobject pgo = new PGobject();
        pgo.setType("json");
        pgo.setValue(parameter.toString());
		ps.setObject(i, pgo);
	}

    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        if(jsonSource != null) {
        	return new JSONObject(jsonSource);
        }else {
        	return null;
        }
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        if(jsonSource != null) {
        	return new JSONObject(jsonSource);
        }else {
        	return null;
        }
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        if(jsonSource != null) {
        	return new JSONObject(jsonSource);
        }else {
        	return null;
        }
    }
}