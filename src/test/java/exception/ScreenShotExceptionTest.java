package exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ScreenShotExceptionTest {

    @Tag("AutoFW")
    @Test
    public void ScreenShotExceptionTestがthrowされること() {
        assertThrows(ScreenShotException.class, () -> execute(null));
    }

    private void execute(String string) throws ScreenShotException {
        if (Objects.isNull(string)) {
            throw new ScreenShotException();
        }
    }

}
