package observer.testObserver;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import observer.TestObserver;
import testClass.TemplateTestClass;

public class AddTestInfoInBeforeEachTest extends TemplateTestClass{
    @Override
    @BeforeAll
    public void beforeAll(TestInfo testInfo) {
        TestObserver testObserver = TestObserver.getInstance();
        assertEquals(true, Objects.isNull(testObserver.getExecutingTestCase()));
        getLogger().info("Success to execute beforeAll method.");
    }

    @Test
    public void addTestInfo(TestInfo testInfo) {
        TestObserver testObserver = TestObserver.getInstance();
        assertFalse(Objects.isNull(testObserver.getExecutingTestCase()));
        getLogger().info("Success to execute test method.");
    }
}
