package exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AutomatedTestExceptionTest {

    @Tag("AutoFW")
    @Test
    public void AutomatedTestExceptionがthrowされること() {
        assertThrows(AutomatedTestException.class, () -> execute(null));
    }

    private void execute(String string) throws AutomatedTestException {
        if (Objects.isNull(string)) {
            throw new AutomatedTestException();
        }
    }

}
