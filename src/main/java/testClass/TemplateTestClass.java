package testClass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import testClass.AbstractTestClass;
import observer.TestObserver;

@TestInstance(Lifecycle.PER_CLASS)
public class TemplateTestClass extends AbstractTestClass {

    public TemplateTestClass() {
        super();
        addObserver(TestObserver.getInstance());
        // TODO:
    }

    static {
        // TODO:
    }

    @Override
    @BeforeAll
    public void beforeAll(TestInfo testInfo) throws Exception {
        // TODO: 全テストケースの共通の事前処理を書く
    }

    @Override
    @BeforeEach
    public void beforeEach(TestInfo testInfo) throws Exception {
        setTestInfo(testInfo);
        notifyObservers();
        // TODO: 汎用性のある事前共通処理を書く
    }

    @Override
    @AfterEach
    public void afterEach(TestInfo testInfo) throws Exception {
        notifyObservers();
        // TODO: 汎用性のある事後共通処理を書く
    }

    @Override
    @AfterAll
    public void afterAll(TestInfo testInfo) throws Exception {
        // TODO: 全テストケースの共通の事後処理を書く
    }

    @Override
    public void updateScreenObject() {
        // TODO
    }

}
