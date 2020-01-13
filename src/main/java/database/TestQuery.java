package database;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TestQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final char HALF_SPACE = ' ';

    protected static String read(String queryFileName, String... queryParam) throws IOException {
        StringBuilder  sqlString = new StringBuilder();
        Path queryFile = Paths.get(queryFileName);
        // 読み込んだ各行を一行にするときに行と行の間に半角スペースを追加する
        Files.lines(queryFile, StandardCharsets.UTF_8).forEach(line -> sqlString.append(line).append(HALF_SPACE));
        // SQLの引数部分を埋める
        String sql = MessageFormat.format(sqlString.toString(), (Object[]) queryParam);

        LOGGER.info("-----------実行クエリ-----------");
        LOGGER.info(sql);
        return sql;
    }

}
