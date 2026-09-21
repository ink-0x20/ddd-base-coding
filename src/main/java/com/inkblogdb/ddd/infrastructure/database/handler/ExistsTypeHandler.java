package com.inkblogdb.ddd.infrastructure.database.handler;

import com.inkblogdb.ddd.domain.type.Existence;
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
@MappedTypes(Existence.class)
@MappedJdbcTypes(JdbcType.BOOLEAN)
public class ExistsTypeHandler extends BaseTypeHandler<Existence> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Existence parameter, JdbcType jdbcType) throws SQLException {
    ps.setBoolean(i, parameter.yes());
  }

  @Override
  public Existence getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return toExists(rs.getBoolean(columnName), rs.wasNull());
  }

  @Override
  public Existence getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return toExists(rs.getBoolean(columnIndex), rs.wasNull());
  }

  @Override
  public Existence getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return toExists(cs.getBoolean(columnIndex), cs.wasNull());
  }

  private Existence toExists(boolean value, boolean wasNull) {
    if (wasNull) {
      return Existence.NO;
    }
    return value ? Existence.YES : Existence.NO;
  }

}
