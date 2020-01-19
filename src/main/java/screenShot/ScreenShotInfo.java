package screenShot;

import java.util.Objects;

import evidence.EvidencePath;

final class ScreenShotInfo {

    private static final ScreenShotInfo INSTANCE = new ScreenShotInfo();
    private EvidencePath evidencePath;
    private int captureNumber = 1;

    private ScreenShotInfo() {
        // nothing to do.
    }

    protected static ScreenShotInfo getSingleton() {
        return INSTANCE;
    }

    protected String getCaptureNumber(EvidencePath currentPath) {
        update(currentPath);
        return String.format("%02d", captureNumber);
    }

    private void update(EvidencePath currentEvidencePath) {
        if (Objects.isNull(evidencePath) || !evidencePath.equals(currentEvidencePath)) {
            evidencePath = currentEvidencePath;
            captureNumber = 1;
            return;
        }
        captureNumber++;
    }

}
