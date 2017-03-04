package scut.carson_ho.borchshop;

import android.os.Environment;

import java.io.File;

/**
 * Created by Carson_Ho on 17/2/28.
 * @Description: sd卡配置
 */
public class SdcardConfig {


    /**
     * sdcard
     */
    public static final String SDCARD_FOLDER = Environment
            .getExternalStorageDirectory().toString();

    /**
     * 根目录
     */
    public static final String ROOT_FOLDER = SDCARD_FOLDER + "/legendshop/";

    /**
     * 缓存目录
     */
    public static final String CACHE_FOLDER = ROOT_FOLDER + "cache/";

    /**
     * 网页缓存目录
     */
    public static final String WEB_CACHE_FOLDER = ROOT_FOLDER + "webCache/";

    /**
     * 数据库缓存目录
     */
    public static final String DB_CACHE_FOLDER = ROOT_FOLDER + "dbCache/";

    /**
     * 城市的数据库地址
     */
    public static final String ADDRESS_DB_CACHE_FILE = ROOT_FOLDER
            + "dbCache/address.db";

    /**
     * 相片目录
     */
    public static final String PHOTO_FOLDER = ROOT_FOLDER + "photo/";

    /**
     * 日志目录
     */
    public static final String LOG_FOLDER = ROOT_FOLDER + "log/";

    /**
     * 用户目录
     */
    public static final String USER_FOLDER = ROOT_FOLDER + "user/";

    private static SdcardConfig sSdcardConfig;

    public static synchronized SdcardConfig getInstance() {
        if (sSdcardConfig == null) {
            sSdcardConfig = new SdcardConfig();
        }
        return sSdcardConfig;
    }

    // *************************************************************************
    /**
     * 【】(sd卡初始化)
     */
    // *************************************************************************
    public void initSdcard() {
        if (!SDCardUtil.hasSDCard())
            return;

        File logFile = new File(LOG_FOLDER);
        if (!logFile.exists())
            logFile.mkdirs();

        File cacheFile = new File(CACHE_FOLDER);
        if (!cacheFile.exists())
            cacheFile.mkdirs();

        File webCacheFile = new File(WEB_CACHE_FOLDER);
        if (!webCacheFile.exists())
            webCacheFile.mkdirs();

        File photoFile = new File(PHOTO_FOLDER);
        if (!photoFile.exists())
            photoFile.mkdirs();

        File dbFile = new File(DB_CACHE_FOLDER);
        if (!dbFile.exists())
            dbFile.mkdirs();

        File userFile = new File(USER_FOLDER);
        if (!userFile.exists())
            userFile.mkdirs();
    }
}
