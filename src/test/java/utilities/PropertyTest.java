package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public final class PropertyTest {

    @Test
    public void databaseプロパティから値が取得できること() {
        assertEquals(Property.getDatabaseProperty("test"), "databasePropertyKey");
    }

    @Test
    public void settingsプロパティから値が取得できること() {
        assertEquals(Property.getSettingsProperty("test"), "settingsPropertyKey");
    }

}
