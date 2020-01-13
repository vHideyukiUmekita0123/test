package observer.testObserver;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import observer.TestObserver;
import testClass.TemplateTestClass;

public class RemoveTestInfoInAfterEachTest extends TemplateTestClass {
    @Test
    public void removeTestInfo(TestInfo testInfo) {
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
