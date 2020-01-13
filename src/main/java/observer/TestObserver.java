package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.TestInfo;

import testClass.AbstractTestClass;

public final class TestObserver implements Observer {
    private static TestObserver testObserver;
    private static List<TestInfo> execTests = new ArrayList<TestInfo>();

    private TestObserver() {
    }

    public static TestObserver getInstance() {
        if (Objects.isNull(testObserver)) {
            return new TestObserver();
        }
        return testObserver;
    }

    @Override
    public void update(AbstractTestClass testCase) {
        if (execTests.remove(testCase.getTestInfo())) {
            return;
        }
        execTests.add(testCase.getTestInfo());
    }

    public TestInfo getExecutingTestCase() {
        if (execTests.size() == 0) {
            return null;
        }
        return execTests.get(execTests.size() - 1);
    }

}
