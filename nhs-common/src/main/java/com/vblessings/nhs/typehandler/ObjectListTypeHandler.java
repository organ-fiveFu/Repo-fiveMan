package com.vblessings.nhs.typehandler;

import cn.hutool.json.JSONUtil;
import com.vblessings.nhs.util.HumpLineUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeReference;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ObjectListTypeHandler<T extends Object> extends TypeReference<T> implements TypeHandler<List<T>> {

    protected Configuration configuration;

    public void setConfiguration(Configuration c) {
        this.configuration = c;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            if (jdbcType == null) {
                throw new TypeException("JDBC requires that the JdbcType must be specified for all nullable parameters.");
            }
            try {
                ps.setNull(i, jdbcType.TYPE_CODE);
            } catch (SQLException e) {
                throw new TypeException("Error setting null for parameter #" + i + " with JdbcType " + jdbcType + " . " +
                        "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. " +
                        "Cause: " + e, e);
            }
        } else {
            try {
                setNonNullParameter(ps, i, parameter, jdbcType);
            } catch (Exception e) {
                throw new TypeException("Error setting non null for parameter #" + i + " with JdbcType " + jdbcType + " . " +
                        "Try setting a different JdbcType for this parameter or a different configuration property. " +
                        "Cause: " + e, e);
            }
        }
    }

    @Override
    public List<T> getResult(ResultSet rs, String columnName) throws SQLException {
        List<T> result;
        try {
            result = getNullableResult(rs, columnName);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
        }
        if (rs.wasNull()) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        List<T> result;
        try {
            result = getNullableResult(rs, columnIndex);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column #" + columnIndex + " from result set.  Cause: " + e, e);
        }
        if (rs.wasNull()) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public List<T> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        List<T> result;
        try {
            result = getNullableResult(cs, columnIndex);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column #" + columnIndex + " from callable statement.  Cause: " + e, e);
        }
        if (cs.wasNull()) {
            return null;
        } else {
            return result;
        }
    }

    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, HumpLineUtil.humpToLine(JSONUtil.toJsonStr(parameter)));
    }

    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String data = rs.getString(columnName);
        return StringUtils.isBlank(data) ? null : JSONUtil.toList(JSONUtil.parseArray(HumpLineUtil.lineToHump(data)), (Class<T>) getRawType());
    }

    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String data = rs.getString(columnIndex);
        return StringUtils.isBlank(data) ? null : JSONUtil.toList(JSONUtil.parseArray(HumpLineUtil.lineToHump(data)), (Class<T>) getRawType());
    }

    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String data = cs.getString(columnIndex);
        return StringUtils.isBlank(data) ? null : JSONUtil.toList(JSONUtil.parseArray(HumpLineUtil.lineToHump(data)), (Class<T>) getRawType());
    }
}
