package exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class NoTestDataExceptionTest {

    @Tag("AutoFW")
    @Test
    public void NoTestDataExceptionがthrowされること() {
        assertThrows(NoTestDataException.class, () -> execute(null));
    }

    private void execute(String string) throws NoTestDataException {
        if (Objects.isNull(string)) {
            throw new NoTestDataException();
        }
    }

}
