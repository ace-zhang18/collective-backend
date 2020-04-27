package handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

@MappedTypes(PGobject.class)
public class PGObjectTypeHandler extends BaseTypeHandler<PGobject> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PGobject parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
	}

    @Override
    public PGobject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        PGobject pg = new PGobject();
        pg.setType("json");
        pg.setValue(jsonSource);
        return pg;
    }

    @Override
    public PGobject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        PGobject pg = new PGobject();
        pg.setType("json");
        pg.setValue(jsonSource);
        return pg;
    }

    @Override
    public PGobject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        PGobject pg = new PGobject();
        pg.setType("json");
        pg.setValue(jsonSource);
        return pg;
    }
}