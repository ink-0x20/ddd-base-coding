package com.inkblogdb.ddd.infrastructure.database.handler;

import com.inkblogdb.ddd.domain.type.Exists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@MappedTypes(Exists.class)
@MappedJdbcTypes(JdbcType.BOOLEAN)
public class ExistsTypeHandler extends BaseTypeHandler<Exists> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Exists parameter, JdbcType jdbcType) throws SQLException {
    ps.setBoolean(i, parameter.isExists());
  }

  @Override
  public Exists getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return toExists(rs.getBoolean(columnName), rs.wasNull());
  }

  @Override
  public Exists getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return toExists(rs.getBoolean(columnIndex), rs.wasNull());
  }

  @Override
  public Exists getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return toExists(cs.getBoolean(columnIndex), cs.wasNull());
  }

  private Exists toExists(boolean value, boolean wasNull) {
    if (wasNull) {
      return Exists.NOT_EXISTS;
    }
    return value ? Exists.EXISTS : Exists.NOT_EXISTS;
  }

}
