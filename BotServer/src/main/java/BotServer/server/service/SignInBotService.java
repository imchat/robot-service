package BotServer.server.service;

import BotServer.server.utils.DBUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 17:26
 * <p>
 * **************************************************************
 */
public class SignInBotService {
    private static final String dbName = "SignBot";

    public static boolean add(String date, String openId) {
        JSONObject data = new JSONObject();
        data.put("openId", openId);
        String key = dbName + "_" + date;
        return DBUtils.writeDB(key, openId, data) > 0;
    }

    public static JSONObject get(String date, String openId) {
        String key = dbName + "_" + date;
        JSONObject res = DBUtils.readDB(key, openId);
        return res;
    }
}
