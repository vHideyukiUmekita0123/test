package database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestQueryTest {

    private static final Class<TestQuery> TEST_QUERY  = TestQuery.class;
    private static final String TEST_METHOD = "read";
    protected static final String[] TEST_ARGS = {"id", "item"};
    protected static final String TEST_FILE_PATH = "src/test/resources/database/Test.sql";

    @Tag("AutoFW")
    @Test
    public void SQLが取得できること() throws Exception {
        Method read = TEST_QUERY.getDeclaredMethod(TEST_METHOD, String.class, String[].class);
        read.setAccessible(true);

        assertEquals(
                "id = id and item = item ",
                (String) read.invoke(null, TEST_FILE_PATH, TEST_ARGS));
    }

}
