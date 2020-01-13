package database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// TODO: テスト書く
@RunWith(PowerMockRunner.class)
@PrepareForTest({DriverManager.class})
public class ManagerTest {

    @Mock
    private Connection conn;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void before() {
        PowerMockito.mockStatic(DriverManager.class);
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void hogetest() throws Exception {
        System.out.println(TestQuery.read(TestQueryTest.TEST_FILE_PATH, TestQueryTest.TEST_ARGS));
    }

    @Tag("AutoFW")
    @Test
    public void hoge2() throws SQLException {
        when(
                DriverManager.getConnection(ConnectInfoDto.getUrl(), ConnectInfoDto.getUserId(), ConnectInfoDto.getPassword())
                )
        .thenReturn(conn);

        System.out.println(DriverManager.getConnection(ConnectInfoDto.getUrl(), ConnectInfoDto.getUserId(), ConnectInfoDto.getPassword()));
    }

    @Tag("AutoFW")
    @Test
    @Ignore
    public void リソースが開放できていること() throws Exception {
        assertFalse(conn.isClosed());
        assertFalse(statement.isClosed());
        assertFalse(resultSet.isClosed());

        Method method = Manager.class.getDeclaredMethod(
                "releaseResources", Connection.class, Statement.class, ResultSet.class
                );
        method.setAccessible(true);
        method.invoke(null, conn, statement, resultSet);

        assertTrue(conn.isClosed());
        assertTrue(statement.isClosed());
        assertTrue(resultSet.isClosed());
    }
}
