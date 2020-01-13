package utilities.invokeAnotherTest;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import testClass.TemplateTestClass;

public final class BaseInvokeTestClass {

    private BaseInvokeTestClass() {
    }

    public static void invoke(TemplateTestClass testCaseClass, ExecutionTestCaseInterface testCase) {

        // 発生した例外一覧
        List<Throwable> cause = new ArrayList<Throwable>();

        try {
            testCaseClass.beforeAll(testCaseClass.getTestInfo());
        } catch (AssertionError e) {
            cause.add(e);
        } catch (Exception e) {
            cause.add(e);
        }

        // beforeAllでエラーが出た場合、before～afterは行わない
        if (cause.size() == 0) {

            try {
                testCaseClass.beforeEach(testCaseClass.getTestInfo());
            } catch (AssertionError e) {
                cause.add(e);
            } catch (Exception e) {
                cause.add(e);
            }

            // beforeでエラーが出た場合、testCaseは行わない
            if (cause.size() == 0) {

                try {
                    testCaseClass.updateScreenObject();
                } catch (AssertionError e) {
                    cause.add(e);
                } catch (Exception e) {
                    cause.add(e);
                }

                try {
                    testCase.test();
                } catch (AssertionError e) {
                    cause.add(e);
                } catch (Exception e) {
                    cause.add(e);
                }
            }

            try {
                testCaseClass.afterEach(testCaseClass.getTestInfo());
            } catch (AssertionError e) {
                cause.add(e);
            } catch (Exception e) {
                cause.add(e);
            }

        }

        try {
            testCaseClass.afterAll(testCaseClass.getTestInfo());
        } catch (AssertionError e) {
            cause.add(e);
        } catch (Exception e) {
            cause.add(e);
        }

        cause.stream().forEach(e -> fail(e));
    }
}
