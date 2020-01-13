package database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ConnectInfoDtoTest {

    private static Class<ConnectInfoDto> databaseInfo = ConnectInfoDto.class;

    @Tag("AutoFW")
    @Test
    public void urlが取得できること() throws Exception {
        Method getUrl = databaseInfo.getDeclaredMethod("getUrl");
        assertEquals("url", (String) getUrl.invoke(null));
    }

    @Tag("AutoFW")
    @Test
    public void ユーザーIDが取得できること() throws Exception {
        Method getUserId = databaseInfo.getDeclaredMethod("getUserId");
        assertEquals("userId", (String) getUserId.invoke(null));
    }

    @Tag("AutoFW")
    @Test
    public void パスワードが取得できること() throws Exception {
        Method getPassword = databaseInfo.getDeclaredMethod("getPassword");
        assertEquals("password", (String) getPassword.invoke(null));
    }

}
