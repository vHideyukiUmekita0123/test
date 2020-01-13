package evidence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import testClass.TemplateTestClass;

public class EvidencePathBuilderTest extends TemplateTestClass{

    private EvidencePath evidencePath;

    @Override
    @BeforeEach
    public void beforeEach(TestInfo testInfo) throws Exception {
        super.beforeEach(testInfo);
        evidencePath = EvidencePathBuilder.create();
    }

    @Tag("AutoFW")
    @Test
    public void テストクラス名が取得できること() {
        assertEquals("evidence.EvidencePathBuilderTest", evidencePath.getTestClassName());
    }

    @Tag("AutoFW")
    @Test
    public void テストケース名が取得できること() {
        assertEquals("テストケース名が取得できること", evidencePath.getTestCaseName());
    }

}
