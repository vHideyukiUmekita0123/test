package database;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.NoTestDataException;

public final class Manager {

    private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Manager() {
    }

    public List<Hashtable<String, String>> getQueryResults(String queryfile, String... queryparam)
            throws SQLException, NoTestDataException, IOException {

        String sql;
        try {
            sql = TestQuery.read(queryfile, queryparam);
        } catch (IOException e) {
            throw new IOException("テストで使用するクエリの読み込みに失敗しました。", e);
        }

        Connection conn =DriverManager.getConnection(
                ConnectInfoDto.getUrl(), ConnectInfoDto.getUserId(), ConnectInfoDto.getPassword()
                );
        Statement statement;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            releaseResources(conn, null, null);
            throw e;
        }

        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            releaseResources(conn, statement, null);
            throw e;
        }

        ResultSetMetaData fieldData;
        try {
            fieldData = resultSet.getMetaData();
        } catch (SQLException e) {
            releaseResources(conn, statement, resultSet);
            throw e;
        }

        List<Hashtable<String, String>> queryResults = new ArrayList<Hashtable<String, String>>();
        try {
            while(resultSet.next()) {
                queryResults.add(fetchRecord(resultSet, fieldData));
            }
        } finally {
            releaseResources(conn, statement, resultSet);
        }

        if (queryResults.size() == 0) {
            throw new NoTestDataException();
        }
        return queryResults;
    }

    public Hashtable<String, String> getQueryResult(String queryfile, String... queryparam)
            throws SQLException, NoTestDataException, IOException {

        return getQueryResults(queryfile, queryparam).stream().findFirst().get();
    }

    private static Hashtable<String, String> fetchRecord(ResultSet resultSet, ResultSetMetaData fieldData) throws SQLException {
        Hashtable<String, String> record = new Hashtable<String, String>();

        for (int i = 1; i <= fieldData.getColumnCount(); i++) {
            String columnName = fieldData.getColumnName(i);
            String columnValue = resultSet.getString(columnName);
            /*
             *FIXME: 環境によるが、基本はnullと空文字は異なる認識。
             *if (Objects.isNull(columnValue)) {
             *   columnValue = "";
             *}
             */
            record.put(columnName, columnValue);
        }

        return record;
    }

    private static void releaseResources(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        boolean err = false;

        if (!Objects.isNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                err = true;
            }
        }

        if (!Objects.isNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                err = true;
            }
        }

        if (!Objects.isNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                err = true;
            }
        }

        if (err) {
            throw new SQLException("データベース接続のリソース開放に失敗しました。");
        }
    }

}
