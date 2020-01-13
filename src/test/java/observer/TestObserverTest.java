package observer;

import static org.junit.Assert.assertFalse;
import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import observer.testObserver.AddTestInfoInBeforeEachTest;
import observer.testObserver.RemoveTestInfoInAfterEachTest;

public class TestObserverTest {

    private static final String EXECUTED_PACKAGE_NAME = "src.test.java.utilities.observer.testObserver";

    @Test
    public void TestObserverが取得できること() {
        assertFalse(Objects.isNull(TestObserver.getInstance()));
    }

    @Test
    public void beforeEachでのupdateによりexecTestに追加されていること() {
        executeTest(AddTestInfoInBeforeEachTest.class, ".*AddTestInfoInBeforeEachTest.*");
    }

    @Test
    public void afterEachでupdateによりexecTestが空になること() {
        executeTest(RemoveTestInfoInAfterEachTest.class, ".*RemoveTestInfoInAfterEach.*");
    }

    private void executeTest(Class<?> selectClass, String classNamePattern) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(EXECUTED_PACKAGE_NAME),
                        selectClass(selectClass)
                )
                .filters(
                        includeClassNamePatterns(classNamePattern)
                )
                .build();
        Launcher launcher = LauncherFactory.create();
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);
    }

}