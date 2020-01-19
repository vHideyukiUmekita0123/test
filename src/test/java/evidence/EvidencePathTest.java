package evidence;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class EvidencePathTest {

    private EvidencePath evidencePath = new EvidencePath();

    @Tag("AutoFW")
    @Test
    public void 同一インスタンスを評価したときtrueであること() {
        assertTrue(evidencePath.equals(evidencePath));
    }

    @Tag("AutoFW")
    @Test
    public void nullをequalsで評価したときfalseであること() {
        assertFalse(evidencePath.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Tag("AutoFW")
    @Test
    public void 異なるclassを評価したときfalseであること() {
        String differentClass = "hoge";
        assertFalse(evidencePath.equals(differentClass));
    }

    @Tag("AutoFW")
    @Test
    public void 異なるクラス名を評価したときfalseであること() {
        evidencePath.setTestClassName("hoge");
        EvidencePath path2 = new EvidencePath();
        path2.setTestClassName("fuga");
        assertFalse(evidencePath.equals(path2));
    }

    @Tag("AutoFW")
    @Test
    public void 異なるケース名を評価したときfalseであること() {
        evidencePath.setTestClassName("hoge");
        evidencePath.setTestCaseName("hogehoge");
        EvidencePath path2 = new EvidencePath();
        path2.setTestClassName("fuga");
        path2.setTestCaseName("fugafuga");
        assertFalse(evidencePath.equals(path2));
    }

    @Tag("AutoFW")
    @Test
    public void クラス名とケース名が一致しているときtrueであること() {
        evidencePath.setTestClassName("hoge");
        evidencePath.setTestCaseName("hogehoge");
        EvidencePath path2 = new EvidencePath();
        path2.setTestClassName("hoge");
        path2.setTestCaseName("hogehoge");
        assertTrue(evidencePath.equals(path2));
    }

}
