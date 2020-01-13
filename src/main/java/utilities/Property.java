package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PropertyUtilsのテストクラス
 *
 * @author ベリサーブ)yuuto suzuki
 *
 */
public final class Property {
    /** ログ出力用のロガー定義 */
    private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * コンストラクタ
     */
    private Property() {
    }

    /**
     * ファイル名、キーからプロパティを取得する
     *
     * @param fileName
     *            ファイル名
     * @param key
     *            プロパティのキー
     *
     * @return プロパティの値
     */
    public static String getProperty(String fileName, String key) {

        // SingletonPropertiesLoaderからファイル名を指定してPropertiesを取り出す
        Properties props = SingletonPropertiesLoader.getProperties(fileName);

        // 取り出したPropertiesに該当するキーがない場合、ログに出力
        if (!props.containsKey(key)) {
            logger.info("指定したプロパティーはありません。");
        }

        return props.getProperty(key);
    }

    /**
     * settings.propertiesファイルの値を取り出したい場合に使用
     *
     * @param key
     *            プロパティのキー
     *
     * @return プロパティの値
     */
    public static String getSettingsProperty(String key) {
        return getProperty("settings.properties", key);
    }

    /**
     * database.propertiesファイルの値を取り出したい場合に使用
     *
     * @param key
     *            プロパティのキー
     *
     * @return プロパティの値
     */
    public static String getDatabaseProperty(String key) {
        return getProperty("database.properties", key);
    }

    /**
     * プロパティファイルをSingletonオブジェクトにするクラス PropertyUtilsの内部クラスとして扱い、PropertyUtilsからしかアクセスされない
     *
     * @author ベリサーブ)yuuto suzuki
     *
     */
    private static class SingletonPropertiesLoader {
        /** Propertiesを保持するMap */
        private static Map<String, Properties> propertysMap = new HashMap<String, Properties>();

        /** propertiesファイルのパス */
        private static final String FOLDER_PATH = "src/main/resources/";

        /**
         * ファイル名からPropertiesを取得する
         *
         * @param fileName
         *            ファイル名
         *
         * @return Properties
         */
        public static Properties getProperties(String fileName) {

            // propertysMapからファイル名を指定してPropertiesを取得
            Properties prop = (Properties) propertysMap.get(fileName);

            // Propertiesが登録されている場合返却する
            if (prop != null) {
                return prop;
            }

            // Propertiesが取得されない場合は他のスレッドが読み込んでいる可能性があるので
            // スレッドと同期を取ってもう一度filePathのPropertiesがmapに登録されているか調べる
            synchronized (SingletonPropertiesLoader.class) {
                prop = propertysMap.get(fileName);
                if (prop != null) {
                    return prop;
                }
            }

            // 指定されたPropertiesが登録されていない場合はファイルを読み込みpropertysMapに登録する
            readPropertyFile(fileName);

            // propertysMapから取り出し返却する
            return propertysMap.get(fileName);
        }

        /**
         * ファイル名を指定してプロパティーファイルを読み込み、propertysMapに登録する
         *
         * @param fileName
         *            ファイル名
         */
        private static void readPropertyFile(String fileName) {

            Properties props = new Properties();

            try (FileInputStream dbcp = new FileInputStream(FOLDER_PATH + fileName);) {

                props.load(dbcp);

            } catch (FileNotFoundException e) {
                logger.error("ファイルを開くことができませんでした", e);
            } catch (UnsupportedEncodingException e) {
                logger.error("文字のエンコーディングに問題があります", e);
            } catch (IOException e) {
                logger.error("ファイルの入出力で問題が発生しました", e);
            }

            propertysMap.put(fileName, props);
        }
    }

}
