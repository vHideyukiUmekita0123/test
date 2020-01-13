package evidence;

import java.util.Objects;

import org.junit.jupiter.api.TestInfo;

import observer.TestObserver;

public final class EvidencePathBuilder {

    private EvidencePathBuilder() {
    }

    public static EvidencePath create() {
        TestInfo executing = TestObserver.getInstance().getExecutingTestCase();
        if (Objects.isNull(executing)) {
            return null;
        }

        EvidencePath evidencePath = new EvidencePath();
        evidencePath.setTestClassName(executing.getTestClass().get().getName());
        evidencePath.setTestCaseName(executing.getTestMethod().get().getName());

        return evidencePath;
    }

}
