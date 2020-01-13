package testClass;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import observer.Observer;

public abstract class AbstractTestClass implements TestCaseInterface {

    private static final Logger LOGGER =  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private TestInfo testInfo;
    private List<Observer> observers = new ArrayList<Observer>();

    public AbstractTestClass() {
    }

    protected abstract void beforeAll(TestInfo testInfo) throws Exception;

    protected abstract void beforeEach(TestInfo testInfo)  throws Exception;

    protected abstract void afterAll(TestInfo testInfo) throws Exception;

    protected abstract void afterEach(TestInfo testInfo) throws Exception;

    protected abstract void updateScreenObject();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.stream().forEach(observer -> observer.update(this));
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    public void setTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}