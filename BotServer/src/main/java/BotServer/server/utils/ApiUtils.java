package BotServer.server.utils;

import BotServer.server.config.AppConfig;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 14:52
 * <p>
 * **************************************************************
 */
public class ApiUtils {
    public static String getToken() {
        return AppConfig.appId + ":" + AppConfig.appSecret;
    }

    /**
     * 获取Token
     *
     * @param method 接口名称
     * @return
     */
    public static String getApi(String method) {
        return String.format(AppConfig.site + "/message/" + getToken() + "/" + method);
    }
}
