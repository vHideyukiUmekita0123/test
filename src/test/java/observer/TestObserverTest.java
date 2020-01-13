package observer;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import testClass.TemplateTestClass;

public class TestObserverTest {

    @Test
    public void TestObserverが取得できること() {
        assertFalse(Objects.isNull(TestObserver.getInstance()));
    }

    @Nested
    public class AddTestInfoInBeforeEachTest extends TemplateTestClass{
        @Override
        @BeforeAll
        public void beforeAll(TestInfo testInfo) {
            TestObserver testObserver = TestObserver.getInstance();
            assertEquals(true, Objects.isNull(testObserver.getExecutingTestCase()));
            getLogger().info("Success to execute beforeAll method.");
        }

        @Test
        public void beforeEachでupdateによりexecTestが設定されること(TestInfo testInfo) {
            TestObserver testObserver = TestObserver.getInstance();
            assertFalse(Objects.isNull(testObserver.getExecutingTestCase()));
            getLogger().info("Success to execute test method.");
        }
    }

    @Nested
    public class RemoveTestInfoInAfterEachTest extends TemplateTestClass {
        @Test
        public void afterEachでupdateによりexecTestが空になること(TestInfo testInfo) {
            TestObserver testObserver = TestObserver.getInstance();
            assertFalse(Objects.isNull(testObserver.getExecutingTestCase()));
            getLogger().info("Success to execute test method.");
        }

        @Override
        @AfterAll
        public void afterAll(TestInfo testInfo) {
            TestObserver testObserver = TestObserver.getInstance();
            assertEquals(true, Objects.isNull(testObserver.getExecutingTestCase()));
            getLogger().info("Success to execute afterAll method.");
        }
    }

}