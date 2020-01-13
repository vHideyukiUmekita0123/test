package testClass;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public final class SampleTestClass extends TemplateTestClass {

    public SampleTestClass() {
    }

    // TODO: beforeAll, beforeEach, afterEach,afterAllはTestCaseクラスとは別の処理をする場合のみoverrideする.

    @Test
    public void test_01() {
        // TODO:
    }

}
